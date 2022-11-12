package javaTest.Admin.guiAdmin;
import javaTest.CONCEPTION.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

public class DocumentPanel extends JPanel implements ActionListener {
   private JButton ajouterBtn, modifierBtn, supprimerBtn;
   private JLabel labelImage;
   private JComboBox<String> types, cdRomTypes;
   private JPanel panelT;
   private TablePanel tabelPanel=null;
   private JTextField searchField, fieldNomAuteur, fieldPrenomAuteur, prixField, fieldTitre,
           quantityField;
   private JRadioButton ouiBtn1, nonBtn1, ouiBtn2, nonBtn2;
   private ButtonGroup gropeEmpr, gropeCons;
   public  static  int id;
   private BaseDeDonne operation;
   private String path=null;

   public DocumentPanel() {
      operation = new BaseDeDonne();

      //colors
      Color labelColor = new Color(0x643023);
      Color panelColor = new Color(0xF9F3EE);
      Color titleColor = new Color(0xD9AA63);

      //icons
      ImageIcon sarchIcon = new ImageIcon("images/search.png");

      //partie des informations
      Font fontLabel = new Font("montserrat", Font.BOLD, 18);
      Font fontField = new Font("montserrat", Font.PLAIN, 15);
      Font fontRadioBtn = new Font("montserrat", Font.BOLD, 15);

      //search
      searchField = new JTextField();
      searchField.setOpaque(false);
      searchField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, titleColor));
      searchField.setBounds(310, 0, 160, 35);
      searchField.setFont(fontField);
      this.add(searchField);
      searchField.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            if (tabelPanel != null) {
               String text = searchField.getText().trim();
               TableRowSorter<TableModel> trs = new TableRowSorter<>(tabelPanel.getModel());
               tabelPanel.setRowSorter(trs);
               trs.setRowFilter(RowFilter.regexFilter(text));
            }
         }
      });

      JLabel labelSearch = new JLabel();
      labelSearch.setBounds(280, 10, 25, 25);
      labelSearch.setIcon(sarchIcon);
      this.add(labelSearch);

      //Left Panel (Document)
      JPanel panelLeft = new JPanel();
      panelLeft.setBackground(panelColor);
      TitledBorder documentBorder = new TitledBorder(BorderFactory.createLineBorder(titleColor),
              "Document",
              TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 20));

      documentBorder.setTitleColor(titleColor);
      panelLeft.setBorder(documentBorder);


      panelLeft.setBounds(5, 40, 770, 370);
      panelLeft.setLayout(null);

      //titre
      JLabel labelTitre = new JLabel("Titre");
      labelTitre.setFont(fontLabel);
      labelTitre.setForeground(labelColor);
      fieldTitre = new JTextField();
      fieldTitre.setFont(fontField);

      labelTitre.setBounds(10, 25, 100, 30);
      fieldTitre.setBounds(130, 25, 250, 35);
      panelLeft.add(labelTitre);
      panelLeft.add(fieldTitre);

      //Auteur
      JPanel panelAuteur = new JPanel();
      TitledBorder auteurBorder = new TitledBorder(BorderFactory.createLineBorder(titleColor),
              "Auteur",
              TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 20));

      auteurBorder.setTitleColor(titleColor);
      panelAuteur.setBorder(auteurBorder);
      panelAuteur.setBackground(panelColor);

      panelAuteur.setLayout(null);
      panelAuteur.setBounds(5, 65, 380, 103);

      JLabel labelNomAuteur = new JLabel("Nom");
      labelNomAuteur.setFont(fontLabel);
      labelNomAuteur.setForeground(labelColor);
      labelNomAuteur.setBounds(10, 20, 100, 30);

      fieldNomAuteur = new JTextField();
      fieldNomAuteur.setFont(fontField);
      fieldNomAuteur.setBounds(125, 20, 250, 35);

      JLabel labelPrenomAuteur = new JLabel("Prenom");
      labelPrenomAuteur.setFont(fontLabel);
      labelPrenomAuteur.setForeground(labelColor);
      labelPrenomAuteur.setBounds(10, 60, 100, 30);

      fieldPrenomAuteur = new JTextField();
      fieldPrenomAuteur.setFont(fontField);
      fieldPrenomAuteur.setBounds(125, 60, 250, 35);

      panelAuteur.add(labelNomAuteur);
      panelAuteur.add(fieldNomAuteur);

      panelAuteur.add(labelPrenomAuteur);
      panelAuteur.add(fieldPrenomAuteur);
      panelLeft.add(panelAuteur);

      //Prix
      JLabel prixLabel = new JLabel("Prix");
      prixLabel.setFont(fontLabel);
      prixLabel.setForeground(labelColor);
      prixLabel.setBounds(10, 175, 100, 30);

      prixField = new JTextField();
      prixField.setFont(fontField);
      prixField.setBounds(130, 175, 250, 35);
      panelLeft.add(prixLabel);
      panelLeft.add(prixField);

      //Quantité
      JLabel quantityLabel = new JLabel("Quantité");
      quantityLabel.setFont(fontLabel);
      quantityLabel.setForeground(labelColor);
      quantityLabel.setBounds(10, 220, 100, 30);


      quantityField = new JTextField();
      quantityField.setFont(fontField);
      quantityField.setBounds(130, 220, 250, 35);
      panelLeft.add(quantityLabel);
      panelLeft.add(quantityField);

      //Right side of panel
      //Document type
      JLabel labelType = new JLabel("Type");
      labelType.setFont(fontLabel);
      labelType.setForeground(labelColor);
      types = new JComboBox<>(new String[]{"Livre", "CdRom", "Journal", "MicroFilm"});
      types.addActionListener(this);
      types.setFont(fontField);
      labelType.setBounds(400, 25, 100, 30);
      types.setBounds(540, 25, 200, 35);
      types.setBackground(panelColor);

      panelLeft.add(labelType);
      panelLeft.add(types);

      //cdRom type
      JLabel labelCdRom = new JLabel("CdRom Type");
      labelCdRom.setFont(fontLabel);
      labelCdRom.setForeground(labelColor);
      cdRomTypes = new JComboBox<>(new String[]{"Image", "Vidio", "Son", "Autre"});
      cdRomTypes.setEnabled(false);
      cdRomTypes.setFont(fontField);
      cdRomTypes.setBackground(panelColor);
      labelCdRom.setBounds(400, 80, 120, 30);
      cdRomTypes.setBounds(540, 80, 200, 35);

      panelLeft.add(labelCdRom);
      panelLeft.add(cdRomTypes);

      //empruntable
      JLabel labelEmprunt = new JLabel("Empruntable");
      labelEmprunt.setFont(fontLabel);
      labelEmprunt.setForeground(labelColor);
      labelEmprunt.setBounds(400, 130, 120, 30);
      ouiBtn1 = new JRadioButton("Oui");
      ouiBtn1.setFont(fontRadioBtn);
      ouiBtn1.setBackground(panelColor);
      ouiBtn1.setActionCommand("true");
      ouiBtn1.setFocusable(false);
      ouiBtn1.setSelected(true);

      nonBtn1 = new JRadioButton("Non");
      nonBtn1.setFocusable(false);
      nonBtn1.setBackground(panelColor);
      nonBtn1.setFont(fontRadioBtn);
      nonBtn1.setActionCommand("false");

      gropeEmpr = new ButtonGroup();
      gropeEmpr.add(ouiBtn1);
      gropeEmpr.add(nonBtn1);

      ouiBtn1.setBounds(540, 130, 80, 30);
      nonBtn1.setBounds(630, 130, 80, 30);
      panelLeft.add(ouiBtn1);
      panelLeft.add(nonBtn1);
      panelLeft.add(labelEmprunt);

      //consultable
      JLabel labelCons = new JLabel("Consultable");
      labelCons.setFont(fontLabel);
      labelCons.setForeground(labelColor);
      labelCons.setBounds(400, 170, 120, 30);
      ouiBtn2 = new JRadioButton("Oui");
      ouiBtn2.setFont(fontRadioBtn);
      ouiBtn2.setFocusable(false);
      ouiBtn2.setBackground(panelColor);
      ouiBtn2.setSelected(true);
      ouiBtn2.setActionCommand("true");

      nonBtn2 = new JRadioButton("Non");
      nonBtn2.setFocusable(false);
      nonBtn2.setFont(fontRadioBtn);
      nonBtn2.setBackground(panelColor);
      nonBtn2.setActionCommand("false");

      gropeCons = new ButtonGroup();
      gropeCons.add(ouiBtn2);
      gropeCons.add(nonBtn2);

      ouiBtn2.setBounds(540, 170, 80, 30);
      nonBtn2.setBounds(630, 170, 80, 30);
      panelLeft.add(ouiBtn2);
      panelLeft.add(nonBtn2);
      panelLeft.add(labelCons);

      //chose image
      labelImage = new JLabel("     Ajouter image");
      labelImage.setBounds(550, 220, 120, 140);
      labelImage.setVerticalTextPosition(JLabel.CENTER);
      labelImage.setForeground(labelColor);
      labelImage.setBorder(BorderFactory.createLineBorder(titleColor));
      labelImage.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choisir Image");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".jpg", "jpg");
            fileChooser.setFileFilter(filter);
            fileChooser.setAcceptAllFileFilterUsed(false);

            int reponse = fileChooser.showSaveDialog(null);

            if (reponse == JFileChooser.APPROVE_OPTION) {
                  path = fileChooser.getSelectedFile().getAbsolutePath();
                  //copy source to target using
                  ImageIcon icon = new ImageIcon(new ImageIcon(path)
                          .getImage()
                          .getScaledInstance(120, 133, Image.SCALE_SMOOTH));

                  labelImage.setText("");
                  labelImage.setIcon(icon);
            }
         }

      });
      panelLeft.add(labelImage);

      //fontBtn
      Font fontBtn = new Font("montserrat", Font.BOLD, 13);
      //ajouterBtn
      ajouterBtn = new JButton("Ajouter");
      ajouterBtn.setFocusable(false);
      ajouterBtn.setFont(fontBtn);
      ajouterBtn.setBounds(10, 310, 100, 35);
      ajouterBtn.setForeground(labelColor);
      ajouterBtn.setBackground(titleColor);
      panelLeft.add(ajouterBtn);

      ajouterBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
              ajouter();
         }
      });

      //modifierBtn
      modifierBtn = new JButton("Modifier");
      modifierBtn.setFocusable(false);
      modifierBtn.setFont(fontBtn);
      modifierBtn.setBounds(140, 310, 100, 35);
      modifierBtn.setForeground(labelColor);
      modifierBtn.setBackground(titleColor);
      panelLeft.add(modifierBtn);
      modifierBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Document document = getFromFields();
            if(document != null)modifier(document);
         }
      });

      //supprimerBtn
      supprimerBtn = new JButton("Supprimer");
      supprimerBtn.setFocusable(false);
      supprimerBtn.setBackground(titleColor);
      supprimerBtn.setFont(fontBtn);
      supprimerBtn.setBounds(270, 310, 100, 35);
      //supprimerBtn.setContentAreaFilled(false);
      supprimerBtn.setForeground(labelColor);
      panelLeft.add(supprimerBtn);
      supprimerBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Document document = getFromFields();
            if(document!=null){
            operation.suprimerDocument(document);}

            //refreche
            if(document instanceof Livre)
               afficherLivreTable();
            else if(document instanceof CdRoom)
               afficherCdRomTable();
            else if(document instanceof Journal)
               afficherJournalTable();
            else
               afficherMicroFilmTable();

         }
      });

      //panelTable
      panelT = new JPanel();
      panelT.setLayout(new BorderLayout());
      panelT.setBackground(panelColor);
      panelT.setBounds(5, 410, 770, 140);
      this.add(panelT);

      afficherLivreTable();

      this.add(panelLeft);
      this.setBackground(panelColor);
      this.setLayout(null);
      this.setBounds(0, 0, 680, 553);
   }


   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == types) {
         int a = types.getSelectedIndex();
         switch (a) {
            case 0:
               cdRomTypes.setEnabled(false);
               fieldNomAuteur.setEnabled(true);
               fieldPrenomAuteur.setEnabled(true);
               afficherLivreTable();
               break;

            case 1:
               cdRomTypes.setEnabled(true);
               fieldNomAuteur.setEnabled(true);
               fieldPrenomAuteur.setEnabled(true);
               afficherCdRomTable();
               break;

            case 2:
               cdRomTypes.setEnabled(false);
               fieldNomAuteur.setEnabled(false);
               fieldPrenomAuteur.setEnabled(false);
               afficherJournalTable();
               break;

            case 3:
               cdRomTypes.setEnabled(false);
               fieldNomAuteur.setEnabled(false);
               fieldPrenomAuteur.setEnabled(false);
               afficherMicroFilmTable();
               break;
         }
      }

   }


   public void afficherLivreTable() {
      panelT.removeAll();
      tabelPanel = new TablePanel();
      TableModel model = operation.AffichageDesDonnes("SELECT IdLivre, TitreLivre, " +
              "NomAuteur, PrenomAuteur, Prix,Quantite, QteDisponible, Empruntable," +
               " Consultable FROM livres, auteurs WHERE livres.IdAuteur = auteurs.IdAuteur");
      tabelPanel.setModel(model);
      panelT.add(new JScrollPane(tabelPanel));
      //RowSelected
      tabelPanel.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
             getDonnerCdLivre(TypeDocument.Livre);
         }
      });
      panelT.repaint();
      panelT.revalidate();

   }

   public void afficherCdRomTable() {
      panelT.removeAll();
      tabelPanel = new TablePanel();
      TableModel model = operation.AffichageDesDonnes("SELECT IdCdrom,Titre,NomAuteur, PrenomAuteur," +
                      "Caution,Quantite,QteDisponible, Empruntable, Consultable FROM cdroms, " +
                      "auteurs WHERE cdroms.IdAuteur = auteurs.IdAuteur");
      tabelPanel.setModel(model);
      panelT.add(new JScrollPane(tabelPanel));
      //row selected
      tabelPanel.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
            getDonnerCdLivre(TypeDocument.CdRom);
         }
      });
      panelT.repaint();
      panelT.revalidate();
   }

   public void afficherJournalTable() {
      panelT.removeAll();
      tabelPanel = new TablePanel();
      TableModel model = operation.AffichageDesDonnes("SELECT IdJournaux, Titre,Prix, Quantite, " +
                                      "QteDisponible,Consultable, Empruntable\n" +
                                      "FROM journaux");
      tabelPanel.setModel(model);
      panelT.add(new JScrollPane(tabelPanel));

      //rowSelected
      tabelPanel.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
           getDonneesMicrJournal(TypeDocument.Journal);
         }
      });
      panelT.repaint();
      panelT.revalidate();

   }

   public void afficherMicroFilmTable() {
      panelT.removeAll();
      tabelPanel = new TablePanel();
      TableModel model = operation.AffichageDesDonnes("SELECT IdMicroFilm, Titre, Prix, Quantite," +
                      "QteDisponible, Consultable, Empruntable\n"+
                      "FROM microfilms");
      tabelPanel.setModel(model);
      panelT.add(new JScrollPane(tabelPanel));

      //rowSelected
      tabelPanel.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
           getDonneesMicrJournal(TypeDocument.MicroFilm);
         }
      });
      panelT.repaint();
      panelT.revalidate();
   }

   //methodes
   private void ajouter() {
      Document document = getFromFields();
         if (document != null && path !=null) {
            operation.ajouterDocument(document, path);

            //refreche
            if(document instanceof Livre)
               afficherLivreTable();
            else if(document instanceof CdRoom)
               afficherCdRomTable();
            else if(document instanceof Journal)
               afficherJournalTable();
            else
               afficherMicroFilmTable();
         }else {
            JOptionPane.showMessageDialog(null, "Importer une imge",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
         }
   }

  private Document getFromFields(){
     Document document = null;
     String titre  = fieldTitre.getText();
     String nom    = fieldNomAuteur.getText();
     String prenom = fieldPrenomAuteur.getText();
     double prix=0;
     int quant=0;

     if(!prixField.getText().isEmpty()){
        prix = Double.parseDouble(prixField.getText());
     }
     if(!quantityField.getText().isEmpty()){
        quant = Integer.parseInt(quantityField.getText());
     }

     int cdType = cdRomTypes.getSelectedIndex();
     int type = types.getSelectedIndex();

     boolean consultable=true, empruntable=true;

     switch (gropeCons.getSelection().getActionCommand()){
        case "false":
           consultable = false;
           break;
        case "true":
           consultable = true;
           break;
     }

     switch (gropeEmpr.getSelection().getActionCommand()){
        case "false":
           empruntable = false;
           break;
        case "true":
           empruntable = true;
           break;
     }

     if(!fieldTitre.getText().isEmpty() &&
             !prixField.getText().isEmpty() && !quantityField.getText().isEmpty()){

        if(!fieldPrenomAuteur.isEnabled()){

              switch (type){
                 case 2:
                    document = new Journal(id, titre, prix, consultable, empruntable, quant);
                    break;

                 case 3:
                    document = new MicroFilm(id, titre, prix, consultable, empruntable, quant);
                    break;
              }

         }else if(!fieldNomAuteur.getText().isEmpty() && !fieldPrenomAuteur.getText().isEmpty()){
           TypeCdRoom cdT;
           switch (type) {
               case 0:
                  document = new Livre(id ,titre, new Auteur(nom, prenom), prix, consultable,
                          empruntable, quant);
                  break;

               case 1:
                     switch (cdType) {
                        case 0:
                           cdT = TypeCdRoom.IMAGE;
                           break;
                        case 1:
                           cdT = TypeCdRoom.VIDIO;
                           break;
                        case 2:
                           cdT = TypeCdRoom.SON;
                           break;
                        default:
                           cdT = TypeCdRoom.AUTRE;
                           break;}

                  document = new CdRoom(id,titre, cdT, new Auteur(nom, prenom), prix, consultable,
                          empruntable, quant);
                  break;
           }

        }else{JOptionPane.showMessageDialog(null,
                 "Remplir tous les champs", "Erreur",
                 JOptionPane.WARNING_MESSAGE);
        }

     }else
        JOptionPane.showMessageDialog(null, "Remplir tous les champs",
                "Erreur",
                JOptionPane.WARNING_MESSAGE);

   return document;}

   private void getDonnerCdLivre(TypeDocument type) {
            int row = tabelPanel.getSelectedRow();
            id = (int) tabelPanel.getValueAt(row, 0);
            String titre = (String) tabelPanel.getValueAt(row, 1);
            String nomA = (String) tabelPanel.getValueAt(row, 2);
            String prenomA = (String) tabelPanel.getValueAt(row, 3);
            double prix = (double) tabelPanel.getValueAt(row, 4);
            int cont = (int) tabelPanel.getValueAt(row, 5);
            boolean empri = (boolean) tabelPanel.getValueAt(row, 7);
            boolean consul = (boolean) tabelPanel.getValueAt(row, 8);

            labelImage.setText("");
            ImageIcon icon = new ImageIcon(operation.recupImage(id, type)
                    .getImage()
                    .getScaledInstance(120, 133, Image.SCALE_SMOOTH));
            path = null;

            labelImage.setIcon(icon);
            fieldTitre.setText(titre);
            fieldNomAuteur.setText(nomA);
            fieldPrenomAuteur.setText(prenomA);
            prixField.setText(Double.toString(prix));
            quantityField.setText(Integer.toString(cont));
            if (empri) ouiBtn1.setSelected(true);
            else nonBtn1.setSelected(true);

            if (consul) ouiBtn2.setSelected(true);
            else nonBtn2.setSelected(true);
      }


      private void getDonneesMicrJournal(TypeDocument type){
               int row = tabelPanel.getSelectedRow();
               id = (int) tabelPanel.getValueAt(row, 0);
               String titre = (String) tabelPanel.getValueAt(row, 1);
               double prix = (double) tabelPanel.getValueAt(row, 2);
               int cont = (int) tabelPanel.getValueAt(row, 3);
               boolean empri = (boolean) tabelPanel.getValueAt(row, 5);
               boolean consul = (boolean) tabelPanel.getValueAt(row, 6);

               labelImage.setText("");
               ImageIcon icon = new ImageIcon(operation.recupImage(id, type)
                 .getImage()
                 .getScaledInstance(120, 133, Image.SCALE_SMOOTH));
               path = null;

               labelImage.setIcon(icon);
               fieldTitre.setText(titre);
               prixField.setText(Double.toString(prix));
               quantityField.setText(Integer.toString(cont));
               fieldNomAuteur.setText(" ");
               fieldPrenomAuteur.setText(" ");
               if (empri) ouiBtn1.setSelected(true);
               else nonBtn1.setSelected(true);

               if (consul) ouiBtn2.setSelected(true);
               else nonBtn2.setSelected(true);
   }


      public void modifier (Document document) {
         if (tabelPanel.getSelectedRowCount() == 1) {
             if(path ==null) operation.modifierDocument(document);
             else operation.modifierDocument(document, path);

         } else if (tabelPanel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "le tableau est vide",
                    "title", JOptionPane.WARNING_MESSAGE);
         } else
            JOptionPane.showMessageDialog(null,
                    "s'il vous plaît séléctioner une seule ligne", "title",
                    JOptionPane.INFORMATION_MESSAGE);

         //refreche
         if(document instanceof Livre)
            afficherLivreTable();
         else if(document instanceof CdRoom)
            afficherCdRomTable();
         else if(document instanceof Journal)
            afficherJournalTable();
         else
            afficherMicroFilmTable();
       }

   }
