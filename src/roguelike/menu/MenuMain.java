package roguelike.menu;

import com.googlecode.blacken.colors.ColorPalette;
import roguelike.Global;

public class MenuMain extends Menu {

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
}
