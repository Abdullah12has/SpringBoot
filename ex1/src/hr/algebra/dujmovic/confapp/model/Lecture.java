package hr.algebra.dujmovic.confapp.model;

import java.util.Date;

public class Lecture {

    public static enum Type{
        PRESENTATION,
        WORKSHOP
    }

    private Long id;
    private String title;
    private Type type;
    private String contents;
    private boolean publish;
    private Date createdAt;

    public Lecture() {
    }

    public Lecture(Long id, String title, Type type, String contents, boolean publish, Date createdAt) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.contents = contents;
        this.publish = publish;
        this.createdAt = createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public String getContents() {
        return contents;
    }

    public boolean isPublish() {
        return publish;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Lecture \n" +
                "id: " + id +
                "\n title: '" + title + '\'' +
                "\n type: " + type +
                "\n contents description: " + contents  +
                "\n publish: " + publish +
                "\n createdAt: " + createdAt
                ;
    }
}
