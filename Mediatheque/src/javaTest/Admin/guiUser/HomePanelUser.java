package javaTest.Admin.guiUser;

import javaTest.Admin.guiAdmin.InfoPanel;
import javaTest.Admin.guiAdmin.TotalClass;

import javax.swing.*;
import java.awt.*;

public class HomePanelUser extends JPanel{
 private int idPersonne;
 public HomePanelUser(int idPersonne){
  this.idPersonne = idPersonne;
  ImageIcon logoIcon = new ImageIcon("pictures\\Logo.png");

  JLabel logoLabel = new JLabel();
  logoLabel.setIcon(logoIcon);
  logoLabel.setBounds(285, 5, 200, 92);
  //logoLabel.setBorder(BorderFactory.createLineBorder(Color.black));
  this.add(logoLabel);

  InfoPanelU panel1 = new InfoPanelU(new TotalClassU("Total Document", "",
          new ImageIcon("images\\document.png"), new Color(0x2296F4),
          "SommeDocument", idPersonne));

  InfoPanelU panel2 = new InfoPanelU(new TotalClassU("Total lecteurs", "",
          new ImageIcon("images\\reading.png"),
          new Color(0x4DB151),"SommeLecture", idPersonne));

  InfoPanelU panel3 = new InfoPanelU(new TotalClassU("Documents empruntes", "",
          new ImageIcon("images\\emprunt.png"),
          new Color(0x725FEC),"SommeEmprunt", idPersonne));

  InfoPanelU panel4 = new InfoPanelU(new TotalClassU("Documents Achetees", "",
          new ImageIcon("images\\acheterLivre.png"),
          new Color(0x3B5A93),"SommeAchat", idPersonne));

  panel1.setBounds(4, 80, 190, 140);
  panel2.setBounds(198, 80, 190, 140);
  panel3.setBounds(392, 80, 190, 140);
  panel4.setBounds(585, 80, 190, 140);

  this.setLayout(null);
  this.setBackground(new Color(0xF5F6F8));
  this.add(panel1);
  this.add(panel2);
  this.add(panel3);
  this.add(panel4);

  //partie des images
  ImageIcon icon1 = new ImageIcon(new ImageIcon("pictures\\image1.jpg")
          .getImage()
          .getScaledInstance(170, 210, Image.SCALE_DEFAULT));

  ImageIcon icon2= new ImageIcon(new ImageIcon("pictures\\image2.jpg")
          .getImage()
          .getScaledInstance(170, 210, Image.SCALE_DEFAULT));

  ImageIcon icon3 = new ImageIcon(new ImageIcon("pictures\\image3.jpg")
          .getImage()
          .getScaledInstance(170, 210, Image.SCALE_DEFAULT));

  ImageIcon icon4 = new ImageIcon(new ImageIcon("pictures\\image4.jpg")
          .getImage()
          .getScaledInstance(170, 210, Image.SCALE_DEFAULT));

  ImageIcon icon5 = new ImageIcon(new ImageIcon("pictures\\image5.jpg")
          .getImage()
          .getScaledInstance(170, 210, Image.SCALE_DEFAULT));

  ImageIcon icon6 = new ImageIcon(new ImageIcon("pictures\\image6.jpg")
          .getImage()
          .getScaledInstance(170, 210, Image.SCALE_DEFAULT));

  ImageIcon icon7 = new ImageIcon(new ImageIcon("pictures\\image7.jpg")
          .getImage()
          .getScaledInstance(170, 210, Image.SCALE_DEFAULT));

  JLabel label = new JLabel("Dernières Documents Ajouter");
  label.setBounds(10, 265, 250, 40);
  label.setFont(new Font("tahoma", Font.ITALIC|Font.BOLD, 13));

  this.add(label);

  JPanel panel = new JPanel();

  JLabel label1 = new JLabel();
  JLabel label2 = new JLabel();
  JLabel label3 = new JLabel();
  JLabel label4 = new JLabel();
  JLabel label5 = new JLabel();
  JLabel label6 = new JLabel();
  JLabel label7 = new JLabel();

  label1.setIcon(icon1);
  label2.setIcon(icon2);
  label3.setIcon(icon3);
  label4.setIcon(icon4);
  label5.setIcon(icon5);
  label6.setIcon(icon6);
  label7.setIcon(icon7);


  panel.add(label1);
  panel.add(label2);
  panel.add(label3);
  panel.add(label4);
  panel.add(label5);
  panel.add(label6);
  panel.add(label7);

  this.setSize(600, 600);
  this.setLayout(null);

  JScrollPane scroll = new JScrollPane(panel);
  scroll.setBorder(BorderFactory.createEmptyBorder());
  scroll.setBounds(5, 300, 770, 240);
  this.add(scroll);
 }

}
