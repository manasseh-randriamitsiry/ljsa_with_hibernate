package com.manasseh.ljsa.page;

import animatefx.animation.FadeInRight;
import animatefx.animation.FadeOutRight;
import com.manasseh.ljsa.DAO.ClasseDAO;
import com.manasseh.ljsa.model.ClasseEntity;
import com.manasseh.ljsa.utils.PopUp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class ClasseController implements Initializable {
    public TableColumn<ClasseEntity,String> classe_column;
    public TableColumn<ClasseEntity, String> coefficient_column;
    public TableColumn<ClasseEntity, ClasseEntity> action_column;
    public TableView<ClasseEntity> table_coefficient;
    public Pane action_pane;
    public TextField classe_input;
    public Button btn_action;
    public Label id;
    public TextField coefficient_input;
    public Pane coefficient_pane;
    public Label coefficient_label;
    ObservableList<ClasseEntity> classeList = FXCollections.observableArrayList();
    ClasseEntity classe = new ClasseEntity();
    ClasseDAO classeDAO = new ClasseDAO();
    PopUp popUp = new PopUp();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FadeOutRight(action_pane).play();
        refreshTable();

        classe_column.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getClasse()));
        coefficient_column.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getCoefficientTotal().toString()));
        action_column.setCellValueFactory(new PropertyValueFactory<>(""));

        // creation de bouton ajout et suppression
        Callback<TableColumn<ClasseEntity, ClasseEntity>, TableCell<ClasseEntity, ClasseEntity>> cellFactory=  (TableColumn<ClasseEntity, ClasseEntity> param) -> new TableCell<ClasseEntity, ClasseEntity>() {
            @Override
            public void updateItem(ClasseEntity item, boolean empty) {
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
                            classe = getTableView().getItems().get(getIndex());
                            coefficient_label.setText("Classe: edition");
                            btn_action.setText("Mettre à jour");
                            ClasseController.this.setText(
                                    classe.getId(),
                                    classe.getClasse(),
                                    Integer.valueOf(classe.getCoefficientTotal()));
                            new FadeInRight(action_pane).play();
                        } catch (NullPointerException e) {
                            popUp.error("Information", "Selectionner un champ avant de cliquer sur editer. Merci");
                        }
                    });
                    dltBtn.setOnAction(event -> {
                        action_pane.setVisible(false);
                        classe = getTableView().getItems().get(getIndex());
                        classeDAO.delete(classe);
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
        action_column.setCellFactory(cellFactory);

        // btn_action
        btn_action.setOnAction(event -> {
            if (btn_action.getText().equals("Ajouter +")){
                try {
                    classe = new ClasseEntity();
                    classe.setClasse(classe_input.getText());
                    classe.setCoefficientTotal(Integer.parseInt(coefficient_input.getText()));
                    classeDAO.insert(classe);
                    new FadeOutRight(action_pane).play();
                    refreshTable();
                    clearInputs();
                } catch (Exception e) {
                    e.printStackTrace();
                    popUp.error("erreur","Erreur, essaye encore une fois");
                }
            } else if (btn_action.getText().equals("Mettre à jour")){
                classe = new ClasseEntity();
                classe.setId(Integer.parseInt(id.getText()));
                classe.setClasse(classe_input.getText());
                classe.setCoefficientTotal(Integer.parseInt(coefficient_input.getText()));
                classeDAO.update(classe);
                new FadeOutRight(action_pane).play();
                refreshTable();
                clearInputs();
            }
        });
    }

    private void setText(Integer id, String classe, Integer totalCoeff) {
        this.id.setText(id.toString());
        this.classe_input.setText(classe);
        this.coefficient_input.setText(totalCoeff.toString());
    }

    private void clearInputs() {
        classe_input.setText("");
        coefficient_input.setText("");
    }

    private void refreshTable() {
        classeList.clear();
        classeList.setAll(classeDAO.listAll());
        table_coefficient.setItems(classeList);
    }

    public void afficherPaneAjout() {
        btn_action.setText("Ajouter +");
        new FadeInRight(action_pane).play();
    }

}
