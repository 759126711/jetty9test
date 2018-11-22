package com.test.jetty.common.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

// 提供静态资源服务,可以在浏览器看到java.io.tmpdir对应目录下面的文件内容
public class OneServletContext {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setResourceBase(System.getProperty("java.io.tmpdir"));
        server.setHandler(context);

        // 增加一个 dump servlet，xx/dump/* 会访问到DumpServlet里面的doGet()方法
        context.addServlet(DumpServlet.class, "/dump/*");
        // 增加一个默认的servlet
        context.addServlet(DefaultServlet.class, "/");

        server.start();
        server.join();
    }
}