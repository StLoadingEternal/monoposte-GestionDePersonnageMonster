package Controller;

import Model.Monstre.Monstre;
import Model.Monstre.MonstreMetier;
import com.example.tp2_lassana_damoi.MonstreApplication;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

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
    private Button deconnexionBtn;


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
        //Configurer les éléments d'interface
        initialiserTableau();
        intialiserSpinner();

        //à la déconnexion on part à la fenêtre de déconnexion
        deconnexionBtn.setOnAction(e -> {
            try {
                mainApp.fenetreConnexion();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        //Sortir de l'application
        quitterCreerBtn.setOnAction(e -> {
            MonstreApplication.getPrimaryStage().close();
        });
    }

    /**
     * Configurer le spinner des points de vie
     */
    public void intialiserSpinner() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        vieSpinner.setValueFactory(valueFactory);
    }

    /**
     * Configurer le tableau qui va accueillir les monstres
     */
    public void initialiserTableau() {
        tableData.setEditable(true);

        tableData.setItems(monstres);

        // Initialisation des colonnes avec les propriété monstres correspondantes
        vieColumn.setCellValueFactory(new PropertyValueFactory<>("pointVie"));
        vieColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        armeColumn.setCellValueFactory(new PropertyValueFactory<>("arme"));
        armeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        familleColumn.setCellValueFactory(new PropertyValueFactory<>("famille"));
        familleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    /**
     * Ajouter un monstre à partir des données du formulaire. S'il existe déjà on envoi un dialog à l'utilisateur
     */
    public void onAjoutAction(){
        if (MonstreApplication.metier.ajouterMonstre(nomMonstre.getText(), vieSpinner.getValue(), familleMonstre.getText(), armeMonstre.getText())){
            afficherAfficherAlerte("Ce monstre existe deja ! (les monstres ne peuvent avoir le meme nom)");
        }
    }

    /**
     * Effacer les entrées du formulaire de création
     */
    public void onEffacerAction(){
        nomMonstre.setText("");
        familleMonstre.setText("");
        armeMonstre.setText("");
        vieSpinner.getValueFactory().setValue(0);
    }

    /**
     * Afficher un message d'erreur dans un dialog
     * @param message
     */
    public void afficherAfficherAlerte(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
