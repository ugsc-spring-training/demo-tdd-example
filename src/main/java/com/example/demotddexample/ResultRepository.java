package com.example.demotddexample;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResultRepository implements IResultRepository {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public ResultRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Integer> findByYear(int year) {

        List<Integer> resultList = new ArrayList<>();
        String sql = "select * from Result where year = ?";

            Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                resultList.add(resultSet.getInt("result"));
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultList;
    }

    public Integer findByYearJDdbcTemplate(int year) {
        return jdbcTemplate.queryForObject(
                "select result from Result where year = ?",
                Integer.class,
                year);
    }

    public List<Race> findAll() {
        return jdbcTemplate.query("select * from Result",
                (rs, rowNum) -> new Race(
                    rs.getLong("id"),
                    rs.getInt("year"),
                    rs.getInt("result")));
    }
}
