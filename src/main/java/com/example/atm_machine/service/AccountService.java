package com.example.atm_machine.service;

import com.example.atm_machine.model.Account;
import com.example.atm_machine.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class AccountService {

    private static final Logger LOG = LoggerFactory.getLogger(AccountService.class);
    public static final Pattern VALID_CARD_NR = Pattern.compile("^[0-9]{16}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PIN = Pattern.compile("^[0-9]{4}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_DOUBLE = Pattern.compile("^[0-9]+(\\\\.[0-9]+)?$", Pattern.CASE_INSENSITIVE);
    public String loginMessage ="";
    public String withdrawMessage ="";

    @Autowired
    AccountRepository accountRepository;


    public String actions(String actionType, String param1, String param2, HttpSession session){
        switch(actionType){
            case "register":
                return checkAccount(param1,param2,session);
            case "withdraw":
                return withdrawMoney(param1,session);
            case "balance":
                return balance();
            case "menu":
                return "menu.html";
            default:
                return goBack();

        }

    }

    private String checkAccount( String param1, String param2, HttpSession session){
        final String[] result = {"index.html"};

        if(!param1.isEmpty() && !param2.isEmpty()) {
            try{
            Matcher matcherCard = VALID_CARD_NR.matcher(param1);
            Matcher matcherPin = VALID_PIN.matcher(param2);

            if (matcherCard.find() && matcherPin.find()) {
                List<Account> accountList = accountRepository.findAll();
                accountList.stream().forEach(i -> {
                    if (i.getCard().equals(param1) && i.getPin().equals(param2)) {
                        session.setAttribute("currentAccount", i);
                        result[0] = "menu.html";
                    }
                });
            } else {
                loginMessage = "Introduce a valid card number (16 digits) and pin (4 digits)";
            }
        }
            catch (Exception e) {
                LOG.error("Got Exception while processing : {}", e.getMessage());
            }
        }
        else{
            loginMessage ="Please fill in both fields!";
        }
        if(!loginMessage.isEmpty()){
            session.setAttribute("loginMessage", loginMessage);
            loginMessage="";
        }

        return result[0];
    }
    private String withdrawMoney( String param1,HttpSession session){
        Account account= (Account) session.getAttribute("currentAccount");

        if(param1!= null) {
            try{
                if(!param1.isEmpty()){
                    Matcher matcherDouble = VALID_DOUBLE.matcher(param1);
                    if (!matcherDouble.find()){
                        withdrawMessage = "Failed";
                    }
                    else if(Double.parseDouble(param1) > account.getBalance()) {
                        withdrawMessage = "Exceed";
                    } else {
                        double newBalance = account.getBalance() - Double.parseDouble(param1);
                        account.setBalance(newBalance);
                        accountRepository.save(account);
                        session.setAttribute("currentAccount", account);
                        withdrawMessage = "Success";
                    }
                }
                else{
                    withdrawMessage = "Empty";
                }

            }
            catch (Exception e) {
                LOG.error("Got Exception while processing : {}", e.getMessage());
            }
        }

        session.setAttribute("messageWithdraw", withdrawMessage);
        withdrawMessage="";
            return "withdraw.html";

    }
 private String balance(){
        return "balance.html";
    }
    private String goBack(){

        return "index.html";
    }


}
