package com.manasseh.ljsa.DAO;

import com.manasseh.ljsa.model.EtudiantsEntity;
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

public class EtudiantDAO implements DAOInterface<EtudiantsEntity> {
    @Override
    public ObservableList<EtudiantsEntity> listAll(){
        Session session = HibernateUtil.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<EtudiantsEntity> query = builder.createQuery(EtudiantsEntity.class);
        query.from(EtudiantsEntity.class);
        List<EtudiantsEntity> list = session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }
    public Long nombreEtudiant() throws SQLException {
        Long nombre;
        Session session = HibernateUtil.getSession();
        Query<Long> query = session.createQuery("select count(id) from EtudiantsEntity");
        nombre = query.getSingleResult();
        session.close();
        return nombre;
    }

    public String getClasse(String n_matricule) throws SQLException {
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(EtudiantsEntity.class);
        EtudiantsEntity etudiantsEntity = (EtudiantsEntity) criteria.add(Restrictions.eq("nMatricule", n_matricule)).uniqueResult();
        session.close();
        return etudiantsEntity.getClasse();
    }

    public EtudiantsEntity getByNmat(String n_matricule){
        Session session = HibernateUtil.getSession();
        Query<EtudiantsEntity> query = session.createQuery("select nMatricule from EtudiantsEntity where nMatricule =:nmat");
        query.setString("nmat",n_matricule);
        EtudiantsEntity etudiant = query.uniqueResult();
        session.close();
        return etudiant;
    }

    public ObservableList listEtudiant(){
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("select nMatricule from EtudiantsEntity ");
        List list = query.getResultList();
        return FXCollections.observableArrayList(list);
    }

}
