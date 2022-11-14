package br.com.almeida.taskadminapi.dtos;

import java.util.Date;
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
    private Date date;
    @NotNull
    private Date alertDate;
    @NotNull
    private boolean isDone;
    // @NotNull
    // private List<UUID> teams;
}
