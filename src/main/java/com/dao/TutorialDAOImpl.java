package com.dao;

import com.model.Tutorial;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TutorialDAOImpl implements TutorialDAO{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TutorialDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int save(Tutorial tutorial) {
        final String INSERT_STMT = "INSERT INTO tutorials (title,description,published) VALUES (:title,:description,:published)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("title",tutorial.getTitle());
        parameters.addValue("description",tutorial.getDescription());
        parameters.addValue("published",tutorial.isPublished());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        return namedParameterJdbcTemplate.update(INSERT_STMT,parameters,keyHolder);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int update(Tutorial tutorial) {
        final String UPDATE = "UPDATE tutorials set title=:title, description=:description, published=:published where id = :id";
        Map<String,Object> map = new HashMap<>();
        map.put("title",tutorial.getTitle());
        map.put("description",tutorial.getDescription());
        map.put("published",tutorial.isPublished());
        map.put("id",tutorial.getId());
        return namedParameterJdbcTemplate.update(UPDATE,map);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteById(Long id) {
        final String DELETE = "DELETE FROM tutorials where id = :id";
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        return namedParameterJdbcTemplate.update(DELETE,map);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteAll() {
        final String DELETE = "DELETE FROM tutorials";
        Map<String,Object> map = new HashMap<>();
        return namedParameterJdbcTemplate.update(DELETE,map);
    }

    @Override
    @Transactional(readOnly = true)
    public Tutorial findById(Long id) {
        final String GET_ONE_STMT = "SELECT id,title,description,published FROM tutorials where id = :id";
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        List<Tutorial> list = namedParameterJdbcTemplate.query(GET_ONE_STMT,map,new TutorialRowMapper());
        if ( list.size() > 0 ) {
            Tutorial tutorial = list.get(0);
            return tutorial;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tutorial> findAll() {
        final String GET_ALL_STMT = "SELECT id,title,description,published FROM tutorials";
        Map<String,Object> map = new HashMap<>();
        List<Tutorial> list = namedParameterJdbcTemplate.query(GET_ALL_STMT,map,new TutorialRowMapper());
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tutorial>  findByTitleContaining(String title) {
        final String GET_ONE_STMT = "SELECT id,title,description,published FROM tutorials where title = :title";
        Map<String,Object> map = new HashMap<>();
        map.put("title",title);
        List<Tutorial> list = namedParameterJdbcTemplate.query(GET_ONE_STMT,map,new TutorialRowMapper());
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tutorial> findByPublished(boolean published) {
        final String GET_ONE_STMT = "SELECT id,title,description,published FROM tutorials where published = :published";
        Map<String,Object> map = new HashMap<>();
        map.put("published",published);
        List<Tutorial> list = namedParameterJdbcTemplate.query(GET_ONE_STMT,map,new TutorialRowMapper());
        return list;
    }

}
