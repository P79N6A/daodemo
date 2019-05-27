package com.djs.daodemo.nettyBoot;

import com.djs.daodemo.DaodemoApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class commonTestApplication {
    public static void runNettyServer()
    {
        //Thread thread
        Thread thread = new Thread(new NettyServer());

        thread.start();
        try
        {
            thread.join();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(commonTestApplication.class, args);
        try {
            runNettyServer();
            //Thread.sleep(1000);
            //runNettyClient();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
