package javaTest;
import javaTest.FrontPage.MainFrame;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class Test {

	public static void main(String[] args) {
		
            
	      SwingUtilities.invokeLater(new Runnable() {

			public void run() {
			new MainFrame();
				
			}
	    	  
	    	  
	      });
	   

	}

}
