package javaTest.CONCEPTION;

import java.sql.Date;

public class Achat {
	  private int IdAchat;
	  private int IdDocument;
	  private int IdLecteur;
	  private Date dateAchat;
	  private TypeDocument typeDocument;

	public Achat(int idAchat, int idDocument, int idLecteur, Date dateAchat, TypeDocument typeDocument) {
		IdAchat = idAchat;
		IdDocument = idDocument;
		IdLecteur = idLecteur;
		this.dateAchat = dateAchat;
		this.typeDocument = typeDocument;
	}
	public Achat(int idDocument, int idLecteur, Date dateAchat, TypeDocument typeDocument) {
		IdDocument = idDocument;
		IdLecteur = idLecteur;
		this.dateAchat = dateAchat;
		this.typeDocument = typeDocument;
	}
	public int getIdAchat() {
		return IdAchat;
	}
	public void setIdAchat(int idAchat) {
		IdAchat = idAchat;
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
	public Date getDateAchat() {
		return dateAchat;
	}
	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}
	public TypeDocument getTypeDocument() {
		return typeDocument;
	}
	public void setTypeDocument(TypeDocument typeDocument) {
		this.typeDocument = typeDocument;
	}
	
	  
}
