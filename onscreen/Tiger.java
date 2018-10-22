package onscreen;

import main.*;

public class Tiger extends Character {
    public Tiger(Cell location){
        super(location, new java.awt.Color(253,131,29), new java.awt.Color(224, 224, 224), new behaviours.ChaseTiger(Stage.getStage().shepherd));
    }
}
