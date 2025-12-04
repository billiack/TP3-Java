package tp3;

import java.time.LocalDate;

public class BonDepot {
    protected int id;
    protected String numeroTel;
    protected LocalDate dateDepot;
    protected int nbArticles;
    protected LigneDepot[] articles;

    public BonDepot(int id, String numeroTel, LocalDate dateDepot, int nbArticles, LigneDepot[] articles) {
        this.id = id;
        this.numeroTel = numeroTel;
        this.dateDepot = dateDepot;
        this.nbArticles = nbArticles;
        this.articles = new LigneDepot[nbArticles];
        for (int i = 0; i < nbArticles; i++) {
            this.articles[i] = articles[i];
        }
    }

    @Override
    public String toString() {
        String out = "BonDepot " + id +
                "\nnuméro : " + numeroTel +
                "\nDate de dépôt : " + dateDepot +
                "\nNb articles : " + nbArticles + 
                "\nArticles : \n";
        for (int i = 0; i < nbArticles; i++) {
            out += articles[i].toString() + "\n";
        }
        return out;
    }
}