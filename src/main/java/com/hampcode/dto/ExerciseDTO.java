package com.hampcode.dto;

import java.util.List;

import lombok.Data;

@Data
public class ExerciseDTO {
    private Integer id;
    private String linkVideo;
    private byte[] image;
    private String description;
    private GoalDTO goal;
    private List<TipDTO> tips;
}
