package tp3;

public class LigneDepot {
    protected String numero;
    protected int quantite;

    public LigneDepot(String numero, int quantite) {
        this.numero = numero;
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "LigneDepot : " +
                "numero : " + numero +
                ", quantité : " + quantite;
    }
}
