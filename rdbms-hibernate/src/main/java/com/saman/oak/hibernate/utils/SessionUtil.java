package com.saman.oak.hibernate.utils;

import com.saman.oak.hibernate.exception.HibernateBaseException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Objects;


public class SessionUtil {

    public static Session openSession(SessionFactory sessionFactory) throws HibernateBaseException {

        if (Objects.nonNull(sessionFactory)) {
            Session session = sessionFactory.openSession();
            return session;
        }

        throw new HibernateBaseException();
    }

    public static void closeSession(Session session) throws HibernateBaseException {

        if (Objects.nonNull(session)) {
            session.close();
            return;
        }

        throw new HibernateBaseException();
    }

    public static void shutdown(SessionFactory sessionFactory) throws HibernateBaseException {

        if (Objects.nonNull(sessionFactory)) {
            sessionFactory.close();
            return;
        }

        throw new HibernateBaseException();
    }
}
