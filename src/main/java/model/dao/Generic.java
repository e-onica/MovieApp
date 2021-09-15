package model.dao;

import model.dao.Configuration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class Generic<T> {
    public void add(T object) {
        try (Session session = Configuration.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T searchByName(String name, T object) {
        try (Session session = Configuration.getSessionFactory().openSession()) {

            Query query = session.createQuery(
                    "FROM " + object.getClass().getName() + " where name=" + "\'" + name + "\'");

            T result = (T) query.getSingleResult();

            return result;

        } catch (Exception e) {
            System.out.println("Sorry we couldn't find anything \n");
        }
        return null;
    }


}
