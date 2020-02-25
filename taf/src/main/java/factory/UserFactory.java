package factory;

import bo.User;
import yandex_disk.service.Configuration;

public class UserFactory {
    public static User defaultUser() {
        User user = new User();
        user.setLogin(Configuration.LOGIN);
        user.setPassword(Configuration.PASSWORD);
        return user;
    }
}
