package GAME;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class STICKS extends Rectangle {

	int id;
	int yv;
	
	static final int speed = 20;
	
	public STICKS(int x,int y,int width,int height,int id) {
		super(x,y,width,height); // RECTANGLE constructor
		this.id = id;
	}
	
	public void KeyPressed(KeyEvent e)
	{
//		System.out.println("HERE");

		
		if(id == 1) /// player 1
		{
			if(e.getKeyCode()==KeyEvent.VK_W)
			{
				setYDir(-speed);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_S)
			{
				setYDir(speed);
				move();
			}
		}
		else /// player 2
		{
			if(e.getKeyCode()==KeyEvent.VK_I)
			{
				setYDir(-speed);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_K)
			{	
				setYDir(speed);
				move();
			}
		}
	}
	public void KeyReleased(KeyEvent e)
	{
		
		if(id == 1)
		{
			if(e.getKeyCode()==KeyEvent.VK_W)
			{
				setYDir(0);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_S)
			{
				setYDir(0);
				move();
			}
		}
		else 
		{
			if(e.getKeyCode()==KeyEvent.VK_I)
			{
				setYDir(0);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_K)
			{	
				setYDir(0);
				move();
			}
		}
	}
	public void setYDir(int yD)
	{
		yv = yD;
	}
	public void move()
	{
		y = y + yv;
	}
	public void draw(Graphics g)
	{		
		if(id == 1) /// player 1
		{
			g.setColor(Color.MAGENTA);
		}
		else /// Player 2
		{
			g.setColor(Color.BLUE);
		}
		g.fillRect(x, y, width, height);
	}

}
