package com.gudp.client;

import com.gudp.event.ReceiveListener;

import java.io.IOException;
import java.net.*;

public class UdpClient {
    private int port;
    private DatagramSocket datagramSocket;
    private InetAddress address;
    private ReceiveListener receiveListener;

    public UdpClient(String host, int port, ReceiveListener receiveListener) throws SocketException, UnknownHostException {
        this.port = port;
        this.receiveListener = receiveListener;
        datagramSocket = new DatagramSocket();
        address = InetAddress.getByName(host);
        datagramSocket.setSoTimeout(10000);
    }

    public byte[] send(byte[] buf) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, address, port);
        datagramSocket.send(datagramPacket);
        return receive();
    }

    private byte[] receive() {
        byte[] receBuf = new byte[1024];
        DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
        try {
            datagramSocket.receive(recePacket);
            byte[] res = recePacket.getData();
            byte[] out = new byte[recePacket.getLength()];
            System.arraycopy(res, 0, out, 0, recePacket.getLength());
            if (receiveListener != null) {
                receiveListener.receive(out);
            }
            return out;
        } catch (IOException e) {
            e.printStackTrace();
            if (receiveListener != null) {
                receiveListener.err(e);
            }
        }
        return null;
    }

    public void close() {
        if (datagramSocket != null) {
            datagramSocket.close();
        }
    }

}