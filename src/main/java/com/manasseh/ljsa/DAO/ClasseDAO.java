package com.manasseh.ljsa.DAO;

import com.manasseh.ljsa.model.ClasseEntity;
import com.manasseh.ljsa.utils.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.*;
import java.util.List;

public class ClasseDAO implements DAOInterface<ClasseEntity>{
    @Override
    public ObservableList<ClasseEntity> listAll() {
        Session session = HibernateUtil.getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ClasseEntity> query = builder.createQuery(ClasseEntity.class);
        query.from(ClasseEntity.class);
        List<ClasseEntity> list = session.createQuery(query).getResultList();

        session.close();
        return FXCollections.observableArrayList(list);
    }

    public Integer getClasse(String classe) throws SQLException {
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(ClasseEntity.class);
        ClasseEntity classeEntity = (ClasseEntity) criteria.add(Restrictions.eq("classe", classe)).uniqueResult();
        session.close();
        return classeEntity.getCoefficientTotal();
    }
    public ObservableList listClasse(){
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("select classe from ClasseEntity");
        List list = query.getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }

}
