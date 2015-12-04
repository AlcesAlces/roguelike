package roguelike.menu;

public class MenuItem {

    public boolean isSelected = false;
    public String text = "";
    public String desc = "";
    public int index = 0;
    public int idleColor;
    public int selectedColor;

    public MenuItem(String s, int i, Boolean select, int icolor, int ocolor)
    {
        this.text = s;
        this.index = i;
        isSelected = select;
        idleColor = icolor;
        selectedColor = ocolor;
    }

    public MenuItem(String s, int i, Boolean select, int icolor, int ocolor, String description){
        desc = description;
        this.text = s;
        this.index = i;
        isSelected = select;
        idleColor = icolor;
        selectedColor = ocolor;
    }

    public int getColor()
    {
        return isSelected ? selectedColor : idleColor;
    }
}
