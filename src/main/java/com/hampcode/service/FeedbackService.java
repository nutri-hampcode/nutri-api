package com.hampcode.service;

import com.hampcode.model.entity.Feedback;
import com.hampcode.dto.FeedbackDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FeedbackService {
    List<Feedback> getAllFeedbacks();
    List<FeedbackDTO> getAllFeedbacksDTO();
    Page<FeedbackDTO> paginate(Pageable pageable);
    Page<FeedbackDTO> paginateByUserId(Pageable pageable, Integer userId);
    FeedbackDTO getFeedbackDTOById(Integer id);
    Feedback getFeedbackById(Integer id);
    List<FeedbackDTO> getFeedbacksByUserId(Integer userId);
    FeedbackDTO create(FeedbackDTO feedback);
    FeedbackDTO update(Integer id, FeedbackDTO feedback);
    void delete(Integer id);

    FeedbackDTO convertToDTO(Feedback feedback);

    Feedback convertToEntity(FeedbackDTO feedbackDTO);
}