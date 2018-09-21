/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import java.util.ArrayList;
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
        public int flashed = 0, count=0, i=0;
        public Random random;
        private boolean gameOver=false, mousePress=false, playingSequence=false,
                dark=false;
        public Random r = new Random();
        
        int randColor[] = {1, 2, 3, 4};
        ArrayList <Integer> simonSequence = new ArrayList<Integer>();
        ArrayList <Integer> currentSequence = new ArrayList<Integer>();
        ArrayList <Integer> playerSequence = new ArrayList<Integer>();
        
        public GameB() 
	{
		JFrame frame = new JFrame("Simon");
		Timer timer = new Timer(500, this);
		panel = new myPanel();
                

		frame.setSize(WIDTH,1000);
		frame.setVisible(true);
		frame.addMouseListener(this);
		frame.setResizable(false);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                
                timer.start();
                run();
		
	}
        
      
        public void run() {
            createSequence();
           /* for (int i=0;i<100;i++){
            flashed=simonSequence.get(1);
            panel.repaint();
            
            }*/
                
              
            
            
        }
        
        public void createSequence(){
            for (int i=0;i<100;i++){
                int n = r.nextInt(4);
                simonSequence.add(randColor[n]);
            }
        }
	

	public static void main(String[] args)
	{
		newSimon = new GameB();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
            if(dark==true){
                flashed=0;
                dark=false;
            }
            
            else if(playingSequence==true && !dark){
                flashed=currentSequence.get(i);
                dark=true;
                i++;
                System.out.println(i + " " + count + " " + currentSequence.size());
              
                if(i==currentSequence.size()){
                    playingSequence=false;
                }
            }
            
            else if (playingSequence==false && !dark){
                currentSequence.add(simonSequence.get(count));
                count++;
                i=0;
                playingSequence=true;
            }
            
            panel.repaint();
	}

	public void paint(Graphics2D g)
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (flashed == 1)
		{
			g.setColor(Color.GREEN);
		}
		else
		{
			g.setColor(Color.GREEN.darker());
		}

		g.fillRect(0, 0, WIDTH / 2, HEIGHT / 2);

		if (flashed == 2)
		{
			g.setColor(Color.RED);
		}
		else
		{
			g.setColor(Color.RED.darker());
		}

		g.fillRect(WIDTH / 2, 0, WIDTH / 2, HEIGHT / 2);

		if (flashed == 3)
		{
			g.setColor(Color.ORANGE);
		}
		else
		{
			g.setColor(Color.ORANGE.darker());
		}

		g.fillRect(0, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);

		if (flashed == 4)
		{
			g.setColor(Color.BLUE);
		}
		else
		{
			g.setColor(Color.BLUE.darker());
		}

		g.fillRect(WIDTH / 2, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
                g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", 1, 142));

          
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
            
            mousePress=true;
            int x = e.getX(), y = e.getY();
             if (x > 0 && x < WIDTH / 2 && y > 0 && y < HEIGHT / 2){
                flashed=1;
                playerSequence.add(1);
             }
            else if (x > WIDTH / 2 && x < WIDTH && y > 0 && y < HEIGHT / 2){
                flashed=2;
                playerSequence.add(2);
            }
            else if (x > 0 && x < WIDTH / 2 && y > HEIGHT / 2 && y < HEIGHT){
                flashed=3;
                playerSequence.add(3);
            }
            if (x > WIDTH / 2 && x < WIDTH && y > HEIGHT / 2 && y < HEIGHT){
                flashed=4;
                playerSequence.add(4);
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

