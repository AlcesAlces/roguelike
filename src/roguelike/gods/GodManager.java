package roguelike.gods;

import java.util.ArrayList;

//Keeps track of all the god information.
public class GodManager {
    int numberOfGods = 2;
    ArrayList<God> gods = new ArrayList<>();

    public GodManager(){

    }

    public void GenerateGods(){

    }

    public String[] godNames(){

        String[] names = new String[1];
        names[0] = "bob";
        return names;
    }
}
