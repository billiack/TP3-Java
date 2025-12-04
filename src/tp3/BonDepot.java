package tp3;

import java.time.LocalDate;

public class BonDepot {

    // Compteur statique pour numéroter les bons automatiquement
    protected static int compteur = 0;

    // Attributs d'instance
    protected int id;
    protected String numeroTel;
    protected LocalDate dateDepot;
    protected int nbArticles;          // nombre de lignes effectivement utilisées
    protected LigneDepot[] articles;   // tableau de capacité fixe

    /**
     * Constructeur :
     * - numérote automatiquement le bon
     * - date automatiquement le dépôt
     * - initialise un tableau avec une capacité maximale
     */
    public BonDepot(String numeroTel, int capaciteMax) {
        this.id = ++compteur;          // numérotation continue
        this.numeroTel = numeroTel;
        this.dateDepot = LocalDate.now();  // datation auto
        this.nbArticles = 0;           // au début, aucune ligne
        this.articles = new LigneDepot[capaciteMax];
    }

    /**
     * Ajoute une ligne de dépôt (5a) en donnant :
     * - le numéro ISBN ou ISSN
     * - le nombre d'exemplaires déposés
     */
    public void ajouterLigne(String numeroRef, int quantite) {

        // Vérifier s'il reste de la place dans le tableau
        if (nbArticles >= articles.length) {
            System.out.println("Impossible d'ajouter une ligne : bon plein (capacité max atteinte).");
            return;
        }

        // Créer une nouvelle ligne de dépôt
        LigneDepot nouvelle = new LigneDepot(numeroRef, quantite);

        // Placer la ligne à la première position libre
        articles[nbArticles] = nouvelle;

        // Mettre à jour le nombre d'articles
        nbArticles++;
    }

    // Getters utiles (optionnel mais pratique)
    public int getId() {
        return id;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public LocalDate getDateDepot() {
        return dateDepot;
    }

    public int getNbArticles() {
        return nbArticles;
    }

    public LigneDepot[] getArticles() {
        return articles;
    }

    @Override
    public String toString() {
        String out = "BonDepot n°" + id +
                "\nNuméro : " + numeroTel +
                "\nDate de dépôt : " + dateDepot +
                "\nNb articles : " + nbArticles +
                "\nArticles : \n";

        for (int i = 0; i < nbArticles; i++) { // on ne parcourt que les lignes remplies
            out += "  - " + articles[i].toString() + "\n";
        }

        return out;
    }
}
