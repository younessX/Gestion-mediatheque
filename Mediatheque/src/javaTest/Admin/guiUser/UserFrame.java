package javaTest.Admin.guiUser;
import javaTest.CONCEPTION.Personne;
import javaTest.FrontPage.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserFrame extends JFrame {
    private JPanel dashPanel,  leftPanel;
    private JLabel homeLabel, biblioLabel,
            labelParam,  logOutLabel;

    private boolean index1=true, index2=true, index3=true;
    private JLabel label;
    private ImageIcon icon;
    ParaPanelU panel;
    //colors
    Color dashColor = new Color(0xF2E5D0);
    Color backEntred = new Color(0xE1B87D);
    Color backExited = new Color(0xF2E5D0);

    Color forBefore = new Color(0xa25f44);
    Color forAfter = new Color(0x2C1810);

    ImageIcon homeImgBefore = new ImageIcon("images\\icon_home_before.png");

    ImageIcon homeImgAfter  = new ImageIcon("images\\icon_home_after.png");

    ImageIcon LibImgBefore = new ImageIcon("images\\icon_bib_before.png");

    ImageIcon LibImgAfter  = new ImageIcon("images\\icon_bib_after.png");

    ImageIcon paraImgBefore =  new ImageIcon("images\\icon_para_before.png");
    ImageIcon paraImgAfter =  new ImageIcon("images\\icon_para_after.png");

    ImageIcon logOutImgBefore = new ImageIcon("images\\icon_log_out_before.png");
    ImageIcon logOutImgAfter =  new ImageIcon("images\\icon_log_out_after.png");
    private UserPicPanel picPanel;
    private  Personne personne;
    public UserFrame(Personne personne){

        this.personne = personne;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Mediatheque");
        this.setSize(1000, 600);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(0xD6E1F8));
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        picPanel=new UserPicPanel(personne);
        picPanel.setBounds(5,5,190,80);
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
        leftPanel.add(new HomePanelUser(personne.getIdPersonne()), BorderLayout.CENTER);


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
                HomePanelUser panel = new HomePanelUser(personne.getIdPersonne());
                leftPanel.removeAll();
                leftPanel.add(panel, BorderLayout.CENTER);
                leftPanel.revalidate();
                leftPanel.repaint();
                index1 = false;
                index2 = index3 = true;
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
                if(index1){
                    homeLabel.setForeground(forBefore);
                    homeLabel.setIcon(homeImgBefore);
                    homeLabel.setOpaque(true);}
                    homeLabel.setBackground(backExited);
            }
        });

        //DocumentLabel
        biblioLabel = new JLabel("Bibliotheque");
        biblioLabel.setIcon(LibImgBefore);
        biblioLabel.setFont(new Font("tahoma", Font.PLAIN,15));
        biblioLabel.setVerticalAlignment(JLabel.CENTER);
        biblioLabel.setHorizontalAlignment(JLabel.LEFT);
        biblioLabel.setBounds(5, 50, dashPanel.getWidth()-10, 40);
        biblioLabel.setForeground(forBefore);
        dashPanel.add(biblioLabel);

        //setMouseListener
        biblioLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                leftPanel.removeAll();
                BiblioPanel panel = new BiblioPanel(personne.getIdPersonne());
                leftPanel.add(panel, BorderLayout.CENTER);
                leftPanel.revalidate();
                leftPanel.repaint();
                if(label!=null && label!= biblioLabel && icon != null){
                    change(label, icon);}
                label = biblioLabel;
                icon = LibImgBefore;
                index2 = false;
                index1 = index3 = true;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                biblioLabel.setBackground(backEntred);
                biblioLabel.setForeground(forAfter);
                biblioLabel.setIcon(LibImgAfter);
                biblioLabel.setOpaque(true);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(index2){
                    biblioLabel.setForeground(forBefore);
                    biblioLabel.setIcon(LibImgBefore);
                    biblioLabel.setOpaque(true);
                }
                biblioLabel.setBackground(backExited);
            }
        });


        //ParameterLabel
        labelParam = new JLabel("Parameter");
        labelParam.setIcon(paraImgBefore);
        labelParam.setFont(new Font("tahoma", Font.PLAIN,15));
        labelParam.setVerticalAlignment(JLabel.CENTER);
        labelParam.setHorizontalAlignment(JLabel.LEFT);
        labelParam.setBounds(5, 95, dashPanel.getWidth()-10, 40);
        labelParam.setForeground(forBefore);
        dashPanel.add(labelParam);

        //setMouseListener
        labelParam.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                leftPanel.removeAll();
                createPPP();
                leftPanel.add(panel, BorderLayout.CENTER);
                leftPanel.repaint();
                leftPanel.revalidate();

                index3 = false;
                index1 = index2 = true;
                if(label!=null && label!= labelParam && icon != null){
                    change(label, icon);}
                label = labelParam;
                icon  = paraImgBefore;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                labelParam.setBackground(backEntred);
                labelParam.setForeground(forAfter);
                labelParam.setIcon(paraImgAfter);
                labelParam.setOpaque(true);


            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(index3){
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
                leftPanel.removeAll();
                leftPanel.repaint();
                leftPanel.revalidate();
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
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private  void change(JLabel label, ImageIcon icon){
        label.setForeground(forBefore);
        label.setBackground(backExited);
        label.setIcon(icon);
    }
    public void createPPP() {
    	panel = new ParaPanelU(personne,this);
    }

    public void setImage(Icon icon) {
		 
		 this.picPanel.setImage(icon);
	 }

     public void setUserName(String userName) {
		 
		 this.picPanel.setUsername(userName);
	 }

     public void kill() {
		
		this.dispose();
    }

}
