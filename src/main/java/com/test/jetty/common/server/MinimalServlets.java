package com.test.jetty.common.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MinimalServlets {
    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);
        //ServletHandler通过一个servlet创建了一个非常简单的context处理器
        //这个处理器需要在Server上注册
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        //传入能匹配到这个servlet的路径
        //提示：这是一个未经处理的servlet，没有通过web.xml或@WebServlet注解或其他方式配置
        handler.addServletWithMapping(HelloServlet.class, "/uri");

        server.start();
        server.join();
    }

    // 同一个url请求，HelloServlet的doGet()方法只会执行一次
    @SuppressWarnings("serial")
    public static class HelloServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);

            System.out.println("===haha===");

            response.getWriter().println("<h1>Hello from HelloServlet</h1>");
            System.out.println("===准备睡觉===");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}