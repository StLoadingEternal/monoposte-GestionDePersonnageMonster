package Controller;

import Model.Monstre.Monstre;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import javax.swing.text.TabableView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MonstreController{

    private static MonstreController instance;
    private List<Monstre> monstres;

    public MonstreController(){}

    public static MonstreController getInstance(){
        if(instance == null){
            instance = new MonstreController();
        }
        return instance;
    }



    public List<Monstre> getMonstres() {
        return monstres;
    }

    public void setMonstres(List<Monstre> monstres) {
        this.monstres = monstres;
    }
}
