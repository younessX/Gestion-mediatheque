package javaTest.FrontPage;
	import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
	import javax.swing.event.*;

import javaTest.Admin.guiAdmin.AdminFrame;
import javaTest.Admin.guiUser.UserFrame;
import javaTest.CONCEPTION.BaseDeDonne;
import javaTest.CONCEPTION.Personne;
public class LoginPanel extends JPanel{



	    private JLabel jcomp1;
	    private MyTextField jcomp2;
	    private JLabel jcomp3;
	    private MyPassword jcomp4;
	    private JLabel jcomp5;
	    private JButton jcomp6;
	    private MyButton jcomp7;
	    private JLabel jcomp8;
	    private ImageIcon image;
	    private Personne pr;
	    private JFrame frame;

	    public LoginPanel(JFrame frame) {
	    	this.frame=frame;
	        //construct components
	    	image=new ImageIcon("Bhome.png");
	        jcomp1 = new JLabel ("E-mail");
	        jcomp1.setForeground(new Color(0xebccc5));
	        jcomp1.setFont(new Font("sansserif",Font.PLAIN,15));
	        jcomp2 = new MyTextField ();
	        jcomp2.setImageIcon(new ImageIcon("user.png"));
	        
	        jcomp3 = new JLabel ("Mot de passe");
	        jcomp3.setFont(new Font("sansserif",Font.PLAIN,15));
	        jcomp2.setForeground(new Color(0xebccc5));
	        jcomp3.setForeground(new Color(0xebccc5));
	        jcomp4 = new MyPassword ();
	        jcomp4.setFont(new Font(null,Font.BOLD,18));
	        jcomp4.setForeground(new Color(0xebccc5));
	        jcomp4.setImageIcon(new ImageIcon("lock.png"));
	        jcomp4.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {

				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
						if(e.getKeyChar()==10) {
						jcomp7.doClick();
						
					}
				}

				@Override
				public void keyReleased(KeyEvent e) {
					
				}

	        });
	        jcomp5 = new JLabel ();
	        jcomp5.setIcon(new ImageIcon("login.png"));
	        jcomp5.setForeground(Color.red);
	        jcomp5.setFont(new Font(null,Font.BOLD,35));
	        jcomp6 = new JButton ("Ici.");
	        jcomp6.setIcon(new ImageIcon("quote-request1.png"));
	        jcomp6.setForeground(new Color(0x3184A4));
	        jcomp6.setFont(new Font("sansserif",Font.BOLD,15));
	        jcomp6.setHorizontalTextPosition(JLabel.LEADING);
	        jcomp6.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseEntered(MouseEvent e) {
					 jcomp6.setIcon(new ImageIcon("quote-request.png"));
					 jcomp6.setForeground(Color.red);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					jcomp6.setIcon(new ImageIcon("quote-request1.png"));
					 jcomp6.setForeground(new Color(0x3184A4));
				}

	        });
	   
	    
	        jcomp6.setBorder(BorderFactory.createMatteBorder(0, 0, 2,0,Color.red));
	        jcomp6.setFocusable(false);
	        jcomp7 = new MyButton ();
	        jcomp7.setText("Login");
	        jcomp7.setFont(new Font("sansserif",Font.BOLD,15));
	        jcomp7.addActionListener((event)->{
	        	
	        	UserOrAdmin(login());
	        });
	        
	      
	        jcomp6.setOpaque(false);
	        jcomp6.setBorderPainted(false);
	        jcomp6.setContentAreaFilled(false);
	       
	        jcomp8 = new JLabel ("Si vous n'avez pas de compte Demandez ");
	        
	        jcomp8.setForeground(new Color(0xebccc5));
	        jcomp8.setFont(new Font("sansserif",Font.BOLD,12));
	        //adjust size and set layout
	        setPreferredSize (new Dimension (792, 535));
	        setLayout (null);

	        //add components
	        add (jcomp1);
	        add (jcomp2);
	        add (jcomp3);
	        add (jcomp4);
	        add (jcomp5);
	        add (jcomp6);
	        add (jcomp7);
	        add (jcomp8);

	        //set component bounds (only needed by Absolute Positioning)
	        jcomp1.setBounds (255, 165, 100, 25);
	        jcomp2.setBounds (255, 190, 255, 33);
	        jcomp3.setBounds (255, 230, 100, 25);
	        jcomp4.setBounds (255, 255, 255, 33);
	        jcomp5.setBounds (318, 70, 160, 60);
	        jcomp6.setBounds (500, 380, 50, 25);
	        jcomp7.setBounds (255, 315, 255, 30);
	        jcomp8.setBounds (258, 380, 255, 25);
	    }

	    
	    
		public void paintComponent(Graphics g) {
			
			Graphics2D G= (Graphics2D)g;
			super.paintComponent(G);

				G.drawImage(image.getImage(), 0, 0,null);
			G.drawImage(new ImageIcon("logo.png").getImage(), -10, -30,null);
		
			G.drawImage(new ImageIcon("book.png").getImage(), 220, 55,null);
				
		}
		public JButton getDemand() {
	    	
	    	
	    	return jcomp6;
	    }
		
		
		public boolean login() {
			
			BaseDeDonne db=new BaseDeDonne();
			db.Connecter();
		 pr=db.login(jcomp2.getText(), jcomp4.getText());
			

			db.deconnecter();
			
			if(pr==null) {
				
				return false;
				
			}else {
				
				return true;
				
			}
			
			
			
			
			
		}
		
		
		public void UserOrAdmin(boolean answar){
			
			if(!answar) {
				
				
			return;
			
			}
			
			if(pr.getTypeUser().equals("User")) {
				
				new UserFrame(pr);
				frame.dispose();
			}
			if(pr.getTypeUser().equals("Admin")) {
				
			SwingUtilities.invokeLater(()->{
					new AdminFrame(pr);
				frame.dispose();
				
			});
				
			}
			
			
			
			
		}
		 
		

	
	

}
