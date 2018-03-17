---
title: 2018-2-1    华工问答
tags: 初版
grammar_cjkRuby: true
---
## 用户信息管理
### 1.1 登录
* router
	* ==GET==  /user/login/{account}/{password}

* request
``` json
[{
	"account":String,
	"password":String
},
...]
```

* response
``` json
	 "isSuccessful":Boolean
```

### 1.2 获取手机验证码
* router
	* ==GET==  /user/identity/{phoneNum}

* request
``` json
	"phoneNum":String
```

* response
``` json

```

### 1.3 找回密码
* router
	* ==GET==  /user/forget/{phoneNum}/{password}/{rePassword}/{idCode}

* request
``` json
	"phoneNum":String,
	"password":String,
	"rePassword":String,
	"idCode":String
```

* response
``` json
	"isSuccessful":Boolean
```

### 1.4 注册
* router
	* ==GET== /user/register/{phoneNum}/{idCode}/{account}/{password}/{rePassword}

* request
``` json
	"account":String,
	"password":String,
	"rePassword":String,
	"idCode":String
```

* response
``` json
	"isSuccessful":Boolean
```

### 1.5 上传头像
* router
	* ==POST== /user/portrait/{userName}

* request
``` json
	"userName":String,
	"userImg":File
```

* response
``` json
	"imgUrl":String
```

### 1.6 个人资料显示
* router
	*  ==GET==  /user/show/info/{userName}

* request
``` json
	"userName":String
```

* response
``` json
	"imgUrl":String,
	"userName":String,
	"phoneNum":String,
	"userSex":String,
	"userArea":String,
	"userAddress":String,
	"userWechat":String
```

### 1.7 修改资料
#### 1.7.1 修改用户名
* router
	* ==POST== /user/alter/userName/{exUserName}

* request
``` json
	"exUserName":String,
	"newUserName":String
```

* response
``` json
	"isSuccessful":Boolean
```

#### 1.7.2 修改用户性别
* router
	* ==POST== /user/alter/sex/{userName}

* request
``` json
	"userName":String,
	"userSex":String
```

* response
``` json
	"isSuccessful":Boolean
```

#### 1.7.3 修改用户地址
* router
	* ==POST== /user/alter/address/{userName}

* request
``` json
	"userName":String,
	"userArea":String,
	"userAddress":String
```

* response
``` json
	"isSuccessful":Boolean
```

#### 1.7.4 修改用户联系方式
* router
	* ==POST== /user/alter/contact/{userName}

* request
``` json
	"userName":String,
	"userWechat":String,
	"phoneNum":String
```

* response
``` json
	"isSuccessful":Boolean
```

## 华园社区

### 2.1发布社区资讯
* router
	* ==POST==  /community/release/{userName}

* request
``` json
	"userName":String,
	"content":String,
	"cutoff":UNIX,
	"contact":String,
	"type":Integer
```

* request 
``` json
	"isSuccessful":Boolean
```

### 2.2 最热资讯
* router
	* ==GET==  /community/hotest/{type}

* request
``` json
	  "type":Integer
```

* response
``` json
	 "cummunityId":Integer,
	  "imgUrl":String,
	  "userName":String,
	  "releaseTime":UNIX,
	  "cutoffTime":UNIX,
	  "likeNum":Integer,
	  "commentNum":Integer,
	  "content":String,
	   "contact":String
```

### 2.3 最新资讯
* router
	* ==GET==  /community/newest/{type}

* request
``` json
	"type":String
```

* response
``` json
	 "cummunityId":Integer,
	 "imgUrl":String,
	  "userName":String,
	  "releaseTime":UNIX,
	  "cutoffTime":UNIX,
	  "likeNum":Integer,
	  "commentNum":Integer,
	   "contact":String
```

### 2.4 资讯搜索
* router
	* ==GET==  /community/search/{keyword}

* request
``` json
	"keyword":String
```

* response
``` json
	 "cummunityId":Integer,
	 "imgUrl":String,
	  "userName":String,
	  "releaseTime":UNIX,
	  "cutoffTime":UNIX,
	  "likeNum":Integer,
	  "commentNum":Integer,
	  "contact":String
```

### 2.4 资讯评论
#### 2.4.1 显示评论
* router
	* ==GET==  /community/comment/show/{communityId}

* request
``` json
	"communityId":Integer
```

* response
``` json
	"imgUrl":String,
	"commentor":String,
	"commentTime":UNIX,
	"likeNum":Integer,
	"content":String
```

#### 2.4.2 添加评论
* router
	* ==POST==  /community/comment/add/{communityId}/{userId}

* request
``` json
	"communityId":Integer,
	"userId":Integer,
	"comment":String
```

* response
``` json
	"commentNum":Integer
```

#### 2.4.3 评论点赞
* router
	* ==GET==  /community/comment/like/{commentId}/{userId}

* request
``` json
	"commentId":Integer,
	"userId":Integer
```

* response
``` json
	"likenNum":Integer
```

### 2.5 资讯点赞
* router
	* ==GET==  /community/like/{communityId}/{userId}

* request
``` json
	"communityId":Integer,
	"userId":Integer
```

* response
``` json
	"likeNum":Integer
```

## 问题&文章
### 3.1 问题
#### 3.1.2 添加问题
* router
	* ==POST==  /question/add/{userId}

* request
``` json
	"userId":Integer,
	"title":String,
	"description":String,
	"topicOne":String,
	"topicTwo":String
```

* response
``` json
	"isSuccessful":Boolean
```

#### 3.1.2 搜索问题
* router
	* ==GET==  /question/search/{keyword}

* request
``` json
	"keyword":String
```

* response
``` json
	"imgUrl":String,
	"questioner":String,
	"releaseTime":UNIX,
	"topicOne":String,
	"topicTwo":String,
	"title":String,
	"description":String,
	"likeNum":Integer
```

#### 3.1.3 问题详情
* router
	* ==GET==  /question/detail/{questionId}

* request
``` json
	"questionId":Integer
```

* response
``` json
	"imgUrl":String,
	"questioner":String,
	"releaseTime":UNIX,
	"topicOne":String,
	"topicTwo":String,
	"title":String,
	"description":String,
	"likeNum":Integer,
	"collecNumt":Integer,
	"answerNum":Integer
```

#### 3.1.4 点赞问题
* router 
	*  ==GET==  /question/like/{questionId}/{userId}

* request
``` json
	"questionId":Integer,
	"userId":Integer
```

* response
``` json
	"likeNum":Integer
```


#### 3.1.5 收藏问题
* router 
	*  ==GET==  /question/collect/{questionId}/{userId}/{type}

* request
``` json
	"questionId":Integer,
	"userId":Integer,
	"type":Integer
```

* response
``` json
	"isSuccessful":Boolean
```


#### 3.1.6 回答问题
* router
	* ==POST==  /question/answer/{questionId}/{userId}

* request
``` json
	"questionId":Integer,
	"userId":Integer,
	"content":String
```

* response
``` json
	"isSuccessful":Boolean
```

#### 3.1.7 更多回答
* router
	*  ==GET==  /question/answer/more/{articleId}

* request
``` json
	"articleId":Integer
```

* response
``` json
	"answerId":Integer,
	"imgUrl":String,
	"answerName":String,
	"likeNum":Integer,
	"content":String
```

### 3.1.8 回答详情页
* router 
	*  ==GET==  /question/answer/detail/{answerId}

* request
``` json
	"answerId":Integer
```

* response
``` json
	"answerId":Integer,
	"imgUrl":String,
	"answerName":String,
	"likeNum":Integer,
	"content":String
	
```

#### 3.1.9 回答点赞
* router
	*  ==POST==  /question/answer/like/{questionId}/{answerId}

* request
``` json
	"questionId":Integer,
	"answerId":Integer
```

* response
``` json
	"likeNum":Integer
```

#### 3.1.10 回答评论
* router
	*  ==POST==  /question/answer/comment/{answerId}/{userId}

* request
``` json
	"answerId":Integer,
	"userId":Integer,
	"content":String
```

* response
``` json
	"isSuccessful":Boolean
```

#### 3.1.11 更多评论
* router
	*  ==GET==  /question/answer/comment/more/{answerId}

* request
``` json
	"answerId":Integer
```

* response
``` json
	"imgUrl":String,
	"userName":String,
	"content":String,
	"likeNum":Integer
```

#### 3.1.11 评论详情
* router
	*  ==GET==  /question/answer/comment/detail/{answerId}

* request
``` json
	"answerId":Integer
```

* response
``` json
	"imgUrl":String,
	"userName":String,
	"content":String,
	"likeNum":Integer
```


### 3.2 文章
#### 3.2.1 写文章
* router
	* ==POST==  /article/add/{userId}

* request
``` json
	"userId":Integer,
	"title":String,
	"content":String,
	"topicOne":String,
	"topicTwo":String
```

* response
``` json
	"isSuccessful":Boolean
```


#### 3.2.2 搜索文章
* router
	* ==GET==  /article/search/{keyword}

* request
``` json
	"keyword":String
```

* response
``` json
	"imgUrl":String,
	"questioner":String,
	"releaseTime":UNIX,
	"topicOne":String,
	"topicTwo":String,
	"title":String,
	"content":String,
	"likeNum":Integer
```

#### 3.2.3 文章详情
* router
	* ==GET==  /article/detail/{articleId}

* request
``` json
	"article":Integer
```

* response
``` json
	"imgUrl":String,
	"questioner":String,
	"releaseTime":UNIX,
	"topicOne":String,
	"topicTwo":String,
	"title":String,
	"content":String,
	"likeNum":Integer,
	"commentNum":Integer,
	"collectNum":Integer
```

#### 3.2.4 文章点赞
* router
	*  ==GET==  /article/like/{articleId}/{userId}

* request
``` json
	"articleId":Integer,
	"userId":Integer
```

* response
``` json
	"likeNum":Integer
```

#### 3.2.5 文章评论
* router
	*  ==POST==  /article/comment/{articleId}/{userId}

* request
``` json
	  "articleId":Integer,
	  "userId":Integer,
	  "content":String
```

* response
``` json
	  "commentNum":Integer
```

#### 3.2.6 更多评论
* router
	* ==GET==  /article/comment/more/{articleId}

* request
``` json
	"commentId":Integer
```

* response
``` json
	"commentId":Integer,
	"imgUrl":String,
	"commentator":String,
	"likeNum":Integer,
	"content":String
```

#### 3.2.7 评论详情
* router
	*  ==GET==  /article/comment/detail/{commentId}

* request
``` json
	"commentId":Integer
```

* response
``` json
	"imgUrl":String,
	"commentator":String,
	"content":String,
	"likeNum":Integer
```

#### 3.2.8 评论点赞
* router 
	* ==GET==  /article/comment/like/{commentld}

* request
``` json
	"commentId":Integer
```

* response
``` json
	"likeNum":Integer
```







 


