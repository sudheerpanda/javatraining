package com.imaginea.training.wiki;

import com.sun.xml.internal.stream.writers.UTF8OutputStreamWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This class is used for call the wiki based on the word and get the description
 *
 * @author sudheerp
 */
public class WikiSearch {

    private static Logger LOGGER = LoggerFactory.getLogger(WikiSearch.class);

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        LOGGER.info("Enter the url-->");
        String url = sc.next();
        String response = HttpUtil.connect(url, "GET");
        //LOGGER.info(response);
        LOGGER.info("Please enter which data need to be capture inside file");
        String data = sc.next();
        if (data == null) {
            LOGGER.error("data cannot be empty");
            return;
        }
        JSONObject json = new JSONObject(response);
        Iterator<String> itr = json.keys();
        WikiSearch search = new WikiSearch();
        search.writeToFile(data, itr, json);

    }

    private void writeToFile(String data, Iterator<String> keys, JSONObject json) throws IOException {
        String content = "";
        while (keys.hasNext()) {
            String key = keys.next();
            if (data.equalsIgnoreCase(key)) {
                content = String.valueOf(json.get(data));
                LOGGER.info(content);
                FileOutputStream fileOutputStream = new FileOutputStream("wiki.txt");
                OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
                LOGGER.info("content length-{}", content.length());
                for (char ch : content.toCharArray()) {
                    writer.write(ch);
                }
                writer.close();
                fileOutputStream.close();
                break;
            } else if (json.get(key) != null && !json.get(key).toString().isEmpty()) {
                Class className = json.get(key).getClass();
                if ((className.getSimpleName()).equalsIgnoreCase("JSONArray")) {
                    JSONArray array = (JSONArray) json.get(key);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject j1 = (JSONObject) array.get(i);
                        writeToFile(data, j1.keys(), j1);
                    }
                } else if (className.getSimpleName().equalsIgnoreCase("JSONObject")) {
                    JSONObject jsonObject = json.getJSONObject(key);
                    writeToFile(data, jsonObject.keys(), jsonObject);
                }

            }
        }
    }
}
