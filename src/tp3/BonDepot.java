package tp3;

import java.time.LocalDate;

public class BonDepot {
    protected static int compteur = 0;
    protected int id;
    protected String numeroTel;
    protected LocalDate dateDepot;
    protected int nbArticles;
    protected LigneDepot[] articles;

    public BonDepot(String numeroTel, int capaciteMax) {
        this.id = ++compteur;
        this.numeroTel = numeroTel;
        this.dateDepot = LocalDate.now();
        this.nbArticles = 0;
        this.articles = new LigneDepot[capaciteMax];
    }

    public void ajouterLigne(String numeroRef, int quantite) {

        if (nbArticles >= articles.length) {
            System.out.println("Bon plein, ajout impossible.");
            return;
        }
        LigneDepot nouvelle = new LigneDepot(numeroRef, quantite);
        articles[nbArticles] = nouvelle;
        nbArticles++;
    }
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

    public String versFichier() {
        String ch = this.numero + System.lineSeparator();
        ch += this.numeroTel + " : " + this.dateDepot + " : " + this.nbArticles + System.lineSeparator();
        for (int i = 0; i < this.nbArticles; i++) {
            ch += this.articles[i].versFichier();
        }
        return ch;
    }

    @Override
    public String toString() {
        String out = "BonDepot n°" + id +
                "\nNuméro : " + numeroTel +
                "\nDate de dépôt : " + dateDepot +
                "\nNb articles : " + nbArticles +
                "\nArticles : \n";

        for (int i = 0; i < nbArticles; i++) {
            out += "  - " + articles[i].toString() + "\n";
        }

        return out;
    }
}
