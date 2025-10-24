package Model.Monstre;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Monstre implements Cloneable {

    //Les propriétés
    private StringProperty nom = new SimpleStringProperty();
    private IntegerProperty pointVie = new SimpleIntegerProperty();
    private StringProperty famille = new SimpleStringProperty();
    private StringProperty arme = new SimpleStringProperty();


    public Monstre(String nom, Integer pointVie, String famille, String arme) {
        this.nom.set(nom);
        this.pointVie.set(pointVie);
        this.famille.set(famille);
        this.arme.set(arme);
    }



    public String getNom() {
        return nom.get();
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public int getPointVie() {
        return pointVie.get();
    }

    public IntegerProperty pointVieProperty() {
        return pointVie;
    }

    public void setPointVie(int pointVie) {
        this.pointVie.set(pointVie);
    }

    public String getFamille() {
        return famille.get();
    }

    public StringProperty familleProperty() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille.set(famille);
    }

    public String getArme() {
        return arme.get();
    }

    public StringProperty armeProperty() {
        return arme;
    }

    public void setArme(String arme) {
        this.arme.set(arme);
    }
}
