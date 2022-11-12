package javaTest.Admin.guiUser;
import javaTest.Admin.guiAdmin.TablePanel;
import javaTest.CONCEPTION.BaseDeDonne;
import javaTest.CONCEPTION.TypeDocument;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.*;

public class BiblioPanel extends JPanel {
 private TablePanel tabelPanelEmp, tabelPanelAch;
 private  BaseDeDonne operation;
 private int idPersonne;
 private  JLabel labelImage;
 public BiblioPanel(int idPersonne){
  this.idPersonne = idPersonne;
  Font fontLabel = new Font("tahoma", Font.ITALIC|Font.BOLD, 13);

  operation = new BaseDeDonne();
  this.setLayout(null);

  labelImage = new JLabel();
  labelImage.setBounds(580, 5, 195, 300);
  labelImage.setBorder(BorderFactory.createLineBorder(Color.black));
  this.add(labelImage);

  //table Emprunt
  JLabel labelEmp = new JLabel("Dernier emprunts");
  labelEmp.setFont(fontLabel);
  labelEmp.setBounds(300, 275, 130, 40);
  this.add(labelEmp);
  tabelPanelEmp = new TablePanel();
  TableModel modelEmp = operation.AffichageDesDonnes("SELECT IdDocument, DateEmprunt, " +
                  "DateRetour, TypeDocument "+
                  "FROM Emprunt where IdAbonnement in "+
                  "(SELECT IdAbonnement FROM abonnement "+
                          "WHERE IdPersonne= " +idPersonne+")");
  tabelPanelEmp.setModel(modelEmp);

  JPanel panelT1 = new JPanel();
  panelT1.setLayout(new BorderLayout());

  panelT1.add(new JScrollPane(tabelPanelEmp), BorderLayout.CENTER);
  panelT1.setBounds(1, 310, 774, 240);
  this.add(panelT1);
  tabelPanelEmp.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
   @Override
   public void valueChanged(ListSelectionEvent e) {
    int row = tabelPanelEmp.getSelectedRow();
    int idDoc = (int)tabelPanelEmp.getValueAt(row, 0);
    String type = (String) tabelPanelEmp.getValueAt(row, 3);
    TypeDocument typeDo=null;
    switch (type){
     case "Livre":
      typeDo = TypeDocument.Livre;
      break;

     case "CdRom":
      typeDo =  TypeDocument.CdRom;
      break;

     case "Journal":
      typeDo = TypeDocument.Journal;
      break;
    }
    labelImage.setIcon(new ImageIcon(operation.recupImage(idDoc, typeDo)
            .getImage().getScaledInstance(195, 300, Image.SCALE_SMOOTH)));
   }
  });

  //table Achat
  JLabel labelAch = new JLabel("Dernier Achats");
  labelAch.setBounds(300, 5, 130, 40);
  labelAch.setFont(fontLabel);
  this.add(labelAch);

  tabelPanelAch = new TablePanel();
  TableModel modelAch = operation.AffichageDesDonnes("SELECT IdDocument, DateAchat, TypeDocument" +
          " FROM Achat where IdAbonnement in (SELECT IdAbonnement FROM abonnement WHERE IdPersonne= "
          +idPersonne+")");

  tabelPanelAch.setModel(modelAch);

  JPanel panelT2 = new JPanel();
  panelT2.setLayout(new BorderLayout());

  panelT2.add(new JScrollPane(tabelPanelAch), BorderLayout.CENTER);
  panelT2.setBounds(1, 40, 574, 240);
  this.add(panelT2);

  tabelPanelAch.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
   @Override
   public void valueChanged(ListSelectionEvent e) {
    int row = tabelPanelAch.getSelectedRow();
    int idDoc = (int)tabelPanelAch.getValueAt(row, 0);
    String type = (String) tabelPanelAch.getValueAt(row, 2);
    TypeDocument typeDo=null;
    switch (type){
     case "Livre":
      typeDo = TypeDocument.Livre;
      break;

     case "CdRom":
      typeDo =  TypeDocument.CdRom;
      break;

     case "Journal":
      typeDo = TypeDocument.Journal;
      break;
    }
    labelImage.setIcon(new ImageIcon(operation.recupImage(idDoc, typeDo)
    .getImage().getScaledInstance(195, 300, Image.SCALE_SMOOTH)));
   }
  });

 }

}
