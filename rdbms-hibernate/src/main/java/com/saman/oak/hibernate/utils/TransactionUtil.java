package com.saman.oak.hibernate.utils;

import com.saman.oak.hibernate.exception.HibernateBaseException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Objects;

public class TransactionUtil {

    public static Transaction createTransaction(Session session) throws HibernateBaseException {

        if (Objects.nonNull(session)) {
            return session.getTransaction();
        }

        throw new HibernateBaseException();
    }

    public static void closeTransaction(Transaction transaction) throws HibernateBaseException {

        if (Objects.nonNull(transaction)) {

            if (transaction.getStatus().canRollback()) {
                transaction.rollback();
                return;
            } else {
                return;
            }
        }

        throw new HibernateBaseException();
    }

    public static void closeTransaction(Session session) throws HibernateBaseException {

        if (Objects.nonNull(session) && Objects.nonNull(session.getTransaction())) {
            if (session.getTransaction().getStatus().canRollback()) {
                session.getTransaction().rollback();
                return;
            } else {
                return;
            }
        }

        throw new HibernateBaseException();
    }
}
