import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.*;

public class FetchAndProcessFromDisk implements FetchAndProcess {
    private Map<String, String> data=new HashMap<String, String>();

    @Override
    public Map<String, String> exposeData() {
	return data;
    }

    @Override
    public void fetch(List<String> paths) {
    	//System.out.println("fin");
	for(int i=0;i<paths.size();i++){
	BufferedReader reader;
		try {
			String pokname=paths.get(i); // name of file with path
			reader = new BufferedReader(new FileReader(pokname));
			String[] arr = pokname.split("/");
			String fname = arr[arr.length-1];
            if (fname.indexOf(".") > 0)
              fname = fname.substring(0, fname.lastIndexOf("."));
			//System.out.println(f2.length);
			String line = reader.readLine();
			while (line != null) {
				line=line.trim();
				if(data.containsKey(line)){
					data.put(line,data.get(line)+","+fname);
				}
				else{
					data.put(line,fname);	
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    Iterator<Map.Entry<String, String>> itr = data.entrySet().iterator(); 
          
        // while(itr.hasNext()) 
        // { 
        //      Map.Entry<String, String> entry = itr.next(); 
        //      System.out.println("Key = " + entry.getKey() +  
        //                          ", Value = " + entry.getValue()); 
        // } 
	}

	


	// public static void main (String args[]){
	// 	List<String> a;
	// 	a.add("data/gym1");
	// 	FetchAndProcessFromDisk.fetch(a);
	// }

}
