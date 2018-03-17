package com.skyworth.controller;

import com.skyworth.service.userService.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Shallow on 2018/3/6.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


}
