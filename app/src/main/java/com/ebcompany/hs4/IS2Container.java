package com.ebcompany.hs4;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

import java.io.PrintStream;

/**
 * Created by ebicompany on 8/6/14.
 */
public class IS2Container implements Container {
    @Override
    public void handle(Request request, Response response) {
        try {
            PrintStream body = response.getPrintStream();
            long time = System.currentTimeMillis();

            response.setValue("Content-Type", "text/plain");
            response.setValue("Server", "HelloWorld/1.0 (Simple 4.0)");
            response.setDate("Date", time);
            response.setDate("Last-Modified", time);

            body.println("Hello World");
            body.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
