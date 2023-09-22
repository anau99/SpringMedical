package ASM3.entity;

import javax.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "doctorId")
    private DoctorUser doctor;

    @ManyToOne
    @JoinColumn(name = "statusId")
    private Status status;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    // Constructors, getters, setters và các phương thức khác

    // Constructors
    public Patient() {
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DoctorUser getDoctor() {
        return doctor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDoctor(DoctorUser doctor) {
        this.doctor = doctor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
