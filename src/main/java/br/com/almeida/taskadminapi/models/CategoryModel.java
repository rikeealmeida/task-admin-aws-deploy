package br.com.almeida.taskadminapi.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "t_category")
public class CategoryModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "color")
    private String color;
    @Column(name = "iconCode")
    private int iconCode;
    // @OneToMany(mappedBy = "category")
    // private List<TaskModel> tasks;
    // @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    // @JsonBackReference
    // private List<TaskModel> tasks;

    public CategoryModel() {
    }

    public CategoryModel(String name, String color, int iconCode) {
        this.name = name;
        this.color = color;
        this.iconCode = iconCode;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getIconCode() {
        return iconCode;
    }

    public void setIconCode(int iconCode) {
        this.iconCode = iconCode;
    }

    // public List<TaskModel> getTasks() {
    //     return tasks;
    // }

    // public void setTasks(List<TaskModel> tasks) {
    //     this.tasks = tasks;
    // }

}
