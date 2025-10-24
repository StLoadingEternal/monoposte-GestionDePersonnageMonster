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

    //On a un seul utilisateur un singleton
    private Utilisateur admin = Utilisateur.getInstance("Ted16", "1616");

    @FXML
    private Button annulerBtn;//Bouton annuler

    @FXML
    private Text errorText;//Text d'erreur à afficher

    @FXML
    private TextField mdpUsager; // champ mot de passe

    private MonstreApplication mainApp;//Reférence sur l'application principale
    
    public void setMainApp(MonstreApplication mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Au clic du bouton annuler on lance la fenêtre de recherche
        annulerBtn.setOnAction(e -> {
            try {
                mainApp.fenetreRecherche();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void onActionConnecter(){
        //Au clic du bouton connecter si les infos sont correct on part à la fenêtre de gestion sinon on change le message d'erreur
        if(mdpUsager.getText().trim().equals(admin.getNomUser()) || mdpUsager.getText().trim().equals(admin.getMotDePasse())){
            try {
                mainApp.fenetreEdition();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            errorText.setText("Utilisateur inexistant ou mot de passe incorrect");
        }
    }
}
