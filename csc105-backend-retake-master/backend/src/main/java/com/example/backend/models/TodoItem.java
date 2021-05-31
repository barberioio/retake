package com.example.backend.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class TodoItem {
    private int id;
    private String content;
    private String description;
    private Date date;
    private boolean is_finish;

    public TodoItem(int id, String content, String description, Date date, boolean is_finish) {
        this.id = id;
        this.content = content;
        this.description = description;
        this.date = date;
        this.is_finish = is_finish;
    }

    public TodoItem(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.content = rs.getString("content");
        this.description = rs.getString("description");
        this.date = rs.getDate("date");
        this.is_finish = rs.getBoolean("is_finish");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isIs_finish() {
        return is_finish;
    }

    public void setIs_finish(boolean is_finish) {
        this.is_finish = is_finish;
    }
}
