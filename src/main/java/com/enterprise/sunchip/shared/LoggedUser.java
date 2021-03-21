package com.enterprise.sunchip.shared;

import com.enterprise.sunchip.models.UserAccount;

/**
 * for potato authentication
 *
 * Place holder logged in user object until we get real security implemented
 */
public class LoggedUser {

    private static UserAccount userAccount;

    public static UserAccount getUserAccount() {
        return userAccount;
    }

    public static void setUserAccount(UserAccount userAccount) {
        LoggedUser.userAccount = userAccount;
    }
}
