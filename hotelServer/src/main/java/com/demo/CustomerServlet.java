package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CustomerDAO;
import com.model.Customer;
import com.google.gson.*;
import com.util.DateUtil;
/**
 * Customer Servlet which handles the all request methods from customer route <strong>/customers</strong>, accepting 4 methods
 * GET, POST, PUT, DELETE
 * @author Phuong Nhu Truong Hoang, Dang Khoa Nguyen
 * @version 1.0.5
 * */
public class CustomerServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    private Customer getCustomerFromParams(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        String telephoneNumber = request.getParameter("telephoneNumber");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        LocalDate dateOfBirth = DateUtil.parse(request.getParameter("dob"));

        return new Customer(0, firstName, lastName, gender, telephoneNumber, address, email, dateOfBirth);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CustomerDAO customerDAO = new CustomerDAO();
        String responseJSON;

        List<Customer> roomList = customerDAO.getAll();
        responseJSON = gson.toJson(roomList);

        System.out.println("JSON: " + responseJSON);

        PrintWriter output = response.getWriter();
        output.println(responseJSON);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CustomerDAO customerDAO = new CustomerDAO();

        Customer newCustomer = getCustomerFromParams(request);
        System.out.println(newCustomer.toString());
        customerDAO.save(newCustomer);

        response.setStatus(200);
        PrintWriter res = response.getWriter();
        res.println(gson.toJson(newCustomer));
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CustomerDAO customerDAO = new CustomerDAO();
        Customer updateCustomer = getCustomerFromParams(request);
        updateCustomer.setId(Integer.parseInt(request.getParameter("id")));
        customerDAO.update(updateCustomer);

        response.setStatus(200);

        PrintWriter res = response.getWriter();
        res.println(gson.toJson(updateCustomer));
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CustomerDAO customerDAO = new CustomerDAO();
        Customer deleteCustomer = customerDAO.get(Integer.parseInt(request.getParameter("id")));
        customerDAO.delete(deleteCustomer);

        response.setStatus(200);

        PrintWriter res = response.getWriter();
        res.println("{\"message\": \"Deleted\"}");
    }
}