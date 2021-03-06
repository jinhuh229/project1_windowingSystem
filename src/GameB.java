/* * Rubayth Haque, Alex Petros, Jin Huh"
 * Project1. Simon Game
 * 09/24/2018
 * CSC 4380/6380 WINDOWING SYSTEMS PROGRAMMING
 *
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.io.IOException;



public class GameB implements ActionListener, MouseListener
{

	public static GameB newSimon;
        public myPanel panel;
        public Random r;
        public static final int WIDTH = 800, HEIGHT = 800;
        public int lit, count, i, currentSize, check;
        private boolean gameOver, playingSequence, dark, userInput;
       
        
        int randColor[] = {1, 2, 3, 4};
        ArrayList <Integer> simonSequence = new ArrayList<Integer>();
        ArrayList <Integer> currentSequence = new ArrayList<Integer>();
        ArrayList <Integer> playerSequence = new ArrayList<Integer>();
        
        JButton b =new JButton("START");  
       
        
        public GameB() 
	{
		JFrame frame = new JFrame("Simon");
		Timer timer = new Timer(300, this);
		panel = new myPanel();
                

		frame.setSize(800,1200);
		frame.setVisible(true);
		frame.addMouseListener(this);
		frame.setResizable(false);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((screen.getWidth() - frame.getWidth()) /2);
                int y = (int) ((screen.getHeight() -frame.getHeight()) /2);
                frame.setLocation(x, y); 
                   
                timer.start();
                
                panel.setLayout(null);
                b.setBounds(358, 381, 80, 20);
                panel.add(b);
                
                b.addActionListener(new Action1());
	}
        
      
        public void run(){
            r = new Random(System.currentTimeMillis());
            currentSequence.clear();
            playerSequence.clear();
            createSequence();
            lit = 0; count=0; i=0; currentSize=0; check=-1;
            gameOver=false;playingSequence=true;
            dark=false; userInput=false;
            b.setVisible(false);
        }
        
        public void createSequence(){
            for (int i=0;i<100;i++){
                int n = r.nextInt(4);
                simonSequence.add(randColor[n]);
            }
            currentSequence.add(simonSequence.get(0));
        }
	

	public static void main(String[] args)
	{
		newSimon = new GameB();
	}

        //actionlistner for start button
        static class Action1 implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
              
                newSimon.run();
            }
        }

        //method always running based on timer
	@Override
	public void actionPerformed(ActionEvent e)
	{
            //turns flash off everytime there is a flash
            if(dark==true){
                lit=0;
                dark=false;
            }
            
            //plays sequence from beginning
            else if(playingSequence==true ){
                lit=currentSequence.get(i);
                dark=true;
                i++;
                
                //end of sequence
                if(i==currentSequence.size()){
                    playingSequence=false;
                    userInput=true;
                }
            }
            //checks user input, either gameover or gets ready for next sequence
            else if(userInput==true && playerSequence.isEmpty()==false ){
                currentSize=currentSequence.size();
                count++;
                i=0;
                if (lit>0){
                  dark=true;
                }
                
                try{
                if (playerSequence.equals(currentSequence)){
                        playingSequence=true;
                        playerSequence.clear();
                        userInput=false;
                        check=-1;
                        currentSequence.add(simonSequence.get(count));
                        }
               
                else if (playerSequence.get(check)!=currentSequence.get(check) &&
                        check<currentSize){
                        gameOver=true;
                        b.setText("PLAY AGAIN");
                        b.setVisible(true);
                    }
                }
                catch(java.lang.IndexOutOfBoundsException a ){
                    gameOver=true;
                    b.setText("PLAY AGAIN");
                    b.setVisible(true);
                }
            }
            panel.repaint();
	}

	public void paint(Graphics2D g) //draws everything
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (lit == 1){
                    g.setColor(Color.GREEN);
		}
		else{
                    g.setColor(Color.GREEN.darker());
		}

		g.fillRect(0, 0, WIDTH / 2, HEIGHT / 2);

		if (lit == 2){
                    g.setColor(Color.RED);
		}
		else{
                    g.setColor(Color.RED.darker());
		}

		g.fillRect(WIDTH / 2, 0, WIDTH / 2, HEIGHT / 2);

		if (lit == 3){
                    g.setColor(Color.ORANGE);
		}
		else{
                    g.setColor(Color.ORANGE.darker());
		}

		g.fillRect(0, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);

		if (lit == 4){
                    g.setColor(Color.BLUE);
		}
		else{
                    g.setColor(Color.BLUE.darker());
		}

		g.fillRect(WIDTH / 2, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
                //border
                g.setColor(Color.BLACK);
		g.fillRect(380, 0, WIDTH / 30, HEIGHT);
		g.fillRect(0, 380, WIDTH, HEIGHT / 30);

                if (gameOver==true){
                    g.setColor(Color.BLACK);
                    g.fillRect(0,0, WIDTH, HEIGHT);
                    g.setColor(Color.RED);
                    g.setFont(new Font("Arial", 1, 120));
                    g.drawString("WRONG", WIDTH / 5 , 200);
                    g.drawString("GAME OVER", WIDTH / 22 , 600);
		}
		
		g.setFont(new Font("Arial", 1, 30));
                g.drawString("Simon Game", 300, 830);
                
                
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.setColor(Color.GRAY);
                g.drawString("Rubayth Haque, Alex Petros, Jin Huh", 220, 850);
                
                                
                g.setFont(new Font("Arial", 1, 20));
               
                g.setColor(Color.BLACK);
                g.drawString("How to Play: ", 0, 870);
               
                g.setFont(new Font("Arial", 1, 13));
                g.drawString("Simon will give the first signal. Repaet the signal by pressing ", 50, 885);
                g.drawString("the same color. Simon will duplicate the first signal and add one.", 50, 900);
                g.drawString("Repeat these two signals by pressing the same color,in order", 50, 915);
                g.drawString("Continue playing as long as you can repeat each sequence of signals correctly", 50, 930);
		
	}
        
	@Override
	public void mousePressed(MouseEvent e){ //detects if clicked on a square
            int x = e.getX(), y = e.getY();
            if(userInput==true){
                if (x > 0 && x < WIDTH / 2 && y > 0 && y < HEIGHT / 2){
                   lit=1;
                   playerSequence.add(1);
                   check++;
                }
                else if (x > WIDTH / 2 && x < WIDTH && y > 0 && y < HEIGHT / 2){
                   lit=2;
                   playerSequence.add(2);
                   check++;
               }
                else if (x > 0 && x < WIDTH / 2 && y > HEIGHT / 2 && y < HEIGHT){
                   lit=3;
                   playerSequence.add(3);
                   check++;
               }
                if (x > WIDTH / 2 && x < WIDTH && y > HEIGHT / 2 && y < HEIGHT){
                   lit=4;
                   playerSequence.add(4);
                   check++;
               }
            }
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}

    
}
