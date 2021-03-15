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
/**
 * Booking Servlet which handles the all request methods from booking route <strong>/bookings</strong>, accepting 4 methods
 * GET, POST, PUT, DELETE
 * @author Phuong Nhu Truong Hoang, Dang Khoa Nguyen
 * @version 1.0.5
 * */
public class BookingServlet extends HttpServlet {
    private Gson gson = new Gson();
    private Booking getBookingFromParams(HttpServletRequest request) {
        String roomNumber = request.getParameter("roomNumber");
        String customerId = request.getParameter("customerId");
        LocalDate checkinDate = DateUtil.parse(request.getParameter("checkin"));
        LocalDate checkoutDate = DateUtil.parse(request.getParameter("checkout"));
        String bookingMethod = request.getParameter("method");

        int bookingID = request.getAttribute("id") == null ?
                0 : (int) request.getAttribute("id");

        return new Booking(bookingID, roomNumber, customerId, checkinDate, checkoutDate, bookingMethod);
    }
    /**
     * @param request HTTP request
     * @param response HTTP response
     * @param bookingObject booking object, either List or Booking type
     * @return boolean to determine whether it's successfully implement or not
     * */
    private boolean sendResponse(HttpServletRequest request, HttpServletResponse response, Object bookingObject) throws IOException {
        //Send & log the response
        PrintWriter output = response.getWriter();
        String responseJSON = gson.toJson(bookingObject);
        if (responseJSON != null) {
            System.out.println(request.getMethod() + " " + responseJSON);
            response.setStatus(200);
            output.println(responseJSON);
            return true;
        } else {
            response.setStatus(500);
            output.println("Error occurred!");
            return false;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String customerID = request.getParameter("customer_id");

        BookingDAO bookingDAO = new BookingDAO();

        List<Booking> roomList = customerID == null ?
                bookingDAO.getAll() :
                bookingDAO.getByCustomerID(customerID);

        String responseStatus = sendResponse(request, response, roomList) ?
                "Successfully send !" :
                "Something is wrong!";

        System.out.println(responseStatus);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BookingDAO bookingDAO = new BookingDAO();
        String responseStatus = null;

        Booking newBooking = getBookingFromParams(request);

        responseStatus = bookingDAO.save(newBooking) ?
                "Successfully save!" :
                "Cannot save";

        responseStatus += sendResponse(request, response, newBooking) ?
                " Successfully send!" :
                " Something is wrong!";

        System.out.println(responseStatus);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BookingDAO bookingDAO = new BookingDAO();
        String responseStatus = null;
        Booking updateBooking = getBookingFromParams(request);

        responseStatus = bookingDAO.update(updateBooking) ?
                "Successfully save!" :
                "Cannot save";

        responseStatus += sendResponse(request, response, updateBooking) ?
                " Successfully send !" :
                " Something is wrong!";

        System.out.println(responseStatus);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BookingDAO bookingDAO = new BookingDAO();
        String responseStatus = null;

        Booking deleteBooking = bookingDAO.get(Integer.parseInt(request.getParameter("id")));

        responseStatus = bookingDAO.delete(deleteBooking) ?
                "Successfully save!" :
                "Cannot save";

        responseStatus += sendResponse(request, response, deleteBooking) ?
                " Successfully send !" :
                " Something is wrong!";

        System.out.println(responseStatus);
    }
}