/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class myPanel extends JPanel
{ 

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
              if (GameB.newSimon != null)
		{
			GameB.newSimon.paint((Graphics2D) g);
		}
	}

        }

