
public class User implements EventHandler {
	
	int workingCount;
	int failureCount;
	double temp= 1;
	
	public User() {
		workingCount=0;
		failureCount=0;
	    temp= 1;
	}

	@Override
	public void respondToEvent(Event e, SimulationTrace s) {
		double timeUser = e.getTime();
		
		if ((timeUser/(60*temp)) > 1)
		{ 	
	//	System.out.println("entro user");
		e.setTime(temp*60);
		temp++;
		//System.out.println("temp "+temp);
		// TODO Auto-generated method stub
		//if (timeUser % 60 == 0)  { 
		if (e.getType()==s.userCheck) {
			if (s.m.working) {
				workingCount++;
			} else {
				failureCount++;
			}
		}
		
		System.out.println(e.getTime()+" user check: working "+(100*workingCount)/(workingCount+failureCount)+"%");
		//e.setTime(e.getTime()+60);
		s.scheduleEvent(e);
	}
	}

}
