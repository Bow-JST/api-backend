package com.zwh.apiinterface.controller;

import com.zwh.clientsdk.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取传递参数 API
 *
 * @author zwh
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request) {
        return "POST 用户名字是" + user.getUsername();
    }
}
