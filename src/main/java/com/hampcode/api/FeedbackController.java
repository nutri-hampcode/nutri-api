package com.hampcode.api;

import com.hampcode.dto.FeedbackDTO;
import com.hampcode.model.entity.Feedback;
import com.hampcode.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<FeedbackDTO>> listFeedbacksDTO() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacksDTO());
    }

    @GetMapping("/paginate")
    public ResponseEntity<Page<FeedbackDTO>> paginateFeedbacks(Pageable pageable) {
        return ResponseEntity.ok(feedbackService.paginate(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable Integer id) {
        return ResponseEntity.ok(feedbackService.getFeedbackDTOById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbacksByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(feedbackService.getFeedbacksByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<FeedbackDTO> createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return new ResponseEntity<>(feedbackService.create(feedbackDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDTO> updateFeedback(@PathVariable Integer id, @RequestBody FeedbackDTO feedbackDTO) {
        return ResponseEntity.ok(feedbackService.update(id, feedbackDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Integer id) {
        feedbackService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
