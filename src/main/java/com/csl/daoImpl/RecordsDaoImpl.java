package com.csl.daoImpl;

import com.csl.dao.IRecords;
import com.csl.domain.RecordsDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by csl on 2017/5/16.
 */
@Repository
public class RecordsDaoImpl implements IRecords {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int getSuccess(String userID) {
        final String sql = " SELECT SUCCESS FROM RECORDS WHERE USERID=:userID";
        final List<Integer> result = new ArrayList<Integer>();
        this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("userID", userID),
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        result.add(resultSet.getInt("SUCCESS"));
                    }
                });
        return result.get(0);
    }

    public int getFail(String userID) {
        final String sql = " SELECT FAIL FROM RECORDS WHERE USERID=:userID";
        final List<Integer> result = new ArrayList<Integer>();
        this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("userID", userID),
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        result.add(resultSet.getInt("FAIL"));
                    }
                });
        return result.get(0);
    }

    public int updateSuccess(String userID, int count) {
        final String sql = " UPDATE RECORDS SET SUCCESS= " + count + " WHERE USERID=:userID";
        return this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValue("userID", userID));
    }

    public int updateFail(String userID,int count) {
        final String sql = " UPDATE RECORDS SET FAIL= " + count + " WHERE USERID=:userID";
        return this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValue("userID", userID));
    }

    public int save(RecordsDO recordsDO) {
        return 0;
    }

    public int remove(RecordsDO recordsDO) {
        return 0;
    }

    public int update(RecordsDO recordsDO) {
        return 0;
    }

    public RecordsDO find(String ID) {
        return null;
    }

    public List<RecordsDO> getAll() {
        return null;
    }
}
