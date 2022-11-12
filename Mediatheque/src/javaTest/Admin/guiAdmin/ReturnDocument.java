package javaTest.Admin.guiAdmin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReturnDocument extends JPanel{
   
	private JTextField idDocText; 
	private JLabel idDoc;
	private JTextField idLecText; 
	private JLabel idLec;
	private JButton voirDetail;
	private JButton returnDoc;
	private JComboBox typeCombo;
	private JLabel type;
	
    private String[] Items = {"Livre", "CdRom", "Journal"};

	
	public ReturnDocument() {
		
		typeCombo=new JComboBox(Items);
		typeCombo.setBackground(new Color(0xF5F6F8));
		type=new JLabel("Type");
		type.setFont(new Font(null,Font.BOLD,15));
		type.setForeground(Color.white);
		type.setFocusable(false);
		idDocText =new JTextField(15);
		idDocText.setFont(new Font(null,Font.BOLD,15));
		idDocText.setOpaque(false);
		idDocText.setBorder(BorderFactory.createMatteBorder(0, 0, 3,0,Color.LIGHT_GRAY));
		
		idDoc=new JLabel("Id Document");
		idDoc.setFont(new Font(null,Font.BOLD,15));
		idDoc.setForeground(Color.white);
		idDoc.setFocusable(false);
		idLecText =new JTextField(15);
		idLecText.setFont(new Font(null,Font.BOLD,15));
		idLecText.setOpaque(false);
		idLecText.setBorder(BorderFactory.createMatteBorder(0, 0, 3,0,Color.LIGHT_GRAY));
		
		idLec=new JLabel("Id Lecteur");
		idLec.setFont(new Font(null,Font.BOLD,15));
		idLec.setForeground(Color.white);
		idLec.setFocusable(false);
		
		voirDetail=new JButton("Voir Details");
		voirDetail.setFocusable(false);
		voirDetail.setFont(new Font(null,Font.BOLD,13));
		voirDetail.setBackground(Color.white);
		voirDetail.setForeground(new Color(239,130,86));
		returnDoc=new JButton("Retourner Document");
		returnDoc.setFocusable(false);
		returnDoc.setForeground(new Color(239,130,86));
		returnDoc.setBackground(Color.white);
		returnDoc.setFont(new Font(null,Font.BOLD,13));
		this.setLayout(null);

		
        idDoc.setBounds (50, 225, 100, 25);
       idDocText.setBounds (155, 153, 185, 30);
        idLec.setBounds (50, 155, 110, 30);
        idLecText.setBounds (160, 220, 185, 30);
        typeCombo.setBounds (155, 285, 100, 25);
         type.setBounds (50, 285, 100, 25);
        voirDetail.setBounds (30, 380, 130, 30);
        returnDoc.setBounds (200, 380, 170, 30);
        
        this.add(typeCombo);
        this.add(idDoc);
        this.add(idDocText);
        this.add(idLec);
        this.add(idLecText);
        this.add(typeCombo);
        this.add(type);
        this.add(voirDetail);
        this.add(returnDoc);
		
		
		
		
		this.setBackground(new Color(239,130,86));
		this.setPreferredSize(new Dimension(380,553));
		
		
		
	}
	
	public void paintComponent(Graphics g) {
		
		Graphics2D G=(Graphics2D)g;
		super.paintComponent(G);
		G.drawImage(new ImageIcon("images/delivering .png").getImage(),25,56,null );
	}

	public JTextField getIdDocText() {
		return idDocText;
	}

	public void setIdDocText(JTextField idDocText) {
		this.idDocText = idDocText;
	}

	public JLabel getIdDoc() {
		return idDoc;
	}

	public void setIdDoc(JLabel idDoc) {
		this.idDoc = idDoc;
	}

	public JTextField getIdLecText() {
		return idLecText;
	}

	public void setIdLecText(JTextField idLecText) {
		this.idLecText = idLecText;
	}

	public JLabel getIdLec() {
		return idLec;
	}

	public void setIdLec(JLabel idLec) {
		this.idLec = idLec;
	}

	public JButton getVoirDetail() {
		return voirDetail;
	}

	public void setVoirDetail(JButton voirDetail) {
		this.voirDetail = voirDetail;
	}

	public JButton getReturnDoc() {
		return returnDoc;
	}

	public void setReturnDoc(JButton returnDoc) {
		this.returnDoc = returnDoc;
	}

	public JComboBox getTypeCombo() {
		return typeCombo;
	}

	public void setTypeCombo(JComboBox typeCombo) {
		this.typeCombo = typeCombo;
	}

	public JLabel getType() {
		return type;
	}

	public void setType(JLabel type) {
		this.type = type;
	}

	public String[] getItems() {
		return Items;
	}

	public void setItems(String[] items) {
		Items = items;
	}
}
