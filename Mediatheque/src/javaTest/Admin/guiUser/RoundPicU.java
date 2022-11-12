package javaTest.Admin.guiUser;


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
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;

import javax.swing.BorderFactory;
import javax.swing.Icon;
	import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javaTest.Admin.guiAdmin.AdminFrame;
import javaTest.CONCEPTION.BaseDeDonne;
import javaTest.CONCEPTION.Personne;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
public class RoundPicU extends JComponent {

	private JButton add;
	  private Icon icon;

	  
	    private int borderSize;
	    
	private Personne personne;
	String path;
	UserFrame frame;
	
	public RoundPicU(Personne personne,UserFrame frame){
		//panel=new 
		this.frame=frame;
		this.personne=personne;
		
		
		getimagedb();
		 
		
			
			
		
		
		
		
		
		
		add=new JButton();
	
		
		add.setIcon(new ImageIcon("images/add-photo.png"));
	    add.setContentAreaFilled(false);
		add.setBorderPainted(false);
	
		add.addActionListener((event)->{
			
			
			
			
			
	
		    	  JFileChooser fileChooser = new JFileChooser();
			         fileChooser.setDialogTitle("Choisir Image");
			         FileNameExtensionFilter filter = new FileNameExtensionFilter(".png", "png");
			         fileChooser.setFileFilter(filter);
			         fileChooser.setAcceptAllFileFilterUsed(false);

			         int reponse = fileChooser.showSaveDialog(null);

			         if (reponse == JFileChooser.APPROVE_OPTION) {
			            try {
			               path = fileChooser.getSelectedFile().getAbsolutePath();
			               String fileName = path;
                              
			               if (path.contains("\\")) {
			                  fileName = fileName.replace("\\", " ");
			                  fileName = fileName.substring(fileName.lastIndexOf(" "));
			               }

			               Path sourceDirectory = Paths.get(path);
			               Path targetDirectory = Paths.get("pictures\\" + fileName);
			               
			               
			               //File dir=new File(PATH.contact(this.getClass().getName());
  
			               File dir=new File("pictures");
			               if(!dir.exists()){
			            	   
			            	   
			            	   dir.mkdir();
			            	   
			               }
			               
			               
			               //copy source to target using Files Class
			               Files.copy(sourceDirectory, targetDirectory,REPLACE_EXISTING);

			               //ajouter Image
			               Icon icon1 = new ImageIcon("pictures\\"+fileName);
			             icon=icon1;
			             frame.setImage(icon);
			             
			             imagedb()  ;
			             repaint();
			             
			              
			               //image.setIcon(icon);

			            } catch (Exception ex) {
			               ex.printStackTrace();
			            }
			         }
			         
			         
			         
			         
			        
			  	         
			  	              
		 
			
			
			
			
		});
		
		
		
//        this.setLayout(null);
//        
//        add.setBounds(365,168, 32, 32);
//        this.add(add);
		this.setLayout(null);
		add.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
		add.setFocusable(false);
		add.setBounds(333,130,100,100);
        this.add(add);
        
        
        
        
       
		
		this.setPreferredSize(new Dimension(780,200));
		this.setBackground(new Color(245,247,246));
		
		
	}
	    public Icon getIcon() {
	        return icon;
	    }

	    public void setIcon(Icon icon) {
	        this.icon = icon;
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
	        	super.paintComponent(grphcs);
	            int width = 200;
	            int height = 200;
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
	                g2.fillOval(0,0, diameter, diameter);
	            }
	            g2.drawImage(img, 280, 0, null);
	          
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
	    
	    public  void  imagedb() {
	    	BaseDeDonne db=new BaseDeDonne();
	    	db.Connecter();
	    	 db.ajouteImage (personne.getIdPersonne(),path);
	    	db.deconnecter();
	    
	}
	    public  void  getimagedb() {
	    	BaseDeDonne db=new BaseDeDonne();
	    	db.Connecter();
	    	ImageIcon img=db.getImage(personne.getIdPersonne());
	    	 if(img!=null)
	    	   icon=img;
	    	   else {System.out.println("image is null");}
	    	db.deconnecter();
	    
	}
	    
	   

}




