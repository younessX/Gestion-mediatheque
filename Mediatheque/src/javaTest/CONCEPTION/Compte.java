package javaTest.CONCEPTION;

import java.sql.Date;



public class Compte  {
private Personne personne;    
private String nomUtilisateur,motPasse;
private Date dateCreation;
private BaseDeDonne db;
public Compte(Personne personne, String nomUtilisateur, String motPasse, Date dateCreation) {
	super();
	this.personne = personne;
	this.nomUtilisateur = nomUtilisateur;
	this.motPasse = motPasse;
	this.dateCreation = dateCreation;
	this.db= new BaseDeDonne();
}
public Compte(Personne personne, String nomUtilisateur, String motPasse) {
	super();
	this.personne = personne;
	this.nomUtilisateur = nomUtilisateur;
	this.motPasse = motPasse;
	
}
public Personne getPersonne() {
	return personne;
}
public void setPersonne(Personne personne) {
	this.personne = personne;
}
public String getNomUtilisateur() {
	return nomUtilisateur;
}
public void setNomUtilisateur(String nomUtilisateur) {
	this.nomUtilisateur = nomUtilisateur;
}
public String getMotPasse() {
	return motPasse;
}
public void modifierMotPasse(String motPasse) {
	this.motPasse = motPasse;
}
   public boolean connection(String nomUtilisateur,String motPasse){
	     db.getPersonne(nomUtilisateur,motPasse);
	     
	   return true;
   }
}

