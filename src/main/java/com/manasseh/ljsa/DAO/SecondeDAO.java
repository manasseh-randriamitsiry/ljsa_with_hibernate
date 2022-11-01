package com.manasseh.ljsa.DAO;

import com.manasseh.ljsa.model.SecondeEntity;
import com.manasseh.ljsa.utils.HibernateUtil;
import com.manasseh.ljsa.utils.PopUp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class SecondeDAO implements DAOInterface<SecondeEntity>{
    PopUp popUp = new PopUp();
    @Override
    public ObservableList<SecondeEntity> listAll() {
        Session session = HibernateUtil.getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SecondeEntity> query = builder.createQuery(SecondeEntity.class);
        query.from(SecondeEntity.class);
        List<SecondeEntity> list = session.createQuery(query).getResultList();

        session.close();
        return FXCollections.observableArrayList(list);
    }

}
