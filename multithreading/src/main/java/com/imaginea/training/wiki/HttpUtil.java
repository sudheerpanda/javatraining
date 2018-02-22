package com.imaginea.training.wiki;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class will connect to any url and get the response
 * and sends back to user
 * @author sudheerp
 */
public class HttpUtil {

    private static Logger LOGGER= LoggerFactory.getLogger(HttpUtil.class);

    /**
     * Connect to Http url
     * @param url
     * @param requestMethod
     * @return
     */
    public static String connect(String url,String requestMethod) {

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod(requestMethod);
            int responseCode = con.getResponseCode();
            LOGGER.info("\nSending '"+requestMethod+"' request to URL : " + url);
            LOGGER.info("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();

        } catch (MalformedURLException e) {
            LOGGER.error("Error while validating the url", e);
        } catch (IOException e) {
            LOGGER.error("Error while open the url -{}", url, e);
        }
        return null;
    }
}
