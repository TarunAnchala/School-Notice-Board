package com.school.noticeboard.Data;

public class Notice {
    private int id;
    private String title;
    private String description;
    private String image;
    private String time;
    private String teacher;
    private String teacher_contact_number;
    private String subject;

    public Notice(int id, String title, String description, String image, String time, String teacher, String teacher_contact_number, String subject) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.time = time;
        this.teacher = teacher;
        this.teacher_contact_number = teacher_contact_number;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacher_contact_number() {
        return teacher_contact_number;
    }

    public void setTeacher_contact_number(String teacher_contact_number) {
        this.teacher_contact_number = teacher_contact_number;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", time='" + time + '\'' +
                ", teacher='" + teacher + '\'' +
                ", teacher_contact_number='" + teacher_contact_number + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
