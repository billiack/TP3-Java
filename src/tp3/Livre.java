package tp3;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author billy
 */

import java.time.LocalDate;

public class Livre extends Article {
    protected String isbn;
    protected int nbPages;
    
    public Livre(String description, double prixInitial, int nombreExemplaires, String isbn, int nombrePages) {
        super(description, prixInitial, nombreExemplaires);
        this.isbn = isbn;
        this.nbPages = nombrePages;
    }
    
    public String getIsbn() {
        return this.isbn;
    }

    public int getNbPages() {
        return this.nbPages;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }
    
    @Override
    public String getNumero() {
        return this.isbn;
    }
    
    @Override
    public double calculerPrix() {
        LocalDate now = LocalDate.now();
        if (now.getMonthValue() == 4) {
            return this.prixInitial * 0.5;
        }
        return this.prixInitial;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\nNombre pages : " + this.nbPages;
    }
}