package com.manasseh.ljsa.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.manasseh.ljsa.model.Terminale;
import com.manasseh.ljsa.utils.DatabaseConnection;
import com.manasseh.ljsa.utils.PopUp;

import java.sql.*;

public class TerminaleDAO implements DAOInterface<Terminale>{
    PopUp popUp = new PopUp();
    @Override
    public ObservableList<Terminale> listAll() {
        ObservableList<Terminale> listTerminales = FXCollections.observableArrayList();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String query = "select * from terminale";
        listTerminales.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
             listTerminales.add(new Terminale(
                     resultSet.getInt("id"),
                     resultSet.getFloat("mlg"),
                     resultSet.getFloat("frs"),
                     resultSet.getFloat("anglais"),
                     resultSet.getFloat("histogeo"),
                     resultSet.getFloat("phylo"),
                     resultSet.getFloat("eps"),
                     resultSet.getFloat("math"),
                     resultSet.getFloat("spc"),
                     resultSet.getFloat("svt"),
                     resultSet.getFloat("ses"),
                     resultSet.getString("nmat"),
                     resultSet.getInt("trimestre"),
                     resultSet.getInt("annee_scolaire")
             ));
            }
        } catch (SQLException error){
            popUp.error("erreur","Erreur de connection au base de donnée. Veuillez contacter l'administrateur");
        }

        return listTerminales;
    }
}
