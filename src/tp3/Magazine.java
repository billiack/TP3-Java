/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp3;

/**
 *
 * @author billy
 */
import java.time.LocalDate;

class Magazine extends Article {
    protected String issn;
    protected String periodicite;
    protected LocalDate datePublication;
    
    public Magazine(String description, double prixInitial, int nbExemplaires, String issn, String periodicite, LocalDate datePublication) {
        super(description, prixInitial, nbExemplaires);
        this.issn = issn;
        this.periodicite = periodicite;
        this.datePublication = datePublication;
    }
    
    @Override
    public String getNumero() {
        return issn;
    }
    
    @Override
    public double calculerPrix() {
        LocalDate now = LocalDate.now();
        long duree = 0;
        int seuil1 = 0, seuil2 = 0;
        
        if (periodicite.equals("hebdomadaire")) {
            duree = datePublication.until(now, java.time.temporal.ChronoUnit.WEEKS);
            seuil1 = 2;
            seuil2 = 4;
        } else if (periodicite.equals("mensuel")) {
            duree = datePublication.until(now, java.time.temporal.ChronoUnit.MONTHS);
            seuil1 = 2;
            seuil2 = 4;
        } else if (periodicite.equals("trimestriel")) {
            duree = datePublication.until(now, java.time.temporal.ChronoUnit.MONTHS) / 3;
            seuil1 = 2;
            seuil2 = 4;
        }
        
        if (duree >= seuil2) {
            return prixInitial * 0.25;
        } else if (duree >= seuil1) {
            return prixInitial * 0.5;
        }
        return prixInitial;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\nPériodicité : " + periodicite + "\nPublication : " + datePublication;
    }
}
