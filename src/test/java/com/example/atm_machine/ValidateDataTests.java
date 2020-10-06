package com.example.atm_machine;

import com.example.atm_machine.service.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidateDataTests {


    @Autowired
    AccountService accountService;

    @Test
    public void validatePin(){
        String[] pins = {"1234","as34d4","1234567", "0000"};
        boolean[] expectedResult = {true,false,false,true};
        boolean [] result = {true,true,true,true};
        for(int i=0;i<pins.length;i++){
            result[i] = accountService.validateData(pins[i], accountService.VALID_PIN);
        }
        Assert.assertArrayEquals(expectedResult, result);

    }

    @Test
    public void validateCard(){
        String[] pins = {"1234123412341234","as34d4345672","1234567891234567", "0000df"};
        boolean[] expectedResult = {true,false,true,false};
        boolean [] result = {true,true,true,true};
        for(int i=0;i<pins.length;i++){
            result[i] = accountService.validateData(pins[i], accountService.VALID_CARD_NR);
        }
        Assert.assertArrayEquals(expectedResult, result);

    }


}
