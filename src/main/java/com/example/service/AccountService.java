package com.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.repository.AccountRepository;
import com.example.entity.Account;

import com.example.exception.*;

@Service
public class AccountService {

        @Autowired
        AccountRepository accountrepository;
        

        public Account registerAccount(Account account) throws ClientErrorException, ConflictingResourceException
        {
            List<Account> accountList = accountrepository.findAll();
            for(Account acc: accountList)
            {
                if (acc.getUsername().equals(account.getUsername()))
                {
                    throw new ConflictingResourceException();
                }
            }

            if((account.getUsername().length() < 1) || (account.getPassword().length()< 4))
            {
                throw new ClientErrorException();
            }
            
            return accountrepository.save(account);

        }

}
