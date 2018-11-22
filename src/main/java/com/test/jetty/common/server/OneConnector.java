package com.test.jetty.common.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

/**
 * 有一个连接的Server
 */
public class OneConnector {
    public static void main(String[] args) throws Exception {
        Server server = new Server();

        // 创建一个HTTP的连接，配置监听主机，端口，以及超时时间
        ServerConnector http = new ServerConnector(server);
        // 注意这来设定的内容是什么，URL的域名部分或者叫authority的部分必须严格一致，
        // 比如这里使用ip，那么浏览器必须用http://192.168.1.101:8080才能访问到，http://127。0.0.1:8080也不行
        // 比如这里使用域名localhost,那么浏览器必须使用http://localhost:8080才能访问，其它的都不行。
        http.setHost("192.168.1.101");
        http.setPort(8080);
        http.setIdleTimeout(30000);

        // 将此连接添加到Server
        server.addConnector(http);

        // 设置一个处理器
        server.setHandler(new HelloHandler("getting", "body content"));

        // 启动Server
        server.start();
        server.join();
    }
}