import java.util.ArrayList;

public class Template {
    
    public static void main(String[] args) {

		// Get the list
        Q5Class teamList = new Q5Class();

        teamList.genList(new ArrayList<Integer>(){{
            add(1);
            add(5);
            add(9);
            add(3);
            add(-3);
        }});
        
        // Process the list
        teamList.processList();
		
		System.out.println("The total is " + teamList.getTotal() + ", and the average is " + teamList.getAverage() + ".");
	}
}