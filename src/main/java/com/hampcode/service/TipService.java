package com.hampcode.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hampcode.model.entity.Tip;

public interface TipService {
    List<Tip> getAllTips();
    Page<Tip> paginate(Pageable pageable);
    Tip findById(Integer id);
    Tip create(Tip tip);
    Tip update(Integer id, Tip tip);
    void delete(Integer id);
}
