package com.service;

import com.dao.TutorialDAO;
import com.model.Tutorial;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialServiceImpl implements TutorialService{

    private TutorialDAO tutorialDAO;

    public TutorialServiceImpl(TutorialDAO tutorialDAO) {
        this.tutorialDAO = tutorialDAO;
    }

    @Override
    public int save(Tutorial tutorial) {
        return tutorialDAO.save(tutorial);
    }

    @Override
    public int update(Tutorial tutorial) {
        return tutorialDAO.update(tutorial);
    }

    @Override
    public int deleteById(Long id) {
        return tutorialDAO.deleteById(id);
    }

    @Override
    public int deleteAll() {
        return tutorialDAO.deleteAll();
    }

    @Override
    public Tutorial findById(Long id) {
        return tutorialDAO.findById(id);
    }


    @Override
    public List<Tutorial> findAll() {
        return tutorialDAO.findAll();
    }

    @Override
    public List<Tutorial> findByTitleContaining(String title) {
        return tutorialDAO.findByTitleContaining(title);
    }

    @Override
    public List<Tutorial> findByPublished(boolean published) {
        return tutorialDAO.findByPublished(published);
    }

}
