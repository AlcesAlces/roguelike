package roguelike.creatures.race_class;


import roguelike.stats.Stats;

public class Race {

    public enum RACE {
        human(0), elf(1);
        private final int value;

        private RACE(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }

    public RACE race;
    //How many of each stat this race gets per level.
    public Stats progression;
    //Starting stats for this race.
    public Stats starting;

    public Race(RACE toSet){

        double[] prog;
        double[] start;

        switch(toSet){
            //TODO: Fill these properly.
            case human:
                prog = new double[]{0,0,0,0,0,0,0};
                start = new double[]{0,0,0,0,0,0,0};
                race = toSet;
                progression = new Stats(prog);
                starting = new Stats(start);
                break;
            case elf:
                prog = new double[]{0,0,0,0,0,0,0};
                start = new double[]{0,0,0,0,0,0,0};
                race = toSet;
                progression = new Stats(prog);
                starting = new Stats(start);
                break;
            default:
                break;
        }
    }
}
