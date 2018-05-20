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

    private static final String algorithmName = "MD5";

    private static final int hashIterations = 2;

    public static User encrypt(User userLogin) {
        userLogin.setUserSalt(generator.nextBytes().toHex());
        String newPassword = new SimpleHash(algorithmName, userLogin.getUserPassword(), ByteSource.Util.bytes(userLogin.getUserSalt()), hashIterations).toHex();
        userLogin.setUserPassword(newPassword);
        return userLogin;
    }

}
