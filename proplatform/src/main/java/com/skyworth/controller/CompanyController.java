package com.skyworth.controller;


import com.skyworth.dto.CompanyInfoDto;
import com.skyworth.dto.PatentDto;
import com.skyworth.model.CompanyInfo;
import com.skyworth.service.companyService.CompanyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;



/**
 * Created by Leo on 2018/3/25.
 */

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Resource
    private CompanyService companyService;

    /**
     * 企业logo上传    不懂从哪里得到logo文件
     * @param companyId
     * @param companyName
     * @param photoName
     * @return
     */
    @RequestMapping("/companyInfo/logo/{companyId}/{companyName}/{photoName}")
    public String uploadCompanyLogo(@PathVariable("companyId") String companyId, @PathVariable("companyName") String companyName
            , @PathVariable("photoName") String photoName) {

        String imgUrl = null;
        return imgUrl;

    }

    /**
     * 企业信息显示
     * @param companyId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/companyInfo/show/{companyId}",method = RequestMethod.GET)
    public CompanyInfoDto showCompanyInfo(@PathVariable("companyId") int companyId){
        CompanyInfo companyInfo=companyService.getInfo(companyId);
        CompanyInfoDto companyInfoDto=new CompanyInfoDto(companyInfo.getCompanyLogo(),companyInfo.getCompanyRealname(),companyService.checkProjects(companyId).size()
        ,companyInfo.getCompanyMajor(),companyInfo.getCompanyDescription());
        return companyInfoDto;

    }

    /**
     * 企业信息编辑
     * @param companyInfoDto
     * @return
     */
    @RequestMapping(value="/companyInfo/alter/{companyId}",method = RequestMethod.POST)
    public CompanyInfoDto updateCompanyInfo(@RequestBody CompanyInfoDto companyInfoDto,@PathVariable("companyId")int companyId) {
        CompanyInfo companyInfo = new CompanyInfo(companyId, companyInfoDto.getImgUrl(), companyInfoDto.getCompanyRealname()
                , companyInfoDto.getCompanyMajor(), companyInfoDto.getCompanyDescription(), null);
        boolean isSucceed = companyService.updateInfo(companyInfo);
        if (isSucceed) {
            return companyInfoDto;
        }
        else {
            return null;
        }
    }

}
