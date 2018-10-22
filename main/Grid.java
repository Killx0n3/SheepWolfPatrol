package main;

import java.awt.*;
import onscreen.*;

public class Grid {
	Cell[] cells = new Cell[400];
	
	public Grid(){
		for(int i = 0; i < 20; i++)
			for(int j = 0; j < 20; j++){
				cells[i*20+j]= new Cell(i,j);
      }
	}

	public void draw(Graphics g){
		for(int i = 0; i < 20; i++)
			for(int j = 0; j < 20; j++)
				cells[i*20+j].draw(g);
	}

  public Cell getCell(int i, int j)        {return cells[i*20+j];}
  public void putCell(int i, int j, Cell e){cells[i*20+j] = e;}

	public Cell giveMeRandomCell(){
		int x = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, 20);
		int y = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, 20);
		return cells[x*20+y];
	}

	public Cell cellAt(Point point){
		for(int i = 0; i < 20; i++){
		  	for(int j = 0; j < 20; j++){
		  		Cell current = cells[i*20 + j];
		  		if (current.getBounds().contains(point)) {
		  			return current;
	        }
		  	}
		}
		return null;
	}
}