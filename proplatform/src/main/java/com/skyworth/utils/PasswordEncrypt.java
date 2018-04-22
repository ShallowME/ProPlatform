package com.skyworth.utils;

import com.skyworth.model.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by Shallow on 2018/3/6.
 */
public class PasswordEncrypt {

    private static RandomNumberGenerator generator = new SecureRandomNumberGenerator();

    private String algorithName = "MD5";

    private final int hashIterations = 2;

    public User encrypt(User userLogin) {
        userLogin.setUserSalt(generator.nextBytes().toHex());
        String newPassword = new SimpleHash(algorithName, userLogin.getUserPassword(), ByteSource.Util.bytes(userLogin.getUserSalt()), hashIterations).toHex();
        userLogin.setUserPassword(newPassword);
        return userLogin;
    }

}
