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

    public FileRecursiveAction(List<String> lines, String destinationPath, int lineThreshold) {
        this.lines = lines;
        this.destinationPath = destinationPath;
        this.lineThreshold = lineThreshold;
    }

    @Override
    protected void compute() {
        invokeAllFiles();

    }

    /**
     * Split the files and fork them up
     * @return List<FileRecursiveAction>
     */
    private List<FileRecursiveAction> invokeAllFiles() {
        int fileLength = lines.size();
        int initialSize = 0;
        int maxSize = lineThreshold;
        int fileIndex = 1;
        LOGGER.info("Split the tasks into multiple chunks");
        List<FileRecursiveAction> tasks = new ArrayList<FileRecursiveAction>();
        for (int i = 0; i < fileLength / lineThreshold; i++) {
                fileIndex++;
                try {
                    Files.write(Paths.get(destinationPath + "bookcsv_" + fileIndex + ".txt"), lines.subList(initialSize, maxSize));
                } catch (IOException e) {
                    LOGGER.error("Error while create a file", e);
                }
                initialSize = maxSize;
                maxSize = initialSize + lineThreshold;
        }
        LOGGER.info("Successfully split and forked the files");
        return tasks;
    }

}
