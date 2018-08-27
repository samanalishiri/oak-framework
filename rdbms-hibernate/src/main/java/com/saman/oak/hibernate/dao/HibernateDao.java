package com.saman.oak.hibernate.dao;

import com.saman.oak.core.business.Specification;
import com.saman.oak.core.business.dao.CompleteDao;
import com.saman.oak.core.domain.BaseEntity;
import com.saman.oak.core.domain.Constant;
import com.saman.oak.core.naming.DaoConstant;
import com.saman.oak.hibernate.exception.HibernateBaseException;
import com.saman.oak.hibernate.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

@Repository(DaoConstant.HIBERNATE_DAO)
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HibernateDao<ID extends Serializable, E extends BaseEntity<ID>> implements CompleteDao<ID, E> {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Class<E> entity;

    @Override
    public void setEntityType(Class<E> c) {
        entity = c;
    }

    @Override
    public Optional<E> save(E e) {
        Session session = getSession();
        session.saveOrUpdate(e);
        session.refresh(e);

        return Optional.of(e);
    }

    @Override
    public Optional<E[]> find() {
        Session session = getSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entity);
        Root<E> root = criteriaQuery.from(entity);
        criteriaQuery.select(root);

        Query<E> query = session.createQuery(criteriaQuery);
        List<E> entities = query.list();

        return Optional.of(entities.toArray((E[]) Array.newInstance(entity, entities.size())));
    }

    @Override
    public Optional<E> find(ID key) {
        return Optional.of(getSession().get(entity, key));
    }

    @Override
    public Optional<E> findUniqueByField(String field, Object value) {
        return findUnique((root, query, cb) -> cb.equal(root.get(field), value));
    }

    @Override
    public Optional<E> findUnique(Specification<E> specification) {
        Session session = getSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entity);
        Root<E> root = criteriaQuery.from(entity);
        criteriaQuery.select(root).where(specification.toPredicate(root, criteriaQuery, criteriaBuilder));

        Query<E> query = session.createQuery(criteriaQuery);
        E e = query.uniqueResult();

        return Optional.of(e);
    }

    @Override
    public Optional<E[]> find(Specification<E> specification) {
        Session session = getSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entity);
        Root<E> root = criteriaQuery.from(entity);
        criteriaQuery.select(root).where(specification.toPredicate(root, criteriaQuery, criteriaBuilder));

        Query<E> query = session.createQuery(criteriaQuery);
        List<E> entities = query.list();

        return Optional.of(entities.toArray((E[]) Array.newInstance(entity, entities.size())));
    }

    @Override
    public Optional<E[]> findByField(String field, Object value) {
        return find((root, query, cb) -> cb.equal(root.get(field), value));
    }

    @Override
    public Optional<E[]> pagination(int first, int max, Specification specification) {
        Session session = getSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entity);
        Root<E> root = criteriaQuery.from(entity);
        criteriaQuery.select(root).where(specification.toPredicate(root, criteriaQuery, criteriaBuilder));

        Query<E> query = session.createQuery(criteriaQuery);
        query.setFirstResult(first);
        query.setMaxResults(max);
        List<E> entities = query.list();

        return Optional.of(entities.toArray((E[]) Array.newInstance(entity, entities.size())));
    }

    @Override
    public Optional<E[]> pagination(int first, int max, E e) {
        return null;
    }

    @Override
    public Optional<E[]> pagination(int first, int max) {
        Session session = getSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entity);
        Root<E> root = criteriaQuery.from(entity);
        criteriaQuery.select(root);

        Query<E> query = session.createQuery(criteriaQuery);
        query.setFirstResult(first);
        query.setMaxResults(max);
        List<E> entities = query.list();

        return Optional.of(entities.toArray((E[]) Array.newInstance(entity, entities.size())));
    }

    @Override
    public Optional<E> update(E e) {
        Session session = getSession();
        session.saveOrUpdate(e);
        session.refresh(e);

        return Optional.of(e);
    }

    @Override
    public void delete(ID key) throws ConstraintViolationException, HibernateBaseException {
        getSession().delete(entity);
    }

    @Override
    public void fastDelete(ID key) throws ConstraintViolationException, HibernateBaseException {
        Session session = getSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<E> criteriaDelete = criteriaBuilder.createCriteriaDelete(entity);
        Root<E> root = criteriaDelete.from(entity);
        criteriaDelete.where(criteriaBuilder.equal(root.get(Constant.ID), key));

        session.createQuery(criteriaDelete).executeUpdate();
    }

    private Session getSession() {
        return SessionUtil.openSession(sessionFactory);
    }

}
