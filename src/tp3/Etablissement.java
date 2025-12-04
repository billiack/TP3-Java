package tp3;

public class Etablissement {
    protected String nom;
    protected Article[] articles;
    protected BonDepot[] bonsDepots;
    private final int MAX = 100;

    public Etablissement(String nom) {
        this.nom = nom;
        this.articles = new Article[MAX];
        this.bonsDepots = new BonDepot[MAX];
    }

    public Etablissement(String nom, Article[] articles, BonDepot[] bonsDepots) {
        this.nom = nom;
        this.articles = new Article[MAX];
        for (int i = 0; i < articles.length && i < MAX; i++) {
            this.articles[i] = articles[i];
        }
        this.bonsDepots = new BonDepot[MAX];
        for (int i = 0; i < bonsDepots.length && i < MAX; i++) {
            this.bonsDepots[i] = bonsDepots[i];
        }
    }

    @Override
    public String toString() {
        String out = "Etablissement " + nom + 
                "\nArticles : \n";
        for (int i = 0; i < MAX; i++) {
            if (articles[i] != null) {
                out += articles[i].toString() + "\n";
            }
        }
        out += "Bons de dépôt : \n";
        for (int i = 0; i < MAX; i++) {
            if (bonsDepots[i] != null) {
                out += bonsDepots[i].toString() + "\n";
            }
        }
        return out;
    }

}