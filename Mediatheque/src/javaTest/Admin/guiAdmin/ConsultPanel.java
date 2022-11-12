package javaTest.Admin.guiAdmin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

class ConsultPanel extends JPanel{
private boolean state=false;
private OnePostPanel posts[]; 

private JFrame frame;
	public ConsultPanel(AdminFrame frame) {
		
		this.frame=frame;
		
		posts=new OnePostPanel[9];
		this.setLayout(new GridLayout(3,3));
		for(int i=0;i<9;i++) {
			
			posts[i]=new OnePostPanel(frame);
		
			add(posts[i]);
		}

		this.setPreferredSize(new Dimension(780,553));
		this.setBackground(new Color(245,247,246));
		
		
	
		
	}
	public void setNum() {
		
		this.posts[0].setNum(0);
	}



}
