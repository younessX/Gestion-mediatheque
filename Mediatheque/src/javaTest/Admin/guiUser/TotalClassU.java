package javaTest.Admin.guiUser;

import javaTest.CONCEPTION.BaseDeDonne;

import javax.swing.*;
import java.awt.*;

public class TotalClassU {
    private String title, number;
    private ImageIcon icon;
    private Color BackgroundColor;
    private String type;
    private  int id;

    public TotalClassU(String title, String number, ImageIcon icon, Color backgroundColor, String type, int id) {
        this.title = title;
        this.number = number;
        this.icon = icon;
        BackgroundColor = backgroundColor;
        this.type=type;
        this.id = id;
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
    	number=db.getStatistics(type, id)+"";
    	
    	db.deconnecter();
    	
    	
    }
    
    
    
    
}
