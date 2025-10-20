package Controller;

import Model.Monstre.Monstre;
import Model.Monstre.MonstreMetier;
import com.example.tp2_lassana_damoi.MonstreApplication;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class EditionController implements Initializable {

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
    private Button ajouterBtn;

    @FXML
    private Button deconnexionBtn;

    @FXML
    private Button effaceFormEditerBtn;

    @FXML
    private TextField armeMonstre;

    @FXML
    private TextField familleMonstre;

    @FXML
    private TextField nomMonstre;

    @FXML
    private Button quitterCreerBtn;

    @FXML
    private Spinner<Integer> vieSpinner;

    private MonstreApplication mainApp;

    private ObservableList<Monstre> monstres = MonstreApplication.metier.getMonstreData();


    public void setMainApp(MonstreApplication mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialiserTableau();
        intialiserSpinner();

        deconnexionBtn.setOnAction(e -> {
            try {
                mainApp.fenetreConnexion();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void intialiserSpinner() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        vieSpinner.setValueFactory(valueFactory);
    }

    public void initialiserTableau() {

        tableData.setItems(monstres);

        // Initialisation des colonnes avec les getters correspondants
        vieColumn.setCellValueFactory(new PropertyValueFactory<>("vie"));
        armeColumn.setCellValueFactory(new PropertyValueFactory<>("arme"));
        familleColumn.setCellValueFactory(new PropertyValueFactory<>("famille"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
    }

    public void onAjoutAction(){
        if (MonstreApplication.metier.ajouterMonstre(nomMonstre.getText(), vieSpinner.getValue(), familleMonstre.getText(), armeMonstre.getText())){
            afficherAfficherAlerte("Ce monstre existe deja ! (les monstres ne peuvent avoir le meme nom)");
        }
    }

    public void onEffacerAction(){
        nomMonstre.setText("");
        familleMonstre.setText("");
        armeMonstre.setText("");
        vieSpinner.getValueFactory().setValue(0);
    }

    public void afficherAfficherAlerte(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
