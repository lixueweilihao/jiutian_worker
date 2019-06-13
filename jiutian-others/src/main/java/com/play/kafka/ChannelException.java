package com.play.kafka;

/**
 * Copyright @ 2018
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/11/15  19:49
 */
public class ChannelException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * @param message the exception message
     */
    public ChannelException(String message) {
        super(message);
    }

    /**
     * @param ex the causal exception
     */
    public ChannelException(Throwable ex) {
        super(ex);
    }

    /**
     * @param message the exception message
     * @param ex the causal exception
     */
    public ChannelException(String message, Throwable ex) {
        super(message, ex);
    }
}
