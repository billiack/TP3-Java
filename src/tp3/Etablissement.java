package tp3;

import java.time.LocalDate;

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
        this.trierArticles();

        this.bonsDepots = new BonDepot[MAX];
        for (int i = 0; i < bonsDepots.length && i < MAX; i++) {
            this.bonsDepots[i] = bonsDepots[i];
        }
    }

    // Tri des articles par numéro (ISBN / ISSN) dans l'ordre lexicographique
    public void trierArticles() {
        for (int i = 0; i < MAX - 1; i++) {
            for (int j = 0; j < MAX - i - 1; j++) {
                if (articles[j] != null && articles[j + 1] != null &&
                    articles[j].getNumero().compareTo(articles[j + 1].getNumero()) > 0) {

                    Article temp = articles[j];
                    articles[j] = articles[j + 1];
                    articles[j + 1] = temp;
                }
            }
        }
    }

    // 4(b) : ajouter un livre
    public void ajouterLivre(String description, double prixInitial, int nbExemplaires,
                             String isbn, int nbPages) {
        for (int i = 0; i < MAX; i++) {
            if (articles[i] == null) {
                articles[i] = new Livre(description, prixInitial, nbExemplaires, isbn, nbPages);
                this.trierArticles();
                return;
            }
        }
        System.out.println("Impossible d'ajouter le livre : capacité maximale atteinte.");
    }

    // 4(b) : ajouter un manuel
    public void ajouterManuel(String description, double prixInitial, int nbExemplaires,
                              String isbn, int nbPages, String matiere, String niveauScolaire) {
        for (int i = 0; i < MAX; i++) {
            if (articles[i] == null) {
                articles[i] = new Manuel(description, prixInitial, nbExemplaires,
                                         isbn, nbPages, matiere, niveauScolaire);
                this.trierArticles();
                return;
            }
        }
        System.out.println("Impossible d'ajouter le manuel : capacité maximale atteinte.");
    }

    // 4(b) : ajouter un magazine
    public void ajouterMagazine(String description, double prixInitial, int nbExemplaires,
                                String issn, String periodicite, LocalDate datePublication) {
        for (int i = 0; i < MAX; i++) {
            if (articles[i] == null) {
                articles[i] = new Magazine(description, prixInitial, nbExemplaires,
                                           issn, periodicite, datePublication);
                this.trierArticles();
                return;
            }
        }
        System.out.println("Impossible d'ajouter le magazine : capacité maximale atteinte.");
    }

    // 4(c) : rechercher un article par numéro ISBN / ISSN
    public Article rechercher(String numero) {
        for (int i = 0; i < MAX; i++) {
            if (articles[i] != null && articles[i].getNumero().equals(numero)) {
                return articles[i];
            }
        }
        return null;
    }

    // 4(c) : augmenter le nombre d'exemplaires d'un article
    public void ajouter(String numero, int quantite) {
        Article art = this.rechercher(numero);
        if (art != null) {
            art.ajouter(quantite);
        } else {
            System.out.println("Article non trouvé.");
        }
    }

    // 4(c) : diminuer le nombre d'exemplaires d'un article
    public void retirer(String numero, int quantite) {
        Article art = this.rechercher(numero);
        if (art != null) {
            art.retirer(quantite);
        } else {
            System.out.println("Article non trouvé.");
        }
    }

    // 5(b) : enregistrer un bon de dépôt (numéroté et daté automatiquement par BonDepot)
    public BonDepot ajouterBonDepot(String numeroTel, int capaciteMax) {
        for (int i = 0; i < MAX; i++) {
            if (bonsDepots[i] == null) {
                BonDepot bon = new BonDepot(numeroTel, capaciteMax);
                bonsDepots[i] = bon;
                return bon;
            }
        }
        System.out.println("Impossible d'ajouter un bon de dépôt : capacité maximale atteinte.");
        return null;
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
