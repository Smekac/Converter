package com.example.demo.Controller;

import com.example.demo.services.Converter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

@RequestMapping(value = "/ko")
public String  cao(){
    System.out.println("Ma znao sam da radii -------*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
    return "This is a String idemoooooooooo o!!!!!!!";
}

@RequestMapping(value = "/prebaci")
public String  prebaci(){
    Converter.convertingExcelFile();
    return "Trebamo da prebacimo    !!!!!!!";
}


}
