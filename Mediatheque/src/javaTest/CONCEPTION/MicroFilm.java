package javaTest.CONCEPTION;
import java.sql.Date;

public class MicroFilm extends Document{
    public MicroFilm(int cote, String titre, double prix, boolean consultable, boolean empruntable,int Qte) {
        super(cote, titre, prix, consultable, empruntable, Qte);
    }

    public MicroFilm(String titre, double prix, boolean consultable, boolean empruntable,int Qte) {
        super(titre, prix, consultable, empruntable,Qte);
    }



}
