package javaTest.CONCEPTION;
import javax.print.Doc;
import java.sql.Date;

public class Livre extends Document {
    private  Auteur auteur;
    public Livre(int cote, String titre, Auteur auteur,double prix,
                boolean consultable, boolean empruntable,int Qte) {
        super(cote, titre, prix, consultable, empruntable,Qte);
        this.auteur = auteur;
    }

    public Livre(String titre, Auteur auteur,double prix,
                 boolean consultable, boolean empruntable,int Qte){
        super(titre,  prix, consultable, empruntable,Qte);
        this.auteur = auteur;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }
}
