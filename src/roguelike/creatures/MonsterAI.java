package roguelike.creatures;

import roguelike.character.Player;
import roguelike.map.Map;
import roguelike.pathfinding.MapPathfinder;

import java.awt.Point;
import java.util.ArrayList;

public class MonsterAI {

    //Handles all of the creature pathing/attack/etc.
    public static void CreatureAction(Player player, Map map){

        for(Creature monster : map.monsters) {

            int distance = (int)Math.sqrt((monster.point.x-player.point.x)*(monster.point.x-player.point.x) +
                                    (monster.point.y-player.point.y)*(monster.point.x-player.point.y));

            if(distance <= monster.aggroRange || monster.aggro) {
                //Monster saw the player, monster will pursue with hate and anger and stuff.
                monster.aggro = true;

                MapPathfinder pf = new MapPathfinder(map.x, map.y, map.tiles);
                ArrayList<Point> path = pf.findPath(monster.point, player.point, 500);
                if (path == null) {
                    //No path sad.
                } else if (path.size() == 1) {
                    //Player is within range.
                    System.out.println("ME FITE");
                } else {
                    map.moveCharacter(monster, path.get(0));
                }
            }
            else{
                //Creature wanders.
            }
        }
    }

}
