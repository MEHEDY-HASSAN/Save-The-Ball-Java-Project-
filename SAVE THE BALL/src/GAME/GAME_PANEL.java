package GAME;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GAME_PANEL extends JPanel implements Runnable {

	static final int GAME_WIDTH = 900;
	static final int GAME_HEIGHT = 500;
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int STICK_WIDTH = 25;
	static final int STICK_HEIGHT = 100;
	
	public boolean running = true;
	
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	STICKS s1;
	STICKS s2;
	BALL ball;
	Score score;
	
	public GAME_PANEL() {
		
		GENERATE_NEW_STICK();
		GENERATE_NEW_BALL();
		score = new Score(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new listener());
		this.setPreferredSize(SCREEN_SIZE);

		gameThread = new Thread(this);
		gameThread.start();
//		System.out.println("HERE");
	}
	
	
	public void GENERATE_NEW_BALL()
	{
		random = new Random();
		int A = (GAME_WIDTH/2) - (BALL_DIAMETER/2);
		int B = random.nextInt((GAME_HEIGHT/2) - (BALL_DIAMETER/2));
		ball = new BALL(A,B,BALL_DIAMETER,BALL_DIAMETER);
	}
	public void GENERATE_NEW_STICK() 
	{
		int middle = GAME_HEIGHT/2;
		int stick_middle = STICK_HEIGHT/2;
		s1 = new STICKS(0,middle - stick_middle,STICK_WIDTH,STICK_HEIGHT,1);
		s2 = new STICKS(GAME_WIDTH-STICK_WIDTH,middle - stick_middle,STICK_WIDTH,STICK_HEIGHT,2);
	}

	public void paint(Graphics g)
	{
//		super.paint(g);
//		draw(g);

		image = createImage(getWidth(),getHeight()); 
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this); // this -> JPanel (game Panel) 
	}
	public void draw(Graphics g)
	{
		s1.draw(g);
		s2.draw(g);
		ball.draw(g);
		score.draw(g);
//		Toolkit.getDefaultToolkit().sync(); 
	}
	public void move()
	{
//		s1.move();
//		s2.move();
		ball.move();
	}
	public void checkcollision()
	{
		// bounce in top and bottom 
		if(ball.y<=0)
		{
			ball.setYD(-ball.yV);
		}
		if(ball.y >= GAME_HEIGHT-BALL_DIAMETER)
		{
			ball.setYD(-ball.yV);
		}
		/// bounce in ball and sticks
		
		if(ball.intersects(s1))
		{
			ball.xV = Math.abs(ball.xV);
			// difficulty
			ball.xV++; 
			if(ball.yV > 0) ball.yV++; // speed increased 
			else ball.yV--;
			ball.setXD(ball.xV);
			ball.setYD(ball.yV);
		}
		if(ball.intersects(s2))
		{
			ball.xV = Math.abs(ball.xV);
			// difficulty
			ball.xV++; 
			if(ball.yV > 0) ball.yV++; // speed increased
			else ball.yV--;
			ball.setXD(-ball.xV);
			ball.setYD(ball.yV);
		}
		
		/// screen er bahire jawa jabe na
		if(s1.y<=0)
		{
			s1.y = 0;
		}
		if(s1.y >= GAME_HEIGHT - STICK_HEIGHT)
		{
			s1.y = GAME_HEIGHT - STICK_HEIGHT;
		}
		if(s2.y<=0)
		{
			s2.y = 0;
		}
		if(s2.y >= GAME_HEIGHT - STICK_HEIGHT)
		{
			s2.y = GAME_HEIGHT - STICK_HEIGHT;
		}
		
		/// score
		if(ball.x <=0) {
			score.player2++;
			GENERATE_NEW_STICK();
			GENERATE_NEW_BALL();
//			System.out.println("Player 2: "+score.player2);
		}
		if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
			score.player1++;
			GENERATE_NEW_STICK();
			GENERATE_NEW_BALL();
//			System.out.println("Player 1: "+score.player1);
		}
		
	}
	
	
//	@Override
//	public void run() {
//		 long LastTime = System.nanoTime();
//		 double amountofTIcks = 60.0; // FPS
//		 double ns = 1000000000 / amountofTIcks;
////		 System.out.println(ns);
//		 double delta = 0;
//		 
//		 while(running) 
//		 {
//			 long now = System.nanoTime();
//			 delta += (now - LastTime)/ns;
////			 System.out.println(delta);
//			 LastTime = now;
//			 if(delta >= 1)
//			 {
//				 move();
//				 checkcollision();
//				 repaint();
//				 --delta;
////				 System.out.println("here");
//			 }
//		 }
////		 FPS is an abbreviation for Frames Per Second. In the context of the above implementation, it is the number of times functions are called per second.
////		 Game Speed is the number of times the game state gets updated per second, or in other words, the number of times update_game() is called per second.
//	}
	
	@Override
	public void run() { // GAME LOOP
		final int tics = 60; /// FPS
		final double ns = 1000000000 / tics;
		double delta = 0;
		long startTime = System.nanoTime();
		while(running)
		{
			long currentTime = System.nanoTime();
			delta += (currentTime - startTime);
			startTime = currentTime;
			if(delta >= ns)
			{
				move();
				checkcollision();
				repaint();
				delta-=ns;
			}
		}
	}
	
	public class listener extends KeyAdapter{
		public void keyPressed(KeyEvent e)
		{
			s1.KeyPressed(e);
			s2.KeyPressed(e);
		}
		public void KeyReleased(KeyEvent e)
		{
			s1.KeyReleased(e);
			s2.KeyReleased(e);
		}
	}
	
}
