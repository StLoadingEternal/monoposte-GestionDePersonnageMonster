package Model.Monstre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class MonstreMetier {

    private static ObservableList<Monstre> monstreData = FXCollections.observableArrayList();


    public MonstreMetier() {
        this.monstreData.add(new Monstre("Lansaxx",90, "Dragons", "Souffle de feu"));
    }

    public ObservableList<Monstre> getMonstreData() {
        return monstreData;
    }

    public boolean ajouterMonstre(String nom, int pontVie, String famille, String arme) {
        boolean monstreExist = monstreData.stream().anyMatch(m->
                m.getNom().equals(nom)
        );
        if (!monstreExist){
            this.monstreData.add(new Monstre(nom, pontVie, famille, arme));
        }

        return monstreExist;
    }

    public void modifierMonstre(Monstre monstre, String nom, int pontVie, String famille, String arme) {
        monstre.setNom(nom);
        monstre.setArme(arme);
        monstre.setFamille(famille);
        monstre.setPointVie(pontVie);
    }

    public static boolean supprimerMonstre(Monstre monstre){
        return monstreData.remove(monstre);
    }

}
