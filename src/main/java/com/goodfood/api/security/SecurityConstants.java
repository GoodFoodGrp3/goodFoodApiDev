package com.goodfood.api.security;

public class SecurityConstants
{
//    public static final String SECRET          = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME   = 86_400_000;          // 1 days
    public static final String TOKEN_PREFIX    = "Bearer ";
    public static final String HEADER_STRING   = "Authorization";
    public static final String REGISTER_URL    = "/register";
}