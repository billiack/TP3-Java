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

    public void ajouterBonDepot(BonDepot bon) {
        for (int i = 0; i < MAX; i++) {
            if (bonsDepots[i] == null) {
                bonsDepots[i] = bon;
                return;
            }
        }
        System.out.println("Impossible d'ajouter un bon de dépôt : capacité maximale atteinte.");
    }

    public void lister() {
        Article[] sortedArticles = new Article[MAX];
        sortedArticles = articles.clone();
        for (int i = 0; i < MAX - 1; i++) {
            for (int j = 0; j < MAX - i - 1; j++) {
                if (sortedArticles[j] != null && sortedArticles[j + 1] != null &&
                    sortedArticles[j].getNbExemplaires() > sortedArticles[j + 1].getNbExemplaires()) {

                    Article temp = sortedArticles[j];
                    sortedArticles[j] = sortedArticles[j + 1];
                    sortedArticles[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < MAX; i++) {
            if (sortedArticles[i] != null) {
                System.out.println(sortedArticles[i].toString() + " - Prix courant : " + sortedArticles[i].getPrixCourant());
            }
        }

        // Optionnel
        public void lister(String numeroTel) {
            BonDepot[] filteredBons = new BonDepot[MAX];
            int count = 0;
            for (int i = 0; i < MAX; i++) {
                if (bonsDepots[i] != null && bonsDepots[i].getNumeroTel().equals(numeroTel)) {
                    filteredBons[count++] = bonsDepots[i];
                }
            }
            for (int i = 0; i < count - 1; i++) {
                for (int j = 0; j < count - i - 1; j++) {
                    if (filteredBons[j].getDateCreation().isAfter(filteredBons[j + 1].getDateCreation())) {
                        BonDepot temp = filteredBons[j];
                        filteredBons[j] = filteredBons[j + 1];
                        filteredBons[j + 1] = temp;
                    }
                }
            }
            for (int i = 0; i < count; i++) {
                System.out.println(filteredBons[i].toString());
            }
        }
    }

    // Optionnel
    void lister(String numero, LocalDate debut, LocalDate fin) {
        for (int i = 0; i < MAX; i++) {
            if (bonsDepots[i] != null && bonsDepots[i].getNumeroTel().equals(numero) &&
                (bonsDepots[i].getDateCreation().isEqual(debut) || bonsDepots[i].getDateCreation().isAfter(debut)) &&
                (bonsDepots[i].getDateCreation().isEqual(fin) || bonsDepots[i].getDateCreation().isBefore(fin))) {
                System.out.println(bonsDepots[i].toString());
            }
        }
    }

    public void versFichierDepots() throws IOException {
        String ch = "";
        for (int i = 0; i < MAX; i++) {
            if (bonsDepots[i] != null) {
                ch += bonsDepots[i].versFichier();
            }
        }
        FileWriter fich = new FileWriter("depots/depots_" + this.nom + ".txt");
        fich.write(ch);
        fich.close();
    }

    public Etablissement depuisFichierDepots(String nomFichier) throws IOException {
        FileReader fich = new FileReader(nomFichier);
        BufferedReader br = new BufferedReader(fich);
        String ligne;
        Etablissement etab = new Etablissement(this.nom);
        while ((ligne = br.readLine()) != null) {
            int bonId = Integer.parseInt(ligne.trim());
            ligne = br.readLine();
            String[] parts = ligne.split(" : ");
            String numeroTel = parts[0].trim();
            LocalDate dateDepot = LocalDate.parse(parts[1].trim());
            int nbArticles = Integer.parseInt(parts[2].trim());
            BonDepot bon = new BonDepot(numeroTel, nbArticles);
            bon.id = bonId;
            bon.dateDepot = dateDepot;
            for (int i = 0; i < nbArticles; i++) {
                ligne = br.readLine();
                String[] artParts = ligne.split(" : ");
                int quantite = Integer.parseInt(artParts[0].trim());
                String reference = artParts[1].trim();
                bon.ajouterLigne(reference, quantite);
            }
            etab.ajouterBonDepot(bon);
        }
        br.close();
        return etab;
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
