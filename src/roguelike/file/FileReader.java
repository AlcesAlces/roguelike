package roguelike.file;

import com.sun.istack.internal.Nullable;
import roguelike.Global;
import roguelike.character.Player;
import roguelike.map.Map;
import roguelike.map.Overworld;

import java.awt.*;
import java.io.*;
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

    public static ArrayList<String> getDirectories(){
        //The dot gets everything in the current dir.
        File file = new File(".");
        File[] names = file.listFiles();
        ArrayList<String> toReturn = new ArrayList<>();
        if(names != null) {
            for (File name : names) {
                String[] split = name.getName().split("(?=-)");
                if (split[split.length - 1].equals("-save")) {
                    toReturn.add(split[0]);
                }
            }
        }

        return toReturn;
    }

    @Nullable
    public static Map getMap(Point pos)
    {
        String dir = Global.saveDir + "\\map\\" + pos.x +","+pos.y;
        File file = new File(dir);
        Map toReturn = null;

        if(!file.exists())
        {
            return null;
        }
        try {
            FileInputStream fin = new FileInputStream(dir);
            ObjectInputStream ois = new ObjectInputStream(fin);
            toReturn = (Map) ois.readObject();
            ois.close();
        }
        catch(Exception ex){
            //TODO: Handle this.
            int i = 0;
        }

        return toReturn;
    }

    @Nullable
    public static Player getPlayer(String name){
        String dir = Global.saveDir + "\\" + name;
        Player toReturn = null;

        try{
            FileInputStream fin = new FileInputStream(dir);
            ObjectInputStream ois = new ObjectInputStream(fin);
            toReturn = (Player) ois.readObject();
            ois.close();
        }
        catch(Exception ex){
            System.out.println("Error in player load function");
        }

        return toReturn;
    }

    @Nullable
    public static Overworld getOverworld(){
        String dir = Global.saveDir + "\\overworld";
        Overworld toReturn = null;

        try{
            FileInputStream fin = new FileInputStream(dir);
            ObjectInputStream ois = new ObjectInputStream(fin);
            toReturn = (Overworld) ois.readObject();
            ois.close();
        }
        catch(Exception ex){
            System.out.println("Error in overworld load function");
        }

        return toReturn;
    }

    public static void checkDirectory(String dir){
        File file = new File(dir);
        if(!file.exists()){
            file.mkdir();
        }
    }
}
