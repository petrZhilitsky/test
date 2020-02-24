package com.epam.gomel.tat.yandex_disk.service;

import com.epam.gomel.tat.framework.bo.User;
import com.epam.gomel.tat.framework.loger.Log;
import com.epam.gomel.tat.yandex_disk.pages.LogInPage;

public class LogInService {
    public void logIn(User user) {
        Log.debug("Logging in user " + user.toString());
        new LogInPage()
                .openPage()
                .openCredentialsInput()
                .inputLogin(user.getLogin())
                .clickConfirm()
                .inputPassword(user.getPassword())
                .clickConfirm();
    }
}
