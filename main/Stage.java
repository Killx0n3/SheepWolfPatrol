package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import onscreen.*;

public class Stage extends    javax.swing.JPanel 
                   implements MouseListener,
                              MouseMotionListener {
	public static Stage stage;
	public Grid grid;
	public onscreen.Character sheep;
	public onscreen.Character tiger;
	public onscreen.Character wolf;
	public onscreen.Character shepherd;
	public boolean readyToStep;
	public boolean isCaught=false;
	public static boolean isInitialized=false;
	
	Point lastMouseLoc = new Point(0, 0);
	Cell mouseWasIn = null;

	List<MouseObserver> observers = new ArrayList<MouseObserver>();

	public static Stage getStage(){
		if(!isInitialized){
			isInitialized = true;
			stage = new Stage();
		}
		return stage;
	}
	
	private Stage() {
		readyToStep = false;
		grid = new Grid();
		
		for(int i = 0; i < 20; i++)
			for(int j = 0; j < 20; j++)
				registerMouseObserver(grid.getCell(i,j));
	}
	

	public void paint(Graphics g) {
		draw(g);
	}

	public void draw(Graphics g) {
		grid.draw(g);
		sheep.draw(g);
		tiger.draw(g);
		wolf.draw(g);
		shepherd.draw(g);
		if (result()){
			g.setColor(Color.BLACK);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			g.drawString("Game Over!", 200,200);
		}
	}

	public void step() {
		sheep.act();
		wolf.act();
		tiger.act();
		readyToStep = false;
		if(sheep.getLocation()==shepherd.getLocation() && !isCaught){
			shepherd = new CharsInSameCell(shepherd);
			isCaught = true;
		}
	}

	public void registerMouseObserver(MouseObserver mo) {
		observers.add(mo);
	}
	
	public void setup(){
		shepherd = new Shepherd(grid.giveMeRandomCell());
		sheep    = new Sheep(grid.giveMeRandomCell());
		tiger    = new Tiger(grid.giveMeRandomCell());
		wolf     = new Wolf(grid.giveMeRandomCell());

		registerMouseObserver(shepherd);

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public Cell oneCellCloserTo(Cell from, Cell to) {
		int xdiff = to.x - from.x;
		int ydiff = to.y - from.y;
		
		if(Math.abs(xdiff)<=5 && Math.abs(ydiff)<=5){		// Checking if it's in range 5
			return grid.getCell(from.x + Integer.signum(xdiff), from.y + Integer.signum(ydiff));
		}
		
		else 
			return randomizer(from);	
	}
	
	

	public boolean leftToRight = true;
	public Cell oneCellCloserToWolPatrolling(Cell from, Cell to){
		int xdiff = to.x - from.x;
		int ydiff = to.y - from.y;
		
		if(Math.abs(xdiff)<=5 && Math.abs(ydiff)<=5){		// Checking if it's in range 5
			
			return grid.getCell(from.x + Integer.signum(xdiff), from.y + Integer.signum(ydiff));
		}
		else { 
			if(from.x<=0)
				leftToRight=false;
			if(from.x>=19)
				leftToRight=true;
			if(!leftToRight)
				return grid.getCell(from.x+1, from.y);
			if(leftToRight)
				return grid.getCell(from.x-1, from.y);
			return grid.getCell(from.x, from.y);
		}
			
		
	}
	
	Cell plsh = new Cell();
	boolean firstTime = true;
	int prevDisX=0;
	int prevDisY=0;
	public Cell oneCellCloserToTiger(Cell from, Cell to) {
		int xdiff = to.x - from.x;
		int ydiff = to.y - from.y;
		if (firstTime)
			plsh = to;
		firstTime =false;
		
		if(Math.abs(xdiff)<=5 && Math.abs(ydiff)<=5){		// Checking if it's in range 5
			if (((xdiff<0 && to.x>plsh.x) || (ydiff<0 && to.y>plsh.y)) ||  ((xdiff>0 && to.x<plsh.x) || (ydiff>0 && to.y<plsh.y))){		// Checking if shepherd is coming towards the tiger
				plsh = to;
				if ((from.x>0 && from.x<19) && (from.y>0 && from.y<19))
					return grid.getCell(from.x + Integer.signum(-xdiff), from.y + Integer.signum(-ydiff));
				else 
					return grid.getCell(from.x + Integer.signum(xdiff), from.y + Integer.signum(ydiff));
			}	
			else{
				plsh = to;
				return grid.getCell(from.x + Integer.signum(xdiff), from.y + Integer.signum(ydiff));
			}
				
		}
		else {		
			plsh = to;		
			return randomizer(from);
		}
			
	}
	
	// implementation of MouseListener and MouseMotionListener
	public void mouseClicked(MouseEvent e){
		if (shepherd.getBounds().contains(e.getPoint())){
		  shepherd.mouseClicked(e);
		}
	}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseDragged(MouseEvent e){}
	public void mouseMoved(MouseEvent e){
		for (MouseObserver mo : observers) {
			Rectangle bounds = mo.getBounds();
			if(bounds.contains(e.getPoint())) {
				mo.mouseEntered(e);
			} else if (bounds.contains(lastMouseLoc)) {
				mo.mouseLeft(e);
			}
		}
		lastMouseLoc = e.getPoint();
	}

  public boolean result(){
  	if (shepherd.getLocation().equals(wolf.getLocation())){
  		return true;
  	}
  	else if (wolf.getLocation().equals(sheep.getLocation())){
  		return true;
  	}
  else if (tiger.getLocation().equals(shepherd.getLocation())){
		return true;
	}
  	else {
  		return false;
  	}
  }
  
  private Cell randomizer (Cell from){
		Random dir = new Random();	
		
		if (dir.nextInt(8) == 0){
			if(from.x+1<20)
				return grid.getCell(from.x+1, from.y);
			else
				return grid.getCell(from.x-1, from.y);
		}
		
		if (dir.nextInt(8) == 1){
			if(from.y+1<20)
				return grid.getCell(from.x, from.y+1);
			else
				return grid.getCell(from.x, from.y-1);
		}
		
		if (dir.nextInt(8) == 2){
			if(from.x-1>-1)
				return grid.getCell(from.x-1, from.y);
			else
				return grid.getCell(from.x+1, from.y);
		}
		
		if (dir.nextInt(8) == 3){
			if(from.y-1>-1)
				return grid.getCell(from.x, from.y-1);
			else
				return grid.getCell(from.x, from.y+1);
		}
		
		if (dir.nextInt(8) == 4){
			if(from.x+1<20 && from.y+1<20)
				return grid.getCell(from.x+1, from.y+1);
			else
				return grid.getCell(from.x-1, from.y-1);
		}
		
		if (dir.nextInt(8) == 5){
			if(from.x-1>-1 && from.y-1>-1)
				return grid.getCell(from.x-1, from.y-1);
			else
				return grid.getCell(from.x+1, from.y+1);
		}
		
		if (dir.nextInt(8) == 6){
			if(from.x+1<20 && from.y-1>-1)
				return grid.getCell(from.x+1, from.y-1);
			else
				return grid.getCell(from.x-1, from.y+1);
		}
		
		else {
			if(from.x-1>-1 && from.y+1<20)
				return grid.getCell(from.x-1, from.y+1);
			else
				return grid.getCell(from.x+1, from.y-1);
		}
	}
  
}
