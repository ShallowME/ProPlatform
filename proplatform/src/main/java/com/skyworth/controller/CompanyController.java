package com.skyworth.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skyworth.dto.CompanyDto;
import com.skyworth.dto.PatentDto;
import com.skyworth.dto.ProjectDto;
import com.skyworth.dto.ResumeDto;
import com.skyworth.model.Company;
import com.skyworth.model.Project;
import com.skyworth.service.companyService.CompanyService;
import com.skyworth.utils.ConstantHolder;
import com.skyworth.utils.shiro.session.CustomSessionDAO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Shallow on 2018/5/5.
 */
@RestController
@RequestMapping(value = "/company")
public class CompanyController {
    private Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Resource
    private CompanyService companyService;

    @Resource
    private CustomSessionDAO customSessionDAO;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, Object> userRegister(HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<>();
        String companyName = request.getParameter("companyName");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        String phoneNum = request.getParameter("phoneNum");
        String idCode = request.getParameter("idCode");

        if (companyService.checkCompanyExist(companyName)){
            resultMap.put("isSuccessful",false);
            resultMap.put("errorMessage","该企业用户已存在，请直接登录。");
        }
        else if (codeTimeOut()){
            resultMap.put("isSuccessful",false);
            resultMap.put("errorMessage","验证码已超时，请重新获取。");
        }
        else {
            Session session = SecurityUtils.getSubject().getSession();
            String code = (String) session.getAttribute(ConstantHolder.SMS_VALIDATE_CODE);
            if (idCode.equals(code) && password.equals(rePassword)){
                resultMap.put("isSuccessful",true);
                resultMap.put("message","注册成功。");
                CompanyDto companyDto = new CompanyDto(companyName, phoneNum, password);
                companyService.register(companyDto);
            }
        }
        return resultMap;
    }

    @RequestMapping(value = "/login/{identity}/{password}", method = RequestMethod.GET)
    public Map<String, Object> companyLogin(@PathVariable("identity") String identity, @PathVariable("password") String password, HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        Company companyLogin = Optional.ofNullable(companyService.getByCompanyName(identity)).orElse(companyService.getByPhoneNum(identity));

        if (companyLogin == null){
            resultMap.put("isSuccessful", false);
            resultMap.put("errorMessage","该企业用户不存在，请先注册。");
            resultMap.put("companyId", 0);
            resultMap.put("companyName", null);
        }
        else {
            String host = request.getRemoteHost();
            UsernamePasswordToken loginToken = new UsernamePasswordToken(companyLogin.getCompanyName(),companyLogin.getCompanyPassword(),host);
            subject.login(loginToken);
            if (subject.isAuthenticated()){
                resultMap.put("isSuccessful",true);
                resultMap.put("errorMessage",null);
                resultMap.put("companyId",companyLogin.getId());
                resultMap.put("companyName",companyLogin.getCompanyName());
            }
        }
        return resultMap;
    }

    @RequestMapping(value = "/idCodeConfirm/{idCode}")
    public Map<String, Boolean> companyIdCodeConfirm(@PathVariable("idCode") String idCode){
        Session companySession = SecurityUtils.getSubject().getSession();
        Session cacheSession = customSessionDAO.readSession(companySession.getId());
        Map<String,Boolean> resultMap = new HashMap<>();
        String code = (String) cacheSession.getAttribute(ConstantHolder.SMS_VALIDATE_CODE);

        resultMap.put("isSuccessful", code.equalsIgnoreCase(idCode));
        return resultMap;
    }

    @RequestMapping(value = "/forget",method = RequestMethod.POST)
    public ResponseEntity<?> companyForgetPasswordAlter( HttpServletRequest request){
        String phoneNum = request.getParameter("phoneNum");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");

        assert !password.equals(rePassword);

        companyService.updatePasswordByPhoneNum(phoneNum,password);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/alter/{companyId}",method = RequestMethod.POST)
    public Map<String, Object> userCommonPasswordAlter(@PathVariable("companyId") int companyId, HttpServletRequest request){
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String rePassword = request.getParameter("rePassword");

        Map<String, Object> resultMap = new HashMap<>();

        Company currentCompany = companyService.findByCompanyId(companyId);
        if (!currentCompany.getCompanyPassword().equals(oldPassword)){
            resultMap.put("isSuccessful", false);
            resultMap.put("message","密码错误");
        }
        else if (!newPassword.equals(rePassword)){
            resultMap.put("isSuccessful", false);
            resultMap.put("message","两次输入的密码不一致");
        }
        else {
           companyService.updatePasswordById(companyId, newPassword);
            resultMap.put("isSuccessful", true);
            resultMap.put("message","修改成功");
        }
        return resultMap;
    }

    @RequestMapping(value = "resume/search/{resumeProfession}/{resumeProfessionType}/{resumeProvince}", method = RequestMethod.POST)
    public List<ResumeDto> searchProjectsByConditions(@PathVariable("resumeProfession") String resumeProfession, @PathVariable("resumeProfessionType") String resumeProfessionType, @PathVariable("resumeProvince") String resumeProvince){
        return companyService.getResumesByConditions(resumeProfession, resumeProfessionType, resumeProvince);
    }

    @RequestMapping(value = "company/save/{proCompanyId}", method = RequestMethod.POST)
    public ResponseEntity<?> saveProject(@PathVariable("proCompanyId") int proCompanyId, HttpServletRequest request){
        String proName = request.getParameter("proName");
        String proDescription = request.getParameter("proDescription");
        String proType = request.getParameter("proType");
        int proCycle = Integer.parseInt(request.getParameter("proCycle"));
        double proMoney = Double.parseDouble(request.getParameter("proMoney"));
        String proRequest = request.getParameter("proRequest");

        Company company = companyService.findByCompanyId(proCompanyId);

        ProjectDto projectDto = new ProjectDto(proCompanyId, company.getCompanyName(), proName, proMoney, proType, proCycle, proDescription, proRequest, 0);

        if (companyService.getProjectByProNameAndCompanyId(proCompanyId, proName) == null)
            companyService.saveProject(projectDto);
        else
            companyService.updateProject(projectDto);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "company/release/{proCompanyId}", method = RequestMethod.POST)
    public ResponseEntity<?> releaseProject(@PathVariable("proCompanyId") int proCompanyId, HttpServletRequest request){
        String proName = request.getParameter("proName");
        String proDescription = request.getParameter("proDescription");
        String proType = request.getParameter("proType");
        int proCycle = Integer.parseInt(request.getParameter("proCycle"));
        double proMoney = Double.parseDouble(request.getParameter("proMoney"));
        String proRequest = request.getParameter("proRequest");

        Company company = companyService.findByCompanyId(proCompanyId);

        ProjectDto projectDto = new ProjectDto(proCompanyId, company.getCompanyName(), proName, proMoney, proType, proCycle, proDescription, proRequest, 1);
        if (companyService.getProjectByProNameAndCompanyId(proCompanyId, proName) == null)
            companyService.saveProject(projectDto);
        else
            companyService.updateProject(projectDto);


        return new ResponseEntity(HttpStatus.OK);
    }

    private boolean codeTimeOut(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Session session = SecurityUtils.getSubject().getSession();
        Session cacheSession = customSessionDAO.readSession(session.getId());

        String createTime = (String) cacheSession.getAttribute(ConstantHolder.SMS_VALIDATE_TIME);


        try {
            Date createDate = sdf.parse(createTime);
            Date validateDate = new Date(System.currentTimeMillis());
            long interval = (validateDate.getTime() - createDate.getTime())/1000;
            return interval > 90;
        } catch (ParseException e) {
            logger.error("Created time parsing error.", e);
        }
        return false;
    }
}
