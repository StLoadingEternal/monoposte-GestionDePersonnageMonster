package com.example.tp2_lassana_damoi;

import Controller.ConnexionController;
import Controller.EditionController;
import Controller.RechercheController;
import Model.Monstre.MonstreMetier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MonstreApplication extends Application {

    private Stage primaryStage; // La fenêtre principale
    public static MonstreMetier metier = new MonstreMetier();//Classe de metier des  monstres

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        fenetreRecherche(); // Afficher la fenêtre de recherche des monstres
    }

    // Afficher la fenêtre principale (recherche de monstres)
    public void fenetreRecherche() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeMonstre.fxml"));
        Scene scene = new Scene(loader.load(), 619, 441);

        primaryStage.setTitle("Rechercher des Monstres");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Controller
        RechercheController controller = loader.getController();
        controller.setMainApp(this);
    }

    // Afficher la fenêtre de connexion
    public void fenetreConnexion() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Identification.fxml"));
        Scene scene = new Scene(loader.load(), 400, 300);

        primaryStage.setTitle("Connexion");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Controller
        ConnexionController controller = loader.getController();
        controller.setMainApp(this);
    }

    // Afficher la fenêtre de création
    public void fenetreEdition() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditerMonstre.fxml"));
        Scene scene = new Scene(loader.load(), 619, 441);

        primaryStage.setTitle("Éditer des monstres");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Controller
        EditionController controller = loader.getController();
        controller.setMainApp(this);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
