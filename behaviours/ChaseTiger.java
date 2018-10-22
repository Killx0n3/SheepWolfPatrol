package behaviours;
import main.*;
import onscreen.*;

public class ChaseTiger implements Behaviour {
  onscreen.Character target;
  
  public ChaseTiger(onscreen.Character target){this.target = target;}

  public Cell execute(Cell location){
    return Stage.getStage().oneCellCloserToTiger(location, target.getLocation());
  }

}