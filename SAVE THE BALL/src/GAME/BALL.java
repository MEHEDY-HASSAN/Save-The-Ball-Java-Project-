package GAME;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BALL extends Rectangle {

	Random random;
	int xV;
	int yV;
	
	public BALL(int x,int y,int width,int height) {
		super(x,y,width,height);
		random = new Random();
		int randomX = random.nextInt(2); // 0 -> left , 1 -> right
		if(randomX == 0)
		{
			randomX--;
		}
		setXD(randomX*2);
		int randomY = random.nextInt(2);
		if(randomY == 0)
		{
			randomY--;
		}
		setYD(randomY*2);
	}
	
	public void setXD(int randomx)
	{
		xV = randomx;
	}
	public void setYD(int randomy)
	{
		yV = randomy;
	}
	public void move()
	{
		x += xV;
		y += yV;
	}
	public void draw(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillOval(x,y,width,height);
	}
	

}
