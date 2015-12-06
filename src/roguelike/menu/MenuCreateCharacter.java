package roguelike.menu;

import com.googlecode.blacken.colors.ColorPalette;
import com.googlecode.blacken.terminal.BlackenKeys;
import com.googlecode.blacken.terminal.CursesLikeAPI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MenuCreateCharacter extends Menu{

    private ArrayList<ArrayList<MenuItem>> items = new ArrayList<>();

    public MenuCreateCharacter(ColorPalette palette){
        super(true);
        populateMenuOptions(palette);
        this.splash = splashtype.border;
    }

    private void populateMenuOptions(ColorPalette palette){

        int ac = palette.get("White");
        int ic = palette.get("Green");

        //Placeholder description string. Going to change the way we get description info.
        String descriptionString = "This is a placeholder!";
        //Each index in the big array list items will be a step of character creation.
        //Races: 0
        ArrayList<MenuItem> toAdd = new ArrayList<>();
        toAdd.add(new MenuItem("Human", 0, true, ac, ic, descriptionString));
        toAdd.add(new MenuItem("Elf", 1, false, ac, ic, descriptionString));
        toAdd.add(new MenuItem("Orc", 2, false, ac, ic, descriptionString));
        //Add the races
        items.add(toAdd);
        toAdd = new ArrayList<>();
        toAdd.add(new MenuItem("Name: __________", 0, false, ac, ic, descriptionString));
        items.add(toAdd);
        //Stats:2
        toAdd = new ArrayList<>();
        toAdd.add(new MenuItem("Strength", 0, true, ac, ic, descriptionString));
        toAdd.add(new MenuItem("Dexterity", 1, true, ac, ic, descriptionString));
        toAdd.add(new MenuItem("Endurance", 1, true, ac, ic, descriptionString));
        toAdd.add(new MenuItem("Intelligence", 1, true, ac, ic, descriptionString));
        toAdd.add(new MenuItem("Wisdom", 1, true, ac, ic, descriptionString));
        items.add(toAdd);
    }

    private boolean menu = true;
    @Override
    public void drawMenu(CursesLikeAPI term, ColorPalette palette){


        super.items = this.items.get(0);
        super.menuTitle = "Race Selection";

        while(menu){
            term.clear();
            super.draw(term);

            term.refresh();
            int ch = term.getch();

            switch (ch) {
                case BlackenKeys.KEY_UP:
                    indexUp();
                    break;
                case BlackenKeys.KEY_DOWN:
                    indexDown();
                    break;
                case BlackenKeys.KEY_ENTER:
                    makeSelection();
                    break;
                case BlackenKeys.KEY_ESCAPE:
                    menu = false;
                default:
                    break;
            }
        }
    }

    private void makeSelection(){

    }
}
