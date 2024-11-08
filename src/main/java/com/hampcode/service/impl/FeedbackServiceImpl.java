package com.hampcode.service.impl;

import com.hampcode.dto.FeedbackDTO;
import com.hampcode.model.entity.Feedback;
import com.hampcode.model.entity.User;
import com.hampcode.repository.FeedbackRepository;
import com.hampcode.repository.UserRepository;
import com.hampcode.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public List<FeedbackDTO> getAllFeedbacksDTO() {
        return getAllFeedbacks().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<FeedbackDTO> paginate(Pageable pageable) {
        return feedbackRepository.findAll(pageable)
                .map(this::convertToDTO);
    }

    @Override
    public Page<FeedbackDTO> paginateByUserId(Pageable pageable, Integer userId) {
        return feedbackRepository.findByUserId(userId, pageable)
                .map(this::convertToDTO);
    }

    @Override
    public FeedbackDTO getFeedbackDTOById(Integer id) {
        Feedback feedback = getFeedbackById(id);
        return convertToDTO(feedback);
    }

    @Override
    public Feedback getFeedbackById(Integer id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found with id: " + id));
    }

    @Override
    public List<FeedbackDTO> getFeedbacksByUserId(Integer userId) {
        return feedbackRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FeedbackDTO create(FeedbackDTO feedbackDTO) {
        Feedback feedback = convertToEntity(feedbackDTO);
        Feedback savedFeedback = feedbackRepository.save(feedback);
        return convertToDTO(savedFeedback);
    }

    @Override
    public FeedbackDTO update(Integer id, FeedbackDTO feedbackDTO) {
        Feedback feedback = getFeedbackById(id);
        feedback.setTitle(feedbackDTO.getTitle());
        feedback.setDescription(feedbackDTO.getDescription());

        // Actualización del user si es necesario
        User user = userRepository.findById(feedbackDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + feedbackDTO.getUserId()));
        feedback.setUser(user);

        Feedback updatedFeedback = feedbackRepository.save(feedback);
        return convertToDTO(updatedFeedback);
    }

    @Override
    public void delete(Integer id) {
        Feedback feedback = getFeedbackById(id);
        feedbackRepository.delete(feedback);
    }

    @Transactional
    @Override
    public FeedbackDTO convertToDTO(Feedback feedback) {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setId(feedback.getId());
        feedbackDTO.setTitle(feedback.getTitle());
        feedbackDTO.setDescription(feedback.getDescription());
        feedbackDTO.setUserId(feedback.getUser().getId()); // Asignar el userId
        return feedbackDTO;
    }

    @Transactional
    @Override
    public Feedback convertToEntity(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setTitle(feedbackDTO.getTitle());
        feedback.setDescription(feedbackDTO.getDescription());

        // Obtener el User a partir del userId en el DTO
        User user = userRepository.findById(feedbackDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + feedbackDTO.getUserId()));
        feedback.setUser(user);

        return feedback;
    }
}
