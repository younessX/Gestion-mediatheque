package javaTest.Admin.guiAdmin;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import javaTest.CONCEPTION.BaseDeDonne;
import javaTest.CONCEPTION.Emprunt;
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

public class EmpruntPanel extends JPanel implements ActionListener {
    private  JButton empruntBtn, imprimerBtn;
    private JComboBox<String> types;
    private  TablePanel tabelPanel;
    private JTextField documentField,lecteurField;
    private  JDateChooser dateEmp, dateRet;
    private  BaseDeDonne operation;
    private JPanel panelT;
    private JLabel labelImage;

    public EmpruntPanel(){
        operation = new BaseDeDonne();
        this.setLayout(null);

        operation = new BaseDeDonne();
        Color labelColor = new Color(0x643023);
        Color panelColor  = new Color(0xF9F3EE);
        Color titleColor  = new Color(0xD9AA63);

        this.setBackground(panelColor);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        Font fontLabel = new Font("montserrat", Font.BOLD, 18);
        Font fontField = new Font("montserrat", Font.PLAIN, 15);

        //document
        JLabel documentLabel =  new JLabel("Id Document");
        documentLabel.setBounds(10, 25, 120, 30);
        documentLabel.setFont(fontLabel);
        documentLabel.setForeground(labelColor);
        panel.add(documentLabel);

        documentField = new JTextField();
        documentField.setFont(fontField);
        documentField.setBounds(190, 25, 250, 35);
        panel.add(documentField);

        //Lecteur
        JLabel LecteurLabel =  new JLabel("Id Lecteur");
        LecteurLabel.setBounds(10, 75, 120, 30);
        LecteurLabel.setFont(fontLabel);
        LecteurLabel.setForeground(labelColor);
        panel.add(LecteurLabel);

        lecteurField = new JTextField();
        lecteurField.setFont(fontField);
        lecteurField.setBounds(190, 75, 250, 35);
        panel.add(lecteurField);

        //dateEmprunt
        JLabel DateEmLabel =  new JLabel("Date Emprunt");
        DateEmLabel.setBounds(10, 120, 140, 30);
        DateEmLabel.setFont(fontLabel);
        DateEmLabel.setForeground(labelColor);
        panel.add(DateEmLabel);

        dateEmp = new JDateChooser();
        dateEmp.setDateFormatString("yyyy-MM-dd");
        dateEmp.getDateEditor().getUiComponent().setOpaque(false);
        dateEmp.getDateEditor().getUiComponent().setBorder(BorderFactory.
                createMatteBorder(0, 0, 2, 0,new Color(185,152,138)));

        dateEmp.getDateEditor().getUiComponent().setFont(fontField);
        dateEmp.setBackground(panelColor);
        dateEmp.setBounds(190, 120, 180, 30);
        panel.add(dateEmp);

        //dateRetourne
        JLabel DateReLabel =  new JLabel("Date Retoure");
        DateReLabel.setBounds(10, 170, 140, 30);
        DateReLabel.setFont(fontLabel);
        DateReLabel.setForeground(labelColor);
        panel.add(DateReLabel);

        dateRet = new JDateChooser();
        dateRet.setBackground(panelColor);
        dateRet.setDateFormatString("yyyy-MM-dd");
        dateRet.getDateEditor().getUiComponent().setOpaque(false);
        dateRet.getDateEditor().getUiComponent().setBorder(BorderFactory
                .createMatteBorder(0, 0, 2, 0,new Color(185,152,138)));

        dateRet.getDateEditor().getUiComponent().setFont(fontField);
        dateRet.setBounds(190, 170, 180, 30);
        panel.add(dateRet);

        //comboBox
        JLabel labelType = new JLabel("Type");
        labelType.setFont(fontLabel);
        labelType.setForeground(labelColor);

        types = new JComboBox<>(new String[]{"Livre", "CdRom","Journal"});
        types.setFont(fontField);
        types.setBackground(panelColor);
        labelType.setBounds(10, 225, 100,30);
        types.setBounds(190,225,200, 35);
        panel.add(types);
        panel.add(labelType);

        //buttons
        Font fontBtn =  new Font("tahoma", Font.PLAIN, 14);

        //ajouterBtn
        empruntBtn = new JButton("Emprunter");
        empruntBtn.setFocusable(false);
        empruntBtn.setFont(fontBtn);
        empruntBtn.setBounds(110, 280, 110, 35);
        empruntBtn.setForeground(labelColor);
        empruntBtn.setBackground(titleColor);
        empruntBtn.addActionListener(this);
        panel.add(empruntBtn);

        //imprimerBtn
        imprimerBtn = new JButton("Imprimer");
        imprimerBtn.setFocusable(false);
        imprimerBtn.setFont(fontBtn);
        imprimerBtn.setBounds(270, 280, 110, 35);
        imprimerBtn.setForeground(labelColor);
        imprimerBtn.setBackground(titleColor);
        panel.add(imprimerBtn);
        imprimerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimer();
            }
        });

        //labelImage
        labelImage = new JLabel();
        labelImage.setBounds(540, 22, 200, 286);
        this.add(labelImage);


        panel.setBackground(panelColor);
        TitledBorder documentBorder = new TitledBorder(BorderFactory.createLineBorder(titleColor),
                "Emprunt",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.ITALIC, 20));

        documentBorder.setTitleColor(titleColor);
        panel.setBorder(documentBorder);

        panel.setBounds(5, 5, 500, 330);
        this.add(panel);

        //Table
        tabelPanel = new TablePanel();
        TableModel model = operation.AffichageDesDonnes("SELECT * FROM Emprunt");
        tabelPanel.setModel(model);
        panelT = new JPanel();
        panelT.setLayout(new BorderLayout());
        panelT.setBackground(Color.cyan);
        panelT.add(new JScrollPane(tabelPanel));
        panelT.setBounds(5, 350, 770, 200);
        this.add(panelT);

        tabelPanel.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = tabelPanel.getSelectedRow();
                int idDoc = (int)tabelPanel.getValueAt(row, 2);
                String type = (String) tabelPanel.getValueAt(row, 3);
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
                        .getImage().getScaledInstance(200, 286, Image.SCALE_SMOOTH)));
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(lecteurField.getText().isEmpty() || documentField.getText().isEmpty() ||
        dateEmp.getDate() == null || dateRet.getDate() == null){
            JOptionPane.showMessageDialog(null, "Remplir tous les champs",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }else{
            int idDocument=0, idLecteur=0;
           if(!documentField.getText().isEmpty()){
               idDocument = Integer.parseInt(documentField.getText());
           }
           if(!lecteurField.getText().isEmpty()){
               idLecteur = Integer.parseInt(lecteurField.getText());
           }

           Date dateR = new Date(dateRet.getDate().getTime());
           Date dateE = new Date(dateEmp.getDate().getTime());

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
           }

           operation.EmprunterDocument(new Emprunt(idDocument, idLecteur, dateE, dateR, typeDocument));

           //refreche
            TableModel model = operation.AffichageDesDonnes("SELECT * FROM Emprunt");
            tabelPanel.setModel(model);
        }
    }


    private void imprimer() {
        int row = tabelPanel.getSelectedRow();
        int idEmp = (int) tabelPanel.getValueAt(row, 0);
        int idDoc = (int) tabelPanel.getValueAt(row, 1);
        int  idLec = (int) tabelPanel.getValueAt(row, 2);
        String typeD = (String) tabelPanel.getValueAt(row, 3);

        Date dateEm = (Date) tabelPanel.getValueAt(row, 4);
        Date dateR = (Date) tabelPanel.getValueAt(row, 5);

        com.itextpdf.text.Document doc=new com.itextpdf.text.Document();
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("choisir un chemin d'emplacement!");

            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {

                String path = fileChooser.getSelectedFile().getAbsolutePath();

                String fileName = path;





                PdfWriter.getInstance(doc,new FileOutputStream(path+"-emprunt.pdf") );

            }
            doc.open();

            com.itextpdf.text.Image im= com.itextpdf.text.Image.getInstance("emprunte.png");
            im.scaleAbsoluteWidth(535);
            im.scaleAbsoluteHeight(200);

            doc.add(im);
            doc.add(new Paragraph(""));

            Paragraph p;
            p=new Paragraph("Code Emprunt :"+idEmp, FontFactory.getFont("Arial",20));
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
            p=new Paragraph("Date d'emprunt :"+dateEm,FontFactory.getFont("Arial",20));
            doc.add(new Paragraph(""));
            doc.add(p);
            p=new Paragraph("Date reteur :"+dateR,FontFactory.getFont("Arial",20));
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
