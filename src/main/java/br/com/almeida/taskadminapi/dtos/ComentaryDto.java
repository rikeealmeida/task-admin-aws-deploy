package br.com.almeida.taskadminapi.dtos;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ComentaryDto {
    @NotNull
    private UUID taskId;
    @NotNull
    private UUID userId;
    @NotNull
    private Date date;
    @NotEmpty
    private String content;
}
