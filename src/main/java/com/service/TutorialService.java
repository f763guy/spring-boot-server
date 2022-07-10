package com.service;

import com.model.Tutorial;

import java.util.List;


public interface TutorialService {
    int save(Tutorial tutorial);

    int update(Tutorial tutorial);

    int deleteById(Long id);

    int deleteAll();

    Tutorial findById(Long id);

    List<Tutorial> findAll();

    List<Tutorial> findByTitleContaining(String title);

    List<Tutorial> findByPublished(boolean published);

}
