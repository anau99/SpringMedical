package ASM3.form;

public class ScheduleForm {

    private String nameDoctor;
    private int idDoctor;

    private String timeLine;
    private String sumBooking;

    public ScheduleForm() {
    }

    public String getNameDoctor() {
        return nameDoctor;
    }

    public void setNameDoctor(String nameDoctor) {
        this.nameDoctor = nameDoctor;
    }

    public String getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(String timeLine) {
        this.timeLine = timeLine;
    }

    public String getSumBooking() {
        return sumBooking;
    }

    public void setSumBooking(String sumBooking) {
        this.sumBooking = sumBooking;
    }
    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

}
