package javaTest.Admin.guiAdmin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;

import com.toedter.calendar.JDateChooser;

import javaTest.CONCEPTION.BaseDeDonne;
public class PostDetails extends JDialog implements MouseListener{
    private JLabel idMicro;
    private JLabel idLecture;
    private JTextField idMicroF;
    private JTextField idLectureF;
    private JLabel dateCons;
    private JDateChooser dateConsF;
    private JLabel consultation;
    private JButton stop;
	private Timer timer;
	private JSpinner min;
	private JSpinner sec;
	private JLabel timel;
	private JLabel mint;
	private JLabel seco;
	private BaseDeDonne db;
	
	private JLabel point2;
	private JButton  demarrer;
	private JPanel panel;
	private int minuts;
	private int seconds;
	private JButton rest;
	private String postName;
	private JLabel nomPost;
	private String time="00:00";
	private OnePostPanel Fpanel;
	public PostDetails(AdminFrame owner,boolean Modal,String postName,OnePostPanel Fpanel) {
				super(owner,Modal);
				this.postName=postName;
				this.Fpanel=Fpanel;
				Color attrColor=new Color(0xD9AA63);
				Font font=new Font(null,Font.BOLD,15);
				idMicro=new JLabel("Id Microfilm");
				idMicro.setFont(font);
				idMicro.setForeground(attrColor);
				idMicroF=new JTextField(10);
				idMicroF.setFont(font);
				idLecture=new JLabel("Id Lecture");
				idLecture.setForeground(attrColor);
				idLecture.setFont(font);
				idLectureF=new JTextField(10);
				idLectureF.setFont(font);
				dateCons=new JLabel("Date Consultation");
				dateCons.setFont(font);
				dateCons.setForeground(attrColor);
				dateConsF=new JDateChooser();
				dateConsF.setFont(font);
				dateConsF.setDateFormatString("yyyy-MM-dd");
				stop=new JButton("Stop");
				stop.setBackground(attrColor);
				mint=new JLabel("minuts");
				mint.setForeground(attrColor);
				seco=new JLabel("seconds");
				seco.setForeground(attrColor);
				point2=new JLabel(":");
				point2.setForeground(attrColor);
				

				consultation=new JLabel("Fich Consulation");
				consultation.setFont(new Font(null,Font.BOLD|Font.ITALIC,23));
				consultation.setForeground(new Color(0x643023));
				

			    
				
				
		    panel=new JPanel();     
		    rest=new JButton();
		    rest.setIcon(new ImageIcon("images/timer.png"));
		    rest.setOpaque(false);
		    rest.setContentAreaFilled(false);
		    rest.setBorderPainted(false);
		    nomPost=new JLabel(postName);
		    nomPost.setFont(new Font(null,Font.BOLD,20));
		    nomPost.setHorizontalTextPosition(JLabel.RIGHT);
		min=new JSpinner(new SpinnerNumberModel(0,0,60,1));
		       
		       sec=new JSpinner(new SpinnerNumberModel(0,0,60,1));
		       demarrer=new JButton("Demarrer");
		       demarrer.setIcon(new ImageIcon("images/play-button.png"));
		       timel=new JLabel("Time");
		       timel.setFont(new Font(null,Font.BOLD,13));
		       timel.setForeground(attrColor);
		      
	demarrer.addActionListener((event)->{
		if(idMicroF.getText().isEmpty()||idLectureF.getText().isEmpty()||dateConsF.getDate()==null) {
			
			JOptionPane.showInternalMessageDialog(null,"Please insert the all fields","ERORR",JOptionPane.ERROR_MESSAGE);
			
			
			
		}
		else {
			int idPost=Integer.parseInt((postName.charAt(postName.length()-1)+" ").trim());
			
			addConsult(Integer.parseInt(idMicroF.getText()), Integer.parseInt(idLectureF.getText()),new java.sql.Date(dateConsF.getDate().getTime()),idPost);
			
			timer.start();
		seconds=Integer.parseInt(sec.getValue()+"");
		minuts=Integer.parseInt(min.getValue()+"");
		Fpanel.getPost().setIcon(new ImageIcon("images/on.png"));
		owner.tableShow();
		owner.repaint();
		this.dispose();
		}
		
		
		
		
		
		
		
	});
	stop.addActionListener((event)->{
		
		timer.start();
		rest.doClick();
		Fpanel.getPost().setIcon(new ImageIcon("images/off.png"));
		this.dispose();
		Fpanel.countDown.setText("00:00");
		
		
	});
	
			timer=new Timer(1000,(event)->{
				
				sec.setValue(seconds--);
				repaint();
		       if(seconds<0) {
		    	   if(minuts>0)
		    	   min.setValue(--minuts);
		    	   repaint();
		    	   seconds=59;
		    	   
		    	   }
		       if(minuts<0)
		    	   timer.stop();
		       if(seconds==59&&minuts<0) {
		    	   rest.doClick();
		    	   timer.stop();
		       }
		       
		       
		       time=cDString(minuts,seconds);
		       if(time.equals("00:00")) {
		    	   timer.stop();
		    	rest.doClick();   
		    	Fpanel.getPost().setIcon(new ImageIcon("images/off.png"));
		       }
		 
		       Fpanel.getCountDown().setText(time);
		       
		       
	       
	});
			
		
			rest.addActionListener((e)->{
				
				min.setValue(0);
			       
			       sec.setValue(0);
			       timer.stop();
			       
			});
				
			
		
		
		   panel.addMouseListener(this);
	

		   panel.setLayout(null);

		        //adjust size and set layout
		       idMicro.setBounds (25, 40, 100, 25);
		        idMicroF.setBounds (130, 45, 100, 25);
		        idMicroF.setOpaque(false);
		       
		        idMicroF.setBorder(BorderFactory.createMatteBorder(0, 0, 2,0, attrColor));
		        idMicroF.addMouseListener(new MouseAdapter() {
		        	
		        	@Override
					public void mouseExited(MouseEvent e) {
		        		idMicroF.setOpaque(false);
		        		repaint();
		 		       
		        		
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						idMicroF.setOpaque(true);
		        		idMicroF.setBackground(new Color(0xecd8c8));
		        		repaint();
					}

					
		        	
		        	
		        });
		      
		    
		        idLecture.setBounds (25, 85, 100, 25);
		        idLectureF.setBounds (130, 85, 100, 25);
		        idLectureF.setOpaque(false);
		        idLectureF.setBorder(BorderFactory.createMatteBorder(0, 0, 2,0, attrColor));
  idLectureF.addMouseListener(new MouseAdapter() {
		        	
		        	@Override
					public void mouseExited(MouseEvent e) {
		        		idLectureF.setOpaque(false);
		        		repaint();
		 		       
		        		
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						idLectureF.setOpaque(true);
						idLectureF.setBackground(new Color(0xecd8c8));
		        		repaint();
					}

					
		        	
		        	
		        });
		        dateCons.setBounds (24, 130, 135, 25);
		        dateConsF.setBounds (170, 128, 110, 25);
		        dateConsF.getDateEditor().getUiComponent().setOpaque(false);
		        dateConsF.setOpaque(false);
		        //dateConsF.getDateEditor().getUiComponent().setBorder(BorderFactory.createMatteBorder(0, 0, 2,0, attrColor));
                dateConsF.getDateEditor().getUiComponent().enable(Modal);;
		        demarrer.setBounds (25, 230, 120, 25);
		        demarrer.setBackground(getBackground());
		        demarrer.setForeground(new Color(0x643023));
		        stop.setBounds (165, 230, 90, 25);
		        stop.setForeground(new Color(0x643023));
		        stop.setBorder(BorderFactory.createLineBorder(new Color(0x643023)));
		       
		        demarrer.setBackground(attrColor);
		        stop.setBackground(attrColor);

		        demarrer.setBorder(BorderFactory.createLineBorder(new Color(0x643023)));
		        
		        stop.setFocusable(false);
		        stop.setIcon(new ImageIcon("images/stop.png"));
		        rest.setFocusable(false);
		        demarrer.setFocusable(false);
		        min.setBounds (70, 190, 35, 20);
		        min.getEditor().setOpaque(false);
		        sec.setBounds (120, 190, 35, 20);
		        rest.setBounds (180, 180, 60, 35);
		         consultation.setBounds (45, -8, 200, 40);
		         timel.setBounds (20, 185, 35, 30);
		         mint.setBounds (65, 165, 40, 25);
		         mint.setFont(new Font(null,Font.PLAIN,10));
		         seco.setBounds (120, 165, 55, 20);
		         seco.setFont(new Font(null,Font.PLAIN,10));
		        //add components
		        panel.add (consultation);
		        panel.add (idMicro);
		        panel.add (idMicroF);
		        panel.add (idLecture);
		        panel.add (idLectureF);
		        panel.add (demarrer);
		        panel.add (rest);
		        panel.add(dateConsF);
		        panel.add (stop);
		        panel.add(dateCons);
		        panel.add(timel);
		        panel.add(mint);
		        panel.add(seco);
		        panel.add(min);
		        panel.add(sec);

		        //set component bounds (only needed by Absolute Positioning)
		        
		    
		   
		   
	panel.setPreferredSize(new Dimension(260, 300));
	panel.setBackground(new Color(0xF9F3EE));
	
	this.add(panel);
     this.pack();
     this.setResizable(false);
     this.setVisible(false);
	
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
		
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public String cDString(int min,int sec) {
		
		if(min<10&&sec<10) {
			return "0"+min+":0"+sec;
		}
		else if(min<10) {
			return "0"+min+":"+sec;
		}
		else if(sec<10) {
			return min+":0"+sec;
		}
		else {
			return min+":"+sec;
		}
		
		
	}
	
	static int t=0;
	public String getTime() {
		return this.time;
	}
	public boolean isRunning() {
		
		return timer.isRunning();
		
	}
	
	
	
	
	
	public void settimerOn30() {
		
		
		
		this.min.setValue(30);
		this.sec.setValue(0);
		
		this.demarrer.doClick();
	}
	public void stoptimer() {
		
		this.min.setValue(0);
		this.sec.setValue(0);
		Fpanel.getCountDown().setText("00:00");
		this.timer.stop();
	}
	
	public void addConsult(int idMicroFilm,int idLecture,java.sql.Date dateConsultation,int idPost) {
		db=new BaseDeDonne();
		db.Connecter();
		db.ajouterConsultation ( idMicroFilm, idLecture,dateConsultation,idPost);
		db.deconnecter();
		
		
	}
	
	
	
}
