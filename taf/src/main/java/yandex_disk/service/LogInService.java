package yandex_disk.service;

import bo.User;
import loger.Log;
import yandex_disk.pages.LogInPage;

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
