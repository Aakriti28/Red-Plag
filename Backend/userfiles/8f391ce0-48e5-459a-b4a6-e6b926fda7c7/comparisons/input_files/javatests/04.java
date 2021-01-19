import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FetchAndProcessFromDisk implements FetchAndProcess {
    private Map<String, String> data = new HashMap<String,String>();

    @Override
    public Map<String, String> exposeData() {
	return data;
    }

    @Override
    public void fetch(List<String> paths) {
        for(String pth : paths){
            try {
                Scanner scanner = new Scanner(new File(pth));
                String[] parts = pth.split("/");
                String fil = parts[parts.length-1];
                String filename = fil.replaceFirst("[.][^.]+$", "");
               // System.out.println(filename);
                while (scanner.hasNextLine()) {
                    String pk = scanner.nextLine();
                    if(data.get(pk) == null){data.put(pk,filename);}
                    else{data.put(pk,data.get(pk)+","+filename);}
                   // System.out.println(pk);
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        data.remove("");
        //System.out.println(data); 
    }
}
