package com.example.todonavigator.service;

import com.example.todonavigator.model.TodoEntry;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoScannerService {

    private static final String[] SUPPORTED_EXTENSIONS = {".java", ".js", ".ts", ".py", ".txt"};
    private static final List<String> IGNORED_DIRS = List.of(".git", "node_modules", "build", "target");

    public List<TodoEntry> scanDirectory(String rootDir, List<String> keywords) {
        List<TodoEntry> todos = new ArrayList<>();
        try {
            Files.walk(Paths.get(rootDir))
                .filter(path -> !isInIgnoredFolder(path))
                .filter(Files::isRegularFile)
                .filter(this::isSupportedFile)
                .forEach(filePath -> todos.addAll(scanFile(filePath, keywords)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return todos;
    }

    private boolean isInIgnoredFolder(Path path) {
        for (Path part : path) {
            if (IGNORED_DIRS.contains(part.toString())) {
                return true;
            }
        }
        return false;
    }

    private boolean isSupportedFile(Path path) {
        for (String ext : SUPPORTED_EXTENSIONS) {
            if (path.toString().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    private List<TodoEntry> scanFile(Path filePath, List<String> keywords) {
        List<TodoEntry> todos = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                for (String keyword : keywords) {
                    if (line.contains(keyword)) {
                        todos.add(new TodoEntry(
                            filePath.getFileName().toString(),
                            i + 1,
                            line.trim()
                        ));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return todos;
    }

}
