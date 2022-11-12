package javaTest.Admin.guiUser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javaTest.CONCEPTION.Personne;

public class UserPicPanel extends JPanel{
	
	private RoundPicOnly pic ;
	private JPanel userPanel;
	private JLabel username;
	private JLabel previlige;
	private JPanel empty;
	private Personne personne;
	public UserPicPanel(Personne personne) {
		this.personne=personne;
		pic=new RoundPicOnly(personne);
		empty=new JPanel();
		empty.setPreferredSize(new Dimension(10,0));
		username=new JLabel(personne.getPrenom()+" "+personne.getNom());
		username.setBounds(10, 10, 100, 25);
		username.setFont(new Font(null,Font.BOLD,15));
		previlige=new JLabel(personne.getTypeUser());
	    previlige.setBounds(10, 45, 100, 25);
	    previlige.setFont(new Font(null,Font.BOLD,15));
		this.setLayout(new BorderLayout());
		userPanel=new JPanel();
		userPanel.setLayout(null);
		userPanel.add(username);
		userPanel.add(previlige);
		this.add(pic,BorderLayout.WEST);
		this.add(userPanel,BorderLayout.CENTER);
		this.add(empty,BorderLayout.EAST);
		
		
		
		
		this.setPreferredSize(new Dimension(200,80));
		
	}
	public JLabel getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username.setText(username);
	}
	public JLabel getPrevilige() {
		return previlige;
	}
	public void setPrevilige(JLabel previlige) {
		this.previlige = previlige;
	}
	public void setImage(Icon icon) {
		
		pic.setIcon(icon);
		
	}
	
	

}
