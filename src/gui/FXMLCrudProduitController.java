/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Produit;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import services.DBProduitManager;

/**
 * FXML Controller class
 *
 * @author charf
 */
public class FXMLCrudProduitController implements Initializable {

    @FXML
    private TextField tfNomProduitAAjouter;
    @FXML
    private Button bSelectImageAAjouter;
    @FXML
    private Label lImageAAjouter;
    @FXML
    private ComboBox<String> cbCategoriesAAjouter;
    @FXML
    private TextField tfPrixAAjouter;
    @FXML
    private TextField tfQuantiteAAjouter;
    @FXML
    private TextArea taDescriptionProduitAAjouter;
    @FXML
    private Button bAjouterProduit;
    @FXML
    private TextField tfNomProduitAModifier;
    @FXML
    private Button bSelectImageAModifier;
    @FXML
    private Label lImageAModifier;
    @FXML
    private ComboBox<String> cbCategoriesAModifier;
    @FXML
    private TextField tfQuantiteAModifier;
    @FXML
    private TextField tfPrixAModifier;
    @FXML
    private TextArea taDescriptionAModifier;
    @FXML
    private Button bModifierProduit;
    @FXML
    private ComboBox<String> cbListProduitAModifier;
    @FXML
    private ComboBox<String> cbListProduitTabSupp;
    @FXML
    private Button bSupprimer;
    
    
    

    DBProduitManager produitManager = new DBProduitManager();
    Produit prodAModifier;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbCategoriesAAjouter.getItems().addAll(
                "Accesoires Fitness",
                "Nutrition Sportive",
                "Musculation",
                "Cross Training"
        );
        cbCategoriesAModifier.getItems().addAll(
                "Accesoires Fitness",
                "Nutrition Sportive",
                "Musculation",
                "Cross Training"
        );
        
        ArrayList<Produit> listProdTMP = new ArrayList<Produit>();
        listProdTMP=produitManager.afficherProduit();
        for(Produit prod:listProdTMP){
            cbListProduitAModifier.getItems().add(prod.getIdProduit()+" "+prod.getNomProduit());
            cbListProduitTabSupp.getItems().add(prod.getIdProduit()+" "+prod.getNomProduit());
        }
        
    }

    @FXML
    private void selectImageTabAjouter(ActionEvent event) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLCrudProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FXMLCrudProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FXMLCrudProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FXMLCrudProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            path = path.replaceAll("\\\\", "/");
            System.out.println(path);
            lImageAAjouter.setText(path);
        }

    }

    @FXML
    private void AjouterProduit(ActionEvent event) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLCrudProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FXMLCrudProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FXMLCrudProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FXMLCrudProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if((Float.parseFloat(tfPrixAAjouter.getText())>0)&&(Integer.parseInt(tfQuantiteAAjouter.getText())>0)){
        JOptionPane.showConfirmDialog(null, "Ajouter produit ?");

        Produit nouveauProduit = new Produit(tfNomProduitAAjouter.getText(), lImageAAjouter.getText(), whatCategorie(cbCategoriesAAjouter.getSelectionModel().getSelectedItem().toString()),
                Float.parseFloat(tfPrixAAjouter.getText()), Integer.parseInt(tfPrixAAjouter.getText()), taDescriptionProduitAAjouter.getText());
        produitManager.ajouterProduit(nouveauProduit);
        }else{
            JOptionPane.showMessageDialog(null,"quantite et produit doivent etre superieur a 0 ");
        }
    }

    @FXML
    private void selectImageTabModifier(ActionEvent event) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLCrudProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FXMLCrudProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FXMLCrudProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FXMLCrudProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            path = path.replaceAll("\\\\", "/");
            System.out.println(path);
            lImageAModifier.setText(path);
        }
    }

    @FXML
    private void modifierProduit(ActionEvent event) {
        System.out.println(prodAModifier.getIdProduit());
        produitManager.modifierProduit(prodAModifier,"nom","image", 0, 0, 0,"descr");
        System.out.println(tfNomProduitAModifier.getText()+"*"+lImageAModifier.getText()+"*"+whatCategorie(cbCategoriesAModifier.getSelectionModel().getSelectedItem().toString())+"*"+
         Float.parseFloat(tfPrixAModifier.getText())+"*"+ Integer.parseInt(tfPrixAModifier.getText())+"*"+ taDescriptionAModifier.getText());

         produitManager.modifierProduit(prodAModifier,tfNomProduitAModifier.getText(),lImageAModifier.getText(),whatCategorie(cbCategoriesAModifier.getSelectionModel().getSelectedItem().toString()),
         Float.parseFloat(tfPrixAModifier.getText()), Integer.parseInt(tfPrixAModifier.getText()), taDescriptionAModifier.getText()); 
        
    }

    @FXML
    private void produitSelectionneTabModifier(ActionEvent event) {
        int idProd=0;
        idProd=Integer.parseInt(cbListProduitAModifier.getSelectionModel().getSelectedItem().split(" ")[0]);
        prodAModifier=produitManager.getProduitParId(idProd);
        lImageAModifier.setText(prodAModifier.getImageProduit());
        tfNomProduitAModifier.setText(prodAModifier.getNomProduit());
        cbCategoriesAAjouter.getSelectionModel().select(whatCategoriefromID(prodAModifier.getCategorieProduit()));
        tfQuantiteAModifier.setText(""+prodAModifier.getQuantiteProduit());
        tfPrixAModifier.setText(""+prodAModifier.getPrixProduit());
        taDescriptionAModifier.setText(prodAModifier.getDescProduit());
    }

    @FXML
    private void produitSelectionneTabSupprimer(ActionEvent event) {
    }

    @FXML
    private void supprimerProduit(ActionEvent event) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLCrudProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FXMLCrudProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FXMLCrudProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FXMLCrudProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showConfirmDialog(null, "confirmer supression ");
        int idProduit =0 ;
        idProduit=Integer.parseInt(cbListProduitTabSupp.getSelectionModel().getSelectedItem().split(" ")[0]);
        produitManager.supprimerProduit(idProduit);
    }

    public int whatCategorie(String categorie) {

        if (categorie.equals("Accesoires Fitness")) {
            return 1;
        }
        if (categorie.equals("Nutrition Sportive")) {
            return 2;
        }
        if (categorie.equals("Musculation")) {
            return 3;
        }
        if (categorie.equals("Cross Training")) {
            return 4;
        }
        return 0;
    }
    
    public String whatCategoriefromID(int id){
        switch(id){
            case 1:
                return "Accesoires Fitness";
            case 2:
                return "Nutrition Sportive";
            case 3:
                return "Musculation";
            case 4:
                return "Cross Training";
        }
        return "";
    }

}
