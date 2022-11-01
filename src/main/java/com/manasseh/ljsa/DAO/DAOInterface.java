package com.manasseh.ljsa.DAO;
import com.manasseh.ljsa.utils.HibernateUtil;
import com.manasseh.ljsa.utils.PopUp;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.hibernate.Session;
import org.hibernate.Transaction;;
import java.util.Optional;

public interface DAOInterface<T> {
    PopUp popUp = new PopUp();
    ObservableList<T> listAll();
    default void update(T data){
        Session session = HibernateUtil.getSession();

        Transaction transaction = session.beginTransaction();
        session.update(data);
        transaction.commit();
        popUp.success("Success","Mise à jour avec success");

        session.close();
    }
    default void insert(T data){
        Session session = HibernateUtil.getSession();

        Transaction transaction = session.beginTransaction();
        session.save(data);
        transaction.commit();
        popUp.success("Success","Ajout avec success");

        session.close();
    }

    default void delete(T data){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(data);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("confirmer la suppression ?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            transaction.commit();
            popUp.success("Supprimée","suppression avec success");
        }

        session.close();
    }

}
