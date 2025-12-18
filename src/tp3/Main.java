package tp3;

import java.time.LocalDate;
import java.util.Scanner;
import java.io.IOException;

import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nom de l'etablissement: ");
        String nom = sc.nextLine().trim();
        if (nom.isEmpty()) {
            nom = "Etablissement";
        }

        Etablissement etab = new Etablissement(nom);

        System.out.print("Voulez-vous charger les dépôts depuis un fichier ? (o/n) : ");
        String rep = sc.nextLine().trim();
        if (rep.equalsIgnoreCase("o") || rep.equalsIgnoreCase("y")) {
            System.out.print("Nom du fichier à charger (chemin) : ");
            String fname = sc.nextLine().trim();
            try {
                Etablissement loaded = etab.depuisFichierDepots(fname);
                if (loaded != null) {
                    etab = loaded;
                    System.out.println("Chargement effectué depuis : " + fname);
                }
            } catch (IOException e) {
                System.out.println("Erreur lors du chargement : " + e.getMessage());
            }
        }

        boolean running = true;
        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1) Ajouter Livre");
            System.out.println("2) Ajouter Manuel");
            System.out.println("3) Ajouter Magazine");
            System.out.println("4) Rechercher Article (ISBN/ISSN)");
            System.out.println("5) Ajouter exemplaires à un article");
            System.out.println("6) Retirer exemplaires d'un article");
            System.out.println("7) Créer bon de dépôt");
            System.out.println("8) Lister articles");
            System.out.println("9) Sauvegarder dépôts (vers fichier)");
            System.out.println("0) Quitter");
            System.out.print("Choix: ");
            String choix = sc.nextLine().trim();

            switch (choix) {
                case "1": {
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Prix initial (ex: 12.5): ");
                    double prix = Double.parseDouble(sc.nextLine());
                    System.out.print("Nombre d'exemplaires: ");
                    int nb = Integer.parseInt(sc.nextLine());
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Nombre de pages: ");
                    int pages = Integer.parseInt(sc.nextLine());
                    etab.ajouterLivre(desc, prix, nb, isbn, pages);
                    System.out.println("Livre ajouté.");
                    break;
                }
                case "2": {
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Prix initial: ");
                    double prix = Double.parseDouble(sc.nextLine());
                    System.out.print("Nombre d'exemplaires: ");
                    int nb = Integer.parseInt(sc.nextLine());
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Nombre de pages: ");
                    int pages = Integer.parseInt(sc.nextLine());
                    System.out.print("Matiere: ");
                    String matiere = sc.nextLine();
                    System.out.print("Niveau scolaire: ");
                    String niveau = sc.nextLine();
                    etab.ajouterManuel(desc, prix, nb, isbn, pages, matiere, niveau);
                    System.out.println("Manuel ajouté.");
                    break;
                }
                case "3": {
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Prix initial: ");
                    double prix = Double.parseDouble(sc.nextLine());
                    System.out.print("Nombre d'exemplaires: ");
                    int nb = Integer.parseInt(sc.nextLine());
                    System.out.print("ISSN: ");
                    String issn = sc.nextLine();
                    System.out.print("Periodicite: ");
                    String periodicite = sc.nextLine();
                    System.out.print("Date publication (YYYY-MM-DD): ");
                    LocalDate datePub = null;
                    try {
                        datePub = LocalDate.parse(sc.nextLine().trim());
                    } catch (DateTimeParseException ex) {
                        System.out.println("Date invalide, utilisation de la date du jour.");
                        datePub = LocalDate.now();
                    }
                    etab.ajouterMagazine(desc, prix, nb, issn, periodicite, datePub);
                    System.out.println("Magazine ajouté.");
                    break;
                }
                case "4": {
                    System.out.print("Numéro (ISBN/ISSN) : ");
                    String num = sc.nextLine().trim();
                    Article a = etab.rechercher(num);
                    if (a != null) {
                        System.out.println(a.toString());
                        System.out.println("Prix courant: " + a.calculerPrix());
                    } else {
                        System.out.println("Article non trouvé.");
                    }
                    break;
                }
                case "5": {
                    System.out.print("Numéro (ISBN/ISSN) : ");
                    String num = sc.nextLine().trim();
                    System.out.print("Quantité à ajouter : ");
                    int q = Integer.parseInt(sc.nextLine());
                    etab.ajouter(num, q);
                    break;
                }
                case "6": {
                    System.out.print("Numéro (ISBN/ISSN) : ");
                    String num = sc.nextLine().trim();
                    System.out.print("Quantité à retirer : ");
                    int q = Integer.parseInt(sc.nextLine());
                    etab.retirer(num, q);
                    break;
                }
                case "7": {
                    System.out.print("Numéro de téléphone: ");
                    String tel = sc.nextLine().trim();
                    System.out.print("Capacité max du bon (nombre d'articles) : ");
                    int cap = Integer.parseInt(sc.nextLine());
                    BonDepot bon = etab.ajouterBonDepot(tel, cap);
                    if (bon != null) {
                        System.out.println("Bon de dépôt créé : " + bon.toString());
                    }
                    break;
                }
                case "8": {
                    etab.lister();
                    break;
                }
                case "9": {
                    try {
                        etab.versFichierDepots();
                        System.out.println("Sauvegarde effectuée.");
                    } catch (IOException e) {
                        System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
                    }
                    break;
                }
                case "0": {
                    System.out.print("Sauvegarder avant de quitter ? (o/n) : ");
                    String s = sc.nextLine().trim();
                    if (s.equalsIgnoreCase("o") || s.equalsIgnoreCase("y")) {
                        try {
                            etab.versFichierDepots();
                            System.out.println("Sauvegarde effectuée.");
                        } catch (IOException e) {
                            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
                        }
                    }
                    running = false;
                    break;
                }
                default:
                    System.out.println("Choix invalide.");
            }
        }

        sc.close();
        System.out.println("Au revoir.");
    }
}