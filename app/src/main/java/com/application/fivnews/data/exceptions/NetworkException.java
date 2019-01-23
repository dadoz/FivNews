package com.application.fivnews.data.exceptions;


import com.application.fivnews.R;

public class NetworkException extends RuntimeException {


    private NetworkException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getErrorString() {
        return R.string.error_network_no_network;
    }

    public static NetworkException networkError(Throwable error) {
        return new NetworkException("Network exception", error);
    }
}