package roguelike;

import java.util.ArrayList;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;

public class EventLog {
    
    ArrayList<String> longEventLog = new ArrayList();
    ArrayList<String> shortEventLog = new ArrayList();
    
    EventLog() {
    } 
    
    public void manageEventLog(String newEvent) {
     
        longEventLog.add(newEvent);
        
        if (newEvent.length() > 25) {
            
            String sub = newEvent.substring(0,25);
            String remainder = newEvent.substring(25);
            shortEventLog.add(sub);
            shortEventLog.add(remainder);
            if (shortEventLog.size() > 2) {
               shortEventLog.remove(0); 
            }
            
        }
        
        else {
            shortEventLog.add(newEvent);
        }
        if (shortEventLog.size() > 5) {
            shortEventLog.remove(0);
        }
    }
    
    public void printEventLog(EventLog eventLog, ConsoleSystemInterface csi) {
        
        if (eventLog.shortEventLog.size() > 0) {
                        for (int i = 0; i < eventLog.shortEventLog.size();i++) {
                            csi.print(50, i + 20, eventLog.shortEventLog.get(i));
                        }
                        
                        }
    }
    
    static void printFullEventLog(EventLog eventLog, ConsoleSystemInterface csi) {
        
        int lastLineTracker = 0;
        int activeMenuOption = 0;
        int maximumIndex;
        int minimumIndex = 0;
        int absoluteMax;
        int arrayRangeCheck = eventLog.longEventLog.size();
        Object itemToCheck;
        boolean closeMenu = false;
        boolean itemDetailMode = false;
        CSIColor activeColor = CSIColor.GREEN;
        
        if (eventLog.longEventLog.size() > 22) {
            maximumIndex = 22;
        }
        
        else {
            maximumIndex = eventLog.longEventLog.size();
        }
        
        activeMenuOption = maximumIndex - 1;
            while (!closeMenu) {
                
                //arrRangeCheck prevents an arraylist index out of bounds error
                //from removing an item.
                if (arrayRangeCheck != eventLog.longEventLog.size()) {
                    
                   
                    if (eventLog.longEventLog.size() > 22) {
                        maximumIndex = 22;
                    }
                    else {
            
                        maximumIndex = eventLog.longEventLog.size();
                    }
                    
                    minimumIndex = 0;
                    arrayRangeCheck = eventLog.longEventLog.size();
                }
                
            csi.cls();
            csi.print(1,0,    "---------------------------------------------------------------------------", CSIColor.WHITE);
            csi.print(35,0,    "EventLog", CSIColor.WHITE);            
            
            lastLineTracker = 0;
            absoluteMax = eventLog.longEventLog.size();
            
            for (int i = maximumIndex - 1; i >= minimumIndex; i--) {
                
                if (i == activeMenuOption) {
            
                    csi.print(1,lastLineTracker + 1,  (eventLog.longEventLog.get(i)), activeColor);
                    lastLineTracker++;
                }
                else {
                    csi.print(1,lastLineTracker + 1,  (eventLog.longEventLog.get(i)), CSIColor.WHITE);
                    lastLineTracker++;
                }
            }
            
            csi.print(1,24,    "------------------------------------------------------------------------", ConsoleSystemInterface.WHITE);
            csi.print(10,24,  "press q to quit");
        
            csi.refresh();
            
            CharKey dir = csi.inkey();
            
            if(dir.isDownArrow() && ((activeMenuOption - 1) != -1)) {
                activeMenuOption--;
                if(activeMenuOption == minimumIndex - 1) {
                    minimumIndex--;
                    maximumIndex--;
                }
            }
            if(dir.isUpArrow() && activeMenuOption + 1 != absoluteMax) {
                activeMenuOption++;
                if (activeMenuOption == maximumIndex) {
                    minimumIndex++;
                    maximumIndex++;
                
                }
            
            }
            
            if (dir.code == CharKey.q) {
                
                closeMenu = true;
            }
    
            }
    }
}
