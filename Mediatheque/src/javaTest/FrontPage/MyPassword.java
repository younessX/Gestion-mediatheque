package javaTest.FrontPage;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class MyPassword extends JPasswordField {

   private ImageIcon image=null;

    public MyPassword() {
    	this.setOpaque(false);
        setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 5, 7, 5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
      
        //  paint border
        if (isFocusOwner()) {
            g.setColor(new Color(6, 135, 196));
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
        } else {
            g.setColor(new Color(0xd28666));
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
        }
        
        Graphics2D g2=(Graphics2D)g;
        if(image!=null)
        g2.drawImage(image.getImage(),getWidth()-30,4,image.getIconWidth()-5,image.getIconHeight()-6, null);
    }
    
    public void setImageIcon(ImageIcon image){
    	
    	
    	this.image=image;
    	
    }



  
}
