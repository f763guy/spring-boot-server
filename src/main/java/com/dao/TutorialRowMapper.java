package com.dao;

import com.model.Tutorial;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorialRowMapper implements RowMapper<Tutorial> {
    @Override
    public Tutorial mapRow(ResultSet rs, int i) throws SQLException {
        Tutorial tutorial = new Tutorial();
        tutorial.setId(rs.getLong("id"));
        tutorial.setTitle(rs.getString("title"));
        tutorial.setDescription(rs.getString("description"));
        tutorial.setPublished(rs.getBoolean("published"));
        return tutorial;
    }
}
