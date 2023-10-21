package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    private static Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @PostMapping("/transact")
    public String initiateTransaction(@RequestParam("receiver") String receiver,
                                      @RequestParam("message") String message,
                                      @RequestParam("amount") Double amount) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();
       logger.info("Password: "+user.getPassword()); //-- prints null
        // auth for logged in user method from security context from where we can get information of logged in user
        //multiple users login
        return transactionService.initiateTransaction(user.getUsername(), receiver, message, amount);
    }
}
