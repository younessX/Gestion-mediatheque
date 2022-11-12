package javaTest.Admin.guiAdmin;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JTable{

    public  TablePanel(){
        this.setShowGrid(true);
        this.setRowHeight(27);
        this.setFont(new Font("serif", Font.BOLD|Font.ITALIC, 14));

        this.getTableHeader().setFont(new Font("Monospace", Font.PLAIN, 13));
        this.getTableHeader().setBackground(new Color(0xD9AA63));

        this.setGridColor(new Color(0x2C1810));
        this.getTableHeader().setForeground(Color.black);
        this.getTableHeader().setPreferredSize(new Dimension(760, 35));
    }

}
