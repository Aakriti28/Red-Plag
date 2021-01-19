import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

         for (String path : paths)
         {
            File file;
            try{
            file = new File(path);
            }
            catch(Exception e)
            {

            }

 
            // for (File file : files)
             
            	String fileWithOutExt = path.replaceFirst("[.][^.]+$", "");
                try{
     	               	// gym.add(file.getName());
        	        Scanner sc = new Scanner(fileWithOutExt);
        	        while(sc.hasNextLine())
        	       {
            	       // sc.nextLine();
				        data.put(sc.nextLine()+'%'+fileWithOutExt,sc.nextLine());
        	       }

            	
            
           }catch(Exception e)
           {
            System.out.println(e);
           }
         
            }


    // File fileNameWithExt = new File("/data/gym1");
    // String fileNameWithOutExt = fileNameWithExt.replaceFirst("[.][^.]+$", "");
    // data = fileNameWithOutExt.collect(Collectors.joining("\n"));
    // fileNameWithOutExt.close();
          
    // Assert.assertEquals(expectedData, data.trim());
    }
}
