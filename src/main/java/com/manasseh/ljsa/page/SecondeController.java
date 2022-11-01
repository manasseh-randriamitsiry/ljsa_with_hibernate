package com.manasseh.ljsa.page;

import animatefx.animation.FadeInRight;
import animatefx.animation.FadeOutRight;
import com.manasseh.ljsa.DAO.EtudiantDAO;
import com.manasseh.ljsa.DAO.SecondeDAO;
import com.manasseh.ljsa.model.EtudiantsEntity;
import com.manasseh.ljsa.model.SecondeEntity;
import com.manasseh.ljsa.utils.AutoCompleteComboBoxListener;
import com.manasseh.ljsa.utils.PopUp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.manasseh.ljsa.page.PremiereController.getList;

public class SecondeController implements Initializable{
    public TableView<SecondeEntity> seconde_table;
    public TableColumn<SecondeEntity, SecondeEntity> action_column;
    public Button afficher_ajout_btn, btn_action;
    public TextField recherche_input, svt_input, trimestre_input, ang_input, eps_input, hg_input, frs_input, math_input, mlg_input, pc_input, eac_input, ses_input, tice_input;
    public ComboBox<String> annee_input;
    public ComboBox<Object> n_mat_input;
    public TableColumn<SecondeEntity, String> trimestre_column, annee_column, ses_column, ang_column, svt_column, tice_column, eps_column, frs_column, moyenne_column, total_column, hg_column, math_column, mlg_column, n_mat_column, eac_column, phys_column;
    public Label  id_label,seconde_label;
    public Pane action_pane;
    public Circle detail_btn;
    ObservableList<SecondeEntity> listseconde = FXCollections.observableArrayList();
    SecondeDAO secondeDAO = new SecondeDAO();
    SecondeEntity secondeEntity = new SecondeEntity();
    PopUp popUp = new PopUp();
    EtudiantDAO etudiantDAO = new EtudiantDAO();
    EtudiantsEntity etudiant;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
        clearInputs();
        n_mat_input.setItems(etudiantDAO.listEtudiant());
        new FadeOutRight(action_pane).play();
        new AutoCompleteComboBoxListener<>(n_mat_input);
        new AutoCompleteComboBoxListener<>(annee_input);
        annee_input.getItems().addAll(getYearList(100));
        n_mat_column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getnMat()));
        ang_column.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getAnglais())));
        mlg_column.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getMalagasy())));
        hg_column.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getHistogeo())));
        eac_column.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getEac())));
        eps_column.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getEps())));
        ses_column.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getSes())));
        math_column.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getMats())));
        phys_column.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getSpc())));
        svt_column.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getSvt())));
        frs_column.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getFrancais())));
        tice_column.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getTice())));
        total_column.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getTotal())));
        trimestre_column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTrimestre()));
        annee_column.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getAnneeScolaire())));
        moyenne_column.setCellValueFactory(param -> {
            try {
                return new SimpleStringProperty(String.valueOf(param.getValue().getMoyenne()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


        Callback<TableColumn<SecondeEntity,SecondeEntity>, TableCell<SecondeEntity,SecondeEntity>> newColumn = (TableColumn<SecondeEntity,SecondeEntity> param) -> new TableCell<SecondeEntity,SecondeEntity>() {
            @Override
            public void updateItem(SecondeEntity item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    final Button editBtn = new Button("Editer");
                    final Button dltBtn = new Button("Supprimer");
                    dltBtn.setStyle("-fx-background-color:#FF6666");
                    editBtn.setOnAction(event -> {
                            secondeEntity = getTableView().getItems().get(getIndex());
                            seconde_label.setText("Seconde: edition");
                            btn_action.setText("Mettre à jour");

                            id_label.setText(String.valueOf(secondeEntity.getId()));
                            mlg_input.setText(String.valueOf(secondeEntity.getMalagasy()));
                            frs_input.setText(String.valueOf(secondeEntity.getFrancais()));
                            ang_input.setText(String.valueOf(secondeEntity.getAnglais()));
                            hg_input.setText(String.valueOf(secondeEntity.getHistogeo()));
                            eac_input.setText(String.valueOf(secondeEntity.getEac()));
                            math_input.setText(String.valueOf(secondeEntity.getMats()));
                            ses_input.setText(String.valueOf(secondeEntity.getSes()));
                            pc_input.setText(String.valueOf(secondeEntity.getSpc()));
                            svt_input.setText(String.valueOf(secondeEntity.getSvt()));
                            tice_input.setText(String.valueOf(secondeEntity.getTice()));
                            eps_input.setText(String.valueOf(secondeEntity.getEps()));
                            n_mat_input.getSelectionModel().select(secondeEntity.getnMat());
                            trimestre_input.setText(secondeEntity.getTrimestre());
                            annee_input.getSelectionModel().select(String.valueOf(secondeEntity.getAnneeScolaire()));

                            action_pane.setVisible(true);
                            new FadeInRight(action_pane).play();
                    });
                    dltBtn.setOnAction(event -> {
                        action_pane.setVisible(false);
                        secondeEntity = getTableView().getItems().get(getIndex());
                        secondeDAO.delete(secondeEntity);
                        refresh();
                    });
                    HBox hb = new HBox();
                    hb.setSpacing(5);
                    hb.setStyle("-fx-alignment:center");
                    hb.getChildren().addAll(editBtn, dltBtn);
                    setGraphic(hb);
                    setText(null);
                }
            }
        };
        action_column.setCellFactory(newColumn);

        // detail pop up
        detail_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                FXMLLoader loader = new FXMLLoader ();
                loader.setLocation(getClass().getResource("detailEtudiant.fxml"));
                try {
                    loader.load();
                } catch (IOException |NullPointerException ex ) {
                    popUp.error("Information", "Selectionner un numero matricule avant de cliquer. Merci");
                }
                DetailController detail = loader.getController();
//                etudiant = etudiantDAO.get((String) n_mat_input.getValue());
//                detail.setInputText(etudiant);
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.initStyle(StageStyle.UTILITY);
                stage.initOwner(((Node)event.getSource()).getScene().getWindow());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                refresh();
            } catch (NullPointerException e) {
                popUp.error("Information", "Selectionner un numero matricule avant de cliquer. Merci");
            }
        });

        btn_action.setOnAction(event -> {
            if (btn_action.getText().equals("Ajouter +")){
                try {
                    secondeEntity = new SecondeEntity();
                    secondeEntity.setMalagasy(Float.parseFloat(mlg_input.getText()));
                    secondeEntity.setFrancais(Float.parseFloat(frs_input.getText()));
                    secondeEntity.setAnglais(Float.parseFloat(ang_input.getText()));
                    secondeEntity.setHistogeo(Float.parseFloat(hg_input.getText()));
                    secondeEntity.setEac(Float.parseFloat(eac_input.getText()));
                    secondeEntity.setSes(Float.parseFloat(ses_input.getText()));
                    secondeEntity.setSpc( Float.parseFloat(pc_input.getText()));
                    secondeEntity.setSvt(Float.parseFloat(svt_input.getText()));
                    secondeEntity.setMats(Float.parseFloat(math_input.getText()));
                    secondeEntity.setEps(Float.parseFloat(eps_input.getText()));
                    secondeEntity.setTice(Float.parseFloat(tice_input.getText()));
                    secondeEntity.setnMat(n_mat_input.getValue().toString());
                    secondeEntity.setTrimestre(trimestre_input.getText());
                    secondeEntity.setAnneeScolaire(Integer.parseInt(annee_input.getValue()));

                    secondeDAO.insert(secondeEntity);
                    new FadeOutRight(action_pane).play();
                    refresh();
                    clearInputs();
                } catch (NumberFormatException e) {
                    popUp.error("erreur","Erreur, verifier que les notes sont des nombres puis essaye encore une fois");
                }
            }
            else if (btn_action.getText().equals("Mettre à jour")){
                secondeEntity = new SecondeEntity();
                secondeEntity.setId(Integer.parseInt(id_label.getText()));
                secondeEntity.setMalagasy(Float.parseFloat(mlg_input.getText()));
                secondeEntity.setFrancais(Float.parseFloat(frs_input.getText()));
                secondeEntity.setAnglais(Float.parseFloat(ang_input.getText()));
                secondeEntity.setHistogeo(Float.parseFloat(hg_input.getText()));
                secondeEntity.setEac(Float.parseFloat(eac_input.getText()));
                secondeEntity.setSes(Float.parseFloat(ses_input.getText()));
                secondeEntity.setSpc( Float.parseFloat(pc_input.getText()));
                secondeEntity.setSvt(Float.parseFloat(svt_input.getText()));
                secondeEntity.setMats(Float.parseFloat(math_input.getText()));
                secondeEntity.setEps(Float.parseFloat(eps_input.getText()));
                secondeEntity.setTice(Float.parseFloat(tice_input.getText()));
                secondeEntity.setnMat(n_mat_input.getValue().toString());
                secondeEntity.setTrimestre(trimestre_input.getText());
                secondeEntity.setAnneeScolaire(Integer.parseInt(annee_input.getValue()));

                secondeDAO.update(secondeEntity);
                refresh();
                clearInputs();
                new FadeOutRight(action_pane).play();
                action_pane.setVisible(true);
            }
        });
    }
    public void afficherPaneAjout(){
        btn_action.setText("Ajouter +");
        seconde_label.setText("Seconde: Ajout");
        new FadeInRight(action_pane).play();
        clearInputs();
    }
    private void clearInputs() {
        PremiereController.clearValues(mlg_input, frs_input, ang_input, hg_input, eac_input, math_input, pc_input, svt_input, tice_input, eps_input, ses_input);
    }
    private void refresh(){
        listseconde.clear();
        listseconde.setAll(secondeDAO.listAll());
        activerRecherche();
        clearInputs();
    }
    public void check(){
    }
    private void activerRecherche() {
        FilteredList<SecondeEntity> filteredList = new FilteredList<>(listseconde, a->true);
        recherche_input.textProperty().addListener((Observable,oldValue,newValue) -> filteredList.setPredicate(seconde -> newValue.isEmpty()
                || seconde.getnMat().contains(newValue.toUpperCase())
                || String.valueOf(seconde.getAnneeScolaire()).contains(newValue)));
        SortedList<SecondeEntity> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(seconde_table.comparatorProperty());
        seconde_table.setItems(sortedList);
    }
    public static ArrayList<String> getYearList(int years) {
        return getList(years);
    }
}
