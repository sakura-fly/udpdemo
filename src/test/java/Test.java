import com.gudp.client.UdpClient;
import com.gudp.event.ReceiveListener;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        byte[] data = new byte[]{(byte) 0xBB, (byte) 0x00, (byte) 0x0E, (byte) 0xB5, (byte) 0x02, (byte) 0x10, (byte) 0x11, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x03, (byte) 0x66};

        UdpClient client = new UdpClient("192.168.2.253", 1030, new ReceiveListener() {
            @Override
            public void receive(byte[] msg) {
                for (byte b : msg) {
                    System.out.println(String.format("0x%02x", b));
                }
            }

            @Override
            public void err(Exception e) {
                System.out.println("err");
            }
        });
        client.send(data);


    }
}
