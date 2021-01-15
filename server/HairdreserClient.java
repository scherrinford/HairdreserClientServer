import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HairdreserClient {

    private String name = null;
    public SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    private Date data;
    private int hour;

    public int getHour() {
        return hour;
    }

    public boolean setHour(int hour) {
        if (validateHour(hour)) this.hour = hour;
        else return false;
        return true;
    }

    public boolean validateHour(int hour){
        return (hour>=10 && hour<18);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date toDateFormat(String date) throws ParseException {
        return sdf.parse(date);
    }
}
