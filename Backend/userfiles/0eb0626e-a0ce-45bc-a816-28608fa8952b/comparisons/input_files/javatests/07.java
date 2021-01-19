import java.io.FileInputStream;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class FetchAndProcessFromDisk implements FetchAndProcess {
    private Map<String, String> data;

    @Override
    public Map<String, String> exposeData() {
	return data;
    }

    @Override
    public void fetch(List<String> paths) {
    	 data=new HashMap<String,String>();
    	 // using '/' as seperator of filenames

	// Implement here
    	 for (int i = 0; i < paths.size(); i++) {
    	 	String s=paths.get(i);
    	 	File f = new File(s);
			// System.out.println(f.getName());
			String fn=f.getName();
			BufferedReader reader;
			try{
				reader=new BufferedReader(new FileReader(s));
				String line = reader.readLine();
				while(line != null){
					String value = data.get(line);
					if(value!= null){
						String nn=value+","+fn;
						// System.out.println(nn);
						data.put(line,nn);
					}
					else{
						data.put(line,fn);
					}
					line=reader.readLine();
				}
			} catch(IOException e){
				e.printStackTrace();
			}
			// System.out.println(crunchifyList.get(i));
		}
		// System.out.println(data);
    }
}
