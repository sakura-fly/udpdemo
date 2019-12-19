package com.gudp.client;

import com.gudp.event.ReceiveListener;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

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
    }

    public void send(byte[] buf) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, address, port);
        datagramSocket.send(datagramPacket);
        receive();
    }

    public void receive() {
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
        } catch (IOException e) {
            e.printStackTrace();
            if (receiveListener != null) {
                receiveListener.err(e);
            }
        }

    }

    public void close() {
        if (datagramSocket != null) {
            datagramSocket.close();
        }
    }

}
    /*


    public com.gudp.client.UdpClient() {
        try {
            byte[] buf = new byte[]{(byte) 0xBB, (byte) 0x00, (byte) 0x0E, (byte) 0xB5, (byte) 0x02, (byte) 0x10, (byte) 0x11, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x03, (byte) 0x66};


            byte[] receBuf = new byte[1024];
            DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
            datagramSocket.receive(recePacket);

            byte[] res = recePacket.getData();
            System.out.println(Arrays.toString(res));
//            String serverIp = recePacket.getAdress();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭socket
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }
}
*/