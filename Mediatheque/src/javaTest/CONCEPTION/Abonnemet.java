package javaTest.CONCEPTION;

import java.sql.Date;

public class Abonnemet {
    private int idLecteur;
    private Date dateDebut, dateFin;

    public Abonnemet(int idLecteur, Date dateDebut, Date dateFin) {
        this.idLecteur = idLecteur;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getIdLecteur() {
        return idLecteur;
    }

    public void setIdLecteur(int idLecteur) {
        this.idLecteur = idLecteur;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
