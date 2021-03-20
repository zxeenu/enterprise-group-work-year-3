package com.enterprise.sunchip.services;

import com.enterprise.sunchip.dao.UserAccountRepo;
import com.enterprise.sunchip.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepo repo;

    public ArrayList<UserAccount> findAllUserAccounts() {
        ArrayList<UserAccount> userAccounts = new ArrayList<UserAccount>();
        userAccounts.addAll(repo.findAll());
        return userAccounts;
    }

}
