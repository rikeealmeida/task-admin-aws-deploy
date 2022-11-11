package br.com.almeida.taskadminapi.configs.security;

public class SecurityConstants {

    public static final String SECRET = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 999999999; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/user/**";
    public static final long JWT_REFRESH_EXPIRATION_TIME = 3600000;
}