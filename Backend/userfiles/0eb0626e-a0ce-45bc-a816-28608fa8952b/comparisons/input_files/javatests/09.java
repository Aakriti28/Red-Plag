import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

public class FetchAndProcessFromDisk implements FetchAndProcess {
    private Map<String, String> data;

    @Override
    public Map<String, String> exposeData() {
	return data;
    }

    @Override
    public void fetch(List<String> paths) {
        data=new HashMap<String,String>();
        try{for(String path : paths){
            
            File file=new File(path);
            String fileNameWithExt=file.getName();
            String fileNameWithOutExt = fileNameWithExt.replaceFirst("[.][^.]+$", ""); 
            Scanner  sc=new Scanner(file);
                     
            String b="";
            while(sc.hasNextLine())
            {
                String line=sc.nextLine();
                if(data.containsKey(line))
                {   
                    data.put(line,data.get(line)+",,"+fileNameWithOutExt);
                }
                else
                {
                    data.put(line,fileNameWithOutExt);
                }
                //b=b+line+":";
            }
           
           
        }   //System.out.println(Arrays.asList(data));
    }
        catch (Exception e) {}

        // Implement here
    }
}
