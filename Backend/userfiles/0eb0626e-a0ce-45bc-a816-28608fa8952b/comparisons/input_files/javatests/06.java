import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.*;


public class FetchAndProcessFromDisk implements FetchAndProcess {
    private Map<String, String> data;

    @Override
    public Map<String, String> exposeData() {
	return data;
    }

    @Override
    public void fetch(List<String> paths) {
	// Implement here
    	//FileInputStream file =  null;
    	data = new HashMap<String, String>();
    	BufferedReader reader = null;
    	try{
    	    for(int i=0;i < paths.size();i++){
    		//file = new FileInputStream(paths.get(i));
    	    	//System.out.println(paths.get(i));
    		File f1 = null;
    		f1 = new File(paths.get(i));
    		String fullfile = f1.getName();
    		String f = fullfile.replaceFirst("[.][^.]+$", "");
			
			//try {
    		reader = new BufferedReader(new FileReader(paths.get(i)));
    		String text = reader.readLine();

	    	while (text != null) {
	    		if (data.containsKey(text)){
	    			String r = data.get(text);
	    			r = r + "," + f;
	    			data.put(text,r);
	    		}
	        	else{data.put(text,f);}
	        	text = reader.readLine();

	    	}}
	    	
	    	//file.close();
	    	reader.close();}
	    catch (IOException e) {
				// if(file!=null) {
    //         	file.close();
    //      		}
				// reader.close();
				//continue;
	    		e.printStackTrace();
			}
			// data.entrySet().forEach(entry->{
   //  		System.out.println(entry.getKey() + " " + entry.getValue());  
 		// 	});
		

    }
}
