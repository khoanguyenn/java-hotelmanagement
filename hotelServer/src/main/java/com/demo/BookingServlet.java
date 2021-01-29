    package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BookingDAO;
import com.model.Booking;
import com.google.gson.*;
import com.util.DateUtil;

public class BookingServlet extends HttpServlet {
    private Gson gson = new Gson();
    private Booking getBookingFromParams(HttpServletRequest request) {
        String roomNumber = request.getParameter("roomNumber");
        String customerId = request.getParameter("customerId");
        LocalDate checkinDate = DateUtil.parse(request.getParameter("checkin"));
        LocalDate checkoutDate = DateUtil.parse(request.getParameter("checkout"));
        String bookingMethod = request.getParameter("method");

        return new Booking(0, roomNumber, customerId, checkinDate, checkoutDate, bookingMethod);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String customerId = request.getParameter("customer_id");

        BookingDAO bookingDAO = new BookingDAO();
        String[] requestURI = request.getRequestURI().split("/");
        String responseJSON = null;

        if (customerId != null) {
            List<Booking> bookingByCustomerID = bookingDAO.getByCustomerID(customerId);
            responseJSON = gson.toJson(bookingByCustomerID);
        } 
        else if (customerId == null){
            List<Booking> roomList = bookingDAO.getAll();
            responseJSON = gson.toJson(roomList);
        }
        System.out.println("JSON: " + responseJSON);

        PrintWriter output = response.getWriter();
        output.println(responseJSON);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BookingDAO bookingDAO = new BookingDAO();

        Booking newBooking = getBookingFromParams(request);
        System.out.println(newBooking.toString());
        bookingDAO.save(newBooking);

        response.setStatus(200);
        PrintWriter res = response.getWriter();
        res.println("");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BookingDAO bookingDAO = new BookingDAO();
        Booking updateBooking = getBookingFromParams(request);
        updateBooking.setId(Integer.parseInt(request.getParameter("id")));
        bookingDAO.update(updateBooking);

        response.setStatus(200);

        PrintWriter res = response.getWriter();
        res.println("{\"message\": \"Put to database\"}");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BookingDAO bookingDAO = new BookingDAO();
        Booking deleteBooking = bookingDAO.get(Integer.parseInt(request.getParameter("id")));
        bookingDAO.delete(deleteBooking);

        response.setStatus(200);

        PrintWriter res = response.getWriter();
        res.println("{\"message\": \"Deleted\"}");
    }
}