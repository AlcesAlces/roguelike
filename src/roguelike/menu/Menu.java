package roguelike.menu;

import com.googlecode.blacken.colors.ColorPalette;
import com.googlecode.blacken.terminal.BlackenKeys;
import com.googlecode.blacken.terminal.CursesLikeAPI;
import roguelike.Global;
import roguelike.character.Player;
import roguelike.map.Map;

import java.awt.*;
import java.util.ArrayList;

//Handles all of the menu related things.
public class Menu {

    public boolean scrollable = false;
    public boolean centered = true;
    protected ArrayList<MenuItem> items = new ArrayList<>();
    private int height;
    private int width;
    private int currentIndex = 0;
    //The splash is the flare for any menu.
    //splashPoint is where you start drawing from. Then you go xLen anx yLen out from it and calculate your menu size.
    private Point splashPoint = new Point(0,0);
    public enum splashtype {border, logo, none}
    public splashtype splash = splashtype.none;
    public int logoX = 0;
    public int logoY = 0;

    public Menu(Boolean c){
        centered = c;
    }

    public void indexUp(){
        if(currentIndex != 0)
        {
            //Can go upward
            items.get(currentIndex).isSelected = false;
            currentIndex--;
            items.get(currentIndex).isSelected = true;
        }
    }

    public void indexDown(){
        if((currentIndex + 1) < items.size()){
            items.get(currentIndex).isSelected = false;
            currentIndex++;
            items.get(currentIndex).isSelected = true;
        }
    }

    public void drawMenu(CursesLikeAPI term, ColorPalette palette){

        int xLen = term.getWidth();
        int yLen = term.getHeight();

        //Determine how to draw stuff via the enum
        switch(splash){
            case logo:
                //Take up half the screen for a sweet logo.
                splashPoint = new Point(0,yLen/2);
                yLen = yLen/2;
                break;
            case border:
                //The border starts at 1,1 and goes x-2 and y-2 (1 block on both sides)
                splashPoint = new Point(1,1);
                xLen -= 2;
                yLen -=2;
                drawBorder();
                break;
            case none:
            default:
                break;
        }

        int xHalf = (xLen/2);
        int yHalf = (yLen/2);

        boolean menu = true;
        while(menu) {
            term.clear();

            draw(term, xHalf, yHalf);

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

    private void draw(CursesLikeAPI term, int xHalf, int yHalf){

        if(scrollable){

        }
        else {
            for (MenuItem item : items) {
                int offset = item.text.length() / 2;
                int startX = (splashPoint.x + xHalf - offset);
                term.mvputs(yHalf + item.index, startX, item.text);
                for(int i = startX; i < startX + item.text.length(); i++) {
                    term.get(yHalf + item.index, i).setForeground(item.getColor());
                }
            }
        }
    }

    public void drawBorder(){

    }
}
