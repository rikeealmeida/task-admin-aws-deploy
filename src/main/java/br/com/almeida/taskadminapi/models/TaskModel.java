package br.com.almeida.taskadminapi.models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "t_task")
public class TaskModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "creatorId", nullable = false)
    private UUID creatorId;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "alertDate")
    private Date alertDate;
    @Column(name = "isDone")
    private boolean isDone;
    // @Column(name = "requireLocalization")
    // private boolean requireLocalization;
    // @Column(name = "latitude")
    // private double latitude;
    // @Column(name = "longitude")
    // private boolean longitude;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "category_id")
    private CategoryModel category;
    // @ManyToMany
    // @JoinTable(name = "t_team_task", joinColumns = { @JoinColumn(name =
    // "task_id") }, inverseJoinColumns = {
    // @JoinColumn(name = "team_id") })
    // private List<TeamModel> teams;

    public TaskModel() {
    }

    public TaskModel(UUID creatorId, String title, String description, Date date, Date alertDate, boolean isDone,
            CategoryModel category
    // ,
    // List<TeamModel> teams
    ) {
        this.creatorId = creatorId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.alertDate = alertDate;
        this.isDone = isDone;
        this.category = category;
        // this.teams = teams;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(Date alertDate) {
        this.alertDate = alertDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UUID creatorId) {
        this.creatorId = creatorId;
    }

    // public List<TeamModel> getTeams() {
    // return teams;
    // }

    // public void setTeams(List<TeamModel> teams) {
    // this.teams = teams;
    // }

    // public List<TeamMemberModel> getMembers() {
    // return members;
    // }

    // public void setMembers(List<TeamMemberModel> members) {
    // this.members = members;
    // }

}
