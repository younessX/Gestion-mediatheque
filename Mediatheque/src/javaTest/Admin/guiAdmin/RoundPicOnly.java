package javaTest.Admin.guiAdmin;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
	import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
	import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
	import java.awt.Rectangle;
	import java.awt.RenderingHints;
	import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.Icon;
	import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javaTest.CONCEPTION.BaseDeDonne;
import javaTest.CONCEPTION.Personne;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
public class RoundPicOnly extends JComponent{

	  private Icon icon=new ImageIcon("images/user_icon.png");
	    private int borderSize;
	    private Personne personne;
	
	public RoundPicOnly(Personne personne){
		this.personne=personne;
		setImagedb();
		this.setPreferredSize(new Dimension(80,80));
		this.setBackground(new Color(245,247,246));
		
		
	}
	    public Icon getIcon() {
	        return icon;
	    }

	    public void setIcon(Icon icon) {
	        this.icon = icon;
	        revalidate();
	        repaint();
	    }

	    public int getBorderSize() {
	        return borderSize;
	    }

	    public void setBorderSize(int borderSize) {
	        this.borderSize = borderSize;
	    }

	  

	    @Override
	    protected void paintComponent(Graphics grphcs) {
	        if (icon != null) {
	            int width = getWidth();
	            int height = getHeight();
	            int diameter = Math.min(width, height);
	            int x = width / 2 - diameter / 2;
	            int y = height / 2 - diameter / 2;
	            int border = borderSize * 2;
	            diameter -= border;
	            Rectangle size = getAutoSize(icon, diameter);
	            BufferedImage img = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
	            Graphics2D g2_img = img.createGraphics();
	            g2_img.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            g2_img.fillOval(0, 0, diameter, diameter);
	            Composite composite = g2_img.getComposite();
	            g2_img.setComposite(AlphaComposite.SrcIn);
	            g2_img.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	            g2_img.drawImage(toImage(icon), size.x, size.y, size.width, size.height, null);
	            g2_img.setComposite(composite);
	            g2_img.dispose();
	            Graphics2D g2 = (Graphics2D) grphcs;
	            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            if (borderSize > 0) {
	                diameter += border;
	                g2.setColor(getForeground());
	                g2.fillOval(x, y, diameter, diameter);
	            }
	            if (isOpaque()) {
	                g2.setColor(getBackground());
	                diameter -= border;
	                g2.fillOval(x + borderSize, y + borderSize, diameter, diameter);
	            }
	            g2.drawImage(img.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH)
						, x + borderSize, y + borderSize, null);
	        }
	        super.paintComponent(grphcs);
	    }

	    private Rectangle getAutoSize(Icon image, int size) {
	        int w = size;
	        int h = size;
	        int iw = image.getIconWidth();
	        int ih = image.getIconHeight();
	        double xScale = (double) w / iw;
	        double yScale = (double) h / ih;
	        double scale = Math.max(xScale, yScale);
	        int width = (int) (scale * iw);
	        int height = (int) (scale * ih);
	        if (width < 1) {
	            width = 1;
	        }
	        if (height < 1) {
	            height = 1;
	        }
	        int cw = size;
	        int ch =size;
	        int x = (cw - width) / 2;
	        int y = (ch - height) / 2;
	        return new Rectangle(new Point(x, y), new Dimension(width, height));
	    }

	    private Image toImage(Icon icon) {
	        return ((ImageIcon) icon).getImage();
	    }
	    
	    public void setImage(Icon icon )
		{
	    	this.icon=icon;
	    	
	    }
	    
	    
	    public void setImagedb() {
	    	
	    	
	    	BaseDeDonne db=new BaseDeDonne();
	    	db.Connecter();
	    	
	    	ImageIcon img = db.getImage(personne.getIdPersonne());
	    	if(img==null)return;
	    	icon=img;
	    	db.deconnecter();
	    }
	}





