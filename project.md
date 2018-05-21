---    
title: 2018-1-20    项目发布平台
tags: version 2.0
grammar_cjkRuby: true
---
=========================================

## 用户信息管理

### 1.1 注册

#### 获取手机验证码

* router
  * ==GET==  /identity/{phoneNum}

* request
```json
	"phoneNum":String
```

* response
```json
[{
    "idCode": String
},
...]
```

#### 注册状态判断

* router
  * ==POST==  /user/register

* request
```json
[{
	"userName":String,
    "phoneNum": String,
	"password":String,
	"rePassword":String,
	"idCode":String
},
...]
```

* response
```json
[{
    "isSucessful": Boolean，
	"errorMessage":String
},
...]
```

### 1.2 登录


* router
  * ==GET==/user/login/{identity}/{password}

* request
``` json
	[{
    "identity": String,
	"password":String
},
...] 
```

* response
``` json
[{
   "isSuccessful":Boolean,
   "errorMessage":String,
   "userId":Number,
   "userName":String
},
...] 
```

### 1.3 密码管理

#### 验证码确认

* router
	* ==GET==/user/idCodeConfirm/{idCode}

* request
``` json
[{
	"idCode":String
},
...] 
```

* response
``` json
[{
   "isSuccessful":Boolean
},
...] 
```

#### 忘记密码修改

* router
	* ==POST==/user/forget

* request
``` json
[{
	"phoneNum":String,
	"password":String,
	"rePassword":String
},
...] 
```

* response
``` json
[{
   ** Status(http状态码)
},
...] 
```

#### 修改密码

* router
	* ==POST==/user/alter/{userId}

* request
``` json
[{
	"userId":Number,
	"oldPassword":String,
	"newPassword":String,
	"rePassword":String
},
...] 
```

* response
``` json
[{
   "isSuccessful":Boolean,
   "message":String
},
...] 
```

### 1.4 个人资料管理

#### 上传头像

* router
	* ==POST== /user/upload/{userName}

* request
``` json
[{
   ** form表单
   "userImg":file
   "userName":String
},
...] 
```

* response 
``` json
 [{
   "imgUrl":String
},
...] 
```

#### 个人信息显示

* router
	* ==GET==  /user/userInfo/show/{userName}
	
* request
``` json
 [{
   "userName":String
},
...] 
```

* response
``` json
[{
	"userId":Number,
	"imgUrl":String,
    "realName":String,
   	"userSex":String,
   	"userProvince":String,
    "userCity":String,
    "userSchool":String,
    "userMajor":String,
    "userMailbox":String,
    "phoneNum":String
},
...] 
```

#### 个人信息修改

* router
	* ==POST==  /user/userInfo/alter/{userName}
	
* request
``` json
 [{
	"userName":String,
 	"realName":String,
    "userSex":String,
    "userProvince":String,
	"userCity":String,
	"userSchool":String,
	"userMajor":String,
	"userMailbox":String
},
...] 
```

* response
``` json
[{
	"userId":Number,
	"imgUrl":String,
   "realName":String,
   "userSex":String,
   "userProvince":String,
   "userCity":String,
   "userSchool":String,
   "userMajor":String,
   "userMailbox":String
},
...] 
```

#### 手机号码修改

* router
	* ==POST== user/userInfo/alterPhone/{userName}

* request

``` json
[{
	"userName":String,
	"newPhoneNum":String,
	"idCode":String
},
...] 
```

* reqponse

``` json
[{
	** Status(http状态码)
},
...] 
```

### 1.5 简历管理

#### 上传简历头像

* router
	* ==POST== /user/resume/upload/{userName}

* request
``` json
[{
	** form表单
	"userImg":file,
	"userName":String,
	"imgName":String
},	
...]
```

* response
``` json
[{
	** Status(http状态码)
},	
...]
```

#### 简历信息显示

* router
	* ==GET== /user/resume/show/{userName}

* request
``` json
[{
	"userName":String
},	
...]
```

* response
``` json
[{
	"imgUrl":String,
	"resumeRealName":String,
	"resumeSex":String,
	"rusumeBirth":String,
	"resumeEducation":String,
	"resumeSchool":String,
	"resumeMajor":String,
	"resumeMailbox":String,
	"resumePhoneNum":String,
	"resumeSchExperience":String,
	"resumeWorkExperience":String
},	
...]
```

#### 简历信息修改

* router
	* ==POST== /user/resume/alter/{userName}

* request
``` json
[{
	"imgUrl":String,
	"resumeRealName":String,
	"resumeSex":String,
	"rusumeBirth":String,
	"resumeEducation":String,
	"resumeSchool":String,
	"resumeMajor":String,
	"resumeMailbox":String,
	"resumePhoneNum":String,
	"resumeSchExperience":String,
	"resumeWorkExperience":String
},	
...]
```

* response
``` json
[{
	"resumeId":Number,
	"imgUrl":String,
	"resumeRealName":String,
	"resumeSex":String,
	"rusumeBirth":String,
	"resumeEducation":String,
	"resumeSchool":String,
	"resumeMajor":String,
	"resumeMailbox":String,
	"resumePhoneNum":String,
	"resumeSchExperience":String,
	"resumeWorkExperience":String
},	
...]
```


### 1.6 专利信息管理

#### 上传专利证书图片

* router
	* ==POST== /patent/certification/upload/{userName}

* request 
``` json
[{
	** form表单
	"certificateImg":File,
	"imgName":String,
	"userName":String
},
...] 
```

* response
``` json
[{
	"imgUrl":String
},
...] 
```

#### 专利信息修改

* router 
	* ==POST== /user/patent/alter/{userName}/{stateCode}

* request
``` json
[{
	"userName":String,
	"patentName":String,
	"patentOwner":String,
	"patentNum":String,
	"patentApplyDate":String,
	"patentAuthDate":String,
	"patentCertiCode":String,
	"patentContact":String,
	"patentState":Number
},
...]
```

* response
``` json
[{
	** Status(http状态码)
},
...]
```

#### 专利信息显示

* router 
	* ==GET== /user/patent/alter/{userId}

* request
``` json
[{
	"userId":Number
},
...]
```

* response
``` json
[{
	"imgUrl":String,
	"patentName":String,
	"patentOwner":String,
	"patentNum":String,
	"patentApplyTime":String,
	"patentAuthTime":String,
	"patentCertiCode":String,
	"patentContact":String,
	"patentState":Number
},
...]
```

### 1.7 消息管理

### 消息分类显示

* router
	* ==GET== /user/message/show/{userId}/{messageStateCode}/{curPage}

* request
``` json
[{
	"userId":Number,
	"messageStateCode":Number,
	"curPage":Number
},	
...]
```

* response
``` json
[{
	"messageId":Number,
	"messageContent":String,
	"messagePubTime":UNIX
},	
...]
```

#### 消息状态改变

* router
	* ==POST== /user/message/alter/{userId}/{messageId}

* request
``` json
[{
	"userId":Number,
	"messageId":Number,
	"changeCode":Number
},	
...]
```

* response
``` json
[{
	** Status(OK)
},	
...]
```

### 1.8 订阅信息管理

#### 新增订阅信息

* router
	* ==POST== /user/subscribe/alter/{userName}

* request
``` json
[{
	"userName":String,
	"subscribeSpot":String,
	"subscribeType":String,
	"subscribeMinPay":Number,
	"subscribeMaxPay":Number
},	
...]
```

* response
``` json
[{
	** Status(OK)
},	
...]
```

#### 删除订阅

* router
	* ==GET== /user/subscribe/delete/{userName}/{subscribeId}

* request
``` json
[{
	"userName":String,
	"subscribeId":Number
},	
...]
```

* response
``` json
[{
	"isSuccessful":Boolean
},	
...]
```

#### 订阅信息显示

* router
	* ==GET== /user/subscribe/show/{userName}

* request
``` json
[{
	"userName":String
},	
...]
```

* response
``` json
[{
	"subscribeSpot":String,
	"subscribeType":String,
	"subscribeMinPay":Number,
	"subscribeMaxPay":Number
},	
...]
```


## 项目信息

### 2.1 项目查找

#### 项目条件搜索

* router
		* ==GET== /project/search/{proName}/{typeCode}/{minProMoney}/{maxProMoney}/{maxProCycle}

* request
``` json
[{
	"proName":String,
	"typeCode":Number,
	"minProMoney":Number,
	"maxProMoney":Number,
	"maxProCycle":Number
},
...]
```


* response
``` json
[{
	"proId":Number,
	"companyId"Number,
	"companyName":String,
	"proName":String,
	"proMoney":Number,
	"proType":String,
	"proCycle":Number,
	"proPubTime":UNIX,
	"proEnrollment":Number,
	"proDescription":Number,
	"proRequest":Number,
	"proState":Number
},
...]
```


#### 猜你喜欢

* router
	* ==GET== /project/index/like/{userId}

* request
``` json
	"userId":Number
```

* response
``` json
[{
	"proId":Number,
	"proName":String,
	"proMoney":Number,
	"proType":String,
	"proCycle":Number,
	"pubTime":UNIX,
	"enrollment":Number
},
...]
```

#### 最新项目

* router
	* ==GET== /project/index/new

* request
``` json

```

* response
``` json
[{
	"proId":Nnmber,
	"proName":String,
	"proMoney":Number,
	"proType":String,
	"proCycle":Number,
	"pubTime":UNIX,
	"enrollment":Number
},
...]
```

###  2.2项目详情显示

* router
	* ==GET== /project/show/{proId}

* request
``` json
[{
	"proId":Number
},
...]
```

* response
``` json
[{
	"proId":Nnmber,
	"proName":String,
	"proMoney":Number,
	"proType":String,
	"companyName":String,
	"proCycle":Number,
	"pubTime":UNIX,
	"proDescription":String,
	"proRequest":String
},
...]
```

### 2.3 项目申请

* router
	* ==GET== /project/user/apply/{userId}/{proId}

* request
``` json
[{
	"userId":Number,
	"proId":Number
},
...]
```

* response
``` json
[{
	"isSuccessful":Boolean
},
...]
```

### 2.4 项目发布
#### 2.4.1 项目暂时保存

* router 
	* ==POST==   /project/company/save/{proCompanyId}

* request 
``` json
[{
	"proCompanyId":Number,
	"proName":String,
	"proDescription":String,
	"proType":String,
	"proCycle":Number,
	"proMoney":Number,
	"proRequest":Number
},
...]
```

* response
``` json
[{
	** Status(http状态码)
},
...]
```

#### 2.4.1 项目确定发布
* router 
	* ==POST==   /project/company/release/{proCompanyId}

* request 
``` json
[{
	"proCompanyId":Number,
	"proName":String,
	"proDescription":String,
	"proType":String,
	"proCycle":Number,
	"proMoney":Number,
	"proRequest":Number
},
...]
```

* response
``` json
[{
	"isSuccessful":Boolean
},
...]
```


### 2.5 查看项目申请

* router
	* ==GET==  /project/company/apply/show/{projectId}

* request
``` json
[{
	"projectId":Number
},
...]
```

* response
``` json
[{
	"imgUrl":Boolean,
	"resumeRealName":String,
	"resumeSex":String,
	"rusumeBirth":UNIX,
	"resumeEducation":String,
	"resumeSchool":String,
	"resumeMajor":String,
	"resumeMajor":String,
	"resumePhoneNum":String,
	"resumeExperience":String,
	"resumeWorkExperience":String
},
...]
```


#### 2.6 项目邀请

* router  
	* ==GET==  /project/company/invite/{companyId}/{proId}/{resumeId}

* request
``` json
[{
	"companyId":Number,
	"proId":Number,
	"resumeId":Number
},
...]
```

* response
``` json
[{
	"isSuccessful":Boolean
},
...]
```


### 2.7 项目管理

#### 项目阶段信息显示

* router
	* ==GET== /project/stage/{proId}

* request
``` json
[{
	"proId":Number
},	
...]
```

* response
``` json
[{
	"stageId":Number,
	"stageNum":Number,
	"stageStartTime":UNIX,
	"stageEndTime":UNIX,
	"stageSettlement":Number,
	"stageSpeed":Number,
	"stageSpeed":Number,
	"stageTargets":List
},	
...]
```

#### 项目阶段添加

* router 
	* ==POST==  /project/company/stage/add/{proId}/{stageNum}

* request
``` json
[{
	"proId":Number
	"stageNum":Number,
	"stageStartTime":UNIX,
	"stageEndTime":UNIX,
	"stageSettlement":String，
	"stageTargets":List
},
...]
```

* reponse
``` json
[{
	** Status(http状态码)
},
...]
```

#### 项目阶段修改

* router 
	* ==POST==  /project/company/stage/update/{proId}/{stageId}

* request
``` json
[{
	"stageId",Number
	"stageNum":Number,
	"stageStartTime":UNIX,
	"stageEndTime":UNIX,
	"stageSettlement":String，
	"stageTargets":List
},
...]
```

* reponse
``` json
[{
	"stageId",Number
	"stageNum":Number,
	"stageStartTime":UNIX,
	"stageEndTime":UNIX,
	"stageSettlement":String，
	"stageSpeed":Number,
	"stageTargets":List
},
...]
```

#### 改变指标状态

* router 
	* ==GET==  /project/company/stage/target/change{targetId}

* request
``` json
[{
	"targetId":Number
},
...]
```

* reponse
``` json
[{
	"isSuccessful":Boolean
},
...]
```

#### 删除指标

* router 
	* ==GET==  /project/company/stage/target/delete{targetId}

* request
``` json
[{
	"targetId":Number
},
...]
```

* reponse
``` json
[{
	"isSuccessful":Boolean
},
...]
```

#### 文件信息显示

**a. 文件汇总**

* router
	* ==GET== /project/file/all/{proId}

* request
``` json
[{
	"proId":Number
},	
...]
```

* response
``` json
[{
	"requestFiles":List
	{
		"requestFileId":Number,
		"companyId":Number,
		"proId":Number,
		"requestFileName":String,
		"requestFileSize":Number,
		"requestFileUploader":String,
		"requestFileModifyTime":UNIX
	}
	"workFiles":List
	{
		"workFileId":Number,
		"stageId":Number,
		"userId":Number,
		"workFileStageNum":Number,
		"workFileName":String,
		"workFfileSize":Number,
		"workFileUploader":String,
		"workFileModifyTime":UNIX
	}
},	
...]
```

**b. 指标文件信息显示**

* router
	* ==GET== /project/file/target/{targetId}

* request
``` json
[{
	"targetId":Number
},	
...]
```

* response
``` json
[{
	"workFileId":Number,
	"stageId":Number,
	"userId":Number,
	"workFileStageNum":Number,
	"workFileName":String,
	"workFfileSize":Number,
	"workFileUploader":String,
	"workFileModifyTime":UNIX
},	
...]
```


#### 文件下载

**a. 需求文件下载**

* router
	* ==GET== /project/requestFile/download/{fileName}

* request
``` json
[{
	"fileName":String
},	
...]
```

* response
``` json
[{
	ResponseEntity
	{
		文件二进流，请求头，状态码
	}
},	
...]
```

**b. 开发文件下载**

* router
	* ==GET== /project/workFile/download/{fileName}

* request
``` json
[{
	"fileName":String
},	
...]
```

* response
``` json
[{
	ResponseEntity
	{
		文件二进流，请求头，状态码
	}
},	
...]
```

#### 文件上传

**a. 个人用户上传开发文件**

* router
	* ==POST== /project/user/workFile/upload/{targetId}

* request
``` json
[{
	** 表单
	"targetId":Number
	"file":File,
	"userId":Number,
	"workFileUploader":String,
	"workFileName":String
},	
...]
```

* response
``` json
[{
	** Status(http状态码)
},	
...]
```

**b. 企业上传需求文件**

* router
	* ==POST== /project/company/requestFile/upload/{proId}

* request
``` json
[{
	** 表单
	"file":File,
	"companyId":Number,
	"requestFileUploader":String,
	"requestFileName":String
},	
...]
```

* response
``` json
[{
	** Status(http状态码)
},	
...]
```


### 2.8 项目中心

#### 个人简历状态
 
* router
	*  ==GET== /project/user/checkout/{userId}/{selectCode}

* request
```json
[{
	"userId":Number,
	"selectCode":Number
},
...]
```

* response
```json
[{
	... List
    "proId":Number,
	"proName":String,
	"proMoney":Number,
	"proType":String,
	"companyName":String,
	"proCycle":Number,
	"proPubTime":UNIX,
	"proEnrollment":Number,
	"proDescription":String,
	"proRequest":String,
	"proState":Number
},
...]
```

#### 企业项目中心
 
* router
	*  ==GET== /project/company/checkout/{companyId}

* request
```json
[{
	"companyId":Number
},
...]
```

* response
```json
[{
	... List
    "proId":Nnmber,
	"proName":String,
	"proMoney":Number,
	"proType":String,
	"companyName":String,
	"proCycle":Number,
	"pubTime":UNIX,
	"proDescription":String,
	"proRequest":String,
	"proState":Number,
	'proApplicants":Number
},
...]
```



## 企业信息管理

### 3.1 注册状态判断

* router
  * ==GET==  /company/register

* request
```json
[{
	"companyName":String,
    "phoneNum": String,
	"password":String,
	"rePassword":String,
	"idCode":String
},
...]
```

* response
```json
[{
    "isSucessful": Boolean
},
...]
```


### 3.2 企业登录

#### 普通登录

* router 
	* ==GET==  /company/login/{companyName}/{password}/{isRemember}

* request
``` json
[{
	"companyName":String,
	"password":String,
	"isRemember":Boolean
},
...]
```

* response 
``` json
[{
	"isSuccessful":Boolean,
	"errorMessage":String,
   "companyId":Number,
   "companyName":String
},
...]	
```

#### 验证码确认

* router
	* ==GET==/company/idCodeConfirm/{idCode}

* request
``` json
[{
	"idCode":String
},
...] 
```

* response
``` json
[{
   **Status(http状态码)
},
...] 
```

#### 忘记密码修改

* router
	* ==POST==  /company/forget

* request
``` json
[{
	"phonrNum":String,
	"password":String,
	"rePassword":String
},
...]
```

* response
``` json
[{
	** Status(http状态码)
},
...]
```

#### 修改密码

* router
	* ==POST==  /company/alter/{companyId}

* request
``` json
[{
	"companyId":Number,
	"oldPassword":String
	"newPassword":String,
	"rePassword":String
},
...]
```

* response
``` json
[{
	** Status(http状态码)
},
...]
```

### 3.3企业信息管理

#### 企业信息显示

* router 
	* ==GET==  /company/companyInfo/show/{companyId}

* request
``` json
[{
	"companyId":Number
},
...]
```

* response
``` json
[{
	"imgUrl":String,
	"companyRealName":String,
	"companyProCount":Number,
	"companyAddress":Stirng,
	"companyDescription":String
},
...]
```

#### 企业信息编辑
* router 
	* ==POST==  /company/companyInfo/alter/{companyId}

* request
``` json
[{
	"companyId":Number
	"companyRealName":String,
	"companyMajor":String,
	"companyDescription":String	
},
...]
```

* response
``` json
[{
	"companyRealName":String,
	"companyMajor":String,
	"companyDescription":String
},
...]
```



### 3.4 消息管理

### 消息分类显示

* router
	* ==GET== /company/message/show/{companyId}/{messageStateCode}/{curPage}

* request
``` json
[{
	"companyId":String,
	"messageStateCode":Number,
	"curPage":Number
},	
...]
```

* response
``` json
[{
	"messageId":Number,
	"messageContent":String,
	"messagePubTime":UNIX
},	
...]
```

#### 消息状态改变

 router 
	* ==POST== /company/message/alter/{companyId}/{messageId}

* request
``` json
[{
	"companyId":Number,
	"messageId":Number,
	"changeCode":Number
},	
...]
```

* response
``` json
[{
	** Status(OK)
},	
...]
```

### 3.5 专利信息管理

#### 专利信息搜索

* router
	* ==GET== /company/patent/search/{keyword}

* request
``` json
[{
	"keyword":String
},
...]
```

* response
``` json
[{
	"imgUrl":String,
	"patentName":String,
	"patentOwner":String,
	"patentNum":String,
	"patentApplyNum":String,
	"patentAuthTime":String,
	"patentCertiCode":String,
	"patentState":Number
},
...]
```


### 3.6 人才信息管理

#### 人才搜索
* router
	* ==GET== /company/resume/search/{resumeProfession}/{resumeProfessionType}/{resumeProvince}

* request
``` json
[{
	"resumeProfession":String,
	"resumeProfession":String,
	"resumeProvince":String
},
...]
```

* response
``` json
[{
	"resumeId":Number,
	"imgUrl":String,
	"resumeRealName":String,
	"resumeSex":String,
	"rusumeBirth":UNIX,
	"resumeEducation":String,
	"resumeSchool":String,
	"resumeMajor":String,
	"resumeMajor":String,
	"resumePhoneNum":String,
	"resumeExperience":String,
	"resumeWorkExperience":String,
	"resumeCompletedProjects":Number,
	"resumeSelectMark":Number
	},
...]
```

#### 简历筛选

* router
	*  ==GET==  /company/apply/select/{applyId}/{stateCode}

* request
``` json
[{
	"applyId":Number,
	"stateCode":Number
},
...]
```

* response
``` json
[{
	** Status(http状态码)
},
...]
```

## 图片管理

### 4.1 图片上传

#### 4.1.1 头像上传

* router
	* ==POST== /photo/upload/headProtrait/{userName}/{photoName}

* request
``` json
[{
	"userName":String,
	"photoName":String,
	"file":File
},
...]
```

* response
``` json
[{
	"url":String
},
...]
```
	
#### 4.1.2 简历照片上传

* router
	* ==POST== /photo/upload/resume/{userName}/{photoName}

* request
``` json
[{
	"userName":String,
	"photoName":String,
	"file":File
},
...]
```

* response
``` json
[{
	"url":String
},
...]
```

#### 4.1.3 专利图片上传

* router
	* ==POST== /photo/upload/patent/{userName}/{photoName}

* request
``` json
[{
	"userName":String,
	"photoName":String,
	"file":File
},
...]
```

* response
``` json
[{
	"url":String
},
...]
```

#### 4.1.4 公司logo上传

* router
	* ==POST== /photo/upload/companyLogo/{companyName}/{photoName}

* request
``` json
[{
	"userName":String,
	"photoName":String,
	"file":File
},
...]
```

* response
``` json
[{
	"url":String
},
...]
```

### 4.2 图片下载

* router
	* ==GET== /photo/download/{photoName}

* request
``` json
[{
	"photoName":String
},	
...]
```

* response
``` json
[{
	ResponseEntity
	{
		文件二进流，请求头，状态码
	}
},	
...]
```



