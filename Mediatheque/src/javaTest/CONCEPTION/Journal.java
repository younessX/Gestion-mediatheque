package javaTest.CONCEPTION;



import java.sql.Date;

public class Journal extends Document{


    public Journal(int cote, String titre, double prix,
                   boolean consultable, boolean empruntable,int Qte) {
        super(cote, titre, prix, consultable, empruntable, Qte);
    }

    public Journal(String titre, double prix, boolean consultable, boolean empruntable,int Qte) {
        super( titre, prix, consultable, empruntable,Qte);
    }
}
