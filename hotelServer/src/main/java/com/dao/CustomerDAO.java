package com.dao;

import com.model.Customer;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.function.Consumer;

public class CustomerDAO {
    private Session session = HibernateUtil.getSessionFactory().openSession();
    public List<Customer> getAll() {
        return session.createQuery("from Customer").getResultList();
    }

    public Customer get(int number) {
        return session.get(Customer.class, number);
    }

    public void save(Customer room) {
        executeInTransaction(session -> session.save(room));
    }

    public void update(Customer room) {
        executeInTransaction(session -> session.update(room));
    }

    public void delete(Customer room) {
        executeInTransaction(session -> session.delete(room));
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
