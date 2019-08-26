public class DateAndTime {
    protected static  String Day;
    protected static String Month;
    protected static String Year;
    protected static String Time;


    public DateAndTime(String dateandtime) {
        if (dateAndTimeChecker(dateandtime)) {
            String date = dateandtime.split(" ")[0];
            String time = dateandtime.split(" ")[1];
            Day = setDay(date);
            Month = setMonth(date);
            Year = setYear(date);
            Time = setTime(time);
        }
    }
    public DateAndTime(String dateandtime, int temp) {
            Day = dateandtime.split(" ")[0];
            Month = dateandtime.split(" ")[2];
            Year = dateandtime.split(" ")[3].substring(0, dateandtime.split(" ")[3].length() - 1);
            Time = dateandtime.split(" ")[4];
    }

    public static boolean dateAndTimeChecker(String dateandtime) {
        if ((dateandtime.split(" ")).length == 2) {
            String[] date = (dateandtime.split(" ")[0]).split("/");
            String time = dateandtime.split(" ")[1];
            try {
                if (date.length == 3 && Integer.parseInt(date[0]) >= 1 && Integer.parseInt(date[0]) <= 31
                        && Integer.parseInt(date[1]) >= 1 && Integer.parseInt(date[1]) <= 12
                        && Integer.parseInt(date[2]) >= 1
                        && Integer.parseInt(time.substring(0, 2)) >= 0 && Integer.parseInt(time.substring(0, 2)) < 24
                        && Integer.parseInt(time.substring(2, 4)) >= 0 && Integer.parseInt(time.substring(2, 4)) <= 59
                        && dayOfMonthChecker(Integer.parseInt(date[0]), Integer.parseInt(date[1]))) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
            return false;
        }
        return false;
    }

    public static boolean dayOfMonthChecker(int day, int month) {
        switch(month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return (day>=1 && day<=31);
            case 2:
                return (day>=1 && day<=28);
            default:
                return (day>=1 && day<=30);
        }
    }

    public static String getDateAndTime() {
        return Day+" of "+Month+" "+Year+", "+Time;
    }

    public static String setDay(String x) {
        int count = Integer.parseInt(x.split("/")[0]);
        if (count>=10&&count<=20) return count+"th";
        switch(count%10) {
            case 1: return count+"st";
            case 2: return count+"nd";
            case 3: return count+"rd";
            default: return count+"th";
        }
    }

    public static String setMonth(String x) {
        int count = Integer.parseInt(x.split("/")[1]);
        switch(count) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            default: return "December";
        }
    }

    public static String setYear(String x) {
        return x.split("/")[2];
    }

    public static String setTime(String x) {
        int hours = Integer.parseInt(x.substring(0,2));
        String minutes = x.substring(2,4);
        String AM_PM = (hours>=12) ? "pm": "am";
        return ((hours>12) ? (hours-12):hours)+":"+minutes+AM_PM;
    }
}