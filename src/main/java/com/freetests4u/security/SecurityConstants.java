package com.freetests4u.security;

public class SecurityConstants {

    public static final String SECRET = "whsjqsbjqnkjhsk";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/createUser";
    public static final String[] AUTH_ROUTE_LIST = {"addUser","addBook","registerBuyerRequest",
    		"getBuyerRequest","getBuyerRequestsForBook","transaction","getPendingTransactionsForUser",
    		"getBuyerRejectionCodes","getSellerRejectionCodes", "getSellerRequest", "createSellerRequest",
    		"getSellerRequestsForBook","getStoreBookCount","createAddress","getAddresses","getAddress"};
}
