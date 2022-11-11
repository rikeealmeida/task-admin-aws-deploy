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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "t_team")
public class TeamModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TeamMemberModel> members;
    // @ManyToMany(mappedBy = "teams")
    // @JsonBackReference
    // private List<TaskModel> tasks;

    public TeamModel() {

    }

    public TeamModel(String name) {
        this.name = name;
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

    public List<TeamMemberModel> getMembers() {
        return members;
    }

    public void setMembers(List<TeamMemberModel> members) {
        this.members = members;
    }

    // public List<TaskModel> getTasks() {
    //     return tasks;
    // }

    // public void setTasks(List<TaskModel> tasks) {
    //     this.tasks = tasks;
    // }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
