package com.enterprise.sunchip.services;

import com.enterprise.sunchip.dao.UserAccountRepo;
import com.enterprise.sunchip.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepo repo;

    /**
     * Gets all Records in the UserAccount table, with passwords, and created an
     * Arraylist of UserAccount objects
     *
     * @return
     */
    public ArrayList<UserAccount> findAllUserAccounts() {
        ArrayList<UserAccount> userAccounts = new ArrayList<UserAccount>();
        userAccounts.addAll(repo.findAll());
        return userAccounts;
    }

}
