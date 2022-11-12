package javaTest.CONCEPTION;
import java.sql.Date;

public class CdRoom extends Document{
    private TypeCdRoom type;
    private Auteur auteur;

    public CdRoom(int cote, String titre, TypeCdRoom type, Auteur auteur, double caution,
            boolean consultable, boolean empruntable, int Qte) {
            super(cote, titre, caution,consultable, empruntable, Qte);
        this.type = type;
        this.auteur = auteur;
       
    }

    public CdRoom(String titre, TypeCdRoom type, Auteur auteur, double caution,
                   boolean consultable, boolean empruntable,int Qte) {
        super( titre, caution, consultable, empruntable, Qte);
        this.type = type;
        this.auteur = auteur;
    }

    public String getType() {
        return type.name();
    }

    public void setType(TypeCdRoom type) {
        this.type = type;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    @Override
    public String toString() {
        return "CdRoom{" +
                "type=" + type +
                ", auteur=" + auteur +
                '}';
    }
}

	


