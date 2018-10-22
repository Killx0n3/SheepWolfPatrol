package onscreen;

import main.*;
import java.awt.*;
import java.awt.event.*;

public class Shepherd extends Character {
    public Shepherd(Cell location){
        super(location, new Color(0,153,0), new Color(0,255,0), new behaviours.Passive());
    }

    public void mouseClicked(MouseEvent e){
      Stage.getStage().shepherd = new HighlightedCharacter(Stage.getStage().shepherd);
    }

}

