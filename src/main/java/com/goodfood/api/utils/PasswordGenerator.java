package com.goodfood.api.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>
 *  Class qui permet de générer un mot de passe hasher avec bcrypt.
 * </p>
 * @author Arthur D.
 */
public class PasswordGenerator
{
    public static void main(String[] args)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "jIwEbWW7ynIDUFVCU1zHFndL3IqcBh";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(rawPassword);
        System.out.println(encodedPassword);
        System.out.println(encoder.matches(rawPassword, encodedPassword));
    }
}
