package com.example.todonavigator.model;

public class TodoEntry {
    private String fileName;
    private int lineNumber;
    private String comment;

    public TodoEntry(String fileName, int lineNumber, String comment) {
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.comment = comment;
    }

    // Getters and Setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
