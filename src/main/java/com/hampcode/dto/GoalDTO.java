package com.hampcode.dto;

import com.hampcode.model.entity.Goal.GoalType;
import lombok.Data;

@Data
public class GoalDTO {
    private Integer id;
    private GoalType goalType;
}
