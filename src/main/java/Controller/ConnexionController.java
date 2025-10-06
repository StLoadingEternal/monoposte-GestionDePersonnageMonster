package Controller;

import Model.Utilisateur.Utilisateur;
import com.example.tp2_lassana_damoi.MonstreApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ConnexionController implements Initializable {

    private Utilisateur admin = Utilisateur.getInstance("Ted16", "1616");

    @FXML
    private Button annulerBtn;

    @FXML
    private Text errorText;

    @FXML
    private Button gererBtn;

    @FXML
    private TextField mdpUsager;

    @FXML
    private TextField nomUsager;

    private MonstreApplication mainApp;


    public void setMainApp(MonstreApplication mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        annulerBtn.setOnAction(e -> {
            try {
                mainApp.fenetreRecherche();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void onActionConnecter(){
        if(mdpUsager.getText().trim().equals(admin.getNomUser()) || mdpUsager.getText().trim().equals(admin.getMotDePasse())){
            try {
                mainApp.fenetreEdition();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            errorText.setText("Utilisateur ou mot de passe n'existe pas.");
        }
    }
}
