## pom
```
<repositories>
    <repository>
        <id>mvn-repo</id>
        <url>https://raw.github.com/sakura-fly/mvn-repo/master</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
</repositories>
<dependency>
    <groupId>com.gao</groupId>
    <artifactId>udpDemo</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## 用法

```
        // 创建客户端
        UdpClient client = new UdpClient("192.168.2.253", 1030);
        // 设置超时，在连接之前，单位毫秒，默认5秒
        client.setTimeOut(10000);
        // 连接
        client.connect();
        // 发送信息，收到响应 
        byte[] = re = client.send(data)；
        // 关闭客户端
        client.close();
        
```