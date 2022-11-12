package javaTest.CONCEPTION;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

public class BaseDeDonne {
private Connection con=null;

public  void Connecter()
{

try {
Class.forName("com.mysql.jdbc.Driver");
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque","root","");

}catch(Exception e) {
System.out.println("ERROR "+e.getMessage());


}
}


public void deconnecter() {

if(con!=null) {

try {
con.close();
} catch (SQLException e) {
System.out.println("Erreur deconnecter "+e);
}
}



}


PreparedStatement state;
ResultSet res;
public boolean getPersonne(String NomUtilisateur,String motPasse){
String Query="Select NomUtilisateur,motPasse From personne where NomUtilisateur=?";


try {
state=(PreparedStatement) con.prepareStatement(Query);
state.setString(1,NomUtilisateur);
res=state.executeQuery();
boolean result;
result=res.next();
if(!result) {
JOptionPane.showMessageDialog(null, "NomUtlisateur incorrect", "Echec ",JOptionPane.WARNING_MESSAGE );

}

else {

while(result) {

String Nom=res.getString("NomUtilisateur");

String MotP=res.getString("MotPasse");


if(!motPasse.equals(MotP)) {
JOptionPane.showMessageDialog(null, "mot PasseIncorrect ", "Echec ",JOptionPane.WARNING_MESSAGE );
}
else {
JOptionPane.showMessageDialog(null, "mot Passe Correct ", "BIENVENUE ",JOptionPane.PLAIN_MESSAGE);
}
result=res.next();
}

}

}catch(SQLException e) {
System.out.println("ERREUR getPersonne "+e);
}

return false;

}

public  int MaxId(String NomTable) {
int max =0;
try {

state=(PreparedStatement) con.prepareStatement("SELECT max(IdPersonne) as maxi from "+NomTable);


//state.setString(1,NomColonne);
// state.setString(1,NomTable);
res=state.executeQuery();
res.next();
max=res.getInt(1);
}catch(SQLException e) {
System.out.println("MaxId"+e);

}

return max;
}

public void AjouterPersonne(Compte co) {
String Query="INSERT into personne  values (?,?,?,?,?,?,?)";
int max=MaxId("personne");
System.out.println(max);
try {

state=(PreparedStatement) con.prepareStatement(Query);

state.setInt(1,++max);
state.setString(2,co.getPersonne().getNom());
state.setString(3,co.getPersonne().getPrenom());
state.setString(4,co.getPersonne().getEmail());
state.setString(5,co.getMotPasse());

state.setDate(6,co.getPersonne().getDateNaissance());

state.setString(7,co.getPersonne().getSexe());
int res1=state.executeUpdate();
System.out.println("La Personne est bien Ajoute");

}catch(SQLException e) {
System.out.println("Ajouterersonne"+e);
}
}


public void SuprimerPersonne(int IdPersonne) {
String Query="Delete from personne where IdPersonne= ?";

try {

state=(PreparedStatement) con.prepareStatement(Query);

state.setInt(1, IdPersonne);
int res1=state.executeUpdate();
System.out.println("La Personne est Supprime ");

}catch(SQLException e) {
System.out.println("SupprimerPersonne "+e);
}
}


public void ModifierMotPasse(int IdPersonne,String AncienMotPasse,
							 String NouveauMotPasse,String Confirmation) {
String Query="Select MotPasse from Personne where IdPersonne = ?";

try {



state=(PreparedStatement) con.prepareStatement(Query);



state.setInt(1,IdPersonne);
ResultSet res1=state.executeQuery();
res1.next();
String motP=res1.getString(1);
if(!motP.equals(AncienMotPasse)) {
JOptionPane.showMessageDialog(null, "Ancien MotPaase Incorrect", "  Erreur ", JOptionPane.ERROR_MESSAGE);
}
else  {

if(!NouveauMotPasse.equals(Confirmation)) {
JOptionPane.showMessageDialog(null, "les deux mot passe ne se ressemble pas ", "  Erreur ", JOptionPane.ERROR_MESSAGE);

}
else if(AncienMotPasse.equals(NouveauMotPasse)) {
JOptionPane.showMessageDialog(null, "Vous pouvez pas modifier mot passe par l'ancien mot passe ", "  Erreur ", JOptionPane.WARNING_MESSAGE);
}


else {
String sql="Update personne set MotPasse= ? where IdPersonne=?";
state=(PreparedStatement) con.prepareStatement(sql);
state.setString(1,NouveauMotPasse);
state.setInt(2,IdPersonne);
int res2=state.executeUpdate();
JOptionPane.showMessageDialog(null, "mot Passe a ete modifier ", "succes", JOptionPane.PLAIN_MESSAGE);




}

}



}catch(SQLException e) {
System.out.println("ModifierCompte "+e);
}

}

public void ModifierPersonne(String nom,String prenom ,Date datenaissance,int idPersonne) {
String Query="update personne  set  NomPersonne=?,PrenomPersonne=?,DateNaissance=? where IdPersonne=? ";
// int max=MaxId("personne");
try {

state=  con.prepareStatement(Query);
int count=1;
state.setString(count++,nom);
state.setString(count++,prenom);
state.setDate(count++,datenaissance);

state.setInt(count++,idPersonne);

int res1=state.executeUpdate();

if(res1==0) {
JOptionPane.showMessageDialog(null, "la Personne n'est pas modifier","Erreur",JOptionPane.WARNING_MESSAGE);
}
else
JOptionPane.showMessageDialog(null, "la Personne est Bien Modifier","Succes",JOptionPane.PLAIN_MESSAGE);


}catch(SQLException e) {
System.out.println("ModifierPersonne"+e);
}
}

// fonction de document
public void EmprunterDocument(Emprunt e) {
	Connecter();
PreparedStatement pr1,pr2 = null;
ResultSet res=null;
/**LIVRE*/

if((e.getTypeDocument().name().equals("Livre"))) {
System.out.println("2");
try {

pr1 = (PreparedStatement) con.prepareStatement("Select QteDisponible from Livres where IdLivre= ?" );
pr1.setInt(1, e.getIdDocument());
res=pr1.executeQuery();
res.next();

if(res.getInt("QteDisponible")>0) {



pr1 = (PreparedStatement) con.prepareStatement("	Update Livres set QteDisponible=QteDisponible-1 where IdLivre= ?" );
pr1.setInt(1, e.getIdDocument());

pr1.executeUpdate();




pr2 = (PreparedStatement) con.prepareStatement("insert into  emprunt    (IdDocument,IdAbonnement,TypeDocument,DateEmprunt,DateRetour) values (?,?,?,?,?)" );
pr2.setInt(1,e.getIdDocument());
pr2.setInt(2,e.getIdLecteur());
pr2.setString(3,e.getTypeDocument().name());
pr2.setDate(4, e.getDateEmpruntl());
pr2.setDate(5,e.getDateRetour());

pr2.executeUpdate();
JOptionPane.showMessageDialog(null, "Le livre est bien emprunter", "Erreur",
		JOptionPane.PLAIN_MESSAGE);
}
else
JOptionPane.showMessageDialog(null, "La Quantite de cette Livre est null", " Erreur",
		JOptionPane.WARNING_MESSAGE);
}
catch(SQLException d) {
JOptionPane.showMessageDialog(null, "Emprunter Livre"+d, "Erreur"
		, JOptionPane.PLAIN_MESSAGE);
}
} else if(e.getTypeDocument().name().equals("CdRom")){


try {
pr1 = (PreparedStatement) con.prepareStatement("Select QteDisponible from cdroms where IdCdrom= ?" );
pr1.setInt(1, e.getIdDocument());
res=pr1.executeQuery();
res.next();
if(res.getInt("QteDisponible")>0) {


pr1 = (PreparedStatement) con.prepareStatement(" update cdroms set QteDisponible=QteDisponible-1 where IdCdrom= ?" );
pr1.setInt(1, e.getIdDocument());

pr1.executeUpdate();


pr2 = (PreparedStatement) con.prepareStatement("	Insert into  emprunt  (IdDocument,IdAbonnement,TypeDocument,DateEmprunt,DateRetour) values (?,?,?,?,?)" );
pr2.setInt(1,e.getIdDocument());
pr2.setInt(2,e.getIdLecteur());
pr2.setString(3,e.getTypeDocument().name());
pr2.setDate(4, e.getDateEmpruntl());
pr2.setDate(5,e.getDateRetour());
pr2.executeUpdate();
JOptionPane.showMessageDialog(null, "Le CdRom est bien emprunter", "Erreur",
		JOptionPane.PLAIN_MESSAGE);
}
else
JOptionPane.showMessageDialog(null, " La Quantite de ce CdRoom est null", " ", JOptionPane.WARNING_MESSAGE);
}
catch(SQLException d) {
JOptionPane.showMessageDialog(null, "Emprunter Document "+d, "Erreur"
		, JOptionPane.PLAIN_MESSAGE);
}

}else if(e.getTypeDocument().name().equals("Journal")){

try {
pr1 = (PreparedStatement) con.prepareStatement("Select QteDisponible from journaux where IdJournaux= ?" );
pr1.setInt(1, e.getIdDocument());
res=pr1.executeQuery();
res.next();
if(res.getInt("QteDisponible")>0) {

pr1 = (PreparedStatement) con.prepareStatement("update journaux set QteDisponible=QteDisponible-1 where IdJournaux= ?" );
pr1.setInt(1, e.getIdDocument());
pr1.executeUpdate();

//rs1.next();


pr2 = (PreparedStatement) con.prepareStatement("Insert into  emprunt (IdDocument,IdAbonnement,TypeDocument,DateEmprunt,DateRetour) values (?,?,?,?,?)" );
pr2.setInt(1,e.getIdDocument());
pr2.setInt(2,e.getIdLecteur());
pr2.setString(3,e.getTypeDocument().name());
pr2.setDate(4, e.getDateEmpruntl());
pr2.setDate(5,e.getDateRetour());
pr2.executeUpdate();
JOptionPane.showMessageDialog(null, "Le Journal est bien emprunter", "Erreur",
		JOptionPane.PLAIN_MESSAGE);
}
else
	JOptionPane.showMessageDialog(null, "La Quantite de ce Journal est null", " ", JOptionPane.WARNING_MESSAGE);

}
catch(SQLException d) {
JOptionPane.showMessageDialog(null, "Emprunter Document "+d, "Erreur"
		, JOptionPane.PLAIN_MESSAGE);
}

}
deconnecter();}


public void suprimerDocument(Document d) {
        Connecter();
		PreparedStatement p;
		PreparedStatement p1;

		try
		{
			if(d instanceof Livre) {
				Livre livre = (Livre) d;


				p1 = (PreparedStatement) con.prepareStatement("select Quantite from livres where TitreLivre= ?");
				p1.setString(1,livre.getTitre());
				ResultSet rs = p1.executeQuery();
				rs.next();
				if(rs.getInt(1) > 0) {
					p = (PreparedStatement) con.prepareStatement("update livres set 	Quantite = Quantite-1 ,QteDisponible=QteDisponible-1 where TitreLivre = ? ");
					p.setString(1, livre.getTitre());
					p.executeUpdate();
					JOptionPane.showMessageDialog(null, "la suppression est bien faite",
							"erreur", JOptionPane.PLAIN_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "La Quantite de ce Livre est null", " Erreur", JOptionPane.WARNING_MESSAGE);

			}
			else if(d instanceof CdRoom) {

				CdRoom cd = (CdRoom) d;
				p1 = (PreparedStatement) con.prepareStatement("select Quantite from cdroms where Titre= ?");
				p1.setString(1, cd.getTitre());
				ResultSet rs = p1.executeQuery();
				rs.next();

				if(rs.getInt("Quantite") > 0) {
					p = (PreparedStatement) con.prepareStatement("Update cdroms set Quantite = Quantite - 1,QteDisponible=QteDisponible-1 where Titre = ? ");
					p.setString(1, cd.getTitre());
					p.executeUpdate();
					JOptionPane.showMessageDialog(null, "la suppression est bien faite",
							"erreur", JOptionPane.PLAIN_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "La Quantite de ce CdRoom est null", " Erreur", JOptionPane.WARNING_MESSAGE);
			}

			else if(d instanceof MicroFilm) {


				MicroFilm m = (MicroFilm) d;

				p1 = (PreparedStatement) con.prepareStatement(" select 	Qantite from microfilms where Titre= ?");

				p1.setString(1, m.getTitre());


				ResultSet res = p1.executeQuery();

				res.next();

				if(res.getInt("Qantite") > 0) {

					p = (PreparedStatement) con.prepareStatement("update microfilms set Qantite = Qantite-1 ,QteDisponible=QteDisponible-1 where Titre = ? ");
					p.setString(1, m.getTitre());
					p.executeUpdate();

					JOptionPane.showMessageDialog(null, "la suppression est bien faite",
							"erreur", JOptionPane.PLAIN_MESSAGE);
				}

				else
					JOptionPane.showMessageDialog(null, "La Quantite de ce MicroFilm est null", " Erreur", JOptionPane.WARNING_MESSAGE);


			}
			else {
				Journal j = (Journal) d;
				p1 = (PreparedStatement) con.prepareStatement("select Quantite from journaux where Titre= = ?");
				p1.setString(1, j.getTitre());
				ResultSet rs = p1.executeQuery();
				rs.next();

				if(rs.getInt("Quantite") > 0) {
					p = (PreparedStatement) con.prepareStatement("Update journaux set Quantite = Quantite-1,QteDisponible=QteDisponible-1 where Titre= ? ");
					p.setString(1,j.getTitre());
					p.executeUpdate();
					JOptionPane.showMessageDialog(null, "la suppression est bien faite",
							"erreur", JOptionPane.PLAIN_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "La Quantite de ce Journal est null", " Erreur", JOptionPane.WARNING_MESSAGE);

			}

		}catch(SQLException e) {
          JOptionPane.showMessageDialog(null, e, "erreur",
				  JOptionPane.WARNING_MESSAGE);
		}

		deconnecter();
}



public void  RetournerDocument(int IdDocument,int Idlecteur,TypeDocument typedoc) {
    Connecter();
	PreparedStatement pr1;
try
{


	pr1 = (PreparedStatement) con.prepareStatement(" delete  from emprunt where  IdDocument=? and IdAbonnement=? and TypeDocument=?");
		pr1.setInt(1, IdDocument);
		pr1.setInt(2, Idlecteur);
		pr1.setString(3,typedoc.name());
		pr1.executeUpdate();
	if(typedoc.name().equals("Livre")) {
		  pr1 = (PreparedStatement) con.prepareStatement("update livres set QteDisponible=QteDisponible+1 where IdLivre= ?");

		  pr1.setInt(1,IdDocument);



		  pr1.executeUpdate();
		  JOptionPane.showMessageDialog(null,"le livre a ete retourne ","Succes ",JOptionPane.PLAIN_MESSAGE);
	}

	else if(typedoc.name().equals("CdRom")) {
		 pr1 = (PreparedStatement) con.prepareStatement(" update cdroms set QteDisponible=QteDisponible+1 where IdCdrom= ?");

		  pr1.setInt(1,IdDocument);
		  pr1.executeUpdate();
	   JOptionPane.showMessageDialog(null,"le cdrom a ete retourne ","Succes ",JOptionPane.PLAIN_MESSAGE);
	}
	else if(typedoc.name().equals("Journal")) {
		pr1 = (PreparedStatement) con.prepareStatement(" update journaux set  QteDisponible=QteDisponible+1 where IdJournaux= ?");

		 pr1.setInt(1,IdDocument);
	   pr1.executeUpdate();
	 JOptionPane.showMessageDialog(null,"le Journal a ete retourne ","Succes ",JOptionPane.PLAIN_MESSAGE);
}

}catch(SQLException e) {
JOptionPane.showMessageDialog(null, e, "ERREUR", JOptionPane.ERROR_MESSAGE);
}

deconnecter();}



	public ArrayList<String> VoirDetails(int IdDocument,int Idlecteur,TypeDocument typedoc) {
		Connecter();
		com.mysql.jdbc.PreparedStatement pr1,pr2;
		ResultSet res1,res2,res3,res4,res5,res6;
		ArrayList<String> details = new ArrayList();
		try
		{


			pr1 = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(" select * from emprunt where  IdDocument=? and IdAbonnement=? and TypeDocument=?");
			pr1.setInt(1, IdDocument);
			pr1.setInt(2, Idlecteur);
			pr1.setString(3,typedoc.name());
			res1=pr1.executeQuery();

			if(res1.next()) {
				pr1 = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(" select NomPersonne,PrenomPersonne from personne  where  IdPersonne=?");
				pr1.setInt(1, Idlecteur);
				res2=pr1.executeQuery();
				res2.next();

				if(typedoc.name().equals("Livre")) {


					pr1 = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("select TitreLivre from livres  where IdLivre= ?");

					pr1.setInt(1,IdDocument);

					res3=pr1.executeQuery();
					res3.next();


					details.add(Integer.toString(res1.getInt("IdDocument")));
					details.add(res3.getString("TitreLivre"));
					details.add(res2.getString("NomPersonne"));
					details.add(res2.getString("PrenomPersonne"));
					details.add(res1.getDate("DateEmprunt").toString());
					details.add(res1.getDate("DateRetour").toString());
					JOptionPane.showMessageDialog(null," Livre :Details",
							"Succes ",JOptionPane.PLAIN_MESSAGE);

				}
				else if(typedoc.name().equals("CdRom")) {
					pr1 = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(" Select Titre from  cdroms  where IdCdrom= ?");
					pr1.setInt(1,IdDocument);
					res4= pr1.executeQuery();
					res4.next();
					details.add(Integer.toString(res1.getInt("IdDocument")));
					details.add(res4.getString("Titre"));
					details.add(res2.getString("NomPersonne"));
					details.add(res2.getString("PrenomPersonne"));
					details.add(res1.getDate("DateEmprunt").toString());
					details.add(res1.getDate("DateRetour").toString());

					JOptionPane.showMessageDialog(null,
							"le cdrom : Details ","Succes ",JOptionPane.PLAIN_MESSAGE);
				}
				else if(typedoc.name().equals("Journal")) {
					pr1 = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(" Select Titre from journaux  where IdJournaux= ?");

					pr1.setInt(1,IdDocument);
					res5=pr1.executeQuery();
					res5.next();
					details.add(Integer.toString(res1.getInt("IdDocument")));
					details.add(res5.getString("Titre"));
					details.add(res2.getString("NomPersonne"));
					details.add(res2.getString("PrenomPersonne"));
					details.add(res1.getDate("DateEmprunt").toString());
					details.add(res1.getDate("DateRetour").toString());
					JOptionPane.showMessageDialog(null,"le Journal : Details","Succes ",JOptionPane.PLAIN_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null,"Les infrmations que vous avez tape n'exitent  pas Dans la table Emprunt  ");

			}


		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e, "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		deconnecter();
		return details;}
//__________________________________________________________________________________________________________________________

public void AcheterDocument(Achat e) {
	 PreparedStatement pr1,pr2 = null;
	 ResultSet res=null;
	 Connecter();

	 if((e.getTypeDocument().name().equals("Livre"))) {
			System.out.println("2");
		 try {

			pr1 = (PreparedStatement) con.prepareStatement("Select QteDisponible from Livres where IdLivre= ?" );
			pr1.setInt(1, e.getIdDocument());
			res=pr1.executeQuery();
			res.next();

			if(res.getInt("QteDisponible")>0) {
			 pr1 = (PreparedStatement) con.prepareStatement("	Update Livres set QteDisponible=QteDisponible-1 , Quantite = Quantite -1  where IdLivre= ?" );
			 pr1.setInt(1, e.getIdDocument());
			 pr1.executeUpdate();


				 pr2 = (PreparedStatement) con.prepareStatement("insert into  Achat    " +
						 "(IdDocument,IdAbonnement,TypeDocument,DateAchat) values (?,?,?,?)" );
				 pr2.setInt(1,e.getIdDocument());
				 pr2.setInt(2,e.getIdLecteur());
				 pr2.setString(3,e.getTypeDocument().name());
				 pr2.setDate(4, e.getDateAchat());
				 pr2.executeUpdate();
		 }
			else
				JOptionPane.showMessageDialog(null, "La Quantite Disponible de ce Livre est null", " Erreur", JOptionPane.WARNING_MESSAGE);
		 }
	 catch(SQLException d) {
		 System.out.println("Achat Livre"+d);
	}
	} else if(e.getTypeDocument().name().equals("CdRom")){


		try {
			 pr1 = (PreparedStatement) con.prepareStatement("Select QteDisponible from cdroms where IdCdrom= ?" );
			 pr1.setInt(1, e.getIdDocument());
			 res=pr1.executeQuery();
			 res.next();
			 if(res.getInt("QteDisponible")>0) {


			pr1 = (PreparedStatement) con.prepareStatement(" update cdroms set QteDisponible=QteDisponible-1 , Quantite = Quantite - 1  where IdCdrom= ?" );
			pr1.setInt(1, e.getIdDocument());
			pr1.executeUpdate();


			  pr2 = (PreparedStatement) con.prepareStatement("	Insert into  achat  " +
					  "(IdDocument, IdAbonnement, TypeDocument,DateAchat) values (?,?,?,?)" );
			 pr2.setInt(1,e.getIdDocument());
			 pr2.setInt(2,e.getIdLecteur());
			 pr2.setString(3,e.getTypeDocument().name());
			 pr2.setDate(4, e.getDateAchat());

				pr2.executeUpdate();
			 }
			 else
				JOptionPane.showMessageDialog(null, " La Quantite Disponible de ce CdRoom est null", " ", JOptionPane.WARNING_MESSAGE);
		}
	catch(SQLException d) {
	 System.out.println("Achat Document "+d);
	}
	}      else if(e.getTypeDocument().name().equals("Journal")){


			try {
				 pr1 = (PreparedStatement) con.prepareStatement("Select QteDisponible from journaux where IdJournaux= ?" );
				 pr1.setInt(1, e.getIdDocument());
				 res=pr1.executeQuery();
				 res.next();
				 if(res.getInt("QteDisponible")>0) {

				pr1 = (PreparedStatement) con.prepareStatement("	update journaux set QteDisponible=QteDisponible-1 , Quantite = Quantite - 1 where IdJournaux= ?" );
				pr1.setInt(1, e.getIdDocument());

				pr1.executeUpdate();

				 //rs1.next();


				 pr2 = (PreparedStatement) con.prepareStatement("	Insert into  achat    " +
						 "(IdDocument,IdAbonnement,TypeDocument,DateAchat) values (?,?,?,?)" );
				 pr2.setInt(1,e.getIdDocument());
				 pr2.setInt(2,e.getIdLecteur());
				 pr2.setString(3,e.getTypeDocument().name());
				 pr2.setDate(4, e.getDateAchat());
					pr2.executeUpdate();
			}
				 else
						JOptionPane.showMessageDialog(null, "La Quantite Disponible de ce Journal est null", " ", JOptionPane.WARNING_MESSAGE);

			}
		catch(SQLException d) {
			JOptionPane.showMessageDialog(null,"Emprunter Document "+d, "Erreur",
					JOptionPane.ERROR_MESSAGE);
	}

	}
		else {
			  try {
				  pr1 = (PreparedStatement) con.prepareStatement("Select QteDisponible from  microfilms where IdMicroFilm= ?" );
				  pr1.setInt(1, e.getIdDocument());
				  res=pr1.executeQuery();
				  res.next();
				  if(res.getInt("QteDisponible")>0) {

				  pr1 = (PreparedStatement) con.prepareStatement("	Update microfilms set QteDisponible=QteDisponible-1 ,Quantite = Quantite -1 where IdMicroFilm= ?" );
				  pr1.setInt(1, e.getIdDocument());

				  pr1.executeUpdate();

				   //rs1.next();


					 pr2 = (PreparedStatement) con.prepareStatement("	Insert into  emprunt    (IdDocument,IdAbonnement,TypeDocument,DateAchat) values (?,?,?,?)" );
					 pr2.setInt(1,e.getIdDocument());
					 pr2.setInt(2,e.getIdLecteur());
					 pr2.setString(3,e.getTypeDocument().name());
					 pr2.setDate(4, e.getDateAchat());
					   pr2.executeUpdate();
				  }
				  else
					  JOptionPane.showMessageDialog(null, "La Quantite Disponible de ce MicroFilm est null", " Erreur", JOptionPane.WARNING_MESSAGE);

			  }
		  catch(SQLException d) {
			 System.out.println("Achat Document "+d);

		}
		}

	deconnecter();}

	/**********************************  Ajouter Document **********************************/
	public void ajouterDocument(Document document,String path){
		Connecter();
		java.sql.PreparedStatement pr1;
		java.sql.PreparedStatement pr2, pr3;
/**LIVRE*/
		if (document instanceof Livre) {
			Livre livre = (Livre) document;
			try {
				// Id Auteur Auteur
				pr1 = (java.sql.PreparedStatement) con.prepareStatement("SELECT IdAuteur FROM Auteurs WHERE NomAuteur=? AND PrenomAuteur=?");
				pr1.setString(1, livre.getAuteur().getNom());
				pr1.setString(2, livre.getAuteur().getPrenom());
				ResultSet rs1 = pr1.executeQuery();

				//    Id Livre Livre
				pr1 = (java.sql.PreparedStatement) con.prepareStatement("SELECT IdLivre from livres WHERE TitreLivre=? ");
				pr1.setString(1, livre.getTitre());
				ResultSet res2 = pr1.executeQuery();

				if (res2.next()) {
					if (!rs1.next()) {
						pr1 = (java.sql.PreparedStatement) con.prepareStatement("INSERT INTO Auteurs (NomAuteur,PrenomAuteur) VALUES (?,?)");
						pr1.setString(1, livre.getAuteur().getNom());
						pr1.setString(2, livre.getAuteur().getPrenom());
						pr1.executeUpdate();
						pr1 = (java.sql.PreparedStatement) con.prepareStatement(" update livres set " +
								"Quantite=Quantite + ?,QteDisponible=QteDisponible+? where IdLivre= ?");

						pr1.setInt(1, livre.getQte());
						pr1.setInt(2, livre.getQte());
						pr1.setInt(3, res2.getInt(1));
						pr1.executeUpdate();

					} else {
						pr1 = (java.sql.PreparedStatement) con.prepareStatement("update livres set Quantite=Quantite+?,QteDisponible=QteDisponible+? where IdLivre= ?");

						pr1.setInt(1, livre.getQte());
						pr1.setInt(2, livre.getQte());
						pr1.setInt(3, res2.getInt(1));
						pr1.executeUpdate();
						JOptionPane.showMessageDialog(null, "La Quantite est bien ajouter",
								"Succe", JOptionPane.PLAIN_MESSAGE);

					}
				} else {
					if (rs1.next()) {
						pr2 = (java.sql.PreparedStatement) con.prepareStatement("INSERT INTO Livres (TitreLivre, Prix, Consultable,	Empruntable, " +
								"IdAuteur, Quantite, QteDisponible, Image) VALUES(?,?,?,?,?,?,?,?)");
						pr2.setString(1, livre.getTitre());
						pr2.setDouble(2, livre.getPrix());

						pr2.setBoolean(3, livre.isConsultable());
						pr2.setBoolean(4, livre.isEmpruntable());
						pr2.setInt(5, rs1.getInt(1));
						pr2.setInt(6, livre.getQte());
						pr2.setInt(7, livre.getQte());

						//ajouter Image
						InputStream image =new FileInputStream(new File(path));
						pr2.setBlob(8, image);
						pr2.executeUpdate();

					} else {


						pr1 = (java.sql.PreparedStatement) con.prepareStatement("INSERT INTO Auteurs (NomAuteur,PrenomAuteur) VALUES (?,?)");
						pr1.setString(1, livre.getAuteur().getNom());
						pr1.setString(2, livre.getAuteur().getPrenom());
						pr1.executeUpdate();

						pr2 = (java.sql.PreparedStatement) con.prepareStatement("SELECT IdAuteur FROM auteurs WHERE NomAuteur=? and PrenomAuteur=? ");
						pr2.setString(1, livre.getAuteur().getNom());
						pr2.setString(2, livre.getAuteur().getPrenom());
						rs1 = pr1.executeQuery();
						rs1.next();


						pr2 = (java.sql.PreparedStatement) con.prepareStatement("INSERT INTO Livres (TitreLivre, Prix, Consultable, Empruntable, IdAuteur,Quantite,QteDisponible, Image) VALUES (?,?,?,?,?,?,?,?)");
						pr2.setString(1, livre.getTitre());
						pr2.setDouble(2, livre.getPrix());
						pr2.setBoolean(3, livre.isConsultable());
						pr2.setBoolean(4, livre.isEmpruntable());
						pr2.setInt(5, rs1.getInt(1));
						pr2.setInt(6, livre.getQte());
						pr2.setInt(7, livre.getQte());

						//ajouter Image
						InputStream image =new FileInputStream(new File(path));
						pr2.setBlob(8, image);

						pr2.executeUpdate();
					}

					JOptionPane.showMessageDialog(null, "L'ajout est bien fait", "Succee", JOptionPane.PLAIN_MESSAGE);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e, "Erreur", JOptionPane.ERROR_MESSAGE);
			}

			/*CD_ROOM*/
		} else if (document instanceof CdRoom) {
			try {
				CdRoom cdRoom = (CdRoom) document;
				pr1 = (java.sql.PreparedStatement) con.prepareStatement("SELECT IdAuteur FROM Auteurs WHERE NomAuteur=? AND PrenomAuteur=?");
				pr1.setString(1, cdRoom.getAuteur().getNom());
				pr1.setString(2, cdRoom.getAuteur().getPrenom());
				ResultSet rs1 = pr1.executeQuery();

				pr1 = (java.sql.PreparedStatement) con.prepareStatement("SELECT IdCdrom from cdroms WHERE Titre=? ");
				pr1.setString(1, cdRoom.getTitre());
				ResultSet res2 = pr1.executeQuery();


				//prtie 2
				if (res2.next()) {
					if (!rs1.next()) {
						pr1 = (java.sql.PreparedStatement) con.prepareStatement("INSERT INTO Auteurs (NomAuteur,PrenomAuteur) VALUES (?,?)");
						pr1.setString(1, cdRoom.getAuteur().getNom());
						pr1.setString(2, cdRoom.getAuteur().getPrenom());
						pr1.executeUpdate();
						pr1 = (java.sql.PreparedStatement) con.prepareStatement(" update cdroms set Quantite=Quantite + ?, QteDisponible=QteDisponible+? where IdCdrom= ?");

						pr1.setInt(1, cdRoom.getQte());
						pr1.setInt(2, cdRoom.getQte());
						pr1.setInt(3, res2.getInt(1));
						pr1.executeUpdate();

					} else {
						pr1 = (java.sql.PreparedStatement) con.prepareStatement("update cdroms set Quantite=Quantite+?,QteDisponible=QteDisponible+? where IdCdrom= ?");

						pr1.setInt(1, cdRoom.getQte());
						pr1.setInt(2, cdRoom.getQte());
						pr1.setInt(3, res2.getInt(1));
						pr1.executeUpdate();
						JOptionPane.showMessageDialog(null, "La Quantite est bien ajouter",
								"Succe", JOptionPane.PLAIN_MESSAGE);

					}
				} else {
					if (rs1.next()) {

						pr2 = (java.sql.PreparedStatement) con.prepareStatement("INSERT INTO  cdroms (Titre,Caution,Consultable,Empruntable,TypeCdRom,IdAuteur,Quantite,QteDisponible, Image) VALUES(?,?,?,?,?,?,?,?,?)");
						pr2.setString(1, cdRoom.getTitre());
						pr2.setDouble(2, cdRoom.getPrix());
						pr2.setBoolean(3, cdRoom.isConsultable());
						pr2.setBoolean(4, cdRoom.isEmpruntable());
						pr2.setString(5, cdRoom.getType());
						pr2.setInt(6, rs1.getInt(1));
						pr2.setInt(7,  cdRoom.getQte());
						pr2.setInt(8, cdRoom.getQte());

						//ajouter Image
						InputStream image =new FileInputStream(new File(path));
						pr2.setBlob(9, image);
						pr2.executeUpdate();
					} else {
						pr1 = (java.sql.PreparedStatement) con.prepareStatement("INSERT INTO  auteurs (NomAuteur,PrenomAuteur) VALUES(?,?)");
						pr1.setString(1, cdRoom.getAuteur().getNom());
						pr1.setString(2, cdRoom.getAuteur().getPrenom());
						pr1.executeUpdate();

						pr1 = (java.sql.PreparedStatement) con.prepareStatement("SELECT IdAuteur FROM auteurs WHERE NomAuteur=? AND PrenomAuteur=?");
						pr1.setString(1, cdRoom.getAuteur().getNom());
						pr1.setString(2, cdRoom.getAuteur().getPrenom());

						rs1 = pr1.executeQuery();
						rs1.next();

						pr2 = con.prepareStatement("INSERT INTO cdroms (Titre,Caution,Consultable,Empruntable,TypeCdRom,IdAuteur,Quantite,	QteDisponible, Image) VALUES(?,?,?,?,?,?,?,?,?)");
						pr2.setString(1, cdRoom.getTitre());
						pr2.setDouble(2, cdRoom.getPrix());
						pr2.setBoolean(3, cdRoom.isConsultable());
						pr2.setBoolean(4, cdRoom.isEmpruntable());
						pr2.setString(5, cdRoom.getType());
						pr2.setInt(6, rs1.getInt(1));
						pr2.setInt(7, cdRoom.getQte());
						pr2.setInt(8, cdRoom.getQte());

						//ajouter Image
						InputStream image =new FileInputStream(new File(path));
						pr2.setBlob(9, image);
						pr2.executeUpdate();
					}


					JOptionPane.showMessageDialog(null, "L'ajout de Cdroom est bien fait", "Succee", JOptionPane.PLAIN_MESSAGE);
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e, "Erreur", JOptionPane.ERROR_MESSAGE);
			}

		} else if (document instanceof MicroFilm) {
			MicroFilm microFilm = (MicroFilm) document;
			try {
				pr1 = (java.sql.PreparedStatement) con.prepareStatement("SELECT IdMicroFilm from microfilms WHERE Titre= ? ");
				pr1.setString(1, microFilm.getTitre());
				ResultSet res2 = pr1.executeQuery();
				if (res2.next()) {
					pr1 = (java.sql.PreparedStatement) con.prepareStatement(" update microfilms set Quantite=Quantite + ? , QteDisponible=QteDisponible+? where IdMicroFilm= ?");

					pr1.setInt(1, microFilm.getQte());
					pr1.setInt(2, microFilm.getQte());
					pr1.setInt(3, res2.getInt(1));
					pr1.executeUpdate();
					JOptionPane.showMessageDialog(null, "La Quantite est bien ajouter",
							"Succe", JOptionPane.PLAIN_MESSAGE);
				} else {

					pr2 = (java.sql.PreparedStatement) con.prepareStatement("INSERT INTO microFilms ( Titre, Prix, Consultable,Empruntable,Quantite,QteDisponible, Image) VALUES(?,?,?,?,?,?,?)");
					pr2.setString(1, microFilm.getTitre());
					pr2.setDouble(2, microFilm.getPrix());
					pr2.setBoolean(3, microFilm.isConsultable());
					pr2.setBoolean(4, microFilm.isEmpruntable());
					pr2.setInt(5, microFilm.getQte());
					pr2.setInt(6, microFilm.getQte());

					//ajouter Image
					InputStream image =new FileInputStream(new File(path));
					pr2.setBlob(7, image);
					pr2.executeUpdate();

					JOptionPane.showMessageDialog(null, "L'ajout est bien fait", "Succee", JOptionPane.PLAIN_MESSAGE);
				}

			} catch (SQLException | FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			}

		} else {
			Journal journal = (Journal) document;
			try {
				pr1 = (java.sql.PreparedStatement) con.prepareStatement("SELECT IdJournaux from journaux WHERE Titre= ? ");
				pr1.setString(1, journal.getTitre());
				ResultSet res2 = pr1.executeQuery();
				if (res2.next()) {
					pr1 = (java.sql.PreparedStatement) con.prepareStatement(" update journaux set Quantite=Quantite + ? , QteDisponible=QteDisponible+? where IdJournaux= ?");

					pr1.setInt(1, journal.getQte());
					pr1.setInt(2, journal.getQte());
					pr1.setInt(3, res2.getInt(1));
					pr1.executeUpdate();
					JOptionPane.showMessageDialog(null, "La Quantite est bien ajouter",
							"Succe", JOptionPane.PLAIN_MESSAGE);
				} else {
					pr2 = (java.sql.PreparedStatement) con.prepareStatement("INSERT INTO journaux (Titre, Prix, Consultable, Empruntable,Quantite, QteDisponible, Image)  VALUES (?,?,?,?,?,?,?)");
					pr2.setString(1, journal.getTitre());
					pr2.setDouble(2, journal.getPrix());
					pr2.setBoolean(3, journal.isConsultable());
					pr2.setBoolean(4, journal.isEmpruntable());
					pr2.setInt(5, journal.getQte());
					pr2.setInt(6, journal.getQte());
					//ajouter Image
					InputStream image =new FileInputStream(new File(path));
					pr2.setBlob(7, image);

					pr2.executeUpdate();
					JOptionPane.showMessageDialog(null, "L'ajout est bien fait", "Succee", JOptionPane.PLAIN_MESSAGE);
				}
			} catch (SQLException | FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Erreur Ajouter Journal", "Erreur", JOptionPane.ERROR_MESSAGE);
			}

		}
      deconnecter();
	}


/********************************** Modifier Document *************************************/
	public void modifierDocument(Document d) {
		Connecter();
		PreparedStatement pr1;
		PreparedStatement pr2,pr3;

		try {
			if(d instanceof Livre){
				Livre livre = (Livre) d;
				//rs1.next();
				pr1 = (PreparedStatement) con.prepareStatement("SELECT IdAuteur FROM auteurs WHERE NomAuteur=? and PrenomAuteur=? ");
				pr1.setString(1, livre.getAuteur().getNom());
				pr1.setString(2, livre.getAuteur().getPrenom());
				ResultSet rs1 = pr1.executeQuery();

				if(rs1.next()) {
					pr2 = (PreparedStatement) con.prepareStatement("Update  livres set TitreLivre=?,Prix=?,Consultable=?,Empruntable=?,IdAuteur=?, Quantite=?"
							+ " where IdLivre = ?");

					pr2.setString(1,livre.getTitre());
					pr2.setDouble(2, livre.getPrix());
					pr2.setBoolean(3, livre.isConsultable());
					pr2.setBoolean(4, livre.isEmpruntable());
					pr2.setInt(5, rs1.getInt(1));
					pr2.setInt(6, livre.getQte());
					pr2.setInt(7, livre.getCote());

					pr2.executeUpdate();
				}
				else {
					//add auteur
					pr2 = (PreparedStatement) con.prepareStatement("Insert Into  auteurs (NomAuteur , PrenomAuteur)  values(?,?)  ");
					pr2.setString(1,livre.getAuteur().getNom());
					pr2.setString(2,livre.getAuteur().getPrenom());
					pr2.executeUpdate();

					CallableStatement cs =(CallableStatement) con.prepareCall("{call maxId } ");
					ResultSet rees =cs.executeQuery();
					rees.next();

					pr2 = (PreparedStatement) con.prepareStatement("Update  livres set TitreLivre=?,Prix=?,Consultable=?,Empruntable=?,IdAuteur=?,Quantite=?, "
							+ " where IdLivre = ? ");

					pr2.setString(1,livre.getTitre());
					pr2.setDouble(2, livre.getPrix());
					pr2.setBoolean(3, livre.isConsultable());
					pr2.setBoolean(4, livre.isEmpruntable());
					pr2.setInt(5, rees.getInt(1));
					pr2.setInt(6, livre.getQte());
					pr2.setInt(7, livre.getCote());
					pr2.executeUpdate();
				}

			}
			else if(d instanceof CdRoom){
				CdRoom cd = (CdRoom) d;
				//rs1.next();
				pr1 = (PreparedStatement) con.prepareStatement("SELECT IdAuteur FROM auteurs WHERE NomAuteur=? and PrenomAuteur=? ");
				pr1.setString(1, cd.getAuteur().getNom());
				pr1.setString(2, cd.getAuteur().getPrenom());
				ResultSet rs1 = pr1.executeQuery();

				if(rs1.next()){
					pr2 = (PreparedStatement) con.prepareStatement("Update  cdroms set Titre=?, Caution=?,Consultable=?,Empruntable=?,TypeCdRom=?,IdAuteur=?,Quantite=?"
							+ " where IdCdrom = ? ");

					pr2.setString(1, cd.getTitre());
					pr2.setDouble(2, cd.getPrix());
					pr2.setBoolean(3, cd.isConsultable());
					pr2.setBoolean(4, cd.isEmpruntable());
					pr2.setString(5, cd.getType());
					pr2.setInt(6, rs1.getInt(1));
					pr2.setInt(7, cd.getQte());
					pr2.setInt(8, cd.getCote());

					pr2.executeUpdate();

				}else {
					pr2 = (PreparedStatement) con.prepareStatement("Insert Into  auteurs (NomAuteur , PrenomAuteur)  values(?,?)  ");
					pr2.setString(1,cd.getAuteur().getNom());
					pr2.setString(2,cd.getAuteur().getPrenom());
					pr2.executeUpdate();

					CallableStatement cs =(CallableStatement) con.prepareCall("{call maxId } ");
					ResultSet rees =cs.executeQuery();
					rees.next();

					pr2 = (PreparedStatement) con.prepareStatement("Update  cdroms set Titre=?,Caution=?, " +
							"Consultable=?,Empruntable=?,TypeCdRom=?,IdAuteur=?,Quantite=?"
							+ " where Idcdrom = ? ");

					pr2.setString(1,cd.getTitre());
					pr2.setDouble(2, cd.getPrix());
					pr2.setBoolean(3, cd.isConsultable());
					pr2.setBoolean(4, cd.isEmpruntable());
					pr2.setString(5 ,cd.getType());
					pr2.setInt(6, rees.getInt(1));
					pr2.setInt(7, cd.getQte());
					pr2.setInt(8, cd.getCote());
					pr2.executeUpdate();

				}

			}
			else if(d instanceof Journal) {
				Journal jo = (Journal) d;

				pr2 = (PreparedStatement) con.prepareStatement("update  journaux set Titre=? " +
						",Prix=? ,Consultable=?,Empruntable=?,Quantite=?" +
						" where IdJournaux=?");

				pr2.setString(1,jo.getTitre());
				pr2.setDouble(2,jo.getPrix() );
				pr2.setBoolean(3,jo.isConsultable());
				pr2.setBoolean(4,jo.isEmpruntable() );
				pr2.setInt(5,jo.getQte());
				pr2.setInt(6, jo.getCote());
				pr2.executeUpdate();

			}else{

				MicroFilm m = ( MicroFilm) d;

				pr2 = (PreparedStatement) con.prepareStatement("update  microfilms set Titre=?" +
						",Prix=?,Consultable=?,Empruntable=?,Quantite=? where IdMicroFilm=?");

				pr2.setString(1,m.getTitre());
				pr2.setDouble(2,m.getPrix());
				pr2.setBoolean(3,m.isConsultable());
				pr2.setBoolean(4,m.isEmpruntable());
				pr2.setInt(5,m.getQte());
				pr2.setInt(6, m.getCote());
				pr2.executeUpdate();
			}

			JOptionPane.showMessageDialog(null, " la modification est bien fait",
					"Succee", JOptionPane.PLAIN_MESSAGE);

		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),
					"erreur", JOptionPane.ERROR_MESSAGE);
		}
		deconnecter();
	}


	public void modifierDocument(Document d, String path) {
		Connecter();
		PreparedStatement pr1;
		PreparedStatement pr2,pr3;

		InputStream image = null;
		try {
			image= new FileInputStream(new File(path));

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e, "Erreur",
					JOptionPane.ERROR_MESSAGE);
		}


		try {
			if(d instanceof Livre){
				Livre livre = (Livre) d;
				//rs1.next();
				pr1 = (PreparedStatement) con.prepareStatement("SELECT IdAuteur FROM auteurs WHERE NomAuteur=? and PrenomAuteur=? ");
				pr1.setString(1, livre.getAuteur().getNom());
				pr1.setString(2, livre.getAuteur().getPrenom());
				ResultSet rs1 = pr1.executeQuery();

				if(rs1.next()) {
					pr2 = (PreparedStatement) con.prepareStatement("Update  livres set TitreLivre=?,Prix=?,Consultable=?,Empruntable=?,IdAuteur=?, Quantite=?"
							+ ", Image=? where IdLivre = ?");

					pr2.setString(1,livre.getTitre());
					pr2.setDouble(2, livre.getPrix());
					pr2.setBoolean(3, livre.isConsultable());
					pr2.setBoolean(4, livre.isEmpruntable());
					pr2.setInt(5, rs1.getInt(1));
					pr2.setInt(6, livre.getQte());
					pr2.setBlob(7, image);
					pr2.setInt(8, livre.getCote());

					pr2.executeUpdate();
				}
				else {
					//add auteur
					pr2 = (PreparedStatement) con.prepareStatement("Insert Into  auteurs (NomAuteur , PrenomAuteur)  values(?,?)  ");
					pr2.setString(1,livre.getAuteur().getNom());
					pr2.setString(2,livre.getAuteur().getPrenom());
					pr2.executeUpdate();

					CallableStatement cs =(CallableStatement) con.prepareCall("{call maxId } ");
					ResultSet rees =cs.executeQuery();
					rees.next();

					pr2 = (PreparedStatement) con.prepareStatement("Update  livres set TitreLivre=?,Prix=?,Consultable=?,Empruntable=?,IdAuteur=?,Quantite=?, " +
							"Image= ?"
							+ " where IdLivre = ? ");

					pr2.setString(1,livre.getTitre());
					pr2.setDouble(2, livre.getPrix());
					pr2.setBoolean(3, livre.isConsultable());
					pr2.setBoolean(4, livre.isEmpruntable());
					pr2.setInt(5, rees.getInt(1));
					pr2.setInt(6, livre.getQte());
					pr2.setBlob(7, image);
					pr2.setInt(8, livre.getCote());
					pr2.executeUpdate();
				}

			}
			else if(d instanceof CdRoom){
				CdRoom cd = (CdRoom) d;
				//rs1.next();
				pr1 = (PreparedStatement) con.prepareStatement("SELECT IdAuteur FROM auteurs WHERE NomAuteur=? and PrenomAuteur=? ");
				pr1.setString(1, cd.getAuteur().getNom());
				pr1.setString(2, cd.getAuteur().getPrenom());
				ResultSet rs1 = pr1.executeQuery();

				if(rs1.next()){
					pr2 = (PreparedStatement) con.prepareStatement("Update  cdroms set Titre=?, Caution=?,Consultable=?,Empruntable=?,TypeCdRom=?,IdAuteur=?,Quantite=?, Image=?"
							+ " where IdCdrom = ? ");

					pr2.setString(1, cd.getTitre());
					pr2.setDouble(2, cd.getPrix());
					pr2.setBoolean(3, cd.isConsultable());
					pr2.setBoolean(4, cd.isEmpruntable());
					pr2.setString(5, cd.getType());
					pr2.setInt(6, rs1.getInt(1));
					pr2.setInt(7, cd.getQte());
					pr2.setBlob(8, image);
					pr2.setInt(9, cd.getCote());

					pr2.executeUpdate();

				}else {
					pr2 = (PreparedStatement) con.prepareStatement("Insert Into  auteurs (NomAuteur , PrenomAuteur)  values(?,?)  ");
					pr2.setString(1,cd.getAuteur().getNom());
					pr2.setString(2,cd.getAuteur().getPrenom());
					pr2.executeUpdate();

					CallableStatement cs =(CallableStatement) con.prepareCall("{call maxId } ");
					ResultSet rees =cs.executeQuery();
					rees.next();

					pr2 = (PreparedStatement) con.prepareStatement("Update  cdroms set Titre=?,Caution=?, " +
							"Consultable=?,Empruntable=?,TypeCdRom=?,IdAuteur=?,Quantite=?, Image=?"
							+ " where Idcdrom = ? ");

					pr2.setString(1,cd.getTitre());
					pr2.setDouble(2, cd.getPrix());
					pr2.setBoolean(3, cd.isConsultable());
					pr2.setBoolean(4, cd.isEmpruntable());
					pr2.setString(5 ,cd.getType());
					pr2.setInt(6, rees.getInt(1));
					pr2.setInt(7, cd.getQte());
					pr2.setBlob(8, image);
					pr2.setInt(9, cd.getCote());
					pr2.executeUpdate();

				}

			}
			else if(d instanceof Journal) {
				Journal jo = (Journal) d;

				pr2 = (PreparedStatement) con.prepareStatement("update  journaux set Titre=? " +
						",Prix=? ,Consultable=?,Empruntable=?,Quantite=?, Image=?" +
						" where IdJournaux=?");

				pr2.setString(1,jo.getTitre());
				pr2.setDouble(2,jo.getPrix() );
				pr2.setBoolean(3,jo.isConsultable());
				pr2.setBoolean(4,jo.isEmpruntable() );
				pr2.setInt(5,jo.getQte());
				pr2.setBlob(6, image);
				pr2.setInt(7, jo.getCote());
				pr2.executeUpdate();

			}else{

				MicroFilm m = ( MicroFilm) d;

				pr2 = (PreparedStatement) con.prepareStatement("update  microfilms set Titre=?" +
						",Prix=?,Consultable=?,Empruntable=?,Quantite=?, Image=? where IdMicroFilm=?");

				pr2.setString(1,m.getTitre());
				pr2.setDouble(2,m.getPrix());
				pr2.setBoolean(3,m.isConsultable());
				pr2.setBoolean(4,m.isEmpruntable());
				pr2.setInt(5,m.getQte());
				pr2.setBlob(6, image);
				pr2.setInt(7, m.getCote());
				pr2.executeUpdate();
			}

			JOptionPane.showMessageDialog(null, " la modification est bien fait",
					"Succee", JOptionPane.PLAIN_MESSAGE);

		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),
					"erreur", JOptionPane.ERROR_MESSAGE);
		}
	deconnecter();
	}

	public void accepterPersonne(Personne p){
	Connecter();
	try {
		 PreparedStatement pr = (PreparedStatement) con.prepareStatement("insert into Personne " +
				 "(NomPersonne, PrenomPersonne, Email, MotPasse, " +
				 "DateNaissance,  Sexe) values (?,?,?,?,?,?)");

		 pr.setString(1,p.getNom());
		 pr.setString(2, p.getPrenom());
		
		 pr.setString(3, p.getEmail());
		 pr.setString(4, p.getDateNaissance().toString());
		 pr.setDate(5, p.getDateNaissance());
		 pr.setString(6, p.getSexe());
		 pr.executeUpdate();

		JOptionPane.showMessageDialog(null, "La Lecteur est bien accepter",
				"Succee",
				JOptionPane.PLAIN_MESSAGE);

	}catch (Exception e){
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur",
				JOptionPane.ERROR_MESSAGE);
	}
deconnecter();
}

public void suppDemande(int id){
	Connecter();
	try {
		PreparedStatement pr = (PreparedStatement)
				con.prepareStatement("delete from Demandes where IdDemandes=?");
		pr.setInt(1, id);
		pr.executeUpdate();

	}catch (Exception e){
		JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur",
				JOptionPane.ERROR_MESSAGE);
	}

	deconnecter();
}

public void supprLecteur(int id){
	Connecter();
	try {
		PreparedStatement pr = (PreparedStatement)
				con.prepareStatement("delete from Personne where IdPersonne=?");
		pr.setInt(1, id);
		pr.executeUpdate();

		JOptionPane.showMessageDialog(null, "La suppression est bien faite",
				"Erreur",
				JOptionPane.PLAIN_MESSAGE);

	}catch (Exception e){
		JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur",
				JOptionPane.ERROR_MESSAGE);
	}

	deconnecter();
}

public void resutMotPasse(int id, Date date){
	Connecter();
	try {
		PreparedStatement pr= (PreparedStatement) con.prepareStatement("update Personne " +
				"set MotPasse = ? where IdPersonne = ?");
		pr.setString(1, date.toString());
		pr.setInt(2, id);
		pr.executeUpdate();

		JOptionPane.showMessageDialog(null, "le mot passe est modifier", "Erreur",
				JOptionPane.PLAIN_MESSAGE);

	}catch (Exception e){
		JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur",
				JOptionPane.ERROR_MESSAGE);
	}

	deconnecter();
}

public void abonner(Abonnemet ab){
	Connecter();
	try {
		PreparedStatement pr = (PreparedStatement) con.prepareStatement("insert into Abonnement " +
				"(IdPersonne, DateAbonnement, FinAbonnement)" +
				" values (?,?,?)");
		pr.setInt(1, ab.getIdLecteur());
		pr.setDate(2, ab.getDateDebut());
		pr.setDate(3, ab.getDateFin());
		pr.executeUpdate();

		JOptionPane.showMessageDialog(null, "L'abonnement est bien fait",
				"Erreur",
				JOptionPane.PLAIN_MESSAGE);

	}catch (Exception e){
	JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur",
			JOptionPane.WARNING_MESSAGE);
	}

	deconnecter();
}

public String[] detailsAbon(int id){
Connecter();
String info[] = new String[3];
try {
	PreparedStatement ps = (PreparedStatement)
			con.prepareStatement("SELECT NomPersonne, PrenomPersonne, FinAbonnement\n" +
			                           "FROM abonnement, personne\n" +
					                    "WHERE abonnement.IdPersonne = ? and\n"+
					                    "personne.IdPersonne= ? \n"+
					                    "ORDER BY abonnement.DateAbonnement");
	       ps.setInt(1, id);
	       ps.setInt(2, id);
		   ResultSet rs = ps.executeQuery();
		   rs.next();
		   info[0] = rs.getString(1);
		   info[1] = rs.getString(2);
		   info[2] = rs.getDate(3).toString();

}catch (Exception e){
JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur",
		JOptionPane.WARNING_MESSAGE);
}

return info;}


public void ajouterConsultation (int idMicroFilm,int idAbonnement,java.sql.Date dateConsultation,int idPost) {
	
	
	
	try {
		PreparedStatement p=con.prepareStatement("insert into consult values(?,?,?,?)");
		
		p.setInt(1,idMicroFilm);
		p.setInt(2, idAbonnement);
		p.setDate(3, dateConsultation);
		p.setInt(4, idPost);
		int res1=p.executeUpdate();

		if(res1==0) {
		JOptionPane.showMessageDialog(null, "Consultation failed","Erreur",JOptionPane.WARNING_MESSAGE);
		}
		else
		JOptionPane.showMessageDialog(null, "Consultation bien fait!","Succes",JOptionPane.PLAIN_MESSAGE);


		
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		JOptionPane.showInternalMessageDialog(null, e.getMessage(), "SQL ERORR",JOptionPane.ERROR_MESSAGE);
	}
	
	
	
	
	
	
	
}

public TableModel AffichageDesDonnes(String sql) {
	this.Connecter();
	java.sql.PreparedStatement pr1;
	ResultSet res;
	TableModel model=null;

	try {
		pr1 = (java.sql.PreparedStatement) con.prepareStatement(sql);
		res=pr1.executeQuery();
		model = DbUtils.resultSetToTableModel(res);
	}catch(SQLException e) {
		System.out.println("Affichage des donnes "+e);
	}
	return model;
	
    

    }


public Personne  login(String email,String password) {
	Personne per=null;
	System.out.println(password);
	 try {
		PreparedStatement  p=con.prepareStatement("select * from personne where Email=? and MotPasse=?");
		System.out.println("here");
		p.setString(1, email);
		p.setString(2, password);
		
		ResultSet res=p.executeQuery();
		
		if(res.next()) {
		 per=new Personne (res.getInt(1),res.getString(2),res.getString(3),
				 res.getString(4),res.getString(7),res.getDate(6),res.getString(8));
		System.out.println(new java.util.Date(res.getDate(6).getDate()));
		
		}
		else {
			JOptionPane.showInternalMessageDialog(null, "User Do Not Exists","USER_ERROR",JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
	
		JOptionPane.showInternalMessageDialog(null, "here: "+ e.getMessage(),"SQL_ERROR",JOptionPane.ERROR_MESSAGE);
	}
	return per;
	
	
	
	
	
	
	
}


public void ajouteImage (int idPersonne,String path){
	
	try {
       PreparedStatement ps = (PreparedStatement) con.prepareStatement("update personne set"
       		+ " image =? "
       		+ "where IdPersonne= ?");
       InputStream is = new FileInputStream(new File(path));

       ps.setBlob(1,is);
       ps.setInt(2, idPersonne);
       ps.executeUpdate();
       JOptionPane.showMessageDialog(null, "Data Inserted");
   }catch(Exception e){
	   JOptionPane.showMessageDialog(null, "ERORR addpic :"+e.getMessage());
   }

	
}


public ImageIcon getImage(int idPersonne) {
	ImageIcon MyImage=null;
	
	try {
	       PreparedStatement ps = (PreparedStatement) con.prepareStatement("select image from personne where IdPersonne=?;");
	       
	       
       
	     
	       ps.setInt(1, idPersonne);
	       ResultSet res=ps.executeQuery();
//	       File file=new File("E:\\sample_image.png");
//	        FileOutputStream fos=new FileOutputStream(file);
//	        byte b[];
//	        	Blob blob;
//	        	 while(res.next()){
//	                 blob=res.getBlob("image");
//	                 b=blob.getBytes(1,(int)blob.length());
//	                 fos.write(b);
//	             
//	        	
//	        	
//	        }
	      if(res.next()) {
	       
	       byte[] img=res.getBytes(1);
	       System.out.println(img.toString());
       MyImage = new ImageIcon(img);
	      }
             
           
    
	       }
	   catch(Exception e){
		   JOptionPane.showMessageDialog(null, "ERORR getPic :"+e.getMessage());
		   
		   
	   }
  return MyImage;
	
	
	
}


public void demnadeAccount(Personne personne) {
	
	
	try {
		PreparedStatement p=con.prepareStatement("insert into demandes(Nom, Prenom,DateNaissance, Email,Sexe) values (?,?,?,?,?); ");
		
		p.setString(1,personne.getNom());
		p.setString(2,personne.getPrenom());
		p.setDate(3,personne.getDateNaissance());
		p.setString(4,personne.getEmail());
		
		p.setString(5,personne.getSexe());
		
		int res=p.executeUpdate();
		
		if(res==1) {
			
			JOptionPane.showInternalMessageDialog(null,"Demander", "Succe",JOptionPane.PLAIN_MESSAGE);
		}
		
			
	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		   JOptionPane.showInternalMessageDialog(null,"ERORR: "+e.getMessage(), "ERORR",JOptionPane.ERROR_MESSAGE);	
	}
	
	
}

public int getStatistics(String type) {
	int count=0;
	if(type.equals("SommeDocument")) {
		try {
		PreparedStatement query1=con.prepareStatement("Select sum(Quantite) from microfilms");
		PreparedStatement query2=con.prepareStatement("Select sum(Quantite) from cdroms");
		PreparedStatement query3=con.prepareStatement("Select sum(Quantite) from journaux");
		PreparedStatement query4=con.prepareStatement("Select sum(Quantite) from livres");
		ResultSet res1=query1.executeQuery();
		res1.next();
		count=res1.getInt(1);
		ResultSet res2=query2.executeQuery();
		res2.next();
		count=count+res2.getInt(1);
		ResultSet res3=query3.executeQuery();
		res3.next();
		count=count+res3.getInt(1);
		ResultSet res4=query4.executeQuery();
		res4.next();
		count=count+res4.getInt(1);
		}
		catch(SQLException e) {
			
			
			count=0;
			System.out.println(e.getMessage());
			
		}
		
		return count;
		
	}
	
	if(type.equals("SommeLecture")) {
		try {
		PreparedStatement query=con.prepareStatement("Select Count(*) from personne where TypeUser='User';");
		ResultSet res=query.executeQuery();
		res.next();
	       count= res.getInt(1);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			return 0;
			
			
		}
		return count;
	}
	
	
	
	if(type.equals("SommeEmprunt")) {
		try {
			PreparedStatement query=con.prepareStatement("Select Count(*) from emprunt ;");
			ResultSet res=query.executeQuery();
			res.next();
		       count= res.getInt(1);
			}
			catch(SQLException e){
				System.out.println(e.getMessage());
				return 0;
				
				
			}
		return count;
		
	
	}
	
	if(type.equals("SommeDisponible")) {
			try {
			
			
			Statement query1=con.createStatement();
			Statement query2=con.createStatement();
			Statement query3=con.createStatement();
			Statement query4=con.createStatement();
			ResultSet res1=query1.executeQuery("select sum(QteDisponible) from Livres");
			res1.next();
			count=res1.getInt(1);
			ResultSet res2=query2.executeQuery("select sum(QteDisponible) from journaux");
			res2.next();
			count=count+res2.getInt(1);
			ResultSet res3=query3.executeQuery("select sum(QteDisponible) from microfilms");
			res3.next();
			count=count+res3.getInt(1);
			ResultSet res4=query4.executeQuery("select sum(QteDisponible) from cdroms");
			res4.next();
			count=count+res4.getInt(1);
			}
			catch(SQLException e) {
				count=0;
				System.out.println(e.getMessage());
				
			}
			
			return count;
			
		
		
	}
	return 0;
	
	
}

public ImageIcon recupImage(int id, TypeDocument type){
    Connecter();
	ImageIcon myImage = null;
	PreparedStatement ps;
	ResultSet res;
	try {
		if(type.equals(TypeDocument.Livre)){
			ps =  (PreparedStatement) con.prepareStatement("select Image " +
					"from Livres where IdLivre= ?");
			ps.setInt(1, id);
			res = ps.executeQuery();
			res.next();
			byte[] img = res.getBytes(1);
			myImage = new ImageIcon(img);

		}else if(type.equals(TypeDocument.CdRom)){
			ps =  (PreparedStatement) con.prepareStatement("select Image " +
					"from cdroms where IdCdrom= ?");
			ps.setInt(1, id);
			res = ps.executeQuery();
			res.next();
			byte[] img = res.getBytes(1);
			myImage = new ImageIcon(img);

		}else if(type.equals(TypeDocument.MicroFilm)){
			ps =  (PreparedStatement) con.prepareStatement("select Image " +
					"from microfilms where IdMicroFilm= ?");
			ps.setInt(1, id);
			res = ps.executeQuery();
			res.next();
			byte[] img = res.getBytes(1);
			myImage = new ImageIcon(img);

		}else if(type.equals(TypeDocument.Journal)){
			ps =  (PreparedStatement) con.prepareStatement("select Image " +
					"from journaux where IdJournaux= ?");
			ps.setInt(1, id);
			res = ps.executeQuery();
			res.next();
			byte[] img = res.getBytes(1);
			myImage = new ImageIcon(img);
		}

	}catch (Exception e){
		JOptionPane.showMessageDialog(null, e.getMessage(),
				"Erreur", JOptionPane.ERROR_MESSAGE);
	}

 return myImage;
 }


public int getStatistics(String type,int id) {
	int count=0;
	if(type.equals("SommeDocument")) {
		
		try {
		PreparedStatement query1=con.prepareStatement("Select sum(Quantite) from microfilms");
		PreparedStatement query2=con.prepareStatement("Select sum(Quantite) from cdroms");
		PreparedStatement query3=con.prepareStatement("Select sum(Quantite) from journaux");
		PreparedStatement query4=con.prepareStatement("Select sum(Quantite) from livres");
		ResultSet res1=query1.executeQuery();
		res1.next();
		count=res1.getInt(1);
		ResultSet res2=query2.executeQuery();
		res2.next();
		count=count+res2.getInt(1);
		ResultSet res3=query3.executeQuery();
		res3.next();
		count=count+res3.getInt(1);
		ResultSet res4=query4.executeQuery();
		res4.next();
		count=count+res4.getInt(1);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			
		}
		
		return count;
	}

	if(type.equals("SommeLecture")) {
		
		try {
		PreparedStatement query=con.prepareStatement("Select Count(*) from personne where TypeUser='User';");
		ResultSet res=query.executeQuery();
		res.next();
	       count= res.getInt(1);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			return 0;
		}
		return count;
	}
	
	
	
	 if(type.equals("SommeEmprunt")) {
		
		try {
			PreparedStatement query=con.prepareStatement("Select Count(*) from Emprunt  where IdAbonnement " +
					"in(select IdAbonnement from Abonnement where IdPersonne= ?) ");
			query.setInt(1, id);
			ResultSet res=query.executeQuery();
			res.next();
		       count= res.getInt(1);
			}
			catch(SQLException e){
				System.out.println(e.getMessage());
				return 0;
				
				
			}
		return count;

	 }if(type.equals("SommeAchat")){
		try {
			PreparedStatement query=con.prepareStatement("Select Count(*) from achat  where IdAbonnement " +
					"in (select IdAbonnement from Abonnement where IdPersonne= ?)");
			query.setInt(1, id);
			ResultSet res=query.executeQuery();

			res.next();
		       count= res.getInt(1);
		       return count;
			}
			catch(SQLException e){
				System.out.println(e.getMessage());
				return 0;
		}
	 }

	return  0;}

}



