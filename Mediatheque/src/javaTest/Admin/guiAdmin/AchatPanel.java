package javaTest.Admin.guiAdmin;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import javaTest.CONCEPTION.Achat;
import javaTest.CONCEPTION.BaseDeDonne;
import javaTest.CONCEPTION.TypeDocument;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.Date;

public class AchatPanel extends JPanel implements ActionListener {

    private JButton acheterBtn, imprimerBtn;
    private  JComboBox<String> types;
    private  TablePanel tablePanel;
    private JTextField documentField, LecteurField;
    private JDateChooser  dateAchat;
    private JPanel panelT;
    private BaseDeDonne operation;
    private JLabel labelImage;

    public AchatPanel(){
        operation = new BaseDeDonne();

        Color labelColor = new Color(0x643023);
        Color panelColor  = new Color(0xF9F3EE);
        Color titleColor  = new Color(0xD9AA63);

        this.setLayout(null);
        this.setBackground(panelColor);

        JPanel panel = new JPanel();
        panel.setBackground(panelColor);
        panel.setLayout(null);

        Font fontLabel = new Font("montserrat", Font.BOLD, 18);
        Font fontField = new Font("montserrat", Font.PLAIN, 15);

        //document
        JLabel documentLabel =  new JLabel("Id Document");
        documentLabel.setBounds(10, 35, 120, 30);
        documentLabel.setFont(fontLabel);
        documentLabel.setForeground(labelColor);
        panel.add(documentLabel);

        documentField = new JTextField();
        documentField.setFont(fontField);
        documentField.setBounds(190, 35, 250, 35);
        panel.add(documentField);

        //Lecteur
        JLabel LecteurLabel =  new JLabel("Id Lecteur");
        LecteurLabel.setBounds(10, 85, 120, 30);
        LecteurLabel.setFont(fontLabel);
        LecteurLabel.setForeground(labelColor);
        panel.add(LecteurLabel);

        LecteurField = new JTextField();
        LecteurField.setFont(fontField);
        LecteurField.setBounds(190, 85, 250, 35);
        panel.add(LecteurField);

        //dateAchat
        JLabel DateAchLabel =  new JLabel("Date Achat");
        DateAchLabel.setBounds(10, 135, 140, 30);
        DateAchLabel.setFont(fontLabel);
        DateAchLabel.setForeground(labelColor);
        panel.add(DateAchLabel);

        dateAchat = new JDateChooser();
        dateAchat.setDateFormatString("yyyy-MM-dd");
        dateAchat.getDateEditor().getUiComponent().setOpaque(false);
        dateAchat.getDateEditor().getUiComponent().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,new Color(185,152,138)));

        dateAchat.getDateEditor().getUiComponent().setFont(fontField);
        dateAchat.setBounds(190, 135, 180, 30);
        dateAchat.setBackground(panelColor);
        panel.add(dateAchat);


        //comboBox
        JLabel labelType = new JLabel("Type");
        labelType.setFont(fontLabel);
        labelType.setForeground(labelColor);
        types = new JComboBox<String>(new String[]{"Livre", "CdRom","Journal", "MicroFilm"});
        types.setFont(fontField);
        labelType.setBounds(10, 190, 100,30);
        types.setBounds(190,190,200, 35);
        types.setBackground(panelColor);
        panel.add(types);
        panel.add(labelType);

        //buttons
        Font fontBtn =  new Font("tahoma", Font.PLAIN, 14);

        //acheterBtn
        acheterBtn = new JButton("Acheter");
        acheterBtn.setFocusable(false);
        acheterBtn.setFont(fontBtn);
        acheterBtn.setBounds(110, 250, 110, 35);
        acheterBtn.setForeground(labelColor);
        acheterBtn.setBackground(titleColor);
        acheterBtn.addActionListener(this);
        panel.add(acheterBtn);

        //imprimerBtn
        imprimerBtn = new JButton("Imprimer");
        imprimerBtn.setFocusable(false);
        imprimerBtn.setFont(fontBtn);
        imprimerBtn.setBounds(270, 250, 110, 35);
        imprimerBtn.setBackground(titleColor);
        imprimerBtn.setForeground(labelColor);
        panel.add(imprimerBtn);
        imprimerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimer();
            }
        });

        //labelImage
        labelImage = new JLabel();
        labelImage.setBounds(540, 22, 200, 265);
        this.add(labelImage);

        TitledBorder achatBorder = new TitledBorder(BorderFactory.createLineBorder(titleColor),"Achat",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 20));

        achatBorder.setTitleColor(titleColor);

        panel.setBorder(achatBorder);
        panel.setBounds(10, 10, 500, 300);
        this.add(panel);

        //Table
        panelT = new JPanel();
        panelT.setBackground(panelColor);
        panelT.setLayout(new BorderLayout());
        panelT.setBackground(panelColor);

        tablePanel = new TablePanel();
        TableModel model = operation.AffichageDesDonnes("SELECT * FROM Achat");
        tablePanel.setModel(model);
        panelT.add(new JScrollPane(tablePanel));
        panelT.setBounds(5, 320, 770, 200);
        this.add(panelT);
        tablePanel.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = tablePanel.getSelectedRow();
                int idDoc = (int) tablePanel.getValueAt(row, 1);
                String type = (String) tablePanel.getValueAt(row, 4);
                TypeDocument typeDo = null;
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

                    case "MicroFilm":
                        typeDo = TypeDocument.MicroFilm;
                }
                labelImage.setIcon(new ImageIcon(operation.recupImage(idDoc, typeDo)
                        .getImage().getScaledInstance(200, 265, Image.SCALE_SMOOTH)));
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(documentField.getText().isEmpty() || LecteurField.getText().isEmpty() ||
                dateAchat.getDate() == null){
            JOptionPane.showMessageDialog(null, "Remplir tous les champs",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }else{
            int idDocument=0, idLecteur=0;
            if(!documentField.getText().isEmpty()){
                idDocument = Integer.parseInt(documentField.getText());
            }
            if(!LecteurField.getText().isEmpty()){
                idLecteur = Integer.parseInt(LecteurField.getText());
            }

            Date dateA = new java.sql.Date(dateAchat.getDate().getTime());

            int type = types.getSelectedIndex();
            TypeDocument typeDocument=null;
            switch (type){
                case 0:
                    typeDocument = TypeDocument.Livre;
                    break;
                case 1:
                    typeDocument = TypeDocument.CdRom;
                    break;
                case 2:
                    typeDocument = TypeDocument.Journal;
                    break;
                case 3:
                    typeDocument = TypeDocument.MicroFilm;
                    break;

            }

            BaseDeDonne baseDeDonne =  new BaseDeDonne();
            baseDeDonne.AcheterDocument(new Achat(idDocument, idLecteur, dateA, typeDocument));
            //Refreche
            TableModel model = operation.AffichageDesDonnes("SELECT * FROM Achat");
            tablePanel.setModel(model);
            panelT.removeAll();
            panelT.add(new JScrollPane(tablePanel));
            panelT.repaint();
            panelT.revalidate();
        }
    }


    private void imprimer() {
        int row = tablePanel.getSelectedRow();
        int idAch = (int) tablePanel.getValueAt(row, 0);
        int idDoc = (int) tablePanel.getValueAt(row, 1);
        int  idLec = (int) tablePanel.getValueAt(row, 2);
        String typeD = (String) tablePanel.getValueAt(row, 4);

        Date dateEm = (Date) tablePanel.getValueAt(row, 3);

        com.itextpdf.text.Document doc=new com.itextpdf.text.Document();
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("choisir un chemin d'emplacement!");

            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {

                String path = fileChooser.getSelectedFile().getAbsolutePath();

                String fileName = path;





                PdfWriter.getInstance(doc,new FileOutputStream(path+"-acheter.pdf") );

            }
            doc.open();

            com.itextpdf.text.Image im= com.itextpdf.text.Image.getInstance("achat.png");
            im.scaleAbsoluteWidth(535);
            im.scaleAbsoluteHeight(200);

            doc.add(im);
            doc.add(new Paragraph(""));

            Paragraph p;
            System.out.println("done!");
            p=new Paragraph("Code Achat :"+idAch,FontFactory.getFont("Arial",20));
            doc.add(new Paragraph(""));
            doc.add(p);
            p=new Paragraph("Id Document :"+idDoc,FontFactory.getFont("Arial",20));
            doc.add(new Paragraph(""));
            doc.add(p);
            p=new Paragraph("Id Abonnement :"+idLec,FontFactory.getFont("Arial",20));
            doc.add(new Paragraph(""));


            doc.add(p);
            p=new Paragraph("Type de document :"+typeD,FontFactory.getFont("Arial",20));
            doc.add(new Paragraph(""));
            doc.add(p);
            p=new Paragraph("Date d'Achat :"+dateEm,FontFactory.getFont("Arial",20));
            doc.add(new Paragraph(""));
            doc.add(p);


            JOptionPane.showMessageDialog(null, "Operation est fait avec succe!");;

            doc.close();

        }
        catch(Exception ex) {
            System.out.println(ex);

        }

    }
}
