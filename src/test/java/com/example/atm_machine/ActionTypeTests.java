package com.example.atm_machine;

import com.example.atm_machine.model.Account;
import com.example.atm_machine.service.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ActionTypeTests {
    @Autowired
    AccountService accountService;

    @Mock
    HttpSession session;

    @Test
    public void actionMenu(){
       String result= accountService.actions("menu","","",session);

       Assert.assertEquals("menu.html", result);
    }

}
