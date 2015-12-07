package roguelike.menu;

import com.googlecode.blacken.colors.ColorPalette;
import com.googlecode.blacken.terminal.BlackenKeys;
import com.googlecode.blacken.terminal.CursesLikeAPI;
import roguelike.Global;
import roguelike.file.FileReader;
import roguelike.map.Overworld;

import java.util.ArrayList;

public class ContinueMenu extends Menu{

    public Overworld loadedOverworld = null;

    public ContinueMenu(ColorPalette palette){
        super(true);
        ArrayList<String> items = FileReader.getDirectories();
        populateMenuOptions(palette, items);
        this.splash = splashtype.border;
    }

    private void populateMenuOptions(ColorPalette palette, ArrayList<String> items){

        int index = 0;
        int ac = palette.get("White");
        int ic = palette.get("Green");
        boolean selected = true;
        for(String item : items){
            this.items.add(new MenuItem(item, index, selected, ac, ic, "why"));
            selected = false;
            index++;
        }
    }

    private boolean menu = true;
    @Override
    public void drawMenu(CursesLikeAPI term, ColorPalette palette){

        super.menuTitle = "Save Games";

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
                    menu = false;
                    break;
                case BlackenKeys.KEY_ESCAPE:
                    menu = false;
                default:
                    break;
            }
        }
    }

    public void makeSelection(){
        String saveDir = "";
        String name = "";

        for(MenuItem item : items){
            if(item.isSelected){
                saveDir = item.text + "-save";
                name = item.text;
            }
        }

        Global.saveDir = saveDir;
        Global.player = FileReader.getPlayer(name);

        loadedOverworld = FileReader.getOverworld();
    }
}
