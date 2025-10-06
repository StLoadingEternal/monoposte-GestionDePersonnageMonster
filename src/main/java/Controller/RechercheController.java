package Controller;

import Model.Monstre.Monstre;
import Model.Monstre.MonstreMetier;
import com.example.tp2_lassana_damoi.MonstreApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

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
    }

    public void intialiserSpinner() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        minVieSpinner.setValueFactory(valueFactory);
        maxVieSpinner.setValueFactory(valueFactory);
    }

    private void intialiserTableau() {
        tableData.setItems(monstres);//Redondance

        // Initialisation des colonnes avec les getters correspondants
        vieColumn.setCellValueFactory(new PropertyValueFactory<>("vie"));
        armeColumn.setCellValueFactory(new PropertyValueFactory<>("arme"));
        familleColumn.setCellValueFactory(new PropertyValueFactory<>("famille"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
    }
}
