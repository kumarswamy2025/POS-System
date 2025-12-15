package com.possystem.mainapplication.DemoPackage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Test {

    @GetMapping("/")
    public String Home(){
        return "Home...";
    }

    @GetMapping("/test")
    public String Demo(){
        return  "Hello world...";
    }
}
