/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dujmovic.confapp.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author matij
 */
@Entity
public class Lecture {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Valid
    @OneToOne(targetEntity = Speaker.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "LECTURE_SPEAKER",
            joinColumns = @JoinColumn(name = "LECTURE"),
            inverseJoinColumns = @JoinColumn(name="SPEAKER")
    )
    private Speaker speaker;
    
    @NotEmpty(message = "{validation.lecture.title.notEmpty}")
    @Size(min = 5, max = 50 , message = "{validation.lecture.title.size}")
    private String title;
    
    @NotEmpty(message = "{validation.lecture.contents.notEmpty}")
    @Size(min = 5, max = 100, message = "{validation.lecture.contents.size}")
    private String contents;
    
    @NotNull(message = "{validation.lecture.type.notNull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "LECTURE_TYPE")
    private Type type;
    
    private boolean publish;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
    
    public static enum Type {
        PRESENTATION,
        WORKSHOP
    }

    public Lecture() {
    }

    public Lecture(Speaker speaker, String title, String contents, Type type, boolean publish) {
        this.speaker = speaker;
        this.title = title;
        this.contents = contents;
        this.type = type;
        this.publish = publish;
    }

    public Lecture(Long id, Speaker speaker, String title, String contents, Type type, boolean publish, Date createdAt) {
        this.id = id;
        this.speaker = speaker;
        this.title = title;
        this.contents = contents;
        this.type = type;
        this.publish = publish;
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.speaker);
        hash = 23 * hash + Objects.hashCode(this.title);
        hash = 23 * hash + Objects.hashCode(this.contents);
        hash = 23 * hash + Objects.hashCode(this.type);
        hash = 23 * hash + (this.publish ? 1 : 0);
        hash = 23 * hash + Objects.hashCode(this.createdAt);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lecture other = (Lecture) obj;
        if (this.publish != other.publish) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.contents, other.contents)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.speaker, other.speaker)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.createdAt, other.createdAt)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lecture{" + "id=" + id + ", speaker=" + speaker + ", title=" + title + ", contents=" + contents + ", type=" + type + ", publish=" + publish + ", createdAt=" + createdAt + '}';
    }
    
    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
}
