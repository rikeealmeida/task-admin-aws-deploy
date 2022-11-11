package br.com.almeida.taskadminapi.dtos;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class TeamDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private List<UUID> members;
}
