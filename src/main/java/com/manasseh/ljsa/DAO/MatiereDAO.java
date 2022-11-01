package com.manasseh.ljsa.DAO;

import com.manasseh.ljsa.model.MatiereEntity;
import com.manasseh.ljsa.utils.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class MatiereDAO implements DAOInterface<MatiereEntity>{
    @Override
    public ObservableList<MatiereEntity> listAll() {
        Session session = HibernateUtil.getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<MatiereEntity> query = builder.createQuery(MatiereEntity.class);
        query.from(MatiereEntity.class);
        List<MatiereEntity> list = session.createQuery(query).getResultList();

        session.close();
        return FXCollections.observableArrayList(list);
    }


}
