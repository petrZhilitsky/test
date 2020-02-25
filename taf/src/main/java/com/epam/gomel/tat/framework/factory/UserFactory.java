package com.epam.gomel.tat.framework.factory;

import com.epam.gomel.tat.framework.bo.User;

public class UserFactory {
    public static User defaultUser() {
        User user = new User();
        user.setLogin(Configuration.LOGIN);
        user.setPassword(Configuration.PASSWORD);
        return user;
    }
}
