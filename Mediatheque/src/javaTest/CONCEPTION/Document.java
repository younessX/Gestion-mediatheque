package javaTest.CONCEPTION;
import java.sql.Connection;


public class Document {
	private int cote;
	private String titre;
	private double prix;
	private boolean consultable;
	private boolean empruntable;
	private int Qte;
	private  Connection conn;

	public Document() {

	}
	    public Document(int cote, String titre, double prix,
	                    boolean consultable, boolean empruntable, int Qte) {
	        this.cote = cote;
	        this.titre = titre;
	        this.prix = prix;
	        this.consultable = consultable;
	        this.empruntable = empruntable;
	        this.Qte=Qte;
	    }

	    public Document(String titre, double prix,
	                    boolean consultable, boolean empruntable,int Qte) {
	        this.titre = titre;
	        this.prix = prix;
	        this.consultable = consultable;
	        this.empruntable = empruntable;
	        this.Qte=Qte;
	    }

	    public int getCote() {
	        return cote;
	    }

	    public void setCote(int cote) {
	        this.cote = cote;
	    }

	    public String getTitre() {
	        return titre;
	    }

	    public void setTitre(String titre) {
	        this.titre = titre;
	    }

	

	
	    public boolean isConsultable() {
	        return consultable;
	    }

	    public void setConsultable(boolean consultable) {
	        this.consultable = consultable;
	    }

	    public boolean isEmpruntable() {
	        return empruntable;
	    }

	    public void setEmpruntable(boolean empruntable) {
	        this.empruntable = empruntable;
	    }

	    public double getPrix() {
	        return prix;
	    }

	    public void setPrix(double prix) {
	        this.prix = prix;
	    }
	    

	    public int getQte() {
			return Qte;
		}
		public void setQte(int qte) {
			Qte = qte;
		}

}



