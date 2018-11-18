package com.test.jetty.common.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;

// 输入http://localhost:8080/helloworld 可以访问
public class HelloWorld extends AbstractHandler {
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // 声明response的编码和文件类型
        response.setContentType("text/html; charset=utf-8");

        // 声明返回状态码
        response.setStatus(HttpServletResponse.SC_OK);

        // 请求的返回值
        response.getWriter().println("<h1>Hello World</h1>");
        System.out.println(target);
        System.out.println("====");
        // 通知Jettyrequest使用此处理器处理请求；true表示不会再传递给下一个Handler处理器处理，false
        baseRequest.setHandled(false);
    }

    public static void main(String[] args) throws Exception {
        //创建一个应用服务监听8080端口
        Server server = new Server(8080);
       // server.setHandler(new HelloWorld());
        HandlerList list = new HandlerList();
        // 注意请求处理的顺序受到XxxHandler的加入顺序的影响，排在最前面的优先处理，如果
        // 如果某个handler里面设置了baseRequest.setHandled(false);那么后面的handler就不会被执行到。
        list.setHandlers(new Handler[]{ new HelloHandler(),new HelloWorld(), new DefaultHandler()});

//        GzipHandler gzip = new GzipHandler();
//        gzip.setHandler(list);
        server.setHandler(list);
        //启动应用服务并等待请求
        server.start();
        server.join();
    }
}