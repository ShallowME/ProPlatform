package com.skyworth.controller;

import com.skyworth.dto.InviteDto;
import com.skyworth.dto.ResumeDto;
import com.skyworth.dto.StageDto;
import com.skyworth.model.Stage;
import com.skyworth.model.Target;
import com.skyworth.service.companyService.CompanyService;
import com.skyworth.utils.TimeFormatUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skyworth.dto.ProjectDto;
import com.skyworth.service.userService.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shallow on 2018/5/11.
 */

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @Resource
    private UserService userService;

    @Resource
    private CompanyService companyService;


    @RequestMapping(value = "search/{proName}/{typeCode}/{minProMoney}/{maxProMoney}/{maxProCycle}", method = RequestMethod.GET)
    public List<ProjectDto> searchProjectsByConditions(@PathVariable("proName") String proName, @PathVariable("typeCode") int typeCode, @PathVariable("minProMoney") double minProMoney, @PathVariable("maxProMoney") double maxProMoney, @PathVariable("maxProCycle") int maxProCycle){
        return userService.getProjectsByConditions(proName, typeCode, minProMoney, maxProMoney, maxProCycle);
    }

    @RequestMapping(value = "/index/like/{userId}", method = RequestMethod.GET)
    public List<ProjectDto> guessYourInterest(@PathVariable("userId") int userId){
        return userService.getBySubscribe(userId);
    }

    @RequestMapping(value = "/index/new", method = RequestMethod.GET)
    public List<ProjectDto> latestProjects(){
        return userService.orderForProjects(31);
    }

    @RequestMapping(value = "/show/{proId}", method = RequestMethod.GET)
    public ProjectDto projectDetails(@PathVariable("proId") int proId){
        return userService.getProjectById(proId);
    }

    @RequestMapping(value = "user/apply/{userId}/{proId}", method = RequestMethod.GET)
    public Map<String, Object> applyForProject(@PathVariable("userId") int userId ,@PathVariable("proId") int proId){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isSuccessful", userService.applyForProject(userId, proId));
        return resultMap;
    }

    @RequestMapping(value = "company/apply/show/{projectId}", method = RequestMethod.GET)
    public List<ResumeDto> checkApplications(@PathVariable("projectId") int projectId){
        return companyService.getApplicationsByProjectId(projectId);
    }

    @RequestMapping(value = "company/invite/{companyId}/{proId}/{resumeId}", method = RequestMethod.GET)
    public Map<String, Object> setInvitation(@PathVariable("companyId") int companyId, @PathVariable("proId") int proId, @PathVariable("resumeId") int resumeId){
        InviteDto inviteDto = new InviteDto(companyId, proId, resumeId);
        Map<String, Object> resuleMap = new HashMap<>();
        resuleMap.put("isSuccessful", companyService.saveInvite(inviteDto));
        return resuleMap;
    }

    @RequestMapping(value = "stage/{proId}", method = RequestMethod.GET)
    public List<StageDto> showStages(@PathVariable("proId") int proId){
        return companyService.getStagesByProId(proId);
    }

    @RequestMapping(value = "company/stage/add/{proId}/{stageNum}", method = RequestMethod.POST)
    public ResponseEntity<?> addStage(@PathVariable("proId") int proId, @PathVariable("stageNum") int stageNum, @RequestBody List<Target> targets, HttpServletRequest request){
        Date stageStartTime = TimeFormatUtil.stringToDate(request.getParameter("stageStartTime"));
        Date stageEndTime = TimeFormatUtil.stringToDate(request.getParameter("stageEndTime"));
        String stageSettlement = request.getParameter("stageSettlement");

        StageDto stageDto = new StageDto();
        stageDto.setProId(proId);
        stageDto.setStageNum(stageNum);
        stageDto.setStageStartTime(stageStartTime);
        stageDto.setStageEndTime(stageEndTime);
        stageDto.setStageSettleRequest(stageSettlement);

        companyService.saveStage(stageDto);

        targets.forEach(target -> companyService.saveTarget(target));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "company/stage/update/{proId}/{stageId}", method = RequestMethod.POST)
    public StageDto updateStage(@PathVariable("proId") int proId, @PathVariable("stageId") int stageId, @RequestBody List<Target> targets, HttpServletRequest request){
        Integer stageNum = Integer.parseInt(request.getParameter("stageNum"));
        Date stageStartTime = TimeFormatUtil.stringToDate(request.getParameter("stageStartTime"));
        Date stageEndTime = TimeFormatUtil.stringToDate(request.getParameter("stageEndTime"));
        String stageSettlement = request.getParameter("stageSettlement");
        Float stageSpeed = Float.parseFloat(request.getParameter("stageSpeed"));

        StageDto stageDto = new StageDto();
        stageDto.setStageId(stageId);
        stageDto.setProId(proId);
        stageDto.setStageNum(stageNum);
        stageDto.setStageStartTime(stageStartTime);
        stageDto.setStageEndTime(stageEndTime);
        stageDto.setStageSettleRequest(stageSettlement);
        stageDto.setStageSpeed(stageSpeed);
        companyService.updateStage(stageDto);

        targets.forEach(target -> companyService.updateTarget(target));

        Stage newStage = companyService.getStage(stageId);
        stageDto = new StageDto(newStage);
        stageDto.setTargets(companyService.getTargetByStageId(stageId));
        float completedTargets = companyService.completedTargetsOneStage(stageId);
        float allTargets = companyService.allTargetsOneStage(stageId);
        stageDto.setStageSpeed(completedTargets / allTargets);

        return stageDto;
    }

    @RequestMapping(value = "company/stage/target/delete/{targetId}", method = RequestMethod.GET)
    public Map<String, Object> deleteTarget(@PathVariable("targetId") int targetId){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("isSuccessful", companyService.removeTarget(targetId));
        return resultMap;
    }

    @RequestMapping(value = "company/stage/target/change{targetId}", method = RequestMethod.GET)
    public Map<String,Object> changeTargetState(@PathVariable("targetId") int targetId){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isSuccessful", companyService.targetCompleted(targetId));
        return resultMap;
    }

    @RequestMapping(value = "user/checkout/{userId}/{selectCode}", method = RequestMethod.GET)
    public List<ProjectDto> checkUserProjects(@PathVariable("userId") int userId, @PathVariable("selectCode") int selectCode)
    {
        return userService.selectProjects(userId, selectCode);
    }

    @RequestMapping(value = "company/checkout/{companyId}", method = RequestMethod.GET)
    public List<ProjectDto> checkCompanyProjects(@PathVariable("companyId")int companyId){
        return companyService.checkProjects(companyId);
    }

}
