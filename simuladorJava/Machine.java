
public class Machine implements EventHandler {
	private double MTTF;
	private double MTTFvariance;
	public boolean working;
	
	public Machine() {
		MTTF = 30.0;
		MTTFvariance = 5.0;
		working = true;
	}
	
	@Override
	public void respondToEvent(Event e, SimulationTrace s) {
		
		
	//	System.out.println("entro2");
		if (e.getType()==s.working) {
			System.out.println(e.getTime()+" machine working");
			working=true;
			double timeToNextFailure = Math.abs(s.generator.nextGaussian()*MTTFvariance+MTTF);
			e.setTime(s.now+timeToNextFailure);
			e.setType(s.failure);
			s.scheduleEvent(e);
			return;
		}
		if (e.getType()==s.failure) {
			System.out.println(e.getTime()+" machine failure");
			working=false;
			return;
		}		
			if (e.getType()==s.startRepair) {		
			Event nextEvent  = new 	Event();
			//nextEvent.setHandler(s.r);
			//nextEvent.setType(s.startRepair);
			//nextEvent.setTime(s.now);
			
		//   System.out.println("event" + nextEvent);
		//	
			nextEvent.setTime(e.getTime());
			nextEvent.setHandler(s.r);
			nextEvent.setType(e.getType());
			nextEvent.getHandler().respondToEvent(nextEvent, s);
			s.scheduleEvent(e);
			return;
		}
	}

	

}
