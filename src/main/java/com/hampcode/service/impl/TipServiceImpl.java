package com.hampcode.service.impl;

import com.hampcode.model.entity.Tip;
import com.hampcode.repository.TipRepository;
import com.hampcode.service.TipService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TipServiceImpl implements TipService {
    private final TipRepository tipRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Tip> getAllTips() {return tipRepository.findAll();}

    @Transactional(readOnly = true)
    @Override
    public Page<Tip> paginate(Pageable pageable) {return tipRepository.findAll(pageable);}

    public Tip findById(Integer id){
        return tipRepository.findById(id).orElseThrow(()->new RuntimeException("No exercise found with id: " + id));
    }

    @Transactional
    @Override
    public Tip create(Tip tip) {
        return tipRepository.save(tip);
    }

    @Override
    public Tip update(Integer id, Tip updateTip) {
        Tip tip = findById(id);
        if(updateTip.getContent() != null){
            tip.setContent(updateTip.getContent());
        }
        if(updateTip.getExercise() != null){
            tip.setExercise(updateTip.getExercise());
        }
        return tipRepository.save(tip);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Tip tip = findById(id);
        tipRepository.delete(tip);
    }
}
