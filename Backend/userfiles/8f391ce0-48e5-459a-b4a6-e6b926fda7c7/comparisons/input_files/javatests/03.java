import java.io.*;
//import java.io.IOException;
//import java.util.ArrayList;
import java.util.*;
//import java.util.List;
//import java.util.Map;
//import java.util.HashMap;

public class FetchAndProcessFromDisk implements FetchAndProcess {
    private Map<String, String> data;

    @Override
    public Map<String, String> exposeData() {
		return data;
    }

    @Override
    public void fetch(List<String> paths) {
	// Implement here
    	data = new HashMap<String,String>();
    	
    	for(int i=0;i<paths.size();i++){
	    	String fileName = paths.get(i);
			File file = new File(fileName);
			String actualFileName=file.getName();
			try{
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while((line = br.readLine()) != null){
			    	//process the line
			    	String value = data.containsKey(line) ? data.get(line) : "";
			    	value+=actualFileName+",";
			    	data.put(line,value);
			    	//System.out.println(value);
			    	//System.out.println(line);
				}
			} catch (Exception e){
				System.out.println(e);
			}
		}

		//for(String key : data.keySet()) System.out.println(key + " " + data.get(key));
    }
}
