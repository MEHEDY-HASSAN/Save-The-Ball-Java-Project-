package GAME;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Score extends Rectangle{
	static int GAME_WIDTH;
	static int GAME_HEIGHT;

	int player1;
	int player2;

	public Score(int w,int h) {
		Score.GAME_HEIGHT = h;
		Score.GAME_WIDTH = w;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.green);
		g.setFont(new Font("Ariel",Font.BOLD,60));
		g.drawLine(GAME_WIDTH/2,0,GAME_WIDTH/2,GAME_HEIGHT-400);
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (GAME_WIDTH/2)-85, 50);
		g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (GAME_WIDTH/2)+20, 50);
	}

}
