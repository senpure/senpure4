package com.senpure.base.init;

import com.senpure.base.spring.SpringContextRefreshEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 罗中正 on 2017/6/7.
 */
@Component
public class IPGeter extends SpringContextRefreshEvent {
    private static String ip;

    public static String getIp() {
        return ip;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        checkIP();

    }

    public  void checkIP() {
        URL url;
        try {
            url = new URL("http://pv.sohu.com/cityjson?ie=utf-8");
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "utf-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String result = sb.toString();
            int i = result.indexOf("\"cip\": \"");
            int j = result.indexOf("\"", i + 8);

            ip = result.substring(i + 8, j);
            logger.debug("本机IP is {}", ip);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
