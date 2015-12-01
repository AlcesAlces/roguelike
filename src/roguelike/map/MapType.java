package roguelike.map;

public class MapType {

    public enum Type { forest(0), desert(1), swamp(2), wasteland(3), bog(4);

        private final int value;

        private Type(int value)
        {
            this.value = value;
        }

        public int getValue()
        {
            return value;
        }
    }

    public static String resolveDisplayCharacter(Type type)
    {
        switch(type)
        {
            case forest:
                return "&";
            case desert:
                return "%";
            case swamp:
                return "#";
            case wasteland:
                return "%";
            case bog:
                return "#";
            default:
                return "#";
        }
    }

    public static Type randomMapType()
    {
        int total = Type.values().length;
        return Type.values()[(int)(Math.random() * total)];
    }

}
