package com.test.jetty.common.server;

import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AllowSymLinkAliasChecker;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.lang.management.ManagementFactory;

/**
 * 以嵌入式方式发布一个war网站文件
 */
public class OneWebApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);

        // 设置 JMX
        MBeanContainer mbContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
        server.addBean(mbContainer);

        // 下面这个web应用是一个完整的web应用，在这个例子里设置/为根路径，web应用所有的配置都是有效的，
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        // File warFile = new File("D:\\softdev\\jetty9\\demo-base\\webapps\\test.war");
        File warFile = new File("D:\\soft_dev\\jetty_9412\\demo-base\\webapps\\test.war");
        webapp.setWar(warFile.getAbsolutePath());
        webapp.addAliasCheck(new AllowSymLinkAliasChecker());

        //将web应用设置到server里
        server.setHandler(webapp);

        server.start();
        server.join();
    }
}