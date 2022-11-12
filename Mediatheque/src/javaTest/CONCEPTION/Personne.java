package javaTest.CONCEPTION;

import java.sql.Date;

public class Personne {
  private int IdPersonne;
  private  String Nom,Prenom,Email,NumeroTlephone;
  private String sexe;
  private Date dateNaissance;
  private String TypeUser;

public Personne(int idPersonne, String nom, String prenom, String email,String sexe,Date dateNaissance,String TypeUser) {
		super();
		IdPersonne = idPersonne;
		Nom = nom;
		Prenom = prenom;
		Email = email;
	
		this.dateNaissance = dateNaissance;
		this.sexe=sexe;
		this.TypeUser=TypeUser;
		
	}
public Personne(int idPersonne, String nom, String prenom, String email,String sexe,Date dateNaissance) {
	super();
	IdPersonne = idPersonne;
	Nom = nom;
	Prenom = prenom;
	Email = email;
	
	this.dateNaissance = dateNaissance;
	this.sexe=sexe;
	
}
public Personne( String nom, String prenom, String email,String sexe,Date dateNaissance) {
	super();
	Nom = nom;
	Prenom = prenom;
	Email = email;

	this.dateNaissance = dateNaissance;
	this.sexe=sexe;
	
}



public int getIdPersonne() {
	return IdPersonne;
}
public void setIdPersonne(int idPersonne) {
	IdPersonne = idPersonne;
}
public String getNom() {
	return Nom;
}
public void setNom(String nom) {
	Nom = nom;
}
public String getPrenom() {
	return Prenom;
}
public void setPrenom(String prenom) {
	Prenom = prenom;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public String getNumeroTlephone() {
	return NumeroTlephone;
}
public void setNumeroTlephone(String numeroTlephone) {
	NumeroTlephone = numeroTlephone;
}
public Date getDateNaissance() {
	return dateNaissance;
}
public void setDateNaissance(Date dateNaissance) {
	this.dateNaissance = dateNaissance;
}

public String getSexe() {
	return sexe;
}

public void setSexe(String sexe) {
	this.sexe = sexe;
}
public String getTypeUser() {
	return TypeUser;
}
public void setTypeUser(String typeUser) {
	TypeUser = typeUser;
}


  
}
