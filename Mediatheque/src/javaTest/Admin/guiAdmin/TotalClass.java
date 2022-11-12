package javaTest.Admin.guiAdmin;
import javax.swing.*;

import javaTest.CONCEPTION.BaseDeDonne;

import java.awt.*;

public class TotalClass {
    private String title, number;
    private ImageIcon icon;
    private Color BackgroundColor;
    private String type;

    public TotalClass(String title, String number, ImageIcon icon, Color backgroundColor,String type) {
        this.title = title;
        this.number = number;
        this.icon = icon;
        BackgroundColor = backgroundColor;
        this.type=type;
        setStatis();
        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public Color getBackgroundColor() {
        return BackgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        BackgroundColor = backgroundColor;
    }
    
    public void setStatis() {
    	
    	BaseDeDonne db=new BaseDeDonne();
    	db.Connecter();
    	number=db.getStatistics(type)+"";
    	
    	db.deconnecter();
    	
    	
    }
    
    
    
    
}
