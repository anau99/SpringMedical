package ASM3.form;

public class CancelAcceptedScheduleForm {
    private int idSchedule;
    // 1 : Là hủy
    //2 : Là Accepted
    private int acceptedOrCancel;
    private String description;

    public CancelAcceptedScheduleForm() {
    }

    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idPatient) {
        this.idSchedule = idPatient;
    }

    public int getAcceptedOrCancel() {
        return acceptedOrCancel;
    }

    public void setAcceptedOrCancel(int acceptedOrCancel) {
        this.acceptedOrCancel = acceptedOrCancel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
