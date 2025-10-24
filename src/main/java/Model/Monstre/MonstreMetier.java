package Model.Monstre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class MonstreMetier {

    private static ObservableList<Monstre> monstreData = FXCollections.observableArrayList();


    //Les données de base des monstres de l'application
    public MonstreMetier() {
        this.monstreData.add(new Monstre("Lansaxx",90, "Dragons", "Souffle de feu"));
        this.monstreData.add(new Monstre("Radagon",100, "Roi", "Eclair"));
        this.monstreData.add(new Monstre("Malenia",100, "Reine", "Epée"));
        this.monstreData.add(new Monstre("GrandeTorche",90, "Torche", "flammes"));
    }

    public ObservableList<Monstre> getMonstreData() {
        return monstreData;
    }

    /**
     * Modifier un monstre
     * @param nom son nom
     * @param pontVie ses points de vie
     * @param famille son nom de famille
     * @param arme son arme
     * @return boolean le monstre existe oui ou nom
     */
    public boolean ajouterMonstre(String nom, int pontVie, String famille, String arme) {
        boolean monstreExist = monstreData.stream().anyMatch(m->
                m.getNom().equals(nom)
        );
        if (!monstreExist){
            this.monstreData.add(new Monstre(nom, pontVie, famille, arme));
        }

        return monstreExist;
    }

    /**
     * Modifier un monstre
     * @param monstre
     * @param nom son nouveau nom
     * @param pontVie ses points de vie
     * @param famille son nom de famille
     * @param arme son arme
     */
    public void modifierMonstre(Monstre monstre, String nom, int pontVie, String famille, String arme) {
        monstre.setNom(nom);
        monstre.setArme(arme);
        monstre.setFamille(famille);
        monstre.setPointVie(pontVie);
    }

    /**
     * Supprimer un monstre
     * @param monstre
     * @return
     */
    public static boolean supprimerMonstre(Monstre monstre){
        return monstreData.remove(monstre);
    }

}
