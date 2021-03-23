package com.demo;

import com.dao.RoomDAO;
import com.model.Room;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
/**
 * Home Servlet, which is a developing feature for web application in the future
 * @author Nhu Truong Hoang Phuong, Khoa Nguyen Dang
 * @version 1.0.5
 * */
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoomDAO roomDAO = new RoomDAO();
        List<Room> roomList = roomDAO.getAll();

        request.setAttribute("roomList", roomList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("room-list.jsp");
        dispatcher.forward(request, response);
    }
}
