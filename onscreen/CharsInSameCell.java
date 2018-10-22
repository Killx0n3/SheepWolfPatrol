package onscreen;
import java.awt.Color;
import java.awt.Graphics;

public class CharsInSameCell extends CharacterDecorator{

	public CharsInSameCell(Character charc) {
		super(charc);
	}
	public void draw(Graphics G){
		character.draw(G);
		Cell location = this.character.getLocation();
		G.setColor(Color.WHITE);
		G.fillRect(location.getTopLeft().x+5, location.getTopLeft().y+5, 25, 25);
	}

}
