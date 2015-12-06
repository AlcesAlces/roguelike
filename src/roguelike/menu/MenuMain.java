package roguelike.menu;

import com.googlecode.blacken.colors.ColorPalette;
import com.googlecode.blacken.terminal.BlackenKeys;
import com.googlecode.blacken.terminal.CursesLikeAPI;
import roguelike.Global;
import roguelike.map.Overworld;

public class MenuMain extends Menu {

    public int responseIndex = 0;

    public MenuMain(Boolean c,ColorPalette p){
        super(c);
        int ac = p.get("White");
        int ic = p.get("Green");
        //Continue
        items.add(new MenuItem("Continue", 0, true, ac, ic));
        items.add(new MenuItem("New Game", 1, false, ac, ic));
        items.add(new MenuItem("Quit", 2, false, ac, ic));
        if(Global.debug)
        {
            items.add(new MenuItem("Debug", 3, false, ac, p.get("Red")));
        }
        this.splash = splashtype.logo;
    }

    private boolean menu = true;
    @Override
    public void drawMenu(CursesLikeAPI term, ColorPalette palette){

        while(menu) {
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

        for(MenuItem item : items){
            if (item.isSelected) {
                switch(item.index){
                    case 0:
                        responseIndex = 0;
                        break;
                    case 1:
                        responseIndex = 1;
                        break;
                    case 2:
                        responseIndex = 2;
                        menu = false;
                        break;
                    case 3:
                        responseIndex = 3;
                        menu = false;
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
