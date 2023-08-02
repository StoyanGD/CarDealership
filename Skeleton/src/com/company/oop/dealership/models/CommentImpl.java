package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.utils.ValidationHelpers;

import static java.lang.String.format;

public class CommentImpl implements Comment {

    public static final int CONTENT_LEN_MIN = 3;
    public static final int CONTENT_LEN_MAX = 200;
    private static final String CONTENT_LEN_ERR = format(
            "Content must be between %d and %d characters long!",
            CONTENT_LEN_MIN,
            CONTENT_LEN_MAX);
    private String author;
    private String content;

    public CommentImpl(String content, String author) {
        setAuthor(author);
        setContent(content);
    }

    @Override
    public String getContent() {
        return content;
    }

    private void setContent(String content){
        validateContent(content);
        this.content=content;

    }

    private static void validateContent(String content){
        ValidationHelpers.validateIntRange(content.length(),CONTENT_LEN_MIN,CONTENT_LEN_MAX,CONTENT_LEN_ERR);
    }

    private void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return String.format("----------\n" +
                "%s\n" +
                "User: %s\n" +
                "----------\n",content,author);
    }
}