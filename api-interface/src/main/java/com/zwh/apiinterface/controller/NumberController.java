package com.zwh.apiinterface.controller;

import com.zwh.clientsdk.model.Calculate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Bonbons
 * @version 1.0
 */
@RestController
@RequestMapping("/number")
public class NumberController {
    @PostMapping("/add")
    public String add(@RequestBody Calculate number, HttpServletRequest request){
        return String.valueOf(number.getNumber1() + number.getNumber2());
    }

    @PostMapping("/sub")
    public String sub(@RequestBody Calculate number, HttpServletRequest request){
        return String.valueOf(number.getNumber1() - number.getNumber2());
    }

    @PostMapping("/multiply")
    public String multiply(@RequestBody Calculate number, HttpServletRequest request){
        return String.valueOf(number.getNumber1() * number.getNumber2());
    }

    @PostMapping("/division")
    public String division(@RequestBody Calculate number, HttpServletRequest request){
        if(number.getNumber2() == 0){
            return "除数不能为零!";
        }
        return String.valueOf(number.getNumber1() / number.getNumber2());
    }
}
