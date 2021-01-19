import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class FetchAndProcessFromDisk implements FetchAndProcess {
    private Map<String, String> data = new HashMap<String, String>();

    @Override
    public Map<String, String> exposeData() {
	return data;
    }

    @Override
    public void fetch(List<String> paths){
        try{
        for(int i=0; i<paths.size(); i++){
            File f = new File(paths.get(i));
            String temp = f.getName();
            temp = temp.replaceFirst("[.][^.]+$","");
            // System.out.println(temp);
            FileInputStream file = new FileInputStream(paths.get(i));
            BufferedReader read = new BufferedReader(new InputStreamReader( file));
            // System.out.println(temp);
            String line = read.readLine();
            // System.out.println(line);            
            while(line != null){
                // System.out.println("cool");                
                if(this.data.containsKey(temp)){
                    // System.out.println("cool");
                    String s=this.data.get(temp);
                    s+=","+line;
                    this.data.put(temp, s);
                }else{
                    // System.out.println("cool else");                    
                    this.data.put(temp, line);
                }
                // System.out.println(line);
                line = read.readLine();
            } 
            //System.out.println(this.data.get(temp));
        }
    }
        catch(FileNotFoundException f){
            System.out.println("no such file");
           }
        catch(IOException e){
            System.out.print("Exception here");
        }
	}
}
