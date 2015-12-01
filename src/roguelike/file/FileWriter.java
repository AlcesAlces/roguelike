package roguelike.file;

import net.slashie.util.Position;
import roguelike.character.Player;
import roguelike.creatures.Creature;
import roguelike.map.Map;
import roguelike.map.MapTiny;
import roguelike.map.Overworld;

import java.io.*;
import java.util.ArrayList;

public class FileWriter {

    public static String overworldFile = "overworld";
    public static String mapsFile = "maps";

    //Writes the overworld object
    public static void writeOverwolrd(Overworld overworld, int x, int y)
    {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(overworldFile));
            oos.writeObject(overworld);
            oos.flush();
            oos.close();
        }
        catch(Exception ex)
        {

        }
    }
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
    //This may not be very efficient, but should be fine.
    public static Map getMap(Position pos)
    {
        ArrayList<Map> temp = getMaps();

        Map toReturn = null;

        for(Map map : temp){
            if(map.pos.x == pos.x && map.pos.y == pos.y)
            {
                toReturn = map;
                break;
            }
        }

        return toReturn;
    }
    //NOTE: This could be really inefficient.
    public static void writeMap(Map map)
    {
        ArrayList<Map> allMaps = getMaps();

        boolean found = false;

        for(int i = 0; i < allMaps.size(); i++)
        {
            if(map.pos.x == allMaps.get(i).pos.x && map.pos.y == allMaps.get(i).pos.y)
            {
                allMaps.set(i, map);
                found = true;
                break;
            }
        }

        if(!found)
        {
            allMaps.add(map);
        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(mapsFile));
            oos.writeObject(allMaps);
            oos.flush();
            oos.close();
        }
        catch(Exception ex)
        {

        }
    }
}
