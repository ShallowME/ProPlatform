package com.skyworth.controller;

import com.skyworth.dto.PatentDto;
import com.skyworth.dto.ResumeDto;
import com.skyworth.dto.SubscribeDto;
import com.skyworth.dto.UserInfoDto;
import com.skyworth.model.*;
import com.skyworth.service.userService.UserService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shallow on 2018/3/6.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     *上传头像，待讨论头像存放路径问题以及返回路径问题
     * @param userName
     * @param userImg
     * @return imgUrl
     */
    @ResponseBody
    @RequestMapping(value = "/upload/{userName}",method = RequestMethod.POST)
    public String uploadHead(@PathVariable("userName") String userName, @RequestParam("userImg") MultipartFile userImg, HttpServletRequest request) throws IOException {
        //头像存放路径
        String path=request.getServletContext().getRealPath("/userHead/");
        if(userImg.isEmpty()){
            System.out.println("文件为空");
        }
        String filename=userImg.getOriginalFilename();
        File filepath=new File(path,filename);
        if(!filepath.getParentFile().exists()){
            filepath.getParentFile().mkdirs();
        }
        userImg.transferTo(new File(path+File.separator+filename));
        //返回路径
        String imgUrl=path+File.separator+filename;
        return imgUrl;
    }

    /**
     *个人信息显示
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userInfo/show/{userName}",method = RequestMethod.GET)
    public UserInfoDto showUserInfo(@PathVariable("userName") String userName){
        User user=userService.getByUsername(userName);
        String phoneNum=user.getUserPhoneNum();
        UserInfo userInfo=userService.getInfo(user.getId());
        //构造UserInfoDto对象
        UserInfoDto userInfoDto=new UserInfoDto(userInfo.getUserId(),userInfo.getUserUrl(),userInfo.getUserRealname(),userInfo.getUserSex()
        ,userInfo.getUserProvince(),userInfo.getUserCity(),userInfo.getUserSchool(),userInfo.getUserMajor(),userInfo.getUserMailbox()
                ,user.getUserPhoneNum());
        return  userInfoDto;

    }

    /**
     * 个人信息修改
     * @param userName
     * @param userInfodto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userInfo/alter/{userName}",method = RequestMethod.POST)
    public UserInfoDto updateUserInfo(@PathVariable("userName") String userName,@RequestBody UserInfoDto userInfodto){
        System.out.println("调用userController 中的方法");
        User user=userService.getByUsername(userName);
        UserInfo userInfo=new UserInfo(null,userInfodto.getImgUrl(),userInfodto.getRealname(),userInfodto.getUserSex(),userInfodto.getUserProvince()
        ,userInfodto.getUserCity(),userInfodto.getUserSchool(),userInfodto.getUserMajor(),userInfodto.getUserMailbox(),null);
        userInfo.setUserId(user.getId());
        boolean isSucceed =userService.updateInfo(userInfo);
        if(isSucceed){

            return  userInfodto;
        }
        else {
            return null;
        }
    }

    /**
     * 手机号码修改
     * @param userName
     * @param map
     * @return
     */
    @RequestMapping(value = "/userInfo/alterPhone/{userName}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updatePhone(@PathVariable("userName") String userName,@RequestBody Map<String,Object>map){
        String newPhoneNum=(String) map.get("newPhoneNum");
        String idCode= (String) map.get("idCode");
        boolean isSucceed=userService.updatePhoneNum(userName,newPhoneNum);
        if(isSucceed){
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *上传简历头像   待讨论头像存放路径问题
     * @param userName
     * @param userImg
     * @param imgName
     * @param request
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/resume/upload/{userName}",method = RequestMethod.POST)
            @ResponseBody
            public ResponseEntity uploadResumeHead(@PathVariable("userName") String userName,MultipartFile userImg,String imgName,HttpServletRequest request) throws IOException {
        String path = request.getServletContext().getRealPath("/resumeHead/");
        File filepath = new File(path, imgName);
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        userImg.transferTo(new File(path + File.separator + imgName));
        return new ResponseEntity(HttpStatus.OK);

    }

    /**
     * 简历信息显示
     * @param userName
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/resume/show/{userName}",method = RequestMethod.GET)
    public ResumeDto showResumeInfo(@PathVariable("userName") String userName){
        User user=userService.getByUsername(userName);
        Resume resume=userService.getResume(user.getId());
        ResumeDto resumeDto=new ResumeDto(resume.getResumeImg(),resume.getResumeRealname(),resume.getResumeSex(),resume.getResumeBirth()
        ,resume.getResumeSchool(),resume.getResumeEducation(),resume.getResumeMajor(),resume.getResumeMailbox(),resume.getResumePhonenum()
        ,resume.getResumeSchExperience(),resume.getResumeWorkExperience());
        return resumeDto;

    }

    /**
     * 修改简历信息
     * @param userName
     * @param resumedto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/resume/alter/{userName}",method = RequestMethod.POST)
    public ResumeDto updateResume(@PathVariable("userName") String userName,@RequestBody ResumeDto resumedto){
        User user=userService.getByUsername(userName);
        Resume resume=new Resume(null,null,resumedto.getImgUrl(),resumedto.getResumeRealname(),resumedto.getResumeSex(),resumedto.getResumeBirth()
                ,resumedto.getResumeSchool(),resumedto.getResumeEducation(),resumedto.getResumeMajor(),resumedto.getResumeMailbox(),resumedto.getResumePhoneNum()
                ,resumedto.getResumeSchExperience(),resumedto.getResumeWorkExperience(),null);
        resume.setUserId(user.getId());
        boolean isSucceed =userService.updateResume(resume);
        if(isSucceed){

            return resumedto;
        }
        else {
            return null;
        }
    }

    /**
     * 上传专利图片
     * @param userName
     * @param certificateImg
     * @param imgName
     * @param request
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/patent/certification/upload/{userName}",method = RequestMethod.POST)
    public String uploadCertificationHead(@PathVariable("userName") String userName,MultipartFile certificateImg,String imgName
    ,HttpServletRequest request) throws IOException {
        String path=request.getServletContext().getRealPath("/patentPicture/");
        File filepath=new File(path,imgName);
        if(!filepath.getParentFile().exists()){
            filepath.getParentFile().mkdirs();
        }
        certificateImg.transferTo(new File(path+File.separator+imgName));
        String imgUrl=path+File.separator+imgName;
        return imgUrl;
    }
    /**
     * 专利信息修改
     * @param stateCode
     * @param patentdto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/patent/alter/{userName}/{stateCode}",method = RequestMethod.POST)
    public ResponseEntity updatePatent(@PathVariable("userName")String userName,@PathVariable("stateCode") String stateCode,@RequestBody PatentDto patentdto){
        int id=userService.getPatentByUserName(userName).getId();
        Patent patent=new Patent(id,null,patentdto.getImgUrl(),patentdto.getPatentName(),patentdto.getPatentOwner(),patentdto.getPatentNum()
        ,patentdto.getPatentApplyTime(),patentdto.getPatentAuthTime(),patentdto.getPatentCertiCode(),patentdto.getPatentContact(),null);
        boolean isSucceed =userService.updatePatent(patent);
        if(isSucceed){

            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 专利信息显示
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/patent/show/{userId}",method = RequestMethod.GET)
    public PatentDto showPatent(@PathVariable("userId") Integer userId){

       Patent patent=userService.getPatentByUserId(userId);

       PatentDto patentDto=new PatentDto(patent.getPatentImg(),patent.getPatentName(),patent.getPatentOwner(),patent.getPatentNum()
               ,patent.getPatentApplyDate(),patent.getPatentAuthDate(),patent.getPatentCertiCode(),patent.getPatentContact());

        return patentDto;

    }

    /**
     * 新增订阅信息
     * @param userName
     * @param subscribedto
     * @return
     */
    @RequestMapping(value = "/subscribe/alter/{userName}",method = RequestMethod.POST)
    public ResponseEntity saveSubscribe(@PathVariable("userName") String userName, @RequestBody SubscribeDto subscribedto){

        User user=userService.getByUsername(userName);
        Subscribe subscribe=new Subscribe(null,user.getId(),subscribedto.getSubscribeSpot(),subscribedto.getSubscribeType()
                   ,subscribedto.getSubscribeMaxPay(),subscribedto.getSubscribeMinPay(),null);

           if(userService.setSubscribe(subscribe)) {
            return new ResponseEntity(HttpStatus.OK);
        }
             return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    /**
     * 删除订阅
     * @param subscribeId
     * @return
     */
    @RequestMapping(value = "/subscribe/delete/{userName}/{subscribeId}",method = RequestMethod.DELETE)
    public void deleteSubscribe(@PathVariable("subscribeId") int subscribeId,Map map){
         boolean isSuccessful =userService.removeSubscribe(subscribeId);
         map.put("isSuccessful",isSuccessful);
    }

    /**
     * 订阅信息显示
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/subscribe/show/{userName}",method = RequestMethod.GET)
    public SubscribeDto showSubscribe(@PathVariable("userName") String userName){
        User user=userService.getByUsername(userName);
        List<Subscribe>subscribes=userService.getSubscribeByUserId(user.getId());
        Subscribe subscribe=subscribes.get(0);
        SubscribeDto subscribeDto=new SubscribeDto(subscribe.getSubSpot(),subscribe.getSubType(),subscribe.getSubMaxPay(),subscribe.getSubMinPay());
        return subscribeDto;

    }

}
