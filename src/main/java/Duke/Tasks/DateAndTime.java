package Duke.Tasks;

public class DateAndTime {
    private String Day;
    private String Month;
    private String Year;
    private String Time;
    private String startTime;
    private String endTime;

    /**
     * Constructor to parse the date and time string entered by the user according to the type of task
     *
     * @param dateAndTime string containing the date and time
     * @param taskType indicates whether the task is a 'deadline' or an 'event'
     */
    public DateAndTime(String dateAndTime, int taskType) {
        if (taskType == 0) { // deadline
            if (dateAndTimeChecker(dateAndTime, taskType)) {
                String date = dateAndTime.split(" ")[0];
                String time = dateAndTime.split(" ")[1];
                this.Day = setDay(date);
                this.Month = setMonth(date);
                this.Year = setYear(date);
                this.Time = setTime(time);
            }
        } else { // event
            if (dateAndTimeChecker(dateAndTime, taskType)) {
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

    /**
     * Constructor to parse the date and time string read from the persistent file storage
     *
     * @param dateAndTime string containing the date and time
     * @param eventType indicates whether the task is a 'deadline' or an 'event'
     * @param dummy variable to differentiate this constructor from the other
     */
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

    /**
     * Checks the validity of the date and time
     *
     * @param dateAndTime string containing the date and time
     * @param eventType indicates whether the task is a 'deadline' or an 'event'
     * @return boolean value indicating validity of the date and time
     */
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
                if (dateChecker(date) && timeChecker(startTime,endTime)) return true;
                return false;
            }
        }
        return false;
    }

    /**
     * Getter function for date and time in the Duke.Duke.DukeOperations format.
     * e.g. for deadline, '2nd of December 2019, 6pm'
     * e.g. for event, '2nd of December 2019, 6pm-7pm'
     *
     * @param eventType indicates whether the task is a 'deadline' or an 'event'
     * @return the string of the date in the Duke.Duke.DukeOperations format
     */
    public String getDateAndTime(int eventType) {
        if (eventType == 0) return this.Day+" of "+this.Month+" "+this.Year+", "+this.Time;
        else return this.Day+" of "+this.Month+" "+this.Year+", "+this.startTime+"-"+this.endTime;
    }


    /**
     * Checks the validity of the date in the format 'DD/MM/YY'
     *
     * @param date string containing the date
     * @return boolean value indicating validity of the date
     */
    private boolean dateChecker(String[] date) {
        return (date.length == 3 && Integer.parseInt(date[0]) >= 1 && Integer.parseInt(date[0]) <= 31
                && Integer.parseInt(date[1]) >= 1 && Integer.parseInt(date[1]) <= 12
                && Integer.parseInt(date[2]) >= 1
                && dayOfMonthChecker(Integer.parseInt(date[0]), Integer.parseInt(date[1])));
    }

    /**
     * Checks the validity of the time in the 24 hr format
     *
     * @param time string containing the time
     * @return boolean value indicating validity of the time
     */
    private boolean timeChecker(String time) {
        return (time.length() == 4 && Integer.parseInt(time.substring(0, 2)) >= 0
                && Integer.parseInt(time.substring(0, 2)) < 24 && Integer.parseInt(time.substring(2, 4)) >= 0
                && Integer.parseInt(time.substring(2, 4)) <= 59);
    }

    /**
     * Checks the validity of the start and end times in the 24 hr format
     *
     * @param startTime string containing the start time
     * @param endTime string containing the end time
     * @return boolean value indicating the validity of the start and end times
     */
    private boolean timeChecker(String startTime, String endTime) {
        return (timeChecker(startTime) && timeChecker(endTime)
                && Integer.parseInt(endTime)>=Integer.parseInt(startTime));
    }

    /**
     * Checks the validity of the day of the month
     *
     * @param day integer containing the day
     * @param month integer containing the month
     * @return boolean value indicating validity of the day of the month
     */
    private boolean dayOfMonthChecker(int day, int month) {
        switch(month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return (day>=1 && day<=31);
            case 2:
                return (day>=1 && day<=28);
            default:
                return (day>=1 && day<=30);
        }
    }

    /**
     * Setter function for the day
     *
     * @param date string containing the date
     * @return the string containing the day in the Duke.Duke.DukeOperations format
     */
    private String setDay(String date) {
        int count = Integer.parseInt(date.split("/")[0]);
        if (count>=10&&count<=20) return count+"th";
        switch(count%10) {
            case 1: return count+"st";
            case 2: return count+"nd";
            case 3: return count+"rd";
            default: return count+"th";
        }
    }

    /**
     * Setter function for the month
     *
     * @param date string containing the date
     * @return the string containing the month in the Duke.Duke.DukeOperations format
     */
    private String setMonth(String date) {
        int count = Integer.parseInt(date.split("/")[1]);
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

    /**
     * Setter function for the year
     *
     * @param date string containing the date
     * @return the string containing the year in the Duke.Duke.DukeOperations format
     */
    private String setYear(String date) {
        return date.split("/")[2];
    }

    /**
     * Setter function for the time
     *
     * @param time string containing the time in the 24 hr format
     * @return string containing the time in the Duke.Duke.DukeOperations format
     */
    private String setTime(String time) {
        int hours = Integer.parseInt(time.substring(0,2));
        int minutes = Integer.parseInt(time.substring(2,4));
        String AM_PM = (hours>=12) ? "pm": "am";
        return ((hours == 0) ? "12" : ((hours>12) ? (hours-12) : hours))+
                ((minutes == 0) ? "" : (":"+time.substring(2,4)))+AM_PM;
    }
}