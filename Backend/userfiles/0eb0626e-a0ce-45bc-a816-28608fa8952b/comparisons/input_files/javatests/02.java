import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.io.File;
import java.util.HashMap;

public class FetchAndProcessFromDisk implements FetchAndProcess {
    private Map<String, String> data;

    @Override
    public Map<String, String> exposeData() {
	return data;
    }

    @Override
    public void fetch(List<String> paths) 
    {
    	data=new HashMap<String,String>();
    	for(Iterator <String> iter=paths.iterator();iter.hasNext();)
    	{
    		String s=iter.next();
            // System.out.println(s);
    		String filename=getFileNameWithoutExtension(new File(s));
            // System.out.println(filename);
    		BufferedReader br;
            try{
        		br=new BufferedReader(new InputStreamReader(new FileInputStream(s)));
        		String poke;
        		try
                {
            		while((poke=br.readLine())!=null)
            		{
            			String key=filename+"--"+poke;
        				data.put(key,filename);
            		}
                }
            	catch(IOException e)
            	{

        		}
    		}
    		catch(FileNotFoundException e)
    		{

    		}
    	}


    }
    private static String getFileNameWithoutExtension(File file) {
        String fileName = "";
 
        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                fileName = name.replaceFirst("[.][^.]+$", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fileName = "";
        }
 
        return fileName;
 
    }
}
