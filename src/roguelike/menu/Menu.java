package roguelike.menu;

import com.googlecode.blacken.colors.ColorPalette;
import com.googlecode.blacken.terminal.BlackenKeys;
import com.googlecode.blacken.terminal.CellWalls;
import com.googlecode.blacken.terminal.CursesLikeAPI;
import com.googlecode.blacken.terminal.TerminalCellLike;
import roguelike.Global;
import roguelike.character.Player;
import roguelike.map.Map;

import java.awt.*;
import java.util.*;

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
    //TODO: Fill in.
    public String menuTitle = "Planes of Endatl";
    int menuTitleColor;

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

        menuTitleColor = palette.get("White");
        boolean menu = true;
        while(menu) {
            term.clear();

            draw(term);

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

    protected void draw(CursesLikeAPI term){

        int xLen = term.getWidth();
        int yLen = term.getHeight();

        //Determine how to draw stuff via the enum
        switch(splash){
            case logo:
                //Take up half the screen for a sweet logo.
                splashPoint = new Point(0,yLen/2);
                drawFrame(term, term.getHeight(), term.getWidth());
                yLen = yLen/2;
                drawSplash(term, xLen, yLen);
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
        int yHalf = (yLen);
        int yOffset = (yLen/4);

        if(scrollable){

        }
        else {
            for (MenuItem item : items) {
                int offset = item.text.length() / 2;
                int startX = (splashPoint.x + xHalf - offset);
                term.mvputs(yOffset + yHalf + item.index, startX, item.text);
                for(int i = startX; i < startX + item.text.length(); i++) {
                    term.get(yOffset + yHalf + item.index, i).setForeground(item.getColor());
                }
            }
        }
    }

    public void drawBorder(){

    }

    public void drawSplash(CursesLikeAPI term, int xLen, int yLen){

        int startX = (xLen/2) - (menuTitle.length() / 2);
        int startY = (yLen/2);
        term.mvputs(startY,startX, menuTitle);
        for(int i = startX; i < startX + menuTitle.length(); i++) {
            //term.get(startY, i).setForeground(menuTitleColor);
        }
    }

    public void drawFrame(CursesLikeAPI term, int yMax, int xMax){

        //Top left
        Point temp = new Point(0,0);
        TerminalCellLike cell = term.get(temp.y, temp.x);
        java.util.List<CellWalls> walls = new ArrayList<>();
        walls.add(CellWalls.TOP);
        walls.add(CellWalls.LEFT);
        cell.setCellWalls(new HashSet<>(walls));

        //Bottom left
        temp = new Point(0, splashPoint.y - 1);
        cell = term.get(temp.y, temp.x);
        walls.clear();
        walls.add(CellWalls.BOTTOM);
        walls.add(CellWalls.LEFT);
        cell.setCellWalls(new HashSet<>(walls));
        //Top Right
        temp = new Point(xMax-1, 0);
        cell = term.get(temp.y, temp.x);
        walls.clear();
        walls.add(CellWalls.TOP);
        walls.add(CellWalls.RIGHT);
        cell.setCellWalls(new HashSet<>(walls));
        //Bottom Right
        temp = new Point(xMax - 1, splashPoint.y - 1);
        cell = term.get(temp.y, temp.x);
        walls.clear();
        walls.add(CellWalls.BOTTOM);
        walls.add(CellWalls.RIGHT);
        cell.setCellWalls(new HashSet<>(walls));

        for(int i = 1; i < xMax - 1; i++){
            //Walls Top
            temp = new Point(i, 0);
            cell = term.get(temp.y, temp.x);
            walls.clear();
            walls.add(CellWalls.TOP);
            cell.setCellWalls(new HashSet<>(walls));
            //Walls Bottom
            temp = new Point(i, splashPoint.y - 1);
            cell = term.get(temp.y, temp.x);
            walls.clear();
            walls.add(CellWalls.BOTTOM);
            cell.setCellWalls(new HashSet<>(walls));
        }
        for(int i = 1; i < splashPoint.y - 1; i++){
            //Walls right
            temp = new Point(0, i);
            cell = term.get(temp.y, temp.x);
            walls.clear();
            walls.add(CellWalls.LEFT);
            cell.setCellWalls(new HashSet<>(walls));
            //Walls left
            temp = new Point(xMax - 1, i);
            cell = term.get(temp.y, temp.x);
            walls.clear();
            walls.add(CellWalls.RIGHT);
            cell.setCellWalls(new HashSet<>(walls));
        }
    }
}
