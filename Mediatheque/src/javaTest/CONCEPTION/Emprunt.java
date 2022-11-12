package javaTest.CONCEPTION;
import java.sql.Date;

public class Emprunt {
  private int IdEmprunt;
  private int IdDocument;
  private int IdLecteur;
  private Date dateEmpruntl;
  private Date dateRetour;
  TypeDocument typeDocument;
  public Emprunt(int IdEmprunt, int idDocument, int idLecteur, Date dateEmpruntl, Date dateRetour, TypeDocument typeDocument) {
		super();
		IdDocument = idDocument;
		IdLecteur = idLecteur;
		this.dateEmpruntl = dateEmpruntl;
		this.dateRetour = dateRetour;
		this.typeDocument = typeDocument;
		this.IdEmprunt=IdEmprunt;
	}
public Emprunt(int idDocument, int idLecteur, Date dateEmpruntl, Date dateRetour, TypeDocument typeDocument) {
	super();
	IdDocument = idDocument;
	IdLecteur = idLecteur;
	this.dateEmpruntl = dateEmpruntl;
	this.dateRetour = dateRetour;
	this.typeDocument = typeDocument;
}
public int getIdDocument() {
	return IdDocument;
}
public void setIdDocument(int idDocument) {
	IdDocument = idDocument;
}
public int getIdLecteur() {
	return IdLecteur;
}
public void setIdLecteur(int idLecteur) {
	IdLecteur = idLecteur;
}
public Date getDateEmpruntl() {
	return dateEmpruntl;
}
public void setDateEmpruntl(Date dateEmpruntl) {
	this.dateEmpruntl = dateEmpruntl;
}
public Date getDateRetour() {
	return dateRetour;
}
public void setDateRetour(Date dateRetour) {
	this.dateRetour = dateRetour;
}
public TypeDocument getTypeDocument() {
	return typeDocument;
}
public void setTypeDocument(TypeDocument typeDocument) {
	this.typeDocument = typeDocument;
}
  
}
