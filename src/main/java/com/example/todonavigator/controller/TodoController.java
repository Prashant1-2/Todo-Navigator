package com.example.todonavigator.controller;

import com.example.todonavigator.model.TodoEntry;
import com.example.todonavigator.service.TodoScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoScannerService todoScannerService;
    @GetMapping
    public List<TodoEntry> getTodos(
            @RequestParam String path,
            @RequestParam(required = false, defaultValue = "TODO") String keywords) {

        List<String> keywordList = List.of(keywords.split(","));
        return todoScannerService.scanDirectory(path, keywordList);
    }
}