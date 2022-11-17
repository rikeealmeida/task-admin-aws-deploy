package br.com.almeida.taskadminapi.dtos;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TaskDto {
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @NotNull
    private UUID creatorId;
    @NotNull
    private UUID category;
    @NotNull
    private Date creationDate;
    @NotNull
    private Date startDate;
    @NotNull
    private Date finishDate;
    @NotNull
    private Date alertDate;
    @NotNull
    private long priority;
    @NotNull
    private long status;

    private List<UUID> comentaries;
    // @NotNull
    // private List<String> comments;
    // @NotNull
    // private List<UUID> teams;
}
