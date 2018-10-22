package onscreen;

import main.*;

public class Wolf extends Character {
    public Wolf(Cell location){
        super(location, new java.awt.Color(255,0,0), new java.awt.Color(153,0,0), new behaviours.WolfPatrolling(Stage.getStage().sheep));
    }
}
