import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Hairdresser {

    public Map<Integer, String> timetable = new TreeMap<>();

    public Map<Integer, String> getTimetable() {
        return timetable;
    }

    public void addElement(int hour, String name){
        timetable.put(hour, name);
    }

    public boolean isFree(int hour){
        return !timetable.containsKey(hour);
    }

    public void cancelVisit(int hour){
        timetable.remove(hour);
    }

    public int isBookedByName(int hour, String name){ return name.compareTo(timetable.get(hour)); }

    public Set<Integer> getReservedDate(){
        return timetable.keySet();
    }


}
