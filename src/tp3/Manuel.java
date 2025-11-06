/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp3;

/**
 *
 * @author billy
 */
public class Manuel extends Livre {
    protected String matiere;
    protected String niveauScolaire;
    
    public Manuel(String description, double prixInitial, int nbExemplaires, String isbn, int nbPages, String matiere, String niveauScolaire) {
        super(description, prixInitial, nbExemplaires, isbn, nbPages);
        this.matiere = matiere;
        this.niveauScolaire = niveauScolaire;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\nMatière : " + this.matiere + "\nNiveau : " + this.niveauScolaire;
    }
}

