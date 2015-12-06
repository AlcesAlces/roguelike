package roguelike.file;

import net.slashie.util.Position;
import roguelike.Global;
import roguelike.character.Player;
import roguelike.creatures.Creature;
import roguelike.map.Map;
import roguelike.map.MapTiny;
import roguelike.map.Overworld;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileWriter {

    public static String overworldFile = "overworld";
    public static String mapsFile = "maps";

    //Gets the overworld from the file.
    public static Overworld overworldFromFile()
    {
        Overworld temp = null;
        try {
            FileInputStream fin = new FileInputStream(FileWriter.overworldFile);
            ObjectInputStream ois = new ObjectInputStream(fin);
            temp = (Overworld) ois.readObject();
            ois.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        return temp;
    }

    public static ArrayList<Map> getMaps()
    {
        ArrayList<Map> temp = new ArrayList<Map>();
        try {
            FileInputStream fin = new FileInputStream(FileWriter.mapsFile);
            ObjectInputStream ois = new ObjectInputStream(fin);
            temp = (ArrayList<Map>) ois.readObject();
            ois.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        return temp;
    }


    //Writes the overworld object
    public static void saveOverwolrd(Overworld overworld)
    {
        checkDirectory(Global.saveDir);
        String dir = Global.saveDir + "\\overworld";

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dir));
            oos.writeObject(overworld);
            oos.flush();
            oos.close();
        }
        catch(Exception ex)
        {

        }
    }

    public static void saveMap(Map map){

        checkDirectory(Global.saveDir);
        checkDirectory(Global.saveDir + "\\map");

        try {
            String dir = Global.saveDir + "\\map\\" + map.overworldPoint.x + "," + map.overworldPoint.y;

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dir));
            oos.writeObject(map);
            oos.flush();
            oos.close();
        }
        catch(Exception ex){

        }
    }

    public static void savePlayer(Player player){
        checkDirectory(Global.saveDir);

        try{
            String dir = Global.saveDir +"\\"+ player.name;

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dir));
            oos.writeObject(player);
            oos.flush();
            oos.close();
        }
        catch(Exception ex){

        }
    }


    public static void checkDirectory(String dir){
        File file = new File(dir);
        if(!file.exists()){
            file.mkdir();
        }
    }
}
