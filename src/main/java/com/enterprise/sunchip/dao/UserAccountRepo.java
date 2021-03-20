package com.enterprise.sunchip.dao;

import com.enterprise.sunchip.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAccountRepo extends JpaRepository<UserAccount, Integer>  {
    List<UserAccount> findByType(String type);
}
