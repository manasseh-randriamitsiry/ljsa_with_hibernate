package com.manasseh.ljsa.page;

import animatefx.animation.FadeInRight;
import animatefx.animation.FadeOutRight;
import com.manasseh.ljsa.DAO.EtudiantDAO;
import com.manasseh.ljsa.DAO.PremiereDAO;
import com.manasseh.ljsa.model.EtudiantsEntity;
import com.manasseh.ljsa.model.PremiereEntity;
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
import com.manasseh.ljsa.utils.AutoCompleteComboBoxListener;

import javax.persistence.ManyToOne;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class PremiereController implements Initializable{
    public TableView<PremiereEntity> premiere_table;
    public TableColumn<PremiereEntity, PremiereEntity> action_column;
    public Button afficher_ajout_btn, btn_action;
    public TextField recherche_input, svt_input, phylo_input, trimestre_input, ang_input, eps_input, hg_input, frs_input, math_input, mlg_input, pc_input, eac_input, ses_input, tice_input;
    public ComboBox<String> annee_input;
    public ComboBox<Object> n_mat_input;
    public TableColumn<PremiereEntity, String> trimestre_column;
    public TableColumn<PremiereEntity, String> annee_column;
    public TableColumn<PremiereEntity, String> ses_column;
    public TableColumn<PremiereEntity, String> ang_column;
    public TableColumn<PremiereEntity, String> svt_column;
    public TableColumn<PremiereEntity, String> tice_column;
    public TableColumn<PremiereEntity, String> eps_column;
    public TableColumn<PremiereEntity, String> frs_column;
    public TableColumn<PremiereEntity, String> hg_column;
    public TableColumn<PremiereEntity, String> math_column;
    public TableColumn<PremiereEntity, String> mlg_column;
    public TableColumn<PremiereEntity, String> phylo_column;
    public TableColumn<PremiereEntity, String> n_mat_column;
    public TableColumn<PremiereEntity, String> eac_column;
    public TableColumn<PremiereEntity, String> phys_column;
    public TableColumn<PremiereEntity, String> moyenne_column;
    public TableColumn<PremiereEntity, String> total_column;
    public Label  id_label;
    public Pane action_pane;
    public Circle detail_btn;
    public Label premiere_label;
    ObservableList<PremiereEntity> listPremiere = FXCollections.observableArrayList();
    PremiereDAO premiereDAO = new PremiereDAO();
    @ManyToOne
    PremiereEntity premiere = new PremiereEntity();
    PopUp popUp = new PopUp();
    EtudiantDAO etudiantDAO = new EtudiantDAO();
    EtudiantsEntity etudiant;

    public PremiereEntity getPremiere() {
        return premiere;
    }

    public void setPremiere(PremiereEntity premiere) {
        this.premiere = premiere;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
        clearInputs();
        new FadeOutRight(action_pane).play();
        n_mat_input.setItems(etudiantDAO.listEtudiant());
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
         phylo_column.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getPhylo())));
        total_column.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getTotal())));
        trimestre_column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTrimestre()));
         annee_column.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getAnneeScolaire())));
        moyenne_column.setCellValueFactory(param -> {
            try {
                return new SimpleStringProperty(param.getValue().getMoyenne());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        Callback<TableColumn<PremiereEntity, PremiereEntity>, TableCell<PremiereEntity, PremiereEntity>> newColumn = (TableColumn<PremiereEntity,PremiereEntity> param) -> new TableCell<PremiereEntity,PremiereEntity>() {
            @Override
            public void updateItem(PremiereEntity item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    final Button editBtn = new Button("Editer");
                    final Button dltBtn = new Button("Supprimer");
                    dltBtn.setStyle("-fx-background-color:#FF6666");
                    editBtn.setOnAction(event -> {

                        premiere = getTableView().getItems().get(getIndex());
                        id_label.setText(String.valueOf(premiere.getId()));
                        mlg_input.setText(String.valueOf(premiere.getMalagasy()));
                        frs_input.setText(String.valueOf(premiere.getFrancais()));
                        ang_input.setText(String.valueOf(premiere.getAnglais()));
                        hg_input.setText(String.valueOf(premiere.getHistogeo()));
                        eac_input.setText(String.valueOf(premiere.getEac()));
                        math_input.setText(String.valueOf(premiere.getMats()));
                        ses_input.setText(String.valueOf(premiere.getSes()));
                        pc_input.setText(String.valueOf(premiere.getSpc()));
                        svt_input.setText(String.valueOf(premiere.getSvt()));
                        tice_input.setText(String.valueOf(premiere.getTice()));
                        eps_input.setText(String.valueOf(premiere.getEps()));
                        phylo_input.setText(String.valueOf(premiere.getPhylo()));
                        n_mat_input.getSelectionModel().select(premiere.getnMat());
                        trimestre_input.setText(premiere.getTrimestre());
                        annee_input.getSelectionModel().select(String.valueOf(premiere.getAnneeScolaire()));

                        btn_action.setText("Mettre à jour");
                        new FadeInRight(action_pane).play();
                    });
                    dltBtn.setOnAction(event -> {
                        action_pane.setVisible(false);
                        premiere = getTableView().getItems().get(getIndex());
                        premiereDAO.delete(premiere);
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
//                etudiant = etudiantDAO.getByNmat((String) n_mat_input.getValue());
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
            premiere = new PremiereEntity();
                setCommonFields();
                premiereDAO.insert(premiere);
            new FadeOutRight(action_pane).play();
            refresh();
            }
            else if (btn_action.getText().equals("Mettre à jour")){
                premiere = new PremiereEntity();
                premiere.setId(Integer.parseInt(id_label.getText()));
                setCommonFields();
                premiereDAO.update(premiere);
                new FadeOutRight(action_pane).play();
                refresh();
            }
        });
    }

    private void setCommonFields() {
        premiere.setMalagasy(Float.parseFloat(mlg_input.getText()));
        premiere.setFrancais(Float.parseFloat(frs_input.getText()));
        premiere.setAnglais(Float.parseFloat(ang_input.getText()));
        premiere.setHistogeo(Float.parseFloat(hg_input.getText()));
        premiere.setEac(Float.parseFloat(eac_input.getText()));
        premiere.setSpc(Float.parseFloat(pc_input.getText()));
        premiere.setSvt(Float.parseFloat(svt_input.getText()));
        premiere.setMats(Float.parseFloat(math_input.getText()));
        premiere.setEps(Float.parseFloat(eps_input.getText()));
        premiere.setTice(Float.parseFloat(tice_input.getText()));
        premiere.setPhylo(Float.parseFloat(phylo_input.getText()));
        premiere.setnMat(n_mat_input.getValue().toString());
        premiere.setTrimestre(trimestre_input.getText());
        premiere.setAnneeScolaire(Integer.parseInt(annee_input.getValue()));
    }

    public void afficherPaneAjout(){
        btn_action.setText("Ajouter +");
        premiere_label.setText("Premiere: Ajout");
        action_pane.setVisible(true);
        new FadeInRight(action_pane).play();
    }
    private void clearInputs() {
        clearValues(mlg_input, frs_input, ang_input, hg_input, eac_input, math_input, pc_input, svt_input, tice_input, eps_input, ses_input);
        phylo_input.setText("");
    }

    static void clearValues(TextField mlg_input, TextField frs_input, TextField ang_input, TextField hg_input, TextField eac_input, TextField math_input, TextField pc_input, TextField svt_input, TextField tice_input, TextField eps_input, TextField ses_input) {
        mlg_input.setText("");
        frs_input.setText("");
        ang_input.setText("");
        hg_input.setText("");
        eac_input.setText("");
        math_input.setText("");
        pc_input.setText("");
        svt_input.setText("");
        tice_input.setText("");
        eps_input.setText("");
        ses_input.setText("");
    }

    private void refresh(){
        listPremiere.clear();
        listPremiere.setAll(premiereDAO.listAll());
        premiere_table.setItems(listPremiere);
        activerRecherche();
        clearInputs();
    }
    public void check(){
    }
    private void activerRecherche() {
        FilteredList<PremiereEntity> filteredList = new FilteredList<>(listPremiere, a->true);
        recherche_input.textProperty().addListener((Observable,oldValue,newValue) -> filteredList.setPredicate(seconde -> newValue.isEmpty()
                || String.valueOf( seconde.getAnglais()).contains(newValue)
                || String.valueOf(seconde.getEac()).contains(newValue)
                || String.valueOf(seconde.getMalagasy()).contains(newValue)
                || seconde.getnMat().contains(newValue.toUpperCase())
                || String.valueOf(seconde.getTice()).contains(newValue)
                || String.valueOf(seconde.getMats()).contains(newValue)
                || String.valueOf(seconde.getSpc()).contains(newValue)
                || String.valueOf(seconde.getSvt()).contains(newValue)
                || String.valueOf(seconde.getEps()).contains(newValue)
                || String.valueOf(seconde.getSes()).contains(newValue)
                || String.valueOf(seconde.getPhylo()).contains(newValue)
                || String.valueOf(seconde.getAnneeScolaire()).contains(newValue)));
        SortedList<PremiereEntity> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(premiere_table.comparatorProperty());
        premiere_table.setItems(sortedList);
    }
    public static ArrayList<String> getYearList(int years) {
        return getList(years);
    }

    static ArrayList<String> getList(int years) {
        ArrayList<String> yearList = new ArrayList<>(years);
        int startYear = Calendar.getInstance().get(Calendar.YEAR) - 30;
        if (startYear<1990){
            startYear = 1990;
        }
        for (int i = 0; i < years; i++)
            yearList.add(Integer.toString(startYear + i));
        return yearList;
    }
}
