public class DateAndTime {
    protected String Day;
    protected String Month;
    protected String Year;
    protected String Time;
    protected String startTime;
    protected String endTime;

    public DateAndTime(String dateAndTime, int eventType) {
        if (eventType == 0) { // deadline
            if (dateAndTimeChecker(dateAndTime, eventType)) {
                String date = dateAndTime.split(" ")[0];
                String time = dateAndTime.split(" ")[1];
                this.Day = setDay(date);
                this.Month = setMonth(date);
                this.Year = setYear(date);
                this.Time = setTime(time);
            }
        } else { // event
            if (dateAndTimeChecker(dateAndTime, eventType)) {
                String date = dateAndTime.split(" ")[0];
                String startTime = dateAndTime.split(" ")[1].split("-",2)[0];
                String endTime = dateAndTime.split(" ")[1].split("-",2)[1];
                this.Day = setDay(date);
                this.Month = setMonth(date);
                this.Year = setYear(date);
                this.startTime = setTime(startTime);
                this.endTime = setTime(endTime);
            }
        }
    }

    public DateAndTime(String dateAndTime, int eventType, int dummy) {
            this.Day = dateAndTime.split(" ")[0];
            this.Month = dateAndTime.split(" ")[2];
            this.Year = dateAndTime.split(" ")[3].substring(0, dateAndTime.split(" ")[3].length() - 1);
            if (eventType == 0) {
                this.Time = dateAndTime.split(" ")[4];
            } else {
                this.startTime = dateAndTime.split(" ")[4].split("-",2)[0];
                this.endTime = dateAndTime.split(" ")[4].split("-",2)[1];
            }
    }

    public boolean dateAndTimeChecker(String dateAndTime, int eventType) {
        if (eventType == 0) { // deadline
            if ((dateAndTime.split(" ")).length == 2) {
                String[] date = (dateAndTime.split(" ")[0]).split("/");
                String time = dateAndTime.split(" ")[1];
                if (dateChecker(date) && timeChecker(time)) return true;
                return false;
            }
        } else { // event
            if ((dateAndTime.split(" ")).length == 2) {
                String[] date = (dateAndTime.split(" ")[0]).split("/");
                String startTime = dateAndTime.split(" ")[1].split("-",2)[0];
                String endTime = dateAndTime.split(" ")[1].split("-",2)[1];
                if (dateChecker(date) && validTimesChecker(startTime,endTime)) return true;
                return false;
            }
        }
        return false;
    }

    public boolean dateChecker(String[] date) {
        if (date.length == 3 && Integer.parseInt(date[0]) >= 1 && Integer.parseInt(date[0]) <= 31
                && Integer.parseInt(date[1]) >= 1 && Integer.parseInt(date[1]) <= 12
                && Integer.parseInt(date[2]) >= 1
                && dayOfMonthChecker(Integer.parseInt(date[0]), Integer.parseInt(date[1]))) {
            return true;
        }
        return false;
    }

    public boolean timeChecker(String time) {
        if (time.length() == 4 && Integer.parseInt(time.substring(0, 2)) >= 0
                && Integer.parseInt(time.substring(0, 2)) < 24 && Integer.parseInt(time.substring(2, 4)) >= 0
                && Integer.parseInt(time.substring(2, 4)) <= 59) {
            return true;
        }
        return false;
    }

    public boolean validTimesChecker(String startTime, String endTime) {
        if (timeChecker(startTime) && timeChecker(endTime)) {
            int startHour = Integer.parseInt(startTime.substring(0,2));
            int startMin = Integer.parseInt(startTime.substring(2,4));
            int endHour = Integer.parseInt(endTime.substring(0,2));
            int endMin = Integer.parseInt(endTime.substring(2,4));
            if (endHour<startHour) return false;
            else if (endHour==startHour && endMin<startMin) return false;
            return true;
        }
        return false;
    }

    public boolean dayOfMonthChecker(int day, int month) {
        switch(month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return (day>=1 && day<=31);
            case 2:
                return (day>=1 && day<=28);
            default:
                return (day>=1 && day<=30);
        }
    }

    public String getDateAndTime(int eventType) {
        if (eventType == 0) return this.Day+" of "+this.Month+" "+this.Year+", "+this.Time;
        else return this.Day+" of "+this.Month+" "+this.Year+", "+this.startTime+"-"+this.endTime;
    }

    public String setDay(String x) {
        int count = Integer.parseInt(x.split("/")[0]);
        if (count>=10&&count<=20) return count+"th";
        switch(count%10) {
            case 1: return count+"st";
            case 2: return count+"nd";
            case 3: return count+"rd";
            default: return count+"th";
        }
    }

    public String setMonth(String x) {
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

    public String setYear(String x) {
        return x.split("/")[2];
    }

    public String setTime(String x) {
        int hours = Integer.parseInt(x.substring(0,2));
        int minutes = Integer.parseInt(x.substring(2,4));
        String AM_PM = (hours>=12) ? "pm": "am";
        return ((hours == 0) ? "12" : ((hours>12) ? (hours-12) : hours))+
                ((minutes == 0) ? "" : (":"+x.substring(2,4)))+AM_PM;
    }
}