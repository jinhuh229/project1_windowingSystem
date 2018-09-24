/*
 * Rubayth Haque, Alex Petros, Jin Huh"
 * Project1. Simon Game
 * 09/24/2018
 * CSC 4380/6380 WINDOWING SYSTEMS PROGRAMMING
 *
 */

import java.awt.BasicStroke;
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
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;import java.util.Random;
import java.util.Random;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.Timer;


public class GameB implements ActionListener, MouseListener
{

	public static GameB newSimon;
        public myPanel panel;
        public static final int WIDTH = 800, HEIGHT = 800;
        public int flashed, count, i, currentSize, check;
        public Random r;
        private boolean gameOver, mousePress, playingSequence, dark, userInput;
       
        
        int randColor[] = {1, 2, 3, 4};
        ArrayList <Integer> simonSequence = new ArrayList<Integer>();
        ArrayList <Integer> currentSequence = new ArrayList<Integer>();
        ArrayList <Integer> playerSequence = new ArrayList<Integer>();
        
        public GameB() 
	{
		JFrame frame = new JFrame("Simon");
		Timer timer = new Timer(200, this);
		panel = new myPanel();
                
<<<<<<< HEAD
		frame.setSize(800,1200);
=======

		frame.setSize(1550,1000);
>>>>>>> 958c397f2f1f7857bc65fa2f2d2df899dafa1a60
		frame.setVisible(true);
		frame.addMouseListener(this);
		frame.setResizable(false);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                               
                timer.start();
                run();
		
	}
        
      
        public void run() {
            r = new Random(System.currentTimeMillis());
            currentSequence.clear();
            playerSequence.clear();
            createSequence();
            flashed = 0; count=0; i=0; currentSize=0; check=-1;
            gameOver=false; mousePress=false; playingSequence=true;
            dark=false; userInput=false;
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

	@Override
	public void actionPerformed(ActionEvent e)
	{
            System.out.println("check");
            //turns flash off everytime there is a flash
            if(dark==true){
                flashed=0;
                dark=false;
            }
            
            //plays sequence from beginning
            else if(playingSequence==true && !dark){
                flashed=currentSequence.get(i);
                dark=true;
                i++;
                
                //end of sequence
                if(i==currentSequence.size()){
                    playingSequence=false;
                }
            }
            //end of sequence, adds next sequence to currentSequence
            else if (playingSequence==false && !dark && userInput==false){
                currentSize=currentSequence.size();
                
                count++;
                i=0;
                userInput=true;
            }
            
            else if(userInput==true && playerSequence.isEmpty()==false ){
                
                //dark=true;
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
                        run();
                    }
            }
            panel.repaint();
	}

	public void paint(Graphics2D g)
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (flashed == 1){
                    g.setColor(Color.GREEN);
		}
		else{
                    g.setColor(Color.GREEN.darker());
		}

		g.fillRect(0, 0, WIDTH / 2, HEIGHT / 2);

		if (flashed == 2){
                    g.setColor(Color.RED);
		}
		else{
                    g.setColor(Color.RED.darker());
		}

		g.fillRect(WIDTH / 2, 0, WIDTH / 2, HEIGHT / 2);

		if (flashed == 3){
                    g.setColor(Color.ORANGE);
		}
		else{
                    g.setColor(Color.ORANGE.darker());
		}

		g.fillRect(0, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);

		if (flashed == 4){
                    g.setColor(Color.BLUE);
		}
		else{
                    g.setColor(Color.BLUE.darker());
		}

		g.fillRect(WIDTH / 2, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
                g.setColor(Color.BLACK);

                
                if (gameOver==true){
                    
                    g.setColor(Color.red);
                    g.fillRect(WIDTH,HEIGHT, WIDTH, HEIGHT);
                    System.out.println(gameOver);
                    //g.drawString("WRONG", WIDTH / 8 , HEIGHT / 2 + 42);
		}
		
                /*g.setFont(new Font("Arial", 1, 100));

		
                g.setFont(new Font("Arial", 1, 100));

                g.drawString("Simon Game", 900, 150);
                
                
                g.setFont(new Font("Arial", Font.BOLD, 30));
                g.setColor(Color.GRAY);
                g.drawString("Rubayth Haque, Alex Petros, Jin Huh", 900, 210);
                
                
                g.setFont(new Font("Arial", 1, 30));
                g.setColor(Color.BLACK);
                g.drawString("How to Play: ", 900, 350);
               
                g.setFont(new Font("Arial", 1, 20));
                g.drawString("Simon will give the first signal. Repaet the signal by pressing ", 900, 400);
                g.drawString("the same color. Simon will duplicate the first signal and add one.", 900, 420);
                g.drawString("Repeat these two signals by pressing the same color,in order", 900, 440);
                g.drawString("Continue playing as long as you can repeat each sequence of", 900, 460);
                g.drawString("signals correctly", 900, 480);

                
                
                g.setColor(Color.BLUE);
                g.setFont(new Font("Arial", 1, 20));
                g.drawString("CSC 4380/6380 WINDOWING SYSTEMS PROGRAMMING,", 900, 750);
                g.drawString("PROJECT 1", 900, 780);

          */

          

	}

        

	@Override
	public void mousePressed(MouseEvent e)
	{
            
            mousePress=true;
            int x = e.getX(), y = e.getY();
             if (x > 0 && x < WIDTH / 2 && y > 0 && y < HEIGHT / 2){
                flashed=1;
                playerSequence.add(1);
                check++;
             }
            else if (x > WIDTH / 2 && x < WIDTH && y > 0 && y < HEIGHT / 2){
                flashed=2;
                playerSequence.add(2);
                check++;
            }
            else if (x > 0 && x < WIDTH / 2 && y > HEIGHT / 2 && y < HEIGHT){
                flashed=3;
                playerSequence.add(3);
                check++;
            }
            if (x > WIDTH / 2 && x < WIDTH && y > HEIGHT / 2 && y < HEIGHT){
                flashed=4;
                playerSequence.add(4);
                check++;
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