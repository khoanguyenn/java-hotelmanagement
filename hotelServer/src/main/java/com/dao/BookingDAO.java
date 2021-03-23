package com.dao;

import com.model.Booking;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import java.util.function.Consumer;
/**
 * Booking data accessing object, utilizes to communicate database to retrieve booking's information
 * @author Nhu Truong Hoang Phuong
 * @version 1.0.1
 * */
public class BookingDAO {
    private Session session = HibernateUtil.getSessionFactory().openSession();
    public List<Booking> getAll() {
        return session.createQuery("from Booking").getResultList();
    }

    public Booking get(int id) {
        return session.get(Booking.class, id);
    }

    public List<Booking> getByCustomerID(String id) {
        System.out.println("Customer ID: " + id);
        Query query = session.createQuery("from Booking B WHERE B.customerID = :customer_id");
        query.setParameter("customer_id", id);
        return query.getResultList();
    }

    public boolean save(Booking booking) {
        return executeInTransaction(session -> session.save(booking));
    }

    public boolean update(Booking booking) {
        return executeInTransaction(session -> session.update(booking));
    }

    public boolean delete(Booking booking) {
        return executeInTransaction(session -> session.delete(booking));
    }

    public boolean executeInTransaction(Consumer<Session> action) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            action.accept(session);
            transaction.commit();
            return true;
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}
