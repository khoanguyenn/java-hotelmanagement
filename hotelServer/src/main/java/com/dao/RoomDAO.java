package com.dao;

import com.model.Room;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.function.Consumer;

public class RoomDAO {
    private Session session = HibernateUtil.getSessionFactory().openSession();
    public List<Room> getAll() {
        return session.createQuery("from Room").getResultList();
    }

    public List<Object[]> getCount() {
        return session
                .createQuery("SELECT r.type, COUNT(r.type) from Room r GROUP BY r.type")
                .getResultList();
    }

    public List<Room> getAllAvailable() {
        return session.createQuery("FROM Room r WHERE r.status = \'Available\'").getResultList();
    }

    public Room get(String number) {
        return session.get(Room.class, number);
    }

    public void save(Room room) {
        executeInTransaction(session -> session.save(room));
    }

    public void update(Room room) {
        executeInTransaction(session -> session.update(room));
    }

    public void delete(Room room) {
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
