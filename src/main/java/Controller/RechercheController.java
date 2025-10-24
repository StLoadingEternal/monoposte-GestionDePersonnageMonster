package Controller;

import Model.Monstre.Monstre;
import Model.Monstre.MonstreMetier;
import com.example.tp2_lassana_damoi.MonstreApplication;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RechercheController implements Initializable {

    @FXML
    private TableView<Monstre> tableData;

    @FXML
    private TableColumn<Monstre, Integer> vieColumn;

    @FXML
    private TableColumn<Monstre, String> armeColumn;

    @FXML
    private TableColumn<Monstre, String> familleColumn;

    @FXML
    private TableColumn<Monstre, String> nomColumn;

    @FXML
    private Button connexionBtn;

    @FXML
    private Button effaceFormRechBtn;

    @FXML
    private Button effaceSelectionBtn;

    @FXML
    private Spinner<Integer> maxVieSpinner;

    @FXML
    private Spinner<Integer> minVieSpinner;

    @FXML
    private TextField nomRecherche;

    @FXML
    private Button quitterBtn;

    @FXML
    private Button rechercheBtn;

    private MonstreApplication mainApp;

    private ObservableList<Monstre> monstres = MonstreApplication.metier.getMonstreData(); //Les données de base se l'application

    public void setMainApp(MonstreApplication mainApp) {
        this.mainApp = mainApp;
    } // Référence sur l'application principale

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Configurer les Widgets
        intialiserTableau();
        intialiserSpinner();

        //Aller à la page de connection
        connexionBtn.setOnAction(e -> {
            try {
                mainApp.fenetreConnexion();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // effacer le monstre sélectionner dans le tableau
        effaceSelectionBtn.setOnAction(e -> {
            Monstre monstre = tableData.getSelectionModel().getSelectedItem();
            if (monstre != null) {
                MonstreApplication.metier.supprimerMonstre(monstre);
            }
        });

        //mettre a jour en fonction des changements sur les données des monstres
        monstres.addListener(
                (ListChangeListener<? super Monstre>) change -> {
                    while(change.next()) {
                        if (change.wasAdded() || change.wasRemoved() || change.wasUpdated()) {
                            tableData.setItems(monstres);
                        }
                    }
                }
        );

        // Fermer l'application
        quitterBtn.setOnAction(e -> {
            MonstreApplication.getPrimaryStage().close();
        });

        //recherche de monstre en fonction de son nom et ses points de vie (min et max)
        rechercheBtn.setOnAction(e -> {
            String nomRecherhcer =  nomRecherche.getText();
            int maxVie = maxVieSpinner.getValue();
            int minVie = minVieSpinner.getValue();
            List<Monstre> monstresTrouves = findMonstres(nomRecherhcer, maxVie, minVie);

            tableData.setItems(FXCollections.observableArrayList(monstresTrouves));
        });

        //effacer les données du formulaire de recherche
        effaceFormRechBtn.setOnAction(e -> {
            tableData.setItems(monstres);
            nomRecherche.setText("");
            maxVieSpinner.getValueFactory().setValue(0);
            minVieSpinner.getValueFactory().setValue(0);
        });
    }

    /**
     * Rechercher un monstre à partir de divers infos
     * @param nomRecherhcer le nom du monstre
     * @param maxVie ses points de vie maximum
     * @param minVie ses points de vie minimum
     * @return les monstres trouvés
     */
    private List<Monstre> findMonstres(String nomRecherhcer, int maxVie, int minVie) {
        return tableData.getItems().stream().filter(
                monstre ->
                        monstre.getNom().equals(nomRecherhcer)
                        && (monstre.getPointVie() >= minVie && monstre.getPointVie() <= maxVie)
        ).collect(Collectors.toList());
    }

    /**
     * Configurer les spinner de points de vie
     */
    public void intialiserSpinner() {
        SpinnerValueFactory<Integer> valueFactoryMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        SpinnerValueFactory<Integer> valueFactoryMax = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        minVieSpinner.setValueFactory(valueFactoryMin);
        maxVieSpinner.setValueFactory(valueFactoryMax);
    }

    /**
     * Configurer le tableView qui va accueillir les monstres
     */
    private void intialiserTableau() {
        tableData.setItems(monstres);

        // Initialisation des colonnes avec les propriétés de monstres correspondants
        vieColumn.setCellValueFactory(new PropertyValueFactory<>("pointVie"));
        armeColumn.setCellValueFactory(new PropertyValueFactory<>("arme"));
        familleColumn.setCellValueFactory(new PropertyValueFactory<>("famille"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
    }
}
