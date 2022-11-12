package javaTest.Admin.guiAdmin;

import javaTest.CONCEPTION.BaseDeDonne;
import javaTest.CONCEPTION.TypeDocument;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class ReturnPanel extends JPanel implements ActionListener{

	private DocDetails docPanel;
	private ReturnDocument returnP;
	private int idDocument=0, idLecteur=0;
	private BaseDeDonne operation;
	private TypeDocument typeDocument;

	public ReturnPanel() {
		  operation = new BaseDeDonne();

		 docPanel=new DocDetails();
		 returnP=new ReturnDocument();

		 this.setLayout(new BorderLayout());

		 this.add(docPanel,BorderLayout.WEST);
		 this.add(returnP,BorderLayout.CENTER);

		//buttons
		returnP.getVoirDetail().addActionListener(this);
		returnP.getReturnDoc().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == returnP.getVoirDetail()){
			detailDocument();
		}else if(e.getSource() == returnP.getReturnDoc()){
		  returnDocument();
		}
	}

	private void detailDocument(){
		getInfo();
		if(idDocument ==0 || idLecteur ==0){
			JOptionPane.showMessageDialog(null, "Remplir tous les champs",
					"erreur", JOptionPane.ERROR_MESSAGE);


		}else{
			ArrayList<String> details = operation.VoirDetails(idDocument, idLecteur, typeDocument);
			docPanel.getIdDocAN().setText(details.get(0));
			docPanel.getTitreDocAN().setText(details.get(1));
			docPanel.getNomEmpAN().setText(details.get(2)+" "+details.get(3));

			docPanel.getDateEmpAN().setText(details.get(4));
			docPanel.getDatereturnAN().setText(details.get(5));
		}

	}

	private void returnDocument(){
		getInfo();
		if(idDocument ==0 || idLecteur ==0){
			JOptionPane.showMessageDialog(null, "Remplir tous les champs",
					"erreur", JOptionPane.ERROR_MESSAGE);
		}else
			operation.RetournerDocument(idDocument, idLecteur, typeDocument);
	}

	private  void getInfo(){
		if(!returnP.getIdDocText().getText().isEmpty()){
			idDocument = Integer.parseInt(returnP.getIdLecText().getText());
		}
		if(!returnP.getIdLecText().getText().isEmpty()){
			idLecteur = Integer.parseInt(returnP.getIdDocText().getText());
		}

		int index = returnP.getTypeCombo().getSelectedIndex();
		switch (index){
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
	}

}
