package com.manasseh.ljsa.page;

import com.manasseh.ljsa.model.EtudiantsEntity;
import com.manasseh.ljsa.utils.ActivateDrag;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DetailController extends ActivateDrag {
    public Pane detailPane;
    public TextField nom;
    public TextField prenom;
    public TextField date_nais;
    public TextField classe;
    public Label n_matricule;

    public void getCursorPosition(MouseEvent event) {
        pressed(event);
    }
    public void activateDrag(MouseEvent event) {
        activate(event, detailPane);
    }
    public void setInputText(EtudiantsEntity etudiant){
        n_matricule.setText(etudiant.getnMatricule());
        this.nom.setText(etudiant.getNom());
        this.prenom.setText(etudiant.getPrenom());
        this.classe.setText(etudiant.getClasse());
        this.date_nais.setText(etudiant.getDateNais().toString());
    }
}
