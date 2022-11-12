package javaTest.FrontPage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import com.toedter.calendar.JDateChooser;

import javaTest.CONCEPTION.BaseDeDonne;
import javaTest.CONCEPTION.Personne;
public class DemandePanel extends JPanel {




    private JLabel jcomp1;
    private JLabel jcomp2;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JLabel jcomp5;
    private JLabel jcomp6;
    private JTextField jcomp7;
    private JTextField jcomp8;
    private JDateChooser jcomp9;
    private JTextField jcomp10;
    private JButton jcomp11;
    private JButton jcomp12;
    private JRadioButton jcomp13;
    private JRadioButton jcomp14;
    private boolean isOffMale=true;
    private boolean isOffFemale=false;
    private ButtonGroup gb;
    private Personne personne;
    public DemandePanel() {
        //construct components]
    	Color attrColor =new Color(0xebccc5);
        jcomp1 = new JLabel ("Demander Votre Compte");
        jcomp1.setFont(new Font("sansserif",Font.BOLD,30));
        jcomp1.setForeground(new Color(0xdf7042));
        jcomp2 = new JLabel ("Sexe");
        jcomp2.setForeground(attrColor);
        jcomp2.setFont(new Font("sansserif",Font.PLAIN,15));
        jcomp3 = new JLabel ("Prenom");
        jcomp3.setForeground(attrColor);
        jcomp3.setFont(new Font("sansserif",Font.PLAIN,15));
        jcomp4 = new JLabel ("Date naissance");
        jcomp4.setForeground(attrColor);
        jcomp4.setFont(new Font("sansserif",Font.PLAIN,15));
        jcomp5 = new JLabel ("Nom");
        jcomp5.setForeground(attrColor);
        jcomp5.setFont(new Font("sansserif",Font.PLAIN,15));
        jcomp6 = new JLabel ("E-mail");
        jcomp6.setForeground(attrColor);
        jcomp6.setFont(new Font("sansserif",Font.PLAIN,15));
        jcomp7 = new MyTextField ();
        jcomp8 = new MyTextField ();
        jcomp9 = new JDateChooser ();
        jcomp9.setOpaque(false);
        jcomp9.setDateFormatString("yyyy-MM-dd");
        jcomp9.getDateEditor().getUiComponent().setOpaque(false);
        jcomp9.setFont(new Font(null,Font.BOLD,17));
        jcomp9.setForeground(new Color(0xebccc5));
        jcomp9.getDateEditor().getUiComponent().setFont(new Font(null,Font.BOLD,17));
        jcomp9.getDateEditor().getUiComponent().setForeground(new Color(0xebccc5));
        jcomp9.setSize(100, 100);
        jcomp9.getDateEditor().getUiComponent().setBorder(BorderFactory.createMatteBorder(2, 2,2, 2,new Color(0xd28666)));
        jcomp10 = new MyTextField ();
        jcomp11 = new MyButton ();
        jcomp11.setText("Demander");
        jcomp11.setFont(new Font(null,Font.BOLD,15));
        jcomp11.addActionListener((e)->{
        	
        	demandercompte();
        });
        jcomp12 = new JButton ();
        jcomp12.setText("Retour au login");
        jcomp12.setForeground(new Color(0x3184A4));
        jcomp12.setBorderPainted(false);
        jcomp12.setFocusable(false);
        jcomp12.setContentAreaFilled(false);
        jcomp12.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				 jcomp12.setForeground(Color.red);
				 jcomp12.setFont(new Font(null,Font.BOLD,12));
			}

			public void mouseExited(MouseEvent e) {
				 jcomp12.setForeground(new Color(0x3184A4));
				 jcomp12.setFont(new Font(null,Font.BOLD,11));
			}
        	
        	
        	
        });
        
        
        jcomp13 = new JRadioButton ("Male");
        jcomp13.setFocusable(false);
        jcomp13.setIcon(new ImageIcon("radio-button2.png"));
        jcomp13.setActionCommand("Male");
        
        
        gb=new ButtonGroup();
        gb.add(jcomp13);
        gb.add(jcomp14);
        jcomp13.addActionListener((event)->{

				if(isOffMale) {
					
				jcomp13.setIcon(new ImageIcon("radio-button1.png"));
				isOffFemale=false;
				jcomp14.doClick();
				
				isOffMale=false;
				}
				else  {
					jcomp13.setIcon(new ImageIcon("radio-button2.png"));
					isOffMale=true;

					
				}
        	
        	
        });
        
        
        jcomp13.setFont(new Font("sansserif",Font.PLAIN,15));
        jcomp13.setForeground(new Color(0xdf7042));
        jcomp13.setOpaque(false);
       
        jcomp14 = new JRadioButton ("Female");
        jcomp14.setActionCommand("Female");
        jcomp14.setFocusable(false);
        jcomp14.setIcon(new ImageIcon("radio-button1.png"));
        jcomp14.addActionListener((event)->{

			if(isOffFemale) {
				
			jcomp14.setIcon(new ImageIcon("radio-button1.png"));
			isOffMale=false;
			jcomp13.doClick();
			isOffFemale=false;
			}
			else  {
				jcomp14.setIcon(new ImageIcon("radio-button2.png"));
				isOffFemale=true;

}});
        
        
        jcomp14.setFont(new Font("sansserif",Font.PLAIN,15));
        jcomp14.setForeground(new Color(0xdf7042));
        jcomp14.setOpaque(false);
        jcomp14.setActionCommand("Female");
       
        
        gb=new ButtonGroup();
        gb.add(jcomp13);
        gb.add(jcomp14);
        
        
        //adjust size and set layout
        setPreferredSize (new Dimension (787, 534));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcomp6);
        add (jcomp7);
        add (jcomp8);
        add (jcomp9);
        add (jcomp10);
        add (jcomp11);
        add (jcomp12);
        add (jcomp13);
        add (jcomp14);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (220, 40, 380, 35);
        jcomp2.setBounds (270, 235, 100, 25);
        jcomp3.setBounds (270, 105, 100, 25);
        jcomp4.setBounds (270, 365, 150, 25);
        jcomp5.setBounds (270, 170, 100, 25);
        jcomp6.setBounds (270, 300, 100, 25);
        jcomp7.setBounds (270, 195, 255, 33);
        jcomp8.setBounds (270, 325, 255, 33);
        jcomp9.setBounds (270, 390, 255, 33);
        jcomp10.setBounds (270, 130, 255, 33);
        jcomp11.setBounds (270, 440, 255, 33);
        jcomp12.setBounds (330, 500, 150, 25);
        jcomp13.setBounds (295, 260, 80, 25);
        jcomp14.setBounds (365, 260, 80, 25);
    }
    public JButton ReturnLogin() {
    	
    	
    	return jcomp12;
    }

    
	public void paintComponent(Graphics g) {
		
		Graphics2D G= (Graphics2D)g;
		super.paintComponent(G);

			G.drawImage(new ImageIcon("Bhome.png").getImage(), 0, 0,null);
		G.drawImage(new ImageIcon("logo.png").getImage(), -10, -30,null);
	
		G.drawImage(new ImageIcon("book.png").getImage(), 220, 55,null);
			
	}
	public void demandercompte() {
		
		BaseDeDonne db=new BaseDeDonne();
		db.Connecter();
		String sexe = gb.getSelection().getActionCommand();
		switch(sexe) {
		case "Male":
			sexe="Femme";
			break;
		case "Female":
		    sexe="Homme";
			break;
		}
		
		personne=new Personne(jcomp10.getText(),jcomp7.getText(),jcomp8.getText(),sexe,new java.sql.Date(jcomp9.getDate().getTime()));
		
		if(personne!=null)
		db.demnadeAccount(personne);
		jcomp7.setText("");
		jcomp8.setText("");
		jcomp10.setText("");
		jcomp9.setDate(null);
		db.deconnecter();
		
		
	}

 


}
