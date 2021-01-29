package com.dao;

import com.model.Booking;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
;
import java.util.List;
import java.util.function.Consumer;

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

    public void save(Booking booking) {
        executeInTransaction(session -> session.save(booking));
    }

    public void update(Booking booking) {
        executeInTransaction(session -> session.update(booking));
    }

    public void delete(Booking booking) {
        executeInTransaction(session -> session.delete(booking));
    }

    public void executeInTransaction(Consumer<Session> action) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            action.accept(session);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            exception.printStackTrace();
        } finally {
            session.close();
        }
    }
}
