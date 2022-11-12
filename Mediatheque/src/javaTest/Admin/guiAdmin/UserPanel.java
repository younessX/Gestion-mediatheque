package javaTest.Admin.guiAdmin;

import com.toedter.calendar.JDateChooser;
import javaTest.CONCEPTION.Abonnemet;
import javaTest.CONCEPTION.BaseDeDonne;
import javaTest.CONCEPTION.Personne;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;

public class UserPanel extends JPanel implements ActionListener {
 private  TablePanel tabelPanelDem, tabelPanelLec, tabelPanelAbo;
 private  JButton abonnerBtn, detailsBtn, accepteBtn, resutBtn, refuseBtn, supprBtn;
 private BaseDeDonne operation;
 private JLabel nomPreField, dernierField;

 public UserPanel(){
  operation = new BaseDeDonne();
  ImageIcon sarchIcon = new ImageIcon("images/search.png");
  //colors
  Color panelColor  = new Color(0xF9F3EE);
  Color titleColor  = new Color(0xD9AA63);
  Color tabColor = new Color(0xF5F6F8);
  Color labelColor = new Color(0x643023);

  //font
  Font fontField = new Font("montserrat", Font.PLAIN, 14);
  Font fontBtn =  new Font("montserrat", Font.BOLD, 12);
  Font fontLabel = new Font("montserrat", Font.BOLD, 18);

//Adding TabbedPane Object
  JTabbedPane tabbedPane = new JTabbedPane();

  JPanel lecteurPanel = new JPanel();
  lecteurPanel.setLayout(null);
  lecteurPanel.setBackground(panelColor);

  JPanel abonPanel = new JPanel();
  abonPanel.setLayout(null);
  abonPanel.setBackground(panelColor);

  //tabbedPanel
  tabbedPane.setBounds(0,0,780,550);
  tabbedPane.add("Lecteur",lecteurPanel);
  tabbedPane.add("Abonnement",abonPanel);


  //Lecteur Panel
  /**lecteur*/
  JPanel panelT = new JPanel();
  panelT.setBackground(panelColor);
  TitledBorder lecteurBorder = new TitledBorder(BorderFactory.createLineBorder(titleColor),"Lecteurs",
          TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 20));

  lecteurBorder.setTitleColor(titleColor);
  panelT.setBorder(lecteurBorder);

  panelT.setLayout(null);
  tabelPanelLec = new TablePanel();
  TableModel modelLec = operation.AffichageDesDonnes("select IdPersonne, PrenomPersonne,NomPersonne ,Email, " +
          "DateNaissance, " +
          " Sexe from Personne where TypeUser='User';");
  tabelPanelLec.setModel(modelLec);
  JScrollPane scroll1 = new JScrollPane(tabelPanelLec);
  scroll1.setBounds(5, 60,765, 150);
  panelT.add(scroll1);

  //buttons
  supprBtn = new JButton("Supprimer");
  supprBtn.setBackground(titleColor);
  supprBtn.setBounds(245, 223, 110, 33);
  supprBtn.setFocusable(false);
  supprBtn.setFont(fontBtn);
  panelT.add(supprBtn);
  supprBtn.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
     if(tabelPanelLec.getSelectedRowCount()==1){
      int row = tabelPanelLec.getSelectedRow();
      int id = (int) tabelPanelLec.getValueAt(row, 0);
      operation.supprLecteur(id);

      //refreche
      TableModel modelLec = operation.AffichageDesDonnes("select IdPersonne, PrenomPersonne, Email, " +
              "DateNaissance, " +
           " Sexe from Personne where TypeUser ='User';");
      tabelPanelLec.setModel(modelLec);

     }else{
      JOptionPane.showMessageDialog(null, "Selectionez une seule ligne",
              "Erreur",
              JOptionPane.ERROR_MESSAGE);
     }
   }
  });

  resutBtn  = new JButton("Resut Mot Passe");
  resutBtn.setBackground(titleColor);
  resutBtn.setFocusable(false);
  resutBtn.setBounds(395, 223,140, 33);
  resutBtn.setFont(fontBtn);
  panelT.add(resutBtn);
  resutBtn.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    if(tabelPanelLec.getSelectedRowCount()==1){
     int row = tabelPanelLec.getSelectedRow();
     int id = (int) tabelPanelLec.getValueAt(row, 0);
     Date date = (Date) tabelPanelLec.getValueAt(row, 4);
     operation.resutMotPasse(id, date);

     //refreche
     TableModel modelLec = operation.AffichageDesDonnes(" select IdPersonne, PrenomPersonne, Email, DateNaissance, " +
             " Sexe from Personne where TypeUser='User';");

     tabelPanelLec.setModel(modelLec);
    }else{
     JOptionPane.showMessageDialog(null, "Selectionez une seule ligne",
             "Erreur",
             JOptionPane.ERROR_MESSAGE);
    }
   }
  });

  //searchLecteur
  JTextField searchLecteur = new JTextField();
  searchLecteur.setOpaque(false);
  searchLecteur.setBorder( BorderFactory.createMatteBorder(0, 0, 2, 0, titleColor));
  searchLecteur.setBounds(310, 20, 160, 25);
  searchLecteur.setFont(fontField);
  panelT.add(searchLecteur);
  searchLecteur.addKeyListener(new KeyAdapter() {
   @Override
   public void keyTyped(KeyEvent e) {
    String text = searchLecteur.getText().trim();
    TableRowSorter<TableModel> trs = new TableRowSorter<>(tabelPanelLec.getModel());
    tabelPanelLec.setRowSorter(trs);
    trs.setRowFilter(RowFilter.regexFilter(text));
   }
  });

  JLabel labelSearchLec = new JLabel();
  labelSearchLec.setBounds(280, 25, 25, 25);
  labelSearchLec.setIcon(sarchIcon);
  panelT.add(labelSearchLec);


  panelT.setBounds(1, 255, 774, 265);
  lecteurPanel.add(panelT);

  /**demande*/
  JPanel panelT2 = new JPanel();
  panelT2.setLayout(null);

  TitledBorder demaBorder = new TitledBorder(BorderFactory.createLineBorder(titleColor),"Demandes",
          TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 20));

  demaBorder.setTitleColor(titleColor);
  panelT2.setBorder(demaBorder);

  tabelPanelDem = new TablePanel();
  TableModel modelDem = operation.AffichageDesDonnes("SELECT * FROM Demandes");
  tabelPanelDem.setModel(modelDem);
  JScrollPane scroll2 = new JScrollPane(tabelPanelDem);
  scroll2.setBounds(5, 60, 765,140);
  panelT2.add(scroll2);
  panelT2.setBackground(panelColor);

  //buttons
  accepteBtn = new JButton("Accépter");
  accepteBtn.setBackground(titleColor);
  accepteBtn.setBounds(245, 210, 110, 33);
  accepteBtn.setFocusable(false);
  accepteBtn.setFont(fontBtn);
  accepteBtn.addActionListener(this);
  panelT2.add(accepteBtn);

  refuseBtn  = new JButton("Refuser");
  refuseBtn.setBackground(titleColor);
  refuseBtn.setFocusable(false);
  refuseBtn.setBounds(395, 210,110, 33);
  refuseBtn.setFont(fontBtn);
  panelT2.add(refuseBtn);
  refuseBtn.addActionListener(this);

  //searchDemande
  JTextField searchDemande = new JTextField();
  searchDemande.setOpaque(false);
  searchDemande.setBorder( BorderFactory.createMatteBorder(0, 0, 2, 0, titleColor));
  searchDemande.setBounds(310, 20, 160, 25);
  searchDemande.setFont(fontField);
  panelT2.add(searchDemande);
  searchDemande.addKeyListener(new KeyAdapter() {
   @Override
   public void keyTyped(KeyEvent e) {
    String text = searchDemande.getText().trim();
    TableRowSorter<TableModel> trs = new TableRowSorter<>(tabelPanelDem.getModel());
    tabelPanelDem.setRowSorter(trs);
    trs.setRowFilter(RowFilter.regexFilter(text));
   }
  });

  JLabel labelSearchDem = new JLabel();
  labelSearchDem.setBounds(280, 25, 25, 25);
  labelSearchDem.setIcon(sarchIcon);
  panelT2.add(labelSearchDem);


  panelT2.setBounds(1, 2, 774, 250);
  lecteurPanel.add(panelT2);


  //abonnementPanel
  JPanel panelAb = new JPanel();
  panelAb.setLayout(null);
  panelAb.setBackground(panelColor);
  TitledBorder abonBorder = new TitledBorder(BorderFactory.createLineBorder(titleColor),"Abonnement",
          TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 20));

  abonBorder.setTitleColor(titleColor);
  panelAb.setBorder(abonBorder);

  //lecteur
  JLabel lecteurLabel =  new JLabel("Id Lecteur");
  lecteurLabel.setBounds(10, 35, 120, 30);
  lecteurLabel.setFont(fontLabel);
  lecteurLabel.setForeground(labelColor);
  panelAb.add( lecteurLabel);

  JTextField  lecteurField = new JTextField();
  lecteurField.setFont(fontField);
  lecteurField.setBounds(200, 35, 180, 35);
  panelAb.add(lecteurField);

  //Abonnement
  JLabel dateAbonLabel =  new JLabel("Date Abonnement");
  dateAbonLabel.setBounds(10, 90, 170, 30);
  dateAbonLabel.setFont(fontLabel);
  dateAbonLabel.setForeground(labelColor);
  panelAb.add(dateAbonLabel);

  JDateChooser dateAbon = new JDateChooser();
  dateAbon.setDateFormatString("yyyy-MM-dd");
  dateAbon.getDateEditor().getUiComponent().setOpaque(false);
  dateAbon.getDateEditor().getUiComponent()
          .setBorder(BorderFactory
          .createMatteBorder(0, 0, 2, 0,new Color(185,152,138)));
  dateAbon.getDateEditor().getUiComponent().setFont(fontField);
  dateAbon.setBackground(panelColor);

  dateAbon.setBounds(200, 90, 180, 35);
  panelAb.add(dateAbon);

  //date Fin
  JLabel dateFinLabel =  new JLabel("Fin Abonnement");
  dateFinLabel.setBounds(10, 145, 170, 30);
  dateFinLabel.setFont(fontLabel);
  dateFinLabel.setForeground(labelColor);
  panelAb.add(dateFinLabel);

  JDateChooser datefin = new JDateChooser();
  datefin.setDateFormatString("yyyy-MM-dd");
  datefin.getDateEditor().getUiComponent().setOpaque(false);
  datefin.getDateEditor().getUiComponent()
          .setBorder(BorderFactory
          .createMatteBorder(0, 0, 2, 0,new Color(185,152,138)));

  datefin.getDateEditor().getUiComponent().setFont(fontField);
  datefin.setBackground(panelColor);
  datefin.setBounds(200, 145, 180, 30);
  panelAb.add(datefin);

  //abonnerBtn
  abonnerBtn = new JButton("Abonner");
  abonnerBtn.setFocusable(false);
  abonnerBtn.setFont(fontBtn);
  abonnerBtn.setBounds(70, 220, 110, 35);
  abonnerBtn.setForeground(labelColor);
  abonnerBtn.setBackground(titleColor);
  panelAb.add(abonnerBtn);
  abonnerBtn.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    try {
     int idLec=0;
     Date dateDebut, dateFin;
     if(!lecteurField.getText().isEmpty()){
      idLec = Integer.parseInt(lecteurField.getText());
      dateDebut = new Date(dateAbon.getDate().getTime());
      dateFin   = new Date(datefin.getDate().getTime());
      operation.abonner(new Abonnemet(idLec, dateDebut, dateFin));
      TableModel modelD = operation.AffichageDesDonnes("SELECT * FROM Abonnement");
      tabelPanelAbo.setModel(modelD);

     }else  JOptionPane.showMessageDialog(null, "Remplir tous les chapms",
             "Erreur", JOptionPane.WARNING_MESSAGE);

    }catch (Exception ex){
     JOptionPane.showMessageDialog(null, "Remplir tous les chapms",
             "Erreur", JOptionPane.WARNING_MESSAGE);
    }
   }
  });

  //detailsBtn
  detailsBtn = new JButton("Plus de details");
  detailsBtn.setFocusable(false);
  detailsBtn.setFont(fontBtn);
  detailsBtn.setBounds(210, 220, 130, 35);
  detailsBtn.setForeground(labelColor);
  detailsBtn.setBackground(titleColor);
  panelAb.add(detailsBtn);
  panelAb.setBounds(5, 5, 400, 270);
  abonPanel.add(panelAb);
  detailsBtn.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    if(!lecteurField.getText().isEmpty()){
     int id = Integer.parseInt(lecteurField.getText());
     String[] info = operation.detailsAbon(id);
     nomPreField.setText(info[0]+" "+info[1]);
     dernierField.setText(info[2]);
    }else
     JOptionPane.showMessageDialog(null, "Sasisir le idLecteur", "Erreur",
             JOptionPane.ERROR_MESSAGE);
   }
  });

  /**information*/
  JPanel infoPanel = new JPanel();
  infoPanel.setLayout(null);
  infoPanel.setBounds(410, 5, 360,270);
  TitledBorder infoBorder = new TitledBorder(BorderFactory.createLineBorder(titleColor),"Details",
          TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 20));

  infoPanel.setBackground(panelColor);
  infoBorder.setTitleColor(titleColor);
  infoPanel.setBorder(infoBorder);

  //Label Icon
  ImageIcon icon =  new ImageIcon("images/details.png");
  JLabel labelIcon = new JLabel();
  labelIcon.setBounds(116, 20, 128, 128);
  labelIcon.setIcon(icon);
  infoPanel.add(labelIcon);

  //Nom et Prenom
  JLabel nomPreLabel =  new JLabel("Nom et Prenom");
  nomPreLabel.setBounds(10, 160, 140, 30);
  nomPreLabel.setFont(fontLabel);
  nomPreLabel.setForeground(labelColor);
  infoPanel.add( nomPreLabel);

  nomPreField = new JLabel();
  nomPreField.setFont(new Font(null,Font.BOLD,17));
  nomPreField.setBounds(160, 160, 190, 35);
  
  infoPanel.add(nomPreField);

  //date dernier abonn
  JLabel dernierLabel =  new JLabel("Dernier Abonn");
  dernierLabel.setBounds(10, 210, 180, 30);
  dernierLabel.setFont(fontLabel);
  dernierLabel.setForeground(labelColor);
  infoPanel.add(dernierLabel);

  dernierField = new JLabel();
  dernierField.setFont(new Font(null,Font.BOLD,17));
  dernierField.setBounds(160, 210, 190, 35);
  
  infoPanel.add(dernierField);

  abonPanel.add(infoPanel);

  //table
  tabelPanelAbo = new TablePanel();
  TableModel modelD = operation.AffichageDesDonnes("SELECT * FROM Abonnement");
  tabelPanelAbo.setModel(modelD);

  JPanel panelT1 = new JPanel();
  panelT1.setLayout(new BorderLayout());

  panelT1.add(new JScrollPane(tabelPanelAbo), BorderLayout.CENTER);
  panelT1.setBounds(1, 280, 774, 240);
  abonPanel.add(panelT1);

  this.setLayout(null);
  this.setBackground(tabColor);
  this.add(tabbedPane);
  this.setBounds( 0, 0, 780, 553);
 }


 @Override
 public void actionPerformed(ActionEvent e) {
if(tabelPanelDem.getSelectedRowCount()==1){
  if(e.getSource() == accepteBtn){
   int row = tabelPanelDem.getSelectedRow();
      int id = (int) tabelPanelDem.getValueAt(row, 0);
      String nom    =(String) tabelPanelDem.getValueAt(row, 1);
      String prenom =(String) tabelPanelDem.getValueAt(row, 2);
      Date   dateN  =(Date) tabelPanelDem.getValueAt(row, 3);
      String email  =(String) tabelPanelDem.getValueAt(row, 4);

      String sexe   =(String) tabelPanelDem.getValueAt(row, 5);
      Personne  per = new Personne(nom, prenom, email, sexe,dateN);
      operation.accepterPersonne(per);
      operation.suppDemande(id);
      TableModel modelDem = operation.AffichageDesDonnes("SELECT * FROM Demandes");
      tabelPanelDem.setModel(modelDem);
      TableModel modelLec = operation.AffichageDesDonnes("select IdPersonne, PrenomPersonne, Email, " +
              "DateNaissance, " +
              " Sexe from Personne where TypeUser='User';");
      tabelPanelLec.setModel(modelLec);
   }else if(e.getSource() == refuseBtn){
      int row = tabelPanelDem.getSelectedRow();
     operation.suppDemande((int)tabelPanelDem.getValueAt(row, 0));
     
     TableModel modelDem = operation.AffichageDesDonnes("SELECT * FROM Demandes");
     tabelPanelDem.setModel(modelDem);
   }

}else{
 JOptionPane.showMessageDialog(null, "Selectionez une seule ligne", "Erreur",
         JOptionPane.ERROR_MESSAGE);
}

 }

}
