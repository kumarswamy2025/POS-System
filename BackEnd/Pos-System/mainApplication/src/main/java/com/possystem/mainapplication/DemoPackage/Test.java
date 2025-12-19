package com.possystem.mainapplication.DemoPackage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Test {

    @GetMapping("/")
    public String Home() {
        return "Home...";
    }

    @GetMapping("/test")
    public String Demo() {
        return "Hello world...";
    }

    public String main() {
        return "main";
    }

    public String kumar() {
        return "kuamr";
    }

    public  String main2(){
        return "main2";
    }
    public  String kumar2(){
        return "kumar 2";
    }

    public  String kumar3(){
        return "kumar 3";
    }
}
