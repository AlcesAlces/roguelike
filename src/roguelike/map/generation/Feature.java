package roguelike.map.generation;

import java.io.Serializable;

//Features describe strategies to generate elements on a map.
public class Feature implements Serializable{
    /*
     * The idea with the feature enum is that you have a map with a bunch of features.
     * These features can be rivers, lakes, trees, buildings and even more generic such as
     * craggy, etc.
     */
    public enum feature { grass_floor, forest, lake }
    //What feature is this?
    public feature feat;
    public int density;

    public Feature(feature f, int d)
    {
        feat = f;
        density = d;
    }

}
