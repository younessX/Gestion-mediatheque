package javaTest.FrontPage;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	private LoginPanel lpanel;
	private DemandePanel dpanel;
	private JPanel panel;
	public MainFrame(){
		panel=new JPanel();
		lpanel=new LoginPanel(this);
		dpanel=new DemandePanel();
		panel.setLayout(new BorderLayout());
		panel.add(lpanel,BorderLayout.CENTER);
		lpanel.getDemand().addActionListener((e)->{
			
			panel.removeAll(); dpanel.revalidate();
			panel.add(dpanel,BorderLayout.CENTER);
			
			repaint();
			revalidate();
			
			
		});
      dpanel.ReturnLogin().addActionListener((e)->{
			
			panel.removeAll();
			lpanel.revalidate();
			panel.add(lpanel,BorderLayout.CENTER);
			
			repaint();
			revalidate();
			
			
		});
		
		this.setLayout(new BorderLayout());
		
		this.add(panel,BorderLayout.CENTER);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	      
	        pack();
	       setVisible (true);
		this.setLocationRelativeTo(null);
		
	}

}
