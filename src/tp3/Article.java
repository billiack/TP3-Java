package tp3;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author billy
 */
public abstract class Article {
    protected String description;
    protected double prixInitial;
    protected int nbExemplaires;
    
    public Article(String description, double prixInitial, int nbExemplaires) {
        this.description = description;
        this.prixInitial = prixInitial;
        this.nbExemplaires = nbExemplaires;
    }
        
    public String getNumero() {
        return "";
    }
    
    public boolean placerApres(Article autre) {
        return this.getNumero().compareTo(autre.getNumero()) > 0;
    }
    
    public void ajouter(int quantite) {
        this.nbExemplaires += quantite;
    }
    
    public void retirer(int quantite) {
        this.nbExemplaires -= quantite;
    }
    
    public double calculerPrix() {
        return this.prixInitial;
    }
    
    @Override
    public String toString() {
        return "Description : " + this.description + "\nPrix : " + this.calculerPrix() + "\nExemplaires : " + this.nbExemplaires;
    }
}
