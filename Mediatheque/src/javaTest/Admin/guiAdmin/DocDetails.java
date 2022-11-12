package javaTest.Admin.guiAdmin;

import javax.swing.*;
import java.awt.*;

public class DocDetails extends JPanel{

	   private JLabel idDoc;
	   private JLabel titreDoc;
	   private JLabel nomEmp;
	   private JLabel dateEmp;
	   private JLabel datereturn;
	   private JLabel idDocAN;
	   private JLabel titreDocAN;
	   private JLabel nomEmpAN;
	   private JLabel dateEmpAN;
	   private JLabel datereturnAN;
	   private JLabel desc;
	   
		
		public DocDetails() {
			desc= new JLabel("Details de Document");
			desc.setFont(new Font(null,Font.BOLD,30));
			idDoc =new JLabel("id Document     :");
			idDoc.setFont(new Font(null,Font.BOLD,20));
			titreDoc =new JLabel("Titre Document :");
			
			nomEmp =new JLabel("Emprunteur       :");
			nomEmp.setFont(new Font(null,Font.BOLD,20));
			dateEmp =new JLabel("Date Emprunt    :");
			dateEmp.setFont(new Font(null,Font.BOLD,20));
			datereturn =new JLabel("Date de  retour  :");
			datereturn.setFont(new Font(null,Font.BOLD,20));
			idDocAN =new JLabel(" ");
			titreDoc.setFont(new Font(null,Font.BOLD,20));
			idDocAN.setFont(new Font(null,Font.BOLD,20));
			titreDocAN =new JLabel(" ");
			titreDocAN.setFont(new Font(null,Font.BOLD,20));
			nomEmpAN =new JLabel(" ");
			nomEmpAN.setFont(new Font(null,Font.BOLD,20));
			dateEmpAN =new JLabel(" ");
			dateEmpAN.setFont(new Font(null,Font.BOLD,20));
			datereturnAN =new JLabel(" ");
			datereturnAN.setFont(new Font(null,Font.BOLD,20));
			this.setLayout(new GridBagLayout());
			GridBagConstraints g=new GridBagConstraints();
			
			
			
			
			g.insets=new Insets(20,10,0,0);
			g.anchor=GridBagConstraints.FIRST_LINE_START;
		
			
			
			g.gridx=0;
			g.gridy=1;
			this.add(idDoc,g);
			g.gridx=1;
			g.gridy=1;
			this.add(idDocAN,g);
			g.gridx=0;
			g.gridy=2;
			this.add(titreDoc,g);
			g.gridx=1;
			g.gridy=2;
			this.add(titreDocAN,g);
			g.gridx=0;
			g.gridy=3;
			this.add(nomEmp,g);
			g.gridx=1;
			g.gridy=3;
			this.add(nomEmpAN,g);
			g.gridx=0;
			g.gridy=4;
			this.add(dateEmp,g);
			g.gridx=1;
			g.gridy=4;
			this.add(dateEmpAN,g);
			g.gridx=0;
			g.gridy=5;
			this.add(datereturn,g);
			g.gridx=1;
			g.gridy=5;
			this.add(datereturnAN,g);
			
			
			this.setBackground(Color.WHITE);
			this.setPreferredSize(new Dimension(400,553));
			
			
		}
		
		
		
		public void paintComponent(Graphics g) {
			
			Graphics2D G=(Graphics2D)g;
			super.paintComponent(G);
			G.drawImage(new ImageIcon("images/detail.png").getImage(),50,50,null );
			
			
		}

	//getters and setters

	public JLabel getIdDoc() {
		return idDoc;
	}

	public void setIdDoc(JLabel idDoc) {
		this.idDoc = idDoc;
	}

	public JLabel getTitreDoc() {
		return titreDoc;
	}

	public void setTitreDoc(JLabel titreDoc) {
		this.titreDoc = titreDoc;
	}

	public JLabel getNomEmp() {
		return nomEmp;
	}

	public void setNomEmp(JLabel nomEmp) {
		this.nomEmp = nomEmp;
	}

	public JLabel getDateEmp() {
		return dateEmp;
	}

	public void setDateEmp(JLabel dateEmp) {
		this.dateEmp = dateEmp;
	}

	public JLabel getDatereturn() {
		return datereturn;
	}

	public void setDatereturn(JLabel datereturn) {
		this.datereturn = datereturn;
	}

	public JLabel getIdDocAN() {
		return idDocAN;
	}

	public void setIdDocAN(JLabel idDocAN) {
		this.idDocAN = idDocAN;
	}

	public JLabel getTitreDocAN() {
		return titreDocAN;
	}

	public void setTitreDocAN(JLabel titreDocAN) {
		this.titreDocAN = titreDocAN;
	}

	public JLabel getNomEmpAN() {
		return nomEmpAN;
	}

	public void setNomEmpAN(JLabel nomEmpAN) {
		this.nomEmpAN = nomEmpAN;
	}

	public JLabel getDateEmpAN() {
		return dateEmpAN;
	}

	public void setDateEmpAN(JLabel dateEmpAN) {
		this.dateEmpAN = dateEmpAN;
	}

	public JLabel getDatereturnAN() {
		return datereturnAN;
	}

	public void setDatereturnAN(JLabel datereturnAN) {
		this.datereturnAN = datereturnAN;
	}

	public JLabel getDesc() {
		return desc;
	}

	public void setDesc(JLabel desc) {
		this.desc = desc;
	}
}
