package com.hampcode.dto;

import lombok.Data;

@Data
public class FeedbackDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer userId;
}
