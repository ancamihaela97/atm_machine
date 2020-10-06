package com.example.atm_machine.controller;


import com.example.atm_machine.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpSession;

@Controller

public class AccountController {

    @Autowired
    AccountService accountService;


    @GetMapping("/")
    public String index(){
        return "index.html";
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public String getAction(@RequestParam("actionType") String actionType,
                                @RequestParam(value = "param1", required = false) String param1,
                                @RequestParam(value = "param2", required = false) String param2, HttpSession session) {

            return accountService.actions(actionType,param1,param2,session);


    }


}
