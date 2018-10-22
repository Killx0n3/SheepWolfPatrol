package behaviours;



import main.Stage;
import onscreen.Cell;

public class WolfPatrolling implements Behaviour {
	onscreen.Character target;
	  
	  public WolfPatrolling(onscreen.Character target){
		  this.target = target;
		  }

	  public Cell execute(Cell location){
		  return Stage.getStage().oneCellCloserToWolPatrolling(location, target.getLocation());
	  }
}
