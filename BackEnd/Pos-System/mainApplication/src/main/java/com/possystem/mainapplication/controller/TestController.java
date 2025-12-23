package com.possystem.mainapplication.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//this class is used to test controllers

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/t1")
    public  String Message(){
        return "this is message";
    }


}
