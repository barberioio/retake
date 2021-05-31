package com.example.backend.controllers;

import com.example.backend.models.ErrorResponse;
import com.example.backend.models.Middleware;
import com.example.backend.models.TodoItem;
import com.example.backend.models.TodoOperation;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "TodoServlet", value = "/TodoServlet")
@MultipartConfig
public class TodoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request, response);

        try {
            TodoOperation todoOperation = new TodoOperation();
            ArrayList<TodoItem> todoItems = todoOperation.getTodoList();
            out.print(gson.toJson(todoItems));
            response.setStatus(200);
        }catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request, response);
        try {
            String content = request.getParameter("content");
            if(content == null){
                ErrorResponse errorResponse = new ErrorResponse("field are require", 400);
                response.setStatus(400);
                out.print(gson(errorResponse));
                return;
            }
            TodoOperation todoOp = new TodoOperation();
            todoOp.insertTodoItem(content);
            ArrayList<TodoItem> todoItems = todoOp.getTodoList();
            out.print(gson.toJson(todoItems));
            response.setStatus(201);

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(400);
            out.print(gson.toJson(errorResponse));

        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request, response);

        try {
            String idTmp = request.getParameter("id");
            String content = request.getParameter("content");
            if(idTmp==null || content==null){
                ErrorResponse errorResponse = new ErrorResponse("field are require", 400);
                response.setStatus(400);
                out.print(gson(errorResponse));
                return;
            }
            int id = Integer.parseInt(idTmp);
            TodoOperation todoOp = new Todooperation();
            todoOp.updateTodoitem(id, content);
            ArrayList<TodoItem> todoItems = todoOp.getTodoList();
            out.print(gson.toJson(todoItems));
            response.setStatus(201);

        }catch (SQLException e){
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 404);
            response.setStatus(404);
            out.print(gson.toJson(errorResponse));
        }
        catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request, response);
        try {
            String id = request.getParater("id");
            if(id==null){
                ErrorResponse errorResponse = new ErrorResponse("field are require", 400);
                response.setStatus(400);
                out.print(gson(errorResponse));
                return;
            }
            TodoOperation todoOp = new TodoOperation();
            todoOp.deleteTodoItem(Integer.parseInt(id));
            ArrayList<TodoItem> todoItems = todoOp.getTodoList();
            out.print(gson.toJson(todoItems));
            response.setStatus(200);
        }catch (SQLException e){
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 404);
            response.setStatus(404);
            out.print(gson.toJson(errorResponse));
        }
        catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
        }
    }
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Middleware.setCORS(req, resp);
    }
}