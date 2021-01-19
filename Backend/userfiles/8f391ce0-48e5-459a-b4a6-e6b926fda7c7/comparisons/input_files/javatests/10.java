import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileInputStream;
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
	public void fetch(List<String> paths) {
		for (String s : paths) {
			File file = new File(s);
			BufferedReader breader = null;
			String p = file.getName().split("\\.")[0];
			try {
				breader = new BufferedReader(new FileReader(s));
				String content;
				while ((content = breader.readLine()) != null) {
					if (data.containsKey(content)) {
						data.put(content, data.get(content) + "@@@" + p);
					} else  data.put(content, p);
				}
				if (breader != null) breader.close();
			} catch (Exception e) {
				System.out.println("Error occured");
				// e.printStackTrace();
			}
		}
	}
}
