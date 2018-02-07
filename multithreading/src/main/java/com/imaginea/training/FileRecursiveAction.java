package com.imaginea.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * This class  used to create multiple files using fork/join methodology
 *
 * @author sudheer
 */
public class FileRecursiveAction extends RecursiveAction {

    public static final Logger LOGGER = LoggerFactory.getLogger(FileRecursiveAction.class);

    private List<String> lines;
    private String destinationPath;
    private int lineThreshold;

    public FileRecursiveAction(List<String> lines, String path, int lineThreshold) {
        this.lines = lines;
        this.destinationPath = path;
        this.lineThreshold = lineThreshold;
    }

    @Override
    protected void compute() {
        addResultsFromTasks(invokeAllFiles());

    }


    private List<FileRecursiveAction> invokeAllFiles() {
        int fileLength = lines.size();
        FileRecursiveAction task = null;
        int initialSize=0;
        int maxSize=lineThreshold;
        int fileIndex = 1;
        LOGGER.info("Split the tasks into multiple chunks");
        List<FileRecursiveAction> tasks = new ArrayList<FileRecursiveAction>();
        for (int i = 0; i < fileLength / lineThreshold; i++) {
            if (lines.size() < lineThreshold) {
                task = new FileRecursiveAction(lines.subList(initialSize,maxSize), destinationPath, lineThreshold);
                task.fork();
                tasks.add(task);
            } else {
                System.out.println("Splitting workLoad : " + lineThreshold);
                fileIndex++;
                try {
                    Files.write(Paths.get(destinationPath + "bookcsv_" +fileIndex + ".txt"), lines.subList(initialSize,maxSize));
                } catch (IOException e) {
                    LOGGER.error("Error while create a file", e);
                }
                initialSize=maxSize;
                maxSize=initialSize+lineThreshold;
            }
        }
        LOGGER.info("Successfully split and forked the files");
        return tasks;
    }

    private void addResultsFromTasks(List<FileRecursiveAction> tasks) {
        LOGGER.info("Join all the tasks");
        for (FileRecursiveAction task : tasks) {
            task.join();
        }
        LOGGER.info("Success fully joined and created the split files");
    }
}
