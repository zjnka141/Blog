package com.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the blog database table.
 *
 */
@Entity
@Table(name = "blog")
public class Blog extends AuditModel implements Serializable  {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Lob
    @NotNull
    private String content;

    @NotNull
    private String title;

    private String description;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private String thumbnail;

    //bi-directional many-to-one association to Category
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="categoryID")
    @JsonIgnore
    private Category category;

    public Blog() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}