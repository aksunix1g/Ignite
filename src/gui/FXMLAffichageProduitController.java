/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;

import entities.Produit;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.DBProduitManager;
import com.jfoenix.controls.JFXMasonryPane;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * FXML Controller class
 *
 * @author charf
 */
public class FXMLAffichageProduitController implements Initializable {

    @FXML
    private TextField tfRechereche;
    @FXML
    private Button bAllCategories;
    @FXML
    private Button bCategorieAcces;
    @FXML
    private Button bCategorieNutrition;
    @FXML
    private Button bCategorieMusc;
    @FXML
    private Button bCategorieTraining;
    @FXML
    private JFXMasonryPane masPaneProduit;
    @FXML
    private Label ltri;
    @FXML
    private Button bCatalogue;
    private static com.itextpdf.text.Font catFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 18,
            com.itextpdf.text.Font.BOLD);

    DBProduitManager produitManager = new DBProduitManager();
    ArrayList<Produit> listProduit = new ArrayList<Produit>();
    int choixTri = 1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listProduit = produitManager.afficherProduit();
        loadProduit(listProduit);

    }

    @FXML
    private void rechercherProduit(ActionEvent event) {
        tfRechereche.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.err.println("tf:" + tfRechereche.getText());
                ArrayList<Produit> listProdTmp = new ArrayList<Produit>();
                for (Produit prod : listProduit) {
                    if (prod.getNomProduit().contains(tfRechereche.getText())) {
                        listProdTmp.add(prod);
                    }
                }
                masPaneProduit.getChildren().clear();
                loadProduit(listProdTmp);
            }
        });

        /*  tfRechereche.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
    @Override
    public void handle(KeyEvent event) {
        System.err.println("tf:"+tfRechereche.getText());


                
    }});
         */
    }

    @FXML
    private void selectAllCategories(ActionEvent event) {
        masPaneProduit.getChildren().clear();
        loadProduit(listProduit);
    }

    @FXML
    private void selectCategoriesAccesoires(ActionEvent event) {
        ArrayList<Produit> listProd = loadProduitParCategories(listProduit, 1);
        masPaneProduit.getChildren().clear();
        loadProduit(listProd);
    }

    @FXML
    private void selectCategorieNutrition(ActionEvent event) {
        ArrayList<Produit> listProd = loadProduitParCategories(listProduit, 2);
        masPaneProduit.getChildren().clear();
        loadProduit(listProd);
    }

    @FXML
    private void selectCategorieMusc(ActionEvent event) {
        ArrayList<Produit> listProd = loadProduitParCategories(listProduit, 3);
        masPaneProduit.getChildren().clear();
        loadProduit(listProd);
    }

    @FXML
    private void selectCategorieTraining(ActionEvent event) {
        ArrayList<Produit> listProd = loadProduitParCategories(listProduit, 4);
        masPaneProduit.getChildren().clear();
        loadProduit(listProd);
    }

    @FXML
    private void genererPDF(ActionEvent event) {
        try {
            System.out.println("imprimer");
            String FILE = "C:/Users/Lenovo/Desktop/Yassine/PIDev/ignite/Documents/catalogue.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
            addContent(document);
            document.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLAffichageProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(FXMLAffichageProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void loadProduit(ArrayList<Produit> listProd) {
        for (Produit prod : listProd) {
            Pane paneProdView = new Pane();
            paneProdView.setStyle("-fx-background-color:rgb(220,200,200)");
            paneProdView.setPrefSize(200, 200);
            File filePathLogoTeam1 = new File(prod.getImageProduit());
            Image imageProduit = new Image(filePathLogoTeam1.toURI().toString());

            ImageView imageViewProduit = new ImageView();
            imageViewProduit.setImage(imageProduit);
            imageViewProduit.setFitWidth(199);
            imageViewProduit.setFitHeight(130);
            Label lNomProduit = new Label(prod.getNomProduit());
            lNomProduit.setTextFill(Color.BLACK);
            imageViewProduit.relocate(10, 10);
            lNomProduit.setPrefSize(200, 50);
            lNomProduit.relocate(10, 140);
            lNomProduit.setFont(new Font("Arial", 30));
            Label lPrixProduit = new Label(prod.getPrixProduit() + " DT");
            lPrixProduit.setTextFill(Color.GREEN);
            lPrixProduit.setFont(new Font("Cambria", 30));
            lPrixProduit.relocate(50, 180);
            lPrixProduit.setPrefSize(200, 50);

            paneProdView.getChildren().add(imageViewProduit);
            paneProdView.getChildren().add(lNomProduit);
            paneProdView.getChildren().add(lPrixProduit);
            paneProdView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    paneProdView.setScaleX(1.1);
                    paneProdView.setScaleY(1.1);
                    paneProdView.setStyle("-fx-background-color:rgb(135,206,250)");
                }

            });
            paneProdView.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    paneProdView.setScaleX(1);
                    paneProdView.setScaleY(1);
                    paneProdView.setStyle("-fx-background-color:rgb(220,200,200)");

                }

            });

            masPaneProduit.getChildren().add(paneProdView);

        }
    }

    public ArrayList<Produit> loadProduitParCategories(ArrayList<Produit> listProd, int categories) {
        ArrayList<Produit> listProdTMP = new ArrayList<Produit>();
        for (Produit prod : listProd) {
            if (prod.getCategorieProduit() == categories) {
                listProdTMP.add(prod);
            }
        }
        return listProdTMP;

    }

    @FXML
    private void tri(MouseEvent event) {
        ArrayList<Produit> listPordTri = null;
        if (choixTri == 1) {
            listPordTri = produitManager.afficherProduittriCroi();
            choixTri = choixTri * -1;
        } else {
            listPordTri = produitManager.afficherProduittriDesc();
            choixTri = choixTri * -1;
        }

        System.out.println("tri");
        masPaneProduit.getChildren().clear();
        loadProduit(listPordTri);

    }

    private void addMetaData(Document document) {
        document.addTitle("Catalogue PDF");
        document.addSubject("catalogue des produits");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Yassine Sboui");
        document.addCreator("Yassine Sboui");
    }

    private void addTitlePage(Document document) {
        try {
            //___________________________________________
            //   Image image1 = Image.getInstance("sample.png");
            //  image1.setAlignment(Element.ALIGN_CENTER);
            //  image1.scaleAbsolute(450, 250);
//Add to document:  document.add(image1);

            /*BufferedImage bffimage = ImageIO.read(new File("C:/Users/Lenovo/Desktop/Yassine/PIDev/ignite/images/COVER.png"));
            ImageIcon image = new ImageIcon(bffimage);
            document.add((Element) image);*/
            //____________________________________________________________________________________________________
            for (Produit prod : listProduit) {
                
                
                Paragraph imgCaption = new Paragraph("Produit :\n"+prod.getNomProduit()+"\n"+prod.getPrixProduit()+"\n"+prod.getDescProduit()+"\n\n\n");

                document.add(imgCaption);
            }

            /*} catch (IOException ex) {
            Logger.getLogger(FXMLAffichageProduitController.class.getName()).log(Level.SEVERE, null, ex);
             */
        } catch (DocumentException ex) {
            Logger.getLogger(FXMLAffichageProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void addContent(Document document) {
    }

}
