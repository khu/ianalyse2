package com.ianalyse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class IanalyseServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        Connector con = new SelectChannelConnector();
        con.setPort(8000);
        server.addConnector(con);
        WebAppContext wac = new WebAppContext();
        if ("production".equals(System.getProperty("env"))) {
            wac.setResourceBase("./ianalyse2");
        } else {
            System.setProperty("url", "http://deadlock.netbeans.org/hudson/api/xml");
            if (new File("./app/webapp/WEB-INF/classes").exists()) {
                FileUtils.cleanDirectory(new File("./app/webapp/WEB-INF/classes"));
            }

            FileUtils.copyDirectory(
                    new File("./out/production/app"),
                    new File("./app/webapp/WEB-INF/classes")
            );
            wac.setResourceBase("./app/webapp");
        }
        wac.setDescriptor("WEB-INF/web.xml");
        wac.setContextPath("/ianalyse2");
        server.setHandler(wac);
        server.start();
    }
}
