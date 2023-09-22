package ASM3.entity;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "contentMarkdown", columnDefinition = "TEXT")
    private String contentMarkdown;

    @Column(name = "contentHtml", columnDefinition = "TEXT")
    private String contentHtml;

    @ManyToOne
    @JoinColumn(name = "forDoctorId")
    private DoctorUser forDoctor;

    @ManyToOne
    @JoinColumn(name = "forSpecializationId")
    private Specialization forSpecialization;

    @ManyToOne
    @JoinColumn(name = "forClinicId")
    private Clinic forClinic;

    @Column(name = "confirmByDoctor")
    private Integer confirmByDoctor;

    @Column(name = "image")
    private String image;

    @Column(name = "createAt")
    private LocalDateTime createdAt;

    @Column(name = "updateAt")
    private LocalDateTime updatedAt;

    @Column(name = "deleteAt")
    private LocalDateTime deletedAt;

    // Constructors, getters, setters, and other methods

    public Post() {
        // Default constructor
    }

    // ... Getters and setters for fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContentMarkdown() {
        return contentMarkdown;
    }

    public void setContentMarkdown(String contentMarkdown) {
        this.contentMarkdown = contentMarkdown;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public DoctorUser getForDoctor() {
        return forDoctor;
    }

    public void setForDoctor(DoctorUser forDoctor) {
        this.forDoctor = forDoctor;
    }

    public Specialization getForSpecialization() {
        return forSpecialization;
    }

    public void setForSpecialization(Specialization forSpecialization) {
        this.forSpecialization = forSpecialization;
    }

    public Clinic getForClinic() {
        return forClinic;
    }

    public void setForClinic(Clinic forClinic) {
        this.forClinic = forClinic;
    }

    public Integer getConfirmByDoctor() {
        return confirmByDoctor;
    }

    public void setConfirmByDoctor(Integer confirmByDoctor) {
        this.confirmByDoctor = confirmByDoctor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // ... Other getters and setters

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
