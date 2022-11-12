package javaTest.Admin.guiAdmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class OnePostPanel extends JPanel implements MouseListener{

	private static int num=0;

	private boolean state=false;
	private JLabel post; 
	public JLabel countDown;

      private String time="";
	private PostDetails postD;
	private JFrame frame;
	private Timer timer;
	private Robot robot;
		public OnePostPanel(AdminFrame frame) {
			
			this.frame=frame;
			
			countDown=new JLabel();
			countDown.setFont(new Font(null,Font.BOLD,20));
			
			post=new JLabel();
			
			this.setLayout(new GridLayout());
			
				 postD=new PostDetails(frame,true,"Post "+(++num),this);
				post=new JLabel("         ");
			  
				
				
				post.setIcon(new ImageIcon("images/off.png"));
				post.setHorizontalTextPosition(JLabel.LEFT);
				post.addMouseListener(this);
				post.setFocusable(false);
			    this.setLayout(new BorderLayout());
				add(post,BorderLayout.CENTER);
				add(countDown,BorderLayout.EAST);
				
				
				
				
				timer=new Timer(1,(e)->{
					time=postD.getTime();
					countDown.setText(time);
					 
					repaint();
					revalidate();
					if(!postD.isRunning()) {
						
						stop();
						
					}
					
					
				});
				timer.start();
				
				
				
			
			
			


			
			
			
			
			
			

			
			
			
			
			
			
             this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY),
            		 "Post "+(num),2, 1,new Font(null,Font.BOLD,20),new Color(0x643023)));
			this.setPreferredSize(new Dimension(120,120));
			this.setBackground(new Color(245,247,246));
			
			
		
			
		}



		@Override
		public void mouseClicked(MouseEvent e) {
			
			if(postD!=null) {
				postD.dispose();
				repaint();
			}
			
		
		
			

				
				
						
					
						
					
				if(e.getSource()==post) {
					
						
					this.setLayout(null);
				
					
					postD.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
								
						postD.setSize(300, 300);
						
				
						    postD.setBounds(e.getXOnScreen(),e.getYOnScreen(),postD.getWidth(),postD.getHeight());
	                    	postD.setLocationRelativeTo(this);
	                    
					
						postD.setVisible(true);
						
						
						
							
						
						
					
						
					}





		
		}


		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		public void stop() {
			
			this.timer.stop();
			
		}
		public JLabel getCountDown() {
			
			return this.countDown;
		}
		public static void setNum(int num) {
			OnePostPanel.num = num;
		}

		
public JLabel getPost() {
			
			return this.post;
		}
		
		
		
		

		
		
}
		
	

