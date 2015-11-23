package roguelike.file;

import java.io.File;
import java.util.ArrayList;

public class FileReader {
    
    public static void testFunction() {
        
        ArrayList<String> textFiles = new ArrayList<>();
        File dir = new File(System.getProperty("user.dir"));
        File[] allfiles = dir.listFiles();
        
        for(File file : allfiles) 
        {
            if(file.toString().endsWith(".txt")) 
            {
                System.out.println(file.toString());
            }
        }
    }
}
