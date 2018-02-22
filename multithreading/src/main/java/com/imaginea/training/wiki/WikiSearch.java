package com.imaginea.training.wiki;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * This class is used for call the wiki based on the word and get the description
 *
 * @author sudheerp
 */
public class WikiSearch {

    private static Logger LOGGER = LoggerFactory.getLogger(WikiSearch.class);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LOGGER.info("Enter the url-->");
        String url = sc.next();
        String response = HttpUtil.connect(url, "GET");
        LOGGER.info(response);
    }
}
