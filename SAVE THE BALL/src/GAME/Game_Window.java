package GAME;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game_Window extends JFrame{
	
	GAME_PANEL p;
	private ImageIcon icon;
	
	public Game_Window() {
		p = new GAME_PANEL();
		this.add(p);
		this.setTitle("SAVE THE BALL");
		this.setResizable(false); 
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocation(200,200);
		Initialize_icon();
	}
	public void Initialize_icon() {
		icon = new ImageIcon(getClass().getResource("Save The Ball Icon.png"));
		this.setIconImage(icon.getImage());
	}
	
	public static void main(String[] args) {
		Game_Window window = new Game_Window();
		window.setVisible(true);
		
	}
	
}

