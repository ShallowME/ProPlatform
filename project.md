---    
title: 2018-1-20    项目发布平台
tags: 初版
grammar_cjkRuby: true
---
=========================================

## 用户信息管理

### 1.1 注册

#### 获取手机验证码

* router
  * ==GET==  /identity

* request
```
```

* response
```json
[{
    "idCode": String,
},
...]
```

#### 注册状态判断

* router
  * ==GET==  /user/register/{userName}/{phoneNum}/{password}/{rePassword}/{idCode}

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
    "isSucessful": Boolean
},
...]
```

### 1.2 登录


* router
  * ==GET==/user/login/{identity}/{password}/{isRemember}

* request
``` json
	[{
    "identity": String,
	"password":String,
	"isRemember":Boolean
},
...] 
```

* response
``` json
[{
   "isSuccessful":Boolean,
   "errorMessage"：String,
   "userName":String
},
...] 
```

### 1.3 密码管理

#### 忘记密码

* router
	* ==POST==/user/forget/{phoneNum}

* request
``` json
[{
   "phoneNum": String,
	"password":String,
	"rePassword":String,
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

#### 修改密码

* router
	* ==POST==/user/alter/{userId}

* request
``` json
[{
	{userId}:Number,
	"oldPassword":String,
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

### 1.4 个人资料管理

#### 上传头像

* router
	* ==GET== /user/upload/{userName}

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
   "userProfession":String,
   "userStation":String,
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
   "userProfession":String,
   "userStation":String,
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
   "userProfession":String,
   "userStation":String,
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

#### 简历信息修改

* router
	* ==POST== /user/resume/alter/{userName}

* request
``` json
[{
	"userName":String,
	"resumeRealName":String,
	"resumeSex":String,
	"resumeBirth":UNIX,
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
	"patentApplyNum":String,
	"patentAuthTime":String,
	"patentCertiNum":String,
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
	"patentApplyNum":String,
	"patentAuthTime":String,
	"patentCertiNum":String,
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
	* ==POST== /user/book/alter/{userName}

* request
``` json
[{
	"userName":String,
	"bookSpot":String,
	"bookType":String,
	"bookMinPay":Number,
	"bookMaxPay":Number
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
	* ==GET== /user/book/delete/{userName}/{bookId}

* request
``` json
[{
	"userName":String,
	"bookId":Number
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
	* ==GET== /user/book/show/{userName}

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
	"bookSpot":String,
	"bookType":String,
	"bookMinPay":Number,
	"bookMaxPay":Number
},	
...]
```


## 项目信息

### 2.1 项目搜索

#### 项目名称模糊搜索

* router
	* ==GET== /project/search/{proName}/{curPage}

* request
``` json
[{
	"proName":String,
	"curPage":Number
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
	"proCycle":Number,
	"pubTime":UNIX,
	"enrollment":Number
},
...]
```

#### 项目名称模糊搜索总页数

* router
	* ==GET== /project/pagnation/{proName}

* request
``` json
[{
	"proName":String
},
...] 
```
* response
``` json
[{
	"totalPages":Number
},
...]
```


#### 分类搜索
	  
* router
	* ==GET==  /project/search/type/{typeCode}/{curPage}
	
* request
``` json
[{
	"typeCode":Number,
	"curPage":Number
...]
```

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

#### 分类搜索总页数

* router
	* ==GET== /project/pagnation/type/{typeCode}

* request
``` json
[{
	"proName":String
},
...] 
```
* response
``` json
[{
	"totalPages":Number
},
...]
```

#### 排序搜索

* router
	* ==GET== /project/search/order/{orderCode}/{curPage}

* request
``` json
[{
	"curPage":int
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
	"proCycle":Number,
	"pubTime":UNIX,
	"enrollment":Number
},
...]
```

#### 排序搜索总页数

* router
	* ==GET== /project/pagnation/order/{orderCode}

* request
``` json
[{
	"orderCode":Number
},
...] 
```
* response
``` json
[{
	"totalPages":Number
},
...]
```

#### 猜你喜欢

* router
	* ==GET== /project/index/like

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

#### 项目申请人数

* router  
	* ==GET==  /project/company/apply/{proId}

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
	"applyNum":Number
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
	"enrollment":Number,
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

* router 
	* ==POST==   /project/company/publish/{proCompanyId}

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

### 2.5 查看项目申请

* router
	* ==GET==  /project/company/apply/show/{proId}

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
	**Status(http状态码)
},
...]
```


### 2.7 项目管理

#### 项目阶段信息显示

* router
	* ==GET== /project/stage/{proId}/{stageNum}

* request
``` json
[{
	"proId":Number,
	"stageNum":Number
},	
...]
```

* response
``` json
[{
	"stageId":Number,
	"stageStartTime":UNIX,
	"stageEndTime":UNIX,
	"stageSettlement":Number,
	"stageSpeed":Number
},	
...]
```

#### 项目阶段添加

* router 
	* ==POST==  /project/company/stage/add/{proId}

* request
``` json
[{
	"proId":Number,
	"stageNum":Number,
	"stageStartTime":UNIX,
	"stageEndTime":UNIX,
	"stageSettlement":String
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


#### 指标信息显示

* router
	* ==GET== /project/stage/{proId}/{stageId}/{targetName}

* request
``` json
[{
	"proId":Number,
	"stageId":Number,
	"targetName":String
},	
...]
```

* response
``` json
[{
	"targetId":Number,
	"targetDeadline":UNIX,
	"stargetDetail":String,
	"stageRemarks":String
},	
...]
```

#### 增加指标

* router 
	* ==POST==  /project/company/stage/tagrget/add/{proId}/{stageId}

* request
``` json
[{
	"proId":Number,
	"stageId":Number,
	"targetName":String,
	"targetDeadline":UNIX,
	"targetDetails":String
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

#### 修改指标

* router 
	* ==POST==  /project/company/stage/tagrget/alter/{proId}/{stageId}

* request
``` json
[{
	"proId":Number,
	"stageId":Number,
	"targetName":String,
	"targetDeadline":UNIX,
	"targetDetails":String
},
...]
```

* reponse
``` json
[{
	"targetId":Number,
	"targetName":String,
	"targetDeadline":UNIX,
	"targetDetails":String
},
...]
```

#### 删除指标

* router 
	* ==GET==  /project/company/stage/tagrget/alter/{proId}/{stageId}/{targetId}

* request
``` json
[{
	"proId":Number,
	"stageId":Number,
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

* router
	* ==GET== /project/file/{proId}/{stageId}

* request
``` json
[{
	"proId":Number,
	"stageId":Number
},	
...]
```

* response
``` json
[{
	"fileId":Number,
	"fileName":String,
	"fileSize":Number,
	"fileUploader":String,
	"fileModifyTime":UNIX
},	
...]
```

#### 文件下载

* router
	* ==GET== /project/file/download/{proId}/{stageId}/{fileName}/{downloadPath}

* request
``` json
[{
	"proId":Number,
	"stageId":Number,
	"fileName":String,
	"downloadPath"
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

#### 文件上传

**a. 个人用户上传**

* router
	* ==POST== /project/file/user/upload/{proId}/{stageId}

* request
``` json
[{
	** 表单
	"file":file,
	"userId":Number,
	"uploader":String,
	"proId":Number,
	"stageId":Number,
	"fileName":String
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

**b. 企业用户下载**

* router
	* ==POST== /project/file/company/upload/{proId}/{stageId}

* request
``` json
[{
	** 表单
	"file":file,
	"companyId":Number,
	"uploader":String,
	"proId":Number,
	"stageId":Number,
	"fileName":String
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


## 企业信息管理

### 3.1 注册状态判断

* router
  * ==GET==  /company/register/{companyName}/{phoneNum}/{password}/{rePassword}/{idCode}

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
	"isSuccessful":Boolean
},
...]	
```

#### 忘记密码

* router
	* ==POST==  /company/forget/{phoneNum}

* request
``` json
[{
	"phoneNum":String,
	"password":String,
	"rePassword":String,
	"idCode":String
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

#### 企业logo上传
* router
	*  ==POST==  /company/companyInfo/logo/{companyId}/{companyName}/{photoName}

* request
``` json
[{
	"companyId":Number,
	"companyName":String,
	photoName:String
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
	"companyMajor":String,
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
	"companyAddress":Stirng,
	"companyMajor":String,
	"companyDescription":String	
},
...]
```

* response
``` json
[{
	"companyRealName":String,
	"companyAddress":Stirng,
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
	* ==GET== /company/patent/search/{keyword}/{curPage}

* request
``` json
[{
	"keyword":String,
	"curPage":Number
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
	"patentCertiNum":String,
	"patentState":Number
},
...]
```

#### 专利信息详情

* router
	* ==GET== /company/patent/detail/{patentId}

* request
``` json
[{
	"patentId":String
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
	"patentCertiNum":String,
	"patentState":Number
},
...]
```

### 3.6 人才信息管理

#### 人才行业搜索
* router
	* ==GET== /company/resume/search/profession/{proType}

* request
``` json
[{
	"proType":String
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
	"resumeWorkExperience":String
	},
...]
```

#### 人才地点搜索
* router
	* ==GET== /company/resume/search/province/{province}

* request
``` json
[{
	"province":String
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
	"resumeWorkExperience":String
},
...]
```

#### 简历筛选

* router
	*  ==GET==  /company/apply/select/{applyId}/{stateCode}

* request
``` json
[{
	applyId":Number,
	stateCode:Number
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






