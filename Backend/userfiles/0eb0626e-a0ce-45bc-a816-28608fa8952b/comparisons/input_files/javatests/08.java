import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.FileNotFoundException;

import java.io.File;
import java.util.Scanner;

public class FetchAndProcessFromDisk implements FetchAndProcess {
    private Map<String, String> data;

    @Override
    public Map<String, String> exposeData() {
	return data;
    }

    @Override
    public void fetch(List<String> paths) {
	// Implement here
    	data=new HashMap<String, String>();
    	for(String f:paths){
    		try{ 
    		File gym = new File(f);
    		int i=f.lastIndexOf('/');
    		String filename = f.substring(i+1,f.length());
    		Scanner sc = new Scanner(gym);
    		while(sc.hasNextLine()){
    			String pokemon =sc.nextLine();
    			if(data.containsKey(pokemon)){
    				String old =data.get(pokemon);
    				String modified = old + ","+filename;
   
    				data.replace(pokemon,modified);
    			}
    			else{
    				data.put(pokemon,filename);
    			}

    			
    		}
    		      	


    		}
    		catch (FileNotFoundException e) {
      			//System.out.println("An error occurred.");
      			e.printStackTrace();
    		}
      	}

    }
}
