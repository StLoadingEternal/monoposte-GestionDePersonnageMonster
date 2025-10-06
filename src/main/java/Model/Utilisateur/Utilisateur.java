package Model.Utilisateur;

public class Utilisateur {

    private static Utilisateur instance;
    private String nomUser;
    private String motDePasse;

    public Utilisateur(String nomUser, String motDePasse) {
        this.nomUser = nomUser;
        this.motDePasse = motDePasse;
    }

    public static Utilisateur getInstance(String nomUser, String motDePasse) {
        if (instance == null) {
            instance = new Utilisateur(nomUser, motDePasse);
        }
        return instance;
    }

    public String getNomUser() {
        return nomUser;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
