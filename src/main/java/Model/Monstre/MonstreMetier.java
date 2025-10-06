package Model.Monstre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MonstreMetier {

    private ObservableList<Monstre> monstreData = FXCollections.observableArrayList();


    public MonstreMetier() {
        this.monstreData.add(new Monstre("Lansaxx",90, "Dragons", "Souffle de feu"));
    }

    public ObservableList<Monstre> getMonstreData() {
        return monstreData;
    }

    public void ajouterMonstre(String nom, int pontVie, String famille, String arme) {
        this.monstreData.add(new Monstre(nom, pontVie, famille, arme));
    }

    public void modifierMonstre(Monstre monstre, String nom, int pontVie, String famille, String arme) {
        monstre.setNom(nom);
        monstre.setArme(arme);
        monstre.setFamille(famille);
        monstre.setPointVie(pontVie);
    }

}
