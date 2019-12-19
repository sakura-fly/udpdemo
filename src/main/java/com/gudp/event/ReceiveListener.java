package com.gudp.event;

public interface ReceiveListener {
    void receive(byte[] msg);

    void err(Exception e);
}
