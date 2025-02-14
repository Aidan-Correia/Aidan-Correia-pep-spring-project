package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.example.entity.*;
import com.example.exception.*;
import com.example.service.*;

import java.util.List;

import javax.websocket.server.PathParam;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    @Autowired
    AccountService accountservice;
    @Autowired
    MessageService messageservice;

    

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody Account account) throws ClientException, ConflictingResourceException
    {
        Account addedAccount = accountservice.registerAccount(account);
        return ResponseEntity.status(200).body(addedAccount);
    }


    //TODO
    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody Account account) throws UnauthorizedException
    {

        return ResponseEntity.status(200).body();
    }

    @PostMapping("/messages")
    public ResponseEntity createMessage(@RequestBody Message message) throws ClientException
    {
        
        return ResponseEntity.status(200).body();
    }

    @GetMapping("/messages")
    public ResponseEntity retrieveMessages()
    {
        List<Message> messageList;
        return ResponseEntity.status(200).body();
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity retrieveMessageById(@PathVariable Integer messageId)
    {
        
        return ResponseEntity.status(200).body();
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity deleteMessage(@PathVariable Integer messageId)
    {
        
        return ResponseEntity.status(200).body();
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity updateMessage(@PathVariable Integer messageId)
    {
        
        return ResponseEntity.status(200).body();
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity retrieveMessagesByUser(@PathVariable Integer accountId)
    {
        
        return ResponseEntity.status(200).body();
    }


}
