package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.entity.*;

import com.example.exception.*;

@Service
public class MessageService {

    @Autowired
    MessageRepository messagerepository;

    @Autowired
    AccountRepository accountrepository;

    public Message createMessage(Message message) throws ClientException
    {
        if ((message.getMessageText().length() < 1) || (message.getMessageText().length() > 255))
        {
            throw new ClientException();
        }
        List<Account> accounts = accountrepository.findAll();
        if (accounts == null)
        {
            throw new ClientException();
        }
        for(Account a : accounts)
        {
            if (a.getAccountId().intValue() == message.getPostedBy().intValue())
            {
                return messagerepository.save(message);
            }
        }
        throw new ClientException();
    }


    public List<Message> retrieveAllMessages()
    {
        return messagerepository.findAll();
    }

    public Message retrieveMessageById(int id)
    {
        return messagerepository.findMessageByMessageId(Integer.valueOf(id));
    }

    public int deleteMessageById(int id) throws IllegalArgumentException
    {
        int deletedRecords = 0;
        List<Message> messageList = messagerepository.findAll();
        for(Message m:messageList)
        {
            if(m.getMessageId().intValue() == id)
            {
                deletedRecords += 1;
                messagerepository.deleteById(Integer.valueOf(id));
            }
        }

        return deletedRecords;
    }

    public int updateMessageById(int id, String newText) throws ClientException
    {
        Message foundMessage = messagerepository.findMessageByMessageId(Integer.valueOf(id));

        if(foundMessage == null)
        {
            throw new ClientException();
        }

        if((newText.length() < 1)||(newText.length()>255))
        {
            throw new ClientException();
        }

        foundMessage.setMessageText(newText);
        messagerepository.save(foundMessage);
        return 1;
    }

    public List<Message> retrieveMessagesByAccount(Integer accountId)
    {
        return messagerepository.findByPostedBy(accountId);
    }
}
