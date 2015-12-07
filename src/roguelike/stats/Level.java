package roguelike.stats;

import java.io.Serializable;

public class Level implements Serializable {

    public int level;
    double toNextLevel;

    public Level(int level){
        this.level = level;
        //TODO: create actual scaling.
        toNextLevel = 10.0;
    }
}
