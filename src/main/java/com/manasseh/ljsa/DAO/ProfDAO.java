package com.manasseh.ljsa.DAO;

import com.manasseh.ljsa.model.ProfsEntity;
import com.manasseh.ljsa.utils.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ProfDAO implements DAOInterface<ProfsEntity> {
    @Override
    public ObservableList<ProfsEntity> listAll() {
        Session session = HibernateUtil.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ProfsEntity> query = builder.createQuery(ProfsEntity.class);
        query.from(ProfsEntity.class);
        List<ProfsEntity> list = session.createQuery(query).getResultList();
        session.close();
        return FXCollections.observableArrayList(list);
    }

}
