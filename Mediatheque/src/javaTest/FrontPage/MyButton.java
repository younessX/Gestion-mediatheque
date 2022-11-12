package javaTest.FrontPage;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class MyButton extends JButton {

    public Color getColorOver() {
        return colorOver;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public boolean over;
    private Color colorOver;
    private Color color;
    private Color colorClick;

    public MyButton() {
       
        colorOver = new Color(0xdc764b);
        color = new Color(0xdf7042);
        colorClick = new Color(0xe4622c);
        
        this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				setB(colorClick);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setB(color);
			}
        	
        	
        	
        	
        	
        });
       this.setBackground(color);
    this.setFocusable(false); 
    this.setBorder(BorderFactory.createMatteBorder(0, 0, 0,0,new Color(0xebccc5)));
    }
      
           
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0xd19b85));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(getBackground());
        g2.fillRect(2, 2, getWidth() - 4, getHeight() - 4);
        super.paintComponent(grphcs);
    }
    
    
    public void setB(Color color) {
    	
    	
    	this.setBackground(color);
    }
}
