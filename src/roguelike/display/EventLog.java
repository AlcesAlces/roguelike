package roguelike.display;

import roguelike.Global;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventLog {

    private ArrayList<String> messageList = new ArrayList<>();
    private int maxLines;

    public void addLog(String msg)
    {
        messageList.add(msg);
    }

    public int sizeOfLog(){
        return messageList.size();
    }

    public ArrayList<String> getShortLogString(int messageLength, int lines)
    {
        maxLines = lines;

        ArrayList<String> toReturn = new ArrayList<>();
        int index = messageList.size() - 1;

        if(messageList.size() != 0) {
            String toAdd = "";
            boolean added = false;
            String msg = messageList.get(index);
            while(toReturn.size() < maxLines && index >= 0) {
                toAdd = "";
                if(msg.length() > messageLength) {
                    String[] split = msg.split("\\s+");
                    for (int i = split.length - 1; i >= 0; i--)
                    {
                        //Keep track of if adding a message will make this too long.
                        if((toAdd.length() + split[i].length() + 1) >= messageLength) {
                            toReturn.add(""+toAdd);
                            added = true;
                            msg = "";
                            for(int j = 0; j <= i; j++){
                                msg = msg + " " + split[j];
                            }
                            break;
                        }
                        else
                        {
                            toAdd = split[i] + " " + toAdd;
                            added = false;
                        }
                    }

                    if(!added){
                        toReturn.add(""+toAdd);
                        index--;
                        if(index >= 0) {
                            msg = messageList.get(index);
                        }
                    }
                }
                else{
                    toReturn.add(msg);
                    index--;
                    if(index >= 0) {
                        msg = messageList.get(index);
                    }
                }


            }
        }
        Collections.reverse(toReturn);
        return toReturn;
    }
}
