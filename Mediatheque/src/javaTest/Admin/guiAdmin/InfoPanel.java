package javaTest.Admin.guiAdmin;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel{
    private TotalClass total;
    private JLabel labelTitle, labelNumber, labelIcon;
public InfoPanel(TotalClass total){
    //attribut;
    this.total = total;

    this.setSize(210, 160);
    this.setBackground(total.getBackgroundColor());
    this.setLayout(null);

    labelTitle = new JLabel(total.getTitle());
    labelNumber = new JLabel(total.getNumber());
    labelIcon = new JLabel();

    labelIcon.setIcon(total.getIcon());
    labelIcon.setBounds(120, 70,   65, 60);

    labelNumber.setFont(new Font("tahoma", Font.BOLD, 25));
    labelNumber.setForeground(new Color(0xE7EEF8));
    labelNumber.setBounds(5, 50, 120, 30);


    labelTitle.setFont(new Font("Arial", Font.PLAIN, 16));
    labelTitle.setForeground(new Color(0xE7EEF8));
    labelTitle.setBounds(5, 15, 170, 25);

    this.add(labelTitle);
    this.add(labelNumber);
    this.add(labelIcon);
}

}
