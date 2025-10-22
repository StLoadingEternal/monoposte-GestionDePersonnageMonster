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

    private ObservableList<Monstre> monstres = MonstreApplication.metier.getMonstreData();

    public void setMainApp(MonstreApplication mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        intialiserTableau();
        intialiserSpinner();
        connexionBtn.setOnAction(e -> {
            try {
                mainApp.fenetreConnexion();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // effacer selection
        effaceSelectionBtn.setOnAction(e -> {
            Monstre monstre = tableData.getSelectionModel().getSelectedItem();
            if (monstre != null) {
                MonstreApplication.metier.supprimerMonstre(monstre);
            }
        });

        //mettre a jour en fonction des changements sur le monstre data
        monstres.addListener(
                (ListChangeListener<? super Monstre>) change -> {
                    while(change.next()) {
                        if (change.wasAdded() || change.wasRemoved() || change.wasUpdated()) {
                            intialiserTableau();
                        }
                    }
                }
        );

        // Fermer l'application
        quitterBtn.setOnAction(e -> {
            MonstreApplication.getPrimaryStage().close();
        });

        //recherche de monstre
        rechercheBtn.setOnAction(e -> {
            String nomRecherhcer =  nomRecherche.getText();
            int maxVie = maxVieSpinner.getValue();
            int minVie = minVieSpinner.getValue();
            List<Monstre> monstresTrouves = findMonstres(nomRecherhcer, maxVie, minVie);

            tableData.setItems(FXCollections.observableArrayList(monstresTrouves));
        });

        //effacer le formulaire de recherche
        effaceFormRechBtn.setOnAction(e -> {
            tableData.setItems(monstres);
        });
    }

    private List<Monstre> findMonstres(String nomRecherhcer, int maxVie, int minVie) {
        return tableData.getItems().stream().filter(
                monstre ->
                        monstre.getNom().equals(nomRecherhcer)
                        && (monstre.getPointVie() >= minVie && monstre.getPointVie() <= maxVie)
        ).collect(Collectors.toList());

    }

    public void intialiserSpinner() {
        SpinnerValueFactory<Integer> valueFactoryMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        SpinnerValueFactory<Integer> valueFactoryMax = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        minVieSpinner.setValueFactory(valueFactoryMin);
        maxVieSpinner.setValueFactory(valueFactoryMax);
    }

    private void intialiserTableau() {
        tableData.setItems(monstres);//Redondance

        // Initialisation des colonnes avec les getters correspondants
        vieColumn.setCellValueFactory(new PropertyValueFactory<>("pointVie"));
        armeColumn.setCellValueFactory(new PropertyValueFactory<>("arme"));
        familleColumn.setCellValueFactory(new PropertyValueFactory<>("famille"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
    }
}
