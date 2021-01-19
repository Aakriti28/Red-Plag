import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;

public class FetchAndProcessFromDisk implements FetchAndProcess {
    private Map<String, String> data=new HashMap<String,String>();

    @Override
    public Map<String, String> exposeData() {
	return data;
    }

    @Override
    public void fetch(List<String> paths)
     {
	// Implement here
    	try{
    	for(int i=0;i<paths.size();i++)
    	{
    		Scanner scanner=new Scanner(new File(paths.get(i)));
    		String[] fl=paths.get(i).split("/");
    		String fname0=fl[fl.length-1];
    		String[] fname1=fname0.split("\\.");
    		String fname=fname1[0];
    		// System.out.println(fname);
    		while(scanner.hasNextLine()){
    			    		// System.out.println(fname);
    			String line = scanner.nextLine();
    			// line=line.replace("\n", "");
    			if(data.containsKey(line)){
    				data.put(line,data.get(line)+","+fname);
    			}
    			else{
    				data.put(line,fname);
    			}
    		}
      }
     	 //      Set< Map.Entry< String,String> > st = data.entrySet();    
  
       // for (Map.Entry< String,String> me:st) 
       // { 
       //     System.out.print(me.getKey()+":"); 
       //     System.out.println(me.getValue()); 
       // }
     	
  	 // System.out.println(1);
  	 }
       catch(Exception e){System.out.println("Censored");}
}
}
