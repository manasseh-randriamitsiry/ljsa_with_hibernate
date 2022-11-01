package com.manasseh.ljsa.DAO;

import com.manasseh.ljsa.model.PremiereEntity;
import com.manasseh.ljsa.utils.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class PremiereDAO implements DAOInterface<PremiereEntity>{
    @Override
    public ObservableList<PremiereEntity> listAll() {
        Session session = HibernateUtil.getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PremiereEntity> query = builder.createQuery(PremiereEntity.class);
        query.from(PremiereEntity.class);
        List<PremiereEntity> list = session.createQuery(query).getResultList();

        session.close();
        return FXCollections.observableArrayList(list);
    }

}
