package javaTest.Admin.guiUser;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.Icon;
	import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import javaTest.CONCEPTION.BaseDeDonne;
import javaTest.CONCEPTION.Document;
import javaTest.CONCEPTION.Personne;

public class ParaPanelU extends JPanel {
	
	private JLabel nom, prenom, datenaissance, ancienepswrd, neveaupswrd1,  neveaupswrd2;
	private JTextField nomF;
	private JTextField prenomF;
	private JDateChooser datenaissanceF;
	private JPasswordField ancienepswrdF;
	private JPasswordField neveaupswrd1F;
	private JPasswordField neveaupswrd2F;
	private JPanel panel;
	private JButton save;
	private JButton cancel;
    private BaseDeDonne db;
	private JLabel empty=new JLabel("     ");
	private Personne personne;


	  private Icon icon=new ImageIcon("images/user_icon.png");
	  private Color labelColor = new Color(0xDC865F);
	  Color titleColor  = new Color(0xD9AA63);
	  Color panelColor  = new Color(0xF9F3EE);
	  Color btnForg = new Color(0x643023);
	  Color btnBack  = new Color(0xD9AA63);
       
	
	private RoundPicU rpic;
	private UserFrame frame; 
	@SuppressWarnings("deprecation")
	public ParaPanelU(Personne personne ,UserFrame frame){
		this.personne=personne;
		this.frame=frame;
		
		save=new JButton("Save changes");
		save.setFont(new Font(null,Font.BOLD,20));
		save.setForeground(btnForg);
		save.setBackground(btnBack);
		save.setFocusable(false);
		save.addActionListener((e)->{
			//mot de passe
			if(ancienepswrdF.getText().isEmpty()||neveaupswrd1F.getText().isEmpty()||neveaupswrd2F.getText().isEmpty()) {
				System.out.println("out");
				
				
				
			}
			else {
				
				restPassword(personne,ancienepswrdF.getText(),neveaupswrd1F.getText(),neveaupswrd2F.getText());	
			System.out.println("in");
				
			}
			// personne info
			
			if(nomF.getText().isEmpty()||prenom.getText().isEmpty()||datenaissanceF.getDate()==null) {
				
				System.out.println("no update");
				
			}
			else {
				
				updatePersonne(nomF.getText(),prenomF.getText(),new java.sql.Date(datenaissanceF.getDate().getTime()),personne.getIdPersonne());
				
		
			}
			
			
			
			
			
		});

		cancel=new JButton("Cancel");
		cancel.setBackground(btnBack);
		cancel.setForeground(btnForg);
		cancel.setFont(new Font(null,Font.BOLD,20));
		cancel.setFocusable(false);
		
		
		rpic=new RoundPicU(personne,frame);
		
		
		panel=new JPanel();
		panel.setBackground(panelColor);
		
		nom=new JLabel("Nom                               ");
		nom.setForeground(labelColor);
	
		nom.setFont(new Font(null,Font.BOLD,20));
		prenom=new JLabel("Prenom                          ");
		prenom.setForeground(labelColor);
		prenom.setFont(new Font(null,Font.BOLD,20));
		datenaissance=new JLabel("Date de naissance         ");
		datenaissance.setForeground(labelColor);
		datenaissance.setFont(new Font(null,Font.BOLD,20));
		
		
		nomF=new JTextField(15);
		
		nomF.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, titleColor));
		nomF.setOpaque(false);
		nomF.setFont(new Font(null,Font.BOLD,20));
		prenomF=new JTextField(15);
		
		prenomF.setFont(new Font(null,Font.BOLD,20));
		prenomF.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, titleColor));
		prenomF.setOpaque(false);
		datenaissanceF=new JDateChooser();
		datenaissanceF.setOpaque(false);
		datenaissanceF.setDateFormatString("yyyy-MM-dd");
		datenaissanceF.getDateEditor().getUiComponent().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, titleColor));
		
		datenaissanceF.getDateEditor().getUiComponent().setOpaque(false);
		datenaissanceF.getDateEditor().getUiComponent().setFont(new Font(null,Font.BOLD,20));;
		datenaissanceF.getDateEditor().getUiComponent().setBackground(new Color(0xF2E5D0));

		ancienepswrd=new JLabel("Ancien mot de passe      ");
		ancienepswrd.setForeground(labelColor);
		ancienepswrd.setFont(new Font(null,Font.BOLD,20));
		
		neveaupswrd1=new JLabel("Noveau mot de passe    ");
		neveaupswrd1.setForeground(labelColor);
		neveaupswrd1.setFont(new Font(null,Font.BOLD,20));
		
		neveaupswrd2=new JLabel("Confirmer mot de passe ");
		neveaupswrd2.setForeground(labelColor);
		neveaupswrd2.setFont(new Font(null,Font.BOLD,20));
		
		ancienepswrdF=new JPasswordField(10);
		ancienepswrdF.setFont(new Font(null,Font.BOLD,20));
		
		ancienepswrdF.setOpaque(false);
		ancienepswrdF.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,titleColor ));
		
		neveaupswrd1F=new JPasswordField(10);
		neveaupswrd1F.setFont(new Font(null,Font.BOLD,20));
		
		neveaupswrd1F.setOpaque(false);
		neveaupswrd1F.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,titleColor));
		neveaupswrd2F=new JPasswordField(10);
		neveaupswrd2F.setFont(new Font(null,Font.BOLD,20));
		
		neveaupswrd2F.setOpaque(false);
		neveaupswrd2F.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,titleColor));
		
		
		fillPara(personne);
        
        
        
        
		panel.setLayout(new GridBagLayout());
		GridBagConstraints g=new GridBagConstraints();
		 //g.anchor=GridBagConstraints.FIRST_LINE_START;
		g.anchor=GridBagConstraints.LINE_START;
        
		
        g.gridx=0;
        g.gridy=0;
        panel.add(nom,g);
        //g.anchor=GridBagConstraints.LAST_LINE_END;
        g.anchor=GridBagConstraints.CENTER;
        g.insets=new Insets(0,0,10,0);
        g.gridx=1;
        g.gridy=0;
        panel.add(nomF,g);
       g.anchor=GridBagConstraints.LINE_START;
        
        g.gridx=0;
        g.gridy=1;
        panel.add(prenom,g);
        g.anchor=GridBagConstraints.CENTER;
        g.gridx=1;
        g.gridy=1;
        panel.add(prenomF,g);
        g.anchor=GridBagConstraints.LINE_START;
        
        g.gridx=0;
        g.gridy=2;
        
        panel.add(datenaissance,g);
        g.fill=GridBagConstraints.HORIZONTAL;
        g.anchor=GridBagConstraints.CENTER;
        //g.anchor=GridBagConstraints.LINE_START;
        
        g.gridx=1;
        g.gridy=2;
       
        panel.add(datenaissanceF,g);
        //g.anchor=GridBagConstraints.LINE_START;
        g.fill=GridBagConstraints.NONE;
        g.anchor=GridBagConstraints.LINE_START;
        g.gridx=0;
        g.gridy=3;
        
        panel.add(ancienepswrd,g);
        g.anchor=GridBagConstraints.CENTER;
        //g.anchor=GridBagConstraints.LAST_LINE_END;
        g.gridx=1;
        g.gridy=3;
        
        panel.add(ancienepswrdF,g);
        g.anchor=GridBagConstraints.LINE_START;
        //g.anchor=GridBagConstraints.LINE_START;
        g.gridx=0;
        g.gridy=4;
        
        panel.add(neveaupswrd1,g);
        g.anchor=GridBagConstraints.CENTER;
        //g.anchor=GridBagConstraints.LAST_LINE_END;
        g.gridx=1;
        g.gridy=4;
        
        panel.add(neveaupswrd1F,g);
        g.anchor=GridBagConstraints.LINE_START;
        //g.anchor=GridBagConstraints.LINE_START;
        g.gridx=0;
        g.gridy=5;
        
        
        panel.add(neveaupswrd2,g);
        g.anchor=GridBagConstraints.CENTER;
        
        //g.anchor=GridBagConstraints.LAST_LINE_END;
        g.gridx=1;
        g.gridy=5;
        
        panel.add(neveaupswrd2F,g);
        g.anchor=GridBagConstraints.LINE_END;
        g.insets=new Insets(0,10,0,0);
		g.gridx=0;
		g.gridy=6;
		panel.add(empty,g);
        g.gridx=0;
        g.gridy=7;
        panel.add(save,g);
        g.insets=new Insets(0,10,0,0);
        g.anchor=GridBagConstraints.LINE_START;
        
        g.gridx=1;
        g.gridy=7;
        panel.add(cancel,g);
		
		this.setLayout(new BorderLayout());
		
		this.add(rpic,BorderLayout.NORTH);
		this.add(panel,BorderLayout.CENTER);

		
		this.setPreferredSize(new Dimension(780,553));
		this.setBackground(new Color(245,247,246));
	}
	
	
	
	
	
	public void fillPara(Personne personne) {
		if(personne==null)return;
		nomF.setText(personne.getNom());
		prenomF.setText(personne.getPrenom());
		datenaissanceF.getDateEditor().setDate((Date)personne.getDateNaissance());
		
	}
	
	
	public void restPassword(Personne personne,String AncienMotPasse,String NouveauMotPasse,String Confirmation) {
		 db=new BaseDeDonne();
		 db.Connecter();
		db.ModifierMotPasse(personne.getIdPersonne(),AncienMotPasse,NouveauMotPasse,Confirmation);
		db.deconnecter();
		
		
		
		
		
	}
	
	
	public void updatePersonne(String nom,String prenom ,java.sql.Date datenaissance,int idPersonne) {
		
		db=new BaseDeDonne();
		 db.Connecter();
		db.ModifierPersonne(nom,prenom ,datenaissance,idPersonne);
		
		db.deconnecter();
		frame.setUserName(prenom+" "+nom);
		
		
	}
	
	
	
	 





	
	  

	 
	}

