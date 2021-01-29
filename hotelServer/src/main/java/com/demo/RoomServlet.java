package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RoomDAO;
import com.model.Room;
import com.google.gson.*;

public class RoomServlet extends HttpServlet {
    private Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RoomDAO roomDAO = new RoomDAO();
        boolean getCount = Boolean.parseBoolean(request.getParameter("get_count"));
        boolean getAvailable = Boolean.parseBoolean(request.getParameter("get_available"));
        String responseJSON = null;

        if (!getCount && !getAvailable) {
            List<Room> roomList = roomDAO.getAll();
            responseJSON = gson.toJson(roomList);
        }

        if (getCount) {
            HashMap<Object, Object> listOfCount = new HashMap<Object, Object>();
            List<Object[]> countList = roomDAO.getCount();
            countList.forEach(item -> listOfCount.put(item[0], item[1]));
            responseJSON = gson.toJson(listOfCount);
        }

        if (getAvailable) {
            List<Room> availableRoom = roomDAO.getAllAvailable();
            responseJSON = gson.toJson(availableRoom);
        }
        PrintWriter output = response.getWriter();
        output.println(responseJSON);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String roomNumber = request.getParameter("number");
        String roomType = request.getParameter("type");
        String roomStatus = request.getParameter("status");

        RoomDAO roomDAO = new RoomDAO();
        Room newRoom = new Room(roomNumber, roomType, roomStatus);
        roomDAO.save(newRoom);

        System.out.println("Add new room: " + newRoom.toString());


        response.setStatus(200);
        PrintWriter res = response.getWriter();
        res.println("{\"message\": \"received\"}");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String roomNumber = request.getParameter("number");
        String roomType = request.getParameter("type");
        String roomStatus = request.getParameter("status");

        RoomDAO roomDAO = new RoomDAO();
        Room updateRoom = new Room(roomNumber, roomType, roomStatus);
        System.out.println(updateRoom.toString());
        roomDAO.update(updateRoom);

        response.setStatus(200);

        PrintWriter res = response.getWriter();
        res.println("{\"message\": \"Put to database\"}");
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse response) throws IOException {
        String roomNumber = req.getParameter("number");

        RoomDAO roomDAO = new RoomDAO();
        //Room deleteRoom = roomDAO.get(roomNumber);
        //System.out.println(deleteRoom.toString());
        //roomDAO.delete(deleteRoom);

        response.setStatus(200);

        PrintWriter res = response.getWriter();
        res.println("{\"message\": \"Deleted\"}");
    }

}