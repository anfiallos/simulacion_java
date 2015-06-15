import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;


public class SimulationTrace {
	public Random generator = new Random(); // random number generator
	public EventHeap h;
	double now;
	
	public Machine m = new Machine();
	public Repairman r = new Repairman();
	public User u = new User();
	
	public SimulationTrace() {
		generator = new Random();
		h = new EventHeap(1000);
		now = 0;
	}

	public void scheduleEvent(Event e) {
		h.insert(e);
	}
	
	public void setup() {
		Event machineEvent = new Event();
		machineEvent.setHandler(m);
		machineEvent.setType(working);
		machineEvent.setTime(0);
		scheduleEvent(machineEvent);
		
		Event userEvent = new Event();
		userEvent.setHandler(u);
		userEvent.setType(userCheck);
		userEvent.setTime(60);
		scheduleEvent(userEvent);
		return;
	}
	
	public void run(double maxTime) {
		boolean flag = true;
		int first = 0;
	//	while (!h.isEmpty() && h.peek().getTime()<=maxTime) {
		while (!h.isEmpty() && flag == true  )  {	
				
		
			try{
			    FileInputStream fstream = new FileInputStream("datos.txt");
			          DataInputStream in = new DataInputStream(fstream);
			          BufferedReader br = new BufferedReader(new InputStreamReader(in));
			          String strLine;
			        
		        	  double dateUser = 0;
			          while ((strLine = br.readLine()) != null)   {
			        	  first ++;	
			        	  String[] tokens = strLine.split(" ");
			        	  Record record = new Record(tokens[0],tokens[1],tokens[2]);//process record , etc
		
			    h.remove();   
			   
				Event nextEvent  = new Event();
				dateUser= Double.parseDouble(tokens[1]);
				nextEvent.setHandler(u);
				nextEvent.setType(userCheck);
			 	nextEvent.setTime(dateUser);
				nextEvent.getHandler().respondToEvent(nextEvent, this);
				scheduleEvent(nextEvent);
	
				nextEvent.setHandler(m);
				nextEvent.setType(Integer.parseInt(tokens[2]));
				nextEvent.setTime(Double.parseDouble(tokens[1]));
			
				nextEvent.getHandler().respondToEvent(nextEvent, this);
				scheduleEvent(nextEvent);      
				
			     }
			   flag = false;
			   in.close();
				
			   }catch (Exception e){
			     System.err.println("Error: " + e.getMessage());
			   }
		// }
			
			
	
		
		}
	}
	// events
	public final int working = 1;
	public final int failure = 2;
	public final int startRepair = 3;
	public final int finishRepair = 4;
	public final int userCheck = 5;

}
