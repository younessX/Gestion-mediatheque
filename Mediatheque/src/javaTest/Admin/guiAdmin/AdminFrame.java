package javaTest.Admin.guiAdmin;

import javax.swing.*;
import javaTest.CONCEPTION.BaseDeDonne;
import javaTest.CONCEPTION.Personne;
import javaTest.FrontPage.MainFrame;
import java.awt.*;
import java.awt.event.*;

public class AdminFrame extends JFrame {
 private JPanel dashPanel,  leftPanel;
 private JLabel homeLabel, searchLabel, libraryLabel, labelAchat, labelConsult,
         labelLecteur, labelParam, labelReturn;
 private JLabel logOutLabel;
 private ConsultPanel panel;
 

 private JScrollPane scroll2;
 private JPanel panelT;
 private Personne personne; 
 ParaPanel panelP;
 UserPicPanel picPanel ;
 Color forBefore = new Color(0xa25f44);
 Color forAfter = new Color(0x2C1810);
 private  boolean index1=true, index2=true,index3=true, index4=true, index5=true,
		    index6=true, index7=true, index8=true;
		 private JLabel label=null;
		 private  ImageIcon icon=null;
		 ImageIcon profile = new ImageIcon("images\\user_icon.png");

	      ImageIcon homeImgBefore = new ImageIcon("images\\icon_home_before.png");

	      ImageIcon homeImgAfter  = new ImageIcon("images\\icon_home_after.png");

	     ImageIcon docImgBefore = new ImageIcon("images\\icon_document_before.png");
	     ImageIcon docImgAfter  = new ImageIcon("images\\icon_document_after.png");

	     ImageIcon empImgBefore =  new ImageIcon("images\\icon_emprunt_before.png");
	     ImageIcon empImgAfter =  new ImageIcon("images\\icon_emprunt_after.png");

	      ImageIcon achImgBefore =  new ImageIcon("images\\icon_acheter_before.png");
	      ImageIcon achImgAfter =  new ImageIcon("images\\icon_acheter_after.png");

	      ImageIcon consImgBefore =  new ImageIcon("images\\icon_consult_before.png");
	      ImageIcon consImgAfter =  new ImageIcon("images\\icon_consult_after.png");

	      ImageIcon returnImgBefore =  new ImageIcon("images\\icon_return_before.png");
	      ImageIcon returnImgAfter  =  new ImageIcon("images\\icon_return_after.png");

	      ImageIcon lectImgBefore =  new ImageIcon("images\\icon_lecteur_before.png");
	      ImageIcon lectImgAfter =  new ImageIcon("images\\icon_lecteur_after.png");

	      ImageIcon paraImgBefore =  new ImageIcon("images\\icon_para_before.png");
	      ImageIcon paraImgAfter =  new ImageIcon("images\\icon_para_after.png");

	     ImageIcon logOutImgBefore = new ImageIcon("images\\icon_log_out_before.png");
	     ImageIcon logOutImgAfter =  new ImageIcon("images\\icon_log_out_after.png");
	     Color backExited = new Color(0xF2E5D0);
  public AdminFrame(Personne personne) throws HeadlessException {
	  this.personne=personne;
      

     panel = new ConsultPanel(this);


    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Home");
    this.setSize(1000, 600);
    this.setResizable(false);
    this.getContentPane().setBackground(new Color(0xD6E1F8));
    this.setLayout(null);
    this.setLocationRelativeTo(null);
    this.setResizable(false);

    //colors
      Color dashColor = new Color(0xF2E5D0);
      Color backEntred = new Color(0xE1B87D);
 


      picPanel = new UserPicPanel(personne);
      picPanel.setBounds(5, 5, 190, 80);
      this.add(picPanel);
    

     //dashPanel
     dashPanel = new JPanel();
     dashPanel.setBounds(5, 85, 190, this.getHeight()-127);
     dashPanel.setBackground(dashColor);
     dashPanel.setLayout(null);
     this.add(dashPanel);

     //leftPanel
      leftPanel = new JPanel();
      leftPanel.setBackground(new Color(0xE7EEF8));
      leftPanel.setBounds(200, 5, this.getWidth()-220, getHeight()-47);
      leftPanel.setLayout(new BorderLayout());
      this.add(leftPanel);
      leftPanel.add(new HomePanel(), BorderLayout.CENTER);


     //labelHome
    homeLabel= new JLabel("Accueil");
    homeLabel.setIcon(homeImgBefore);
    homeLabel.setFont(new Font("tahoma", Font.PLAIN,15));
    homeLabel.setVerticalAlignment(JLabel.CENTER);
    homeLabel.setHorizontalAlignment(JLabel.LEFT);
    homeLabel.setBounds(5, 5, dashPanel.getWidth()-10, 40);
    homeLabel.setForeground(forBefore);
    dashPanel.add(homeLabel);

    //setMouseListener
    homeLabel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
         HomePanel panel = new HomePanel();
         leftPanel.removeAll();
         leftPanel.add(panel, BorderLayout.CENTER);
         leftPanel.revalidate();
         leftPanel.repaint();
         index1 = false;
         index2 = index3 = index4 = index5 = index6 =index7 = index8 = true;
         if(label!=null && label!= homeLabel && icon != null){
             change(label, icon);}
         label = homeLabel;
         icon = homeImgBefore;
        

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            homeLabel.setBackground(backEntred);
            homeLabel.setForeground(forAfter);
            homeLabel.setIcon(homeImgAfter);
            homeLabel.setOpaque(true);

        }

        @Override
        public void mouseExited(MouseEvent e) {
        	if(index1) {
            homeLabel.setForeground(forBefore);
            homeLabel.setIcon(homeImgBefore);
            homeLabel.setOpaque(true);
        }homeLabel.setBackground(backExited);}
    });


    //DocumentLabel
      searchLabel = new JLabel("Gestion Documents");
      searchLabel.setIcon(docImgBefore);
      searchLabel.setFont(new Font("tahoma", Font.PLAIN,15));
      searchLabel.setVerticalAlignment(JLabel.CENTER);
      searchLabel.setHorizontalAlignment(JLabel.LEFT);
      searchLabel.setBounds(5, 50, dashPanel.getWidth()-10, 40);
      searchLabel.setForeground(forBefore);
      dashPanel.add(searchLabel);

      //setMouseListener
      searchLabel.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
              leftPanel.removeAll();
              DocumentPanel panel = new DocumentPanel();
              leftPanel.add(panel, BorderLayout.CENTER);
              leftPanel.revalidate();
              leftPanel.repaint();
              if(label!=null && label !=searchLabel && icon != null){
                  change(label, icon);}
                  label = searchLabel;
                  icon = docImgBefore;
                  index2 = false;
                  index1 = index3 = index4 = index5 = index6 = index7 = index8 = true;
          }

          @Override
          public void mouseEntered(MouseEvent e) {
              searchLabel.setBackground(backEntred);
              searchLabel.setForeground(forAfter);
              searchLabel.setIcon(docImgAfter);
              searchLabel.setOpaque(true);

          }

          @Override
          public void mouseExited(MouseEvent e) {
        	  if(index2) {
              searchLabel.setForeground(forBefore);
            ;
              searchLabel.setIcon(docImgBefore);
              searchLabel.setOpaque(true);
          }
        	  searchLabel.setBackground(backExited);
          }
      });


      //EmpruntLabel
      libraryLabel = new JLabel("Gestion Emprunt");
      libraryLabel.setIcon(empImgBefore);
      libraryLabel.setFont(new Font("tahoma", Font.PLAIN,15));
      libraryLabel.setVerticalAlignment(JLabel.CENTER);
      libraryLabel.setHorizontalAlignment(JLabel.LEFT);
      libraryLabel.setBounds(5, 95, dashPanel.getWidth()-10, 40);
      libraryLabel.setForeground(forBefore);
      dashPanel.add(libraryLabel);

      //setMouseListener
      libraryLabel.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
              leftPanel.removeAll();
              EmpruntPanel panel = new EmpruntPanel();
              leftPanel.add(panel, BorderLayout.CENTER);
              leftPanel.repaint();
              leftPanel.revalidate();
              index3 = false;
              index1 = index2 = index4 = index5 = index6 =index7 = index8 = true;
              if(label!=null && label!= libraryLabel && icon != null){
                  change(label, icon);}
                  label = libraryLabel;
                  icon  = empImgBefore;
          }

          @Override
          public void mouseEntered(MouseEvent e) {
              libraryLabel.setBackground(backEntred);
              libraryLabel.setForeground(forAfter);
              libraryLabel.setIcon(empImgAfter);
              libraryLabel.setOpaque(true);

          }

          @Override
          public void mouseExited(MouseEvent e) {
        	  if(index3) {
              libraryLabel.setForeground(forBefore);
              
              libraryLabel.setIcon(empImgBefore);
              libraryLabel.setOpaque(true);
          }
        	  libraryLabel.setBackground(backExited);
          }
      });


      //AchatLabel
      labelAchat = new JLabel("Gestion Achat");
      labelAchat.setIcon(achImgBefore);
      labelAchat.setFont(new Font("tahoma", Font.PLAIN,15));
      labelAchat.setVerticalAlignment(JLabel.CENTER);
      labelAchat.setHorizontalAlignment(JLabel.LEFT);
      labelAchat.setBounds(5, 140, dashPanel.getWidth()-10, 40);
      labelAchat.setForeground(forBefore);
      dashPanel.add(labelAchat);

      //setMouseListener
      labelAchat.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
              leftPanel.removeAll();
              AchatPanel panel = new AchatPanel();
              leftPanel.add(panel);
              leftPanel.repaint();
              leftPanel.revalidate();
              index4 = false;
              index1 = index2 = index3 = index5 = index6 =index7 = index8 = true;
              if(label!=null && label!= labelAchat && icon != null){
                  change(label, icon);}
              label = labelAchat;
              icon  = achImgBefore;
          }

          @Override
          public void mouseEntered(MouseEvent e) {
              labelAchat.setBackground(backEntred);
              labelAchat.setForeground(forAfter);
              labelAchat.setIcon(achImgAfter);
              labelAchat.setOpaque(true);

          }

          @Override
          public void mouseExited(MouseEvent e) {
        	  if(index4) {
              labelAchat.setForeground(forBefore);
              
              labelAchat.setIcon(achImgBefore);
              labelAchat.setOpaque(true);
          }
        	  labelAchat.setBackground(backExited);
          }
      });


      //ConsultLabel
      labelConsult = new JLabel("Gestion Consultation");
      labelConsult.setIcon(consImgBefore);
      labelConsult.setFont(new Font("tahoma", Font.PLAIN,15));
      labelConsult.setVerticalAlignment(JLabel.CENTER);
      labelConsult.setHorizontalAlignment(JLabel.LEFT);
      labelConsult.setBounds(5, 185, dashPanel.getWidth()-10, 40);
      labelConsult.setForeground(forBefore);
      dashPanel.add(labelConsult);

      //setMouseListener
    
        	  labelConsult.addMouseListener(new MouseAdapter() {
                  @Override
                  public void mouseClicked(MouseEvent e) {
                      leftPanel.removeAll();
                      panel.setBounds(0, 0, 200, 200);
                      panel.setNum();
                      leftPanel.add(panel,BorderLayout.CENTER);
                      panelT = new JPanel();
                      
                      panelT.setPreferredSize(new Dimension(765, 130));
                      panelT.setLayout(new BorderLayout());
                      
        /////////////////////////////////////////////
                      tableShow();
                      index5 = false;
                      index1 = index2 = index3 = index4 = index6 =index7 = index8 = true;
                      if(label!=null && label!=labelConsult && icon != null){
                          change(label, icon);}
                      label = labelConsult;
                      icon  = consImgBefore;
                     
                  
          }

          @Override
          public void mouseEntered(MouseEvent e) {
              labelConsult.setBackground(backEntred);
              labelConsult.setForeground(forAfter);
              labelConsult.setIcon(consImgAfter);
              labelConsult.setOpaque(true);

          }

          @Override
          public void mouseExited(MouseEvent e) {
        	  if(index5) {
              labelConsult.setForeground(forBefore);
              
              labelConsult.setIcon(consImgBefore);
              labelConsult.setOpaque(true);
          }
        	  labelConsult.setBackground(backExited);
          }
        	  
      });

      
      //LectureLabel
      labelLecteur = new JLabel("Gestion Lecteur");
      labelLecteur.setIcon(lectImgBefore);
      labelLecteur.setFont(new Font("tahoma", Font.PLAIN,15));
      labelLecteur.setVerticalAlignment(JLabel.CENTER);
      labelLecteur.setHorizontalAlignment(JLabel.LEFT);
      labelLecteur.setBounds(5, 225, dashPanel.getWidth()-10, 40);
      labelLecteur.setForeground(forBefore);
      dashPanel.add(labelLecteur);

      //setMouseListener
      labelLecteur.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
              leftPanel.removeAll();
              UserPanel panel = new UserPanel();
              leftPanel.add(panel);
              leftPanel.repaint();
              leftPanel.revalidate();
              
              index6 = false;
              index1 = index2 = index3 = index4 = index5 =index7 = index8 = true;
              if(label!=null && label!=labelLecteur && icon != null){
                  change(label, icon);}
              label = labelLecteur;
              icon  = lectImgBefore;
          }

          @Override
          public void mouseEntered(MouseEvent e) {
              labelLecteur.setBackground(backEntred);
              labelLecteur.setForeground(forAfter);
              labelLecteur.setIcon(lectImgAfter);
              labelLecteur.setOpaque(true);

          }

          @Override
          public void mouseExited(MouseEvent e) {
        	  if(index6) {
              labelLecteur.setForeground(forBefore);
             
              labelLecteur.setIcon(lectImgBefore);
              labelLecteur.setOpaque(true);
          }
        	 labelLecteur.setBackground(backExited);  
          }
      });

      //ReturnLabel
      labelReturn = new JLabel("Retour document");
      labelReturn.setIcon(returnImgBefore);
      labelReturn.setFont(new Font("tahoma", Font.PLAIN,15));
      labelReturn.setVerticalAlignment(JLabel.CENTER);
      labelReturn.setHorizontalAlignment(JLabel.LEFT);
      labelReturn.setBounds(5, 270, dashPanel.getWidth()-10, 40);
      labelReturn.setForeground(forBefore);
      dashPanel.add(labelReturn);

      //setMouseListener
      labelReturn.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
              ReturnPanel panel = new ReturnPanel();
              leftPanel.removeAll();
              leftPanel.add(panel);
              leftPanel.repaint();
              leftPanel.revalidate();
              index7 = false;
              index1 = index2 = index3 = index4 = index5 =index6 = index8 = true;
              if(label!=null && label !=labelReturn && icon != null){
                  change(label, icon);}
              label = labelReturn;
              icon  = returnImgBefore;
          }

          @Override
          public void mouseEntered(MouseEvent e) {
              labelReturn.setBackground(backEntred);
              labelReturn.setForeground(forAfter);
              labelReturn.setIcon(returnImgAfter);
              labelReturn.setOpaque(true);

          }

          @Override
          public void mouseExited(MouseEvent e) {
        	  if(index7) {
              labelReturn.setForeground(forBefore);
             
              labelReturn.setIcon(returnImgBefore);
              labelReturn.setOpaque(true);
          }
        	   labelReturn.setBackground(backExited);
          }
      });


      //ParameterLabel
      labelParam = new JLabel("Parameter");
      labelParam.setIcon(paraImgBefore);
      labelParam.setFont(new Font("tahoma", Font.PLAIN,15));
      labelParam.setVerticalAlignment(JLabel.CENTER);
      labelParam.setHorizontalAlignment(JLabel.LEFT);
      labelParam.setBounds(5, 315, dashPanel.getWidth()-10, 40);
      labelParam.setForeground(forBefore);
      dashPanel.add(labelParam);

      //setMouseListener
      labelParam.addMouseListener(new MouseAdapter() {
          
          public void mouseClicked(MouseEvent e) {
        	  
              leftPanel.removeAll();
              createPPP();
              leftPanel.add(panelP, BorderLayout.CENTER);
              leftPanel.repaint();
              leftPanel.revalidate();
              index8 = false;
              index1 = index2 = index3 = index4 = index5 =index6 = index7 = true;
              if(label!=null && label!= labelParam && icon != null){
                  change(label, icon);}
              label = labelParam;
              icon  = paraImgBefore;
          }

      
          public void mouseEntered(MouseEvent e) {
              labelParam.setBackground(backEntred);
              labelParam.setForeground(forAfter);
              labelParam.setIcon(paraImgAfter);
              labelParam.setOpaque(true);

          }

          @Override
          public void mouseExited(MouseEvent e) {
        	  
        	  if(index8) {
              labelParam.setForeground(forBefore);
             
              labelParam.setIcon(paraImgBefore);
              labelParam.setOpaque(true);
          }
        	   labelParam.setBackground(backExited);
          }
      });


      //logOutLabel
      logOutLabel = new JLabel("Deconnexion");
      logOutLabel.setIcon(logOutImgBefore);
      logOutLabel.setFont(new Font("tahoma", Font.PLAIN,15));
      logOutLabel.setVerticalAlignment(JLabel.CENTER);
      logOutLabel.setHorizontalAlignment(JLabel.LEFT);
      logOutLabel.setBounds(5, dashPanel.getHeight()-70, dashPanel.getWidth()-10, 40);
      logOutLabel.setForeground(forBefore);
      dashPanel.add(logOutLabel);

      logOutLabel.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
        	  kill();
              new MainFrame();

          }

          @Override
          public void mouseEntered(MouseEvent e) {
              logOutLabel.setBackground(backEntred);
              logOutLabel.setForeground(forAfter);
              logOutLabel.setIcon(logOutImgAfter);
              logOutLabel.setOpaque(true);

          }

          @Override
          public void mouseExited(MouseEvent e) {
              logOutLabel.setForeground(forBefore);
              logOutLabel.setBackground(backExited);
              logOutLabel.setIcon(logOutImgBefore);
              logOutLabel.setOpaque(true);
          }
      });


      this.setVisible(true);
      this.setLocationRelativeTo(null);

  }
  
  public void tableShow() {
		
		panelT.removeAll();
		
	  TablePanel tabelPanelDem ;
	  BaseDeDonne db=new BaseDeDonne();
	  db.Connecter();
	  tabelPanelDem = new TablePanel();
	  tabelPanelDem.setModel(db.AffichageDesDonnes("select * from consult;"));
	  db.deconnecter();
	 scroll2 = new JScrollPane(tabelPanelDem);
	  scroll2.setBounds(5, 5, 765,140);
	  
	 panelT.add(scroll2, BorderLayout.CENTER);
	  leftPanel.add(panelT, BorderLayout.SOUTH);
	  leftPanel.repaint();
	  leftPanel.revalidate();}


	public void kill() {
		this.dispose();
	}
	public void createPPP() {
		panelP = new ParaPanel(personne,this);
	}
	
	 public void setUserName(String userName) {
		 
		 this.picPanel.setUsername(userName);
	 }
public void setImage(Icon icon) {
		 
		 this.picPanel.setImage(icon);
	 }
private  void change(JLabel label, ImageIcon icon){
    label.setForeground(forBefore);
    label.setIcon(icon);
}

}