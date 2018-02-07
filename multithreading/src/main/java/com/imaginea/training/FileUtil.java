package com.imaginea.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * This class represents how to spril the files
 * <p>
 * Example:A csv file can split into multiple files using some n lines as a chunk
 *
 * @author sudheer
 */
public class FileUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);


    private File getFile(String path) {
        LOGGER.info("Get the file from given path-{}", path);

        File file = Paths.get(path).toFile();
        LOGGER.info("Successfully got the file from the given path and the size is-{}", file.length());
        return file;
    }

    private void split(String path, int lineThreshold, String destination) throws IOException {
        RecursiveAction fileRecursiveAction = new FileRecursiveAction(Files.readAllLines(Paths.get(path)), destination, lineThreshold);
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        forkJoinPool.invoke(fileRecursiveAction);
    }

    public static void main(String[] args) {
        FileUtil fileUtil = new FileUtil();
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Please give the file path of csv ");
        String filePath = scanner.next();
        LOGGER.info("Given File path -{}", filePath);
        if (filePath == null) {
            LOGGER.error("File path cannot be null");
            return;
        }
        LOGGER.info("Please give the destination path to store the files");
        String destination = scanner.next();
        if (filePath == null) {
            LOGGER.error("Destination path cannot be null");
            return;
        }

        LOGGER.info("Please enter the expected  file length for new file");
        int fileLength= scanner.nextInt();
        try {
            fileUtil.split(filePath, fileLength, destination);
        } catch (IOException e) {
            LOGGER.error("Error while split the file", e);
        }
        LOGGER.info("Successfully split  all the files into -{}",destination);

    }
}
