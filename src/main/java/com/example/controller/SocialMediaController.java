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
    public ResponseEntity registerAccount(@RequestBody Account account) throws ClientException, ConflictingResourceException
    {
        Account addedAccount = accountservice.registerAccount(account);
        return ResponseEntity.status(200).body(addedAccount);
    }


    
    @PostMapping("/login")
    public ResponseEntity loginAccount(@RequestBody Account account) throws UnauthorizedException
    {
        Account foundAccount = accountservice.loginAccount(account);
        return ResponseEntity.status(200).body(foundAccount);
    }

    @PostMapping("/messages")
    public ResponseEntity createMessage(@RequestBody Message message) throws ClientException
    {
        Message addedMessage = messageservice.createMessage(message);
        return ResponseEntity.status(200).body(addedMessage);
    }

    @GetMapping("/messages")
    public ResponseEntity retrieveMessages()
    {
        List<Message> messageList = messageservice.retrieveAllMessages();
        return ResponseEntity.status(200).body(messageList);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity retrieveMessageById(@PathVariable Integer messageId)
    {
        Message returnedMessage = messageservice.retrieveMessageById(messageId);
        return ResponseEntity.status(200).body(returnedMessage);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity deleteMessage(@PathVariable Integer messageId) throws IllegalArgumentException
    {
        int numDeleted = messageservice.deleteMessageById(messageId.intValue());
        if(numDeleted < 1)
        {
            return ResponseEntity.status(200).body(null);
        }
        return ResponseEntity.status(200).body(Integer.valueOf(numDeleted));
        
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity updateMessage(@PathVariable("messageId") int messageId, @RequestBody Message newMessage) throws ClientException
    {
        int foundMessages = messageservice.updateMessageById(messageId, newMessage.getMessageText());
        return ResponseEntity.status(200).body(foundMessages);
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity retrieveMessagesByUser(@PathVariable Integer accountId)
    {
        List<Message> foundMessages = messageservice.retrieveMessagesByAccount(accountId);
        return ResponseEntity.status(200).body(foundMessages);
    }


}
