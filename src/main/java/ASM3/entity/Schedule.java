package ASM3.entity;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId")
    private User doctor;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "maxBooking")
    private String maxBooking;

    @Column(name = "sumBooking")
    private String sumBooking;

    @Column(name = "createAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(name = "updateAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @Column(name = "deleteAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteAt;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @Column(name ="cancel")
    private int cancel;

    @Column(name="description")
    private String description;

    public int getCancel() {
        return cancel;
    }

    public void setCancel(int cancel) {
        this.cancel = cancel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Constructors, getters, setters và các phương thức khác

    // Constructors
    public Schedule() {
    }
    // Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User getDoctor() {
        return doctor;
    }
    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getMaxBooking() {
        return maxBooking;
    }
    public void setMaxBooking(String maxBooking) {
        this.maxBooking = maxBooking;
    }
    public String getSumBooking() {
        return sumBooking;
    }
    public void setSumBooking(String sumBooking) {
        this.sumBooking = sumBooking;
    }
    public Date getCreateAt() {
        return createAt;
    }
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    public Date getUpdateAt() {
        return updateAt;
    }
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
    public Date getDeleteAt() {
        return deleteAt;
    }
    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}