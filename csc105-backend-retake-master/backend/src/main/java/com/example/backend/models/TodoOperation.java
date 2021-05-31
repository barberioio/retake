package com.example.backend.models;

import com.example.backend.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class TodoOperation {
    Connection con;

    public ArrayList<TodoItem> getTodoList() throws SQLException{
        try{
            con = DBConnection.getConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM todo");
            ResultSet rs = pstm.executeQuery();
            ArrayList<TodoItem> todolist = new ArrayList<>();
            while (rs.next()){
                todolist.add(new TodoItem(rs));
            }
            return todolist;
        } finally {
            con.close();
        }
    }
    public TodoItem getTodoItem(int id) throws SQLException{
        try{
            con = DBConnection.getConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM todo WHERE id = ?");
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                return new TodoItem(rs);
            }
            return null;
        } finally {
            con.close();
        }
    }
    public void insertTodoItem(String content) throws SQLException{
        try{
            con = DBConnection.getConnection();
            PreparedStatement pstm = con.prepareStatement("INSERT INTO todo (content) VALUE (?)");
            pstm.setString(1, content);
            pstm.execute();
        } finally {
            con.close();
        }
    }
    public void updateTodoItem(int noteId,String content) throws SQLException {
        try {
            con = DBConnection.getConnection();
            PreparedStatement pstm = con.prepareStatement("UPDATE todo SET content = ? WHERE id = ?");
            pstm.setString(1, content);
            pstm.setInt(2, noteId);
            pstm.execute();
        } finally {
            con.close();
        }
    }
    public void checkFinish(int noteId) throws SQLException{
        try {

        } finally {
            con.close();
        }
    }
    public void deleteTodoItem(int noteId) throws SQLException {
        try {
            con = DBConnection.getConnection();
            PreparedStatement pstm = con.prepareStatement("DELETE FROM todo WHERE id = ?");
            pstm.setInt(1, noteId);
            pstm.execute();
        } finally {
            con.close();
        }
    }