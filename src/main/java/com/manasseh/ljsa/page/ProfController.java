package com.manasseh.ljsa.page;

import animatefx.animation.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import com.manasseh.ljsa.DAO.ProfDAO;
import com.manasseh.ljsa.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import com.manasseh.ljsa.utils.PopUp;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProfController implements Initializable{
    public Pane profsPane;
    public Label professeur;
    public Button btn_ajout_prof;
    public Button btn_action;
    public TableView<ProfsEntity> table_prof;
    public Pane action_pane;
    public TextField numero_matricule_input;
    public TextField nom_prof_input;
    public TextField prenom_prof_input;
    public TableColumn<ProfsEntity,String> n_mat_column;
    public TableColumn<ProfsEntity,String> nom_column;
    public TableColumn<ProfsEntity,String> prenom_column;
    public TableColumn<ProfsEntity,String> date_nais_column;
    public TableColumn<ProfsEntity,ProfsEntity> actionColumn;
    public DatePicker date_nais_picker;
    public Label id;
    public TextField recherche_input;
    ObservableList<ProfsEntity> profList = FXCollections.observableArrayList();
    ProfDAO dao = new ProfDAO();
    ProfsEntity prof = new ProfsEntity();
    PopUp popUp = new PopUp();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        check();
        new FadeOutRight(action_pane).play();
        refreshTable();

        n_mat_column.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getnMatricule()));
        nom_column.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getNomProf()));
        prenom_column.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getPrenomProf()));
        date_nais_column.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDateNais().toString()));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>(""));

        // creation de bouton ajout et suppression
        Callback<TableColumn<ProfsEntity, ProfsEntity>, TableCell<ProfsEntity, ProfsEntity>> cellFactory=  (TableColumn<ProfsEntity, ProfsEntity> param) -> new TableCell<ProfsEntity,ProfsEntity>() {
            @Override
            public void updateItem(ProfsEntity item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    final Button editBtn = new Button("Editer");
                    final Button dltBtn = new Button("Supprimer");
                    dltBtn.setStyle("-fx-background-color:#FF6666");
                    editBtn.setOnAction(event -> {
                        action_pane.setVisible(true);
                        try {
                            prof = getTableView().getItems().get(getIndex());
                            professeur.setText("Professeur: edition");
                            btn_action.setText("Mettre à jour");
                            ProfController.this.setText(
                                    prof.getId(),
                                    prof.getnMatricule(),
                                    prof.getNomProf(),
                                    prof.getPrenomProf(),
                                    prof.getDateNais());
                            new FadeInRight(action_pane).play();
                        } catch (NullPointerException e) {
                            popUp.error("Information", "Selectionner un champ avant de cliquer sur editer. Merci");
                        }
                    });
                    dltBtn.setOnAction(event -> {
                        prof = getTableView().getItems().get(getIndex());
                        dao.delete(prof);
                        action_pane.setVisible(false);
                        refreshTable();
                    });
                    HBox hb = new HBox();
                    hb.setSpacing(2);
                    hb.setStyle("-fx-alignment:center");
                    hb.getChildren().addAll(editBtn, dltBtn);
                    setGraphic(hb);
                    setText(null);
                }
            }
        };
        actionColumn.setCellFactory(cellFactory);

        // bouton action toggle
        btn_action.setOnAction(event -> {
            if (btn_action.getText().equals("Ajouter")){
                try {
                    prof = new ProfsEntity();
                    prof.setNomProf(nom_prof_input.getText());
                    prof.setPrenomProf(prenom_prof_input.getText());
                    prof.setnMatricule(numero_matricule_input.getText());
                    prof.setDateNais(date_nais_picker.getValue());

                    dao.insert(prof);
                    new FadeOutRight(action_pane).play();
                    refreshTable();
                    clearInputs();
                } catch (Exception e) {
                    popUp.error("erreur","Erreur, essaye encore une fois");
                    e.printStackTrace();
                }
            } else if (btn_action.getText().equals("Mettre à jour")){
                prof = new ProfsEntity();
                prof.setId(Integer.parseInt(id.getText()));
                prof.setNomProf(nom_prof_input.getText());
                prof.setPrenomProf(prenom_prof_input.getText());
                prof.setnMatricule(numero_matricule_input.getText());
                prof.setDateNais(date_nais_picker.getValue());

                dao.update(prof);
                new FadeOutRight(action_pane).play();
                refreshTable();
                clearInputs();
            }
        });
    }
    public void activerRecherche(){
        FilteredList<ProfsEntity> filteredList = new FilteredList<>(profList,prof -> true);
        recherche_input.textProperty().addListener((Observable,oldValue,newValue)-> filteredList.setPredicate(prof -> prof.getNomProf().toUpperCase().contains(newValue.toUpperCase())
                    || prof.getPrenomProf().toLowerCase().contains(newValue.toLowerCase())
                    || prof.getnMatricule().toUpperCase().contains(newValue.toUpperCase())
                    || prof.getDateNais().toString().contains(newValue)));
        SortedList<ProfsEntity> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(table_prof.comparatorProperty());
        table_prof.setItems(sortedData);
    }
    public void refreshTable() {
        profList.clear();
        profList.setAll(dao.listAll());
        activerRecherche();
    }
    public void clearInputs(){
        numero_matricule_input.setText("");
        nom_prof_input.setText("");
        prenom_prof_input.setText("");
    }
    public void ajoutProfesseur(){
        action_pane.setVisible(true);
        clearInputs();
        professeur.setText("Professeur: ajout");
        btn_action.setText("Ajouter");
        new FadeInRightBig(action_pane).play();
    }
    public void check(){
        if (!numero_matricule_input.getText().isEmpty()
                && !nom_prof_input.getText().isEmpty()
                && !prenom_prof_input.getText().isEmpty()
                && date_nais_picker.getValue() !=null){
            btn_action.setVisible(true);
        } else if (numero_matricule_input.getText().isEmpty()
                || nom_prof_input.getText().isEmpty()
                || prenom_prof_input.getText().isEmpty()
                || date_nais_picker.getValue() !=null){
            btn_action.setVisible(false);
        }
    }
    public void setText(Integer id,String n_mat,String nom,String prenom, LocalDate date_nais){
        numero_matricule_input.setText(n_mat);
        nom_prof_input.setText(nom);
        prenom_prof_input.setText(prenom);
        date_nais_picker.setValue(date_nais);
        this.id.setText(String.valueOf(id));
    }
    public void zoomText() {
        profsPane.setStyle("-fx-font-size:14px");
    }

}