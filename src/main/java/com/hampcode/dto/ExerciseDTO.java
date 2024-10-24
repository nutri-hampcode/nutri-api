package com.hampcode.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExerciseDTO {
    private Integer id;
    private String linkVideo;
    private byte[] image;
    private String description;
    private GoalDTO goal;
    private List<TipDTO> tips;
}
