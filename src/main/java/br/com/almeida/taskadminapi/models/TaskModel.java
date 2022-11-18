package br.com.almeida.taskadminapi.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "creationDate", nullable = false)
    private Date creationDate;
    @Column(name = "startDate", nullable = false)
    private Date startDate;
    @Column(name = "finishDate", nullable = false)
    private Date finishDate;
    @Column(name = "alertDate")
    private Date alertDate;
    @Column(name = "priority", nullable = false)
    private long priority;
    @Column(name = "status", nullable = false)
    private long status;
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<ComentaryModel> comentaries;
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

    public TaskModel(UUID creatorId, String title, String description, Date creationDate, Date startDate,
            Date finishDate, Date alertDate, long status, long priority,
            CategoryModel category
    // ,
    // List<TeamModel> teams
    ) {
        this.creatorId = creatorId;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.alertDate = alertDate;
        this.status = status;
        this.priority = priority;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(Date alertDate) {
        this.alertDate = alertDate;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public List<ComentaryModel> getComentaries() {
        return comentaries;
    }

    public void setComentaries(List<ComentaryModel> comentaries) {
        this.comentaries = comentaries;
    }

}
