package com.csl.daoImpl;

import com.csl.dao.IUserDao;
import com.csl.domain.UserDO;
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
 * Created by csl on 2017/3/7.
 */
@Repository
public class UserDaoImpl implements IUserDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int save(final UserDO userDO) {
        final String sql = " INSERT INTO USER VALUES (:ID,:password,:email,:name,:telephone,:region,:credit) ";
        return this.namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(userDO));
    }

    public int remove(final UserDO userDO) {
        final String sql = " DELETE FROM USER WHERE ID = :ID ";
        return this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValue("ID", userDO.getID()));
    }

    public int update(final UserDO userDO) {
        final String sql = " UPDATE USER SET PASSWORD=:password,NAME=:name, "
                + " TELEPHONE=:telephone,REGION=:region,CREDIT=:credit WHERE ID=:ID ";
        return this.namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(userDO));
    }

    public UserDO find(final String ID) {
        final String sql = " SELECT PASSWORD,EMAIL,NAME,TELEPHONE,REGION,CREDIT FROM USER WHERE ID=:ID";
        final UserDO userDO = new UserDO();
        this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("ID", ID),
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        userDO.setID(ID);
                        userDO.setPassword(resultSet.getString("PASSWORD"));
                        userDO.setEmail(resultSet.getString("EMAIL"));
                        userDO.setName(resultSet.getString("NAME"));
                        userDO.setTelephone(resultSet.getString("TELEPHONE"));
                        userDO.setRegion(resultSet.getString("REGION"));
                        userDO.setCredit(resultSet.getInt("CREDIT"));
                    }
                });
        return userDO;
    }

    public List<UserDO> getAll() {
        final String sql = " SELECT ID,PASSWORD,EMAIL,NAME,TELEPHONE,REGION,CREDIT FROM USER ";
        final List<UserDO> userDOList = new ArrayList<UserDO>();
        this.namedParameterJdbcTemplate.getJdbcOperations().query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                UserDO userDO = new UserDO();
                userDO.setID(resultSet.getString("ID"));
                userDO.setPassword(resultSet.getString("PASSWORD"));
                userDO.setEmail(resultSet.getString("EMAIL"));
                userDO.setName(resultSet.getString("NAME"));
                userDO.setTelephone(resultSet.getString("TELEPHONE"));
                userDO.setRegion(resultSet.getString("REGION"));
                userDO.setCredit(Integer.parseInt(resultSet.getString("CREDIT")));
                userDOList.add(userDO);
            }
        });
        return userDOList;
    }

    public UserDO getByEmail(final String email) {
        final String sql = " SELECT ID,PASSWORD,EMAIL,NAME,TELEPHONE,REGION,CREDIT FROM USER WHERE EMAIL=:email";
        final UserDO userDO = new UserDO();
        this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("email", email),
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        userDO.setID(resultSet.getString("ID"));
                        userDO.setPassword(resultSet.getString("PASSWORD"));
                        userDO.setEmail(resultSet.getString("EMAIL"));
                        userDO.setName(resultSet.getString("NAME"));
                        userDO.setTelephone(resultSet.getString("TELEPHONE"));
                        userDO.setRegion(resultSet.getString("REGION"));
                        userDO.setCredit(resultSet.getInt("CREDIT"));
                    }
                });
        return userDO;
    }

    public int updatePassword(final String email, final String password) {
        final String sql = " UPDATE USER SET PASSWORD=:password WHERE EMAIL=:email ";
        return this.namedParameterJdbcTemplate.update(sql,
                new MapSqlParameterSource().addValue("password", password).addValue("email", email));
    }

    public int checkEmail(String email) {
        final String sql = " SELECT ID FROM USER WHERE EMAIL=:email ";
        final List<String> ID = new ArrayList<String>();
        this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("email", email),
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        ID.add(resultSet.getString("ID"));
                    }
                });
        return ID.size();
    }

    public int checkName(final String name) {
        final String sql = " SELECT ID FROM USER WHERE NAME=:name ";
        final List<String> ID = new ArrayList<String>();
        this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("name", name),
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        ID.add(resultSet.getString("ID"));
                    }
                });
        return ID.size();
    }

    public int checkTelephone(String telephone) {
        final String sql = " SELECT ID FROM USER WHERE TELEPHONE=:telephone ";
        final List<String> ID = new ArrayList<String>();
        this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("telephone", telephone),
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        ID.add(resultSet.getString("ID"));
                    }
                });
        return ID.size();
    }

    public UserDO getOwner(String goodsID) {
        final String sql = " SELECT ID,PASSWORD,EMAIL,NAME,TELEPHONE,REGION,CREDIT FROM OWNER LEFT JOIN USER ON OWNER.USERID=ID WHERE GOODSID=:GOODSID ";
        final UserDO userDO = new UserDO();
        this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("GOODSID", goodsID),
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        userDO.setID(resultSet.getString("ID"));
                        userDO.setPassword(resultSet.getString("PASSWORD"));
                        userDO.setEmail(resultSet.getString("EMAIL"));
                        userDO.setName(resultSet.getString("NAME"));
                        userDO.setTelephone(resultSet.getString("TELEPHONE"));
                        userDO.setRegion(resultSet.getString("REGION"));
                        userDO.setCredit(resultSet.getInt("CREDIT"));
                    }
                });
        return userDO;
    }

    public int updateCredit(String ID, int credit) {
        final String sql = " UPDATE USER SET CREDIT= " + credit + " WHERE ID=:ID ";
        return this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValue("ID", ID));
    }

    public int getCredit(String ID) {
        final String sql = " SELECT CREDIT FROM USER WHERE ID='" + ID + "'";
        final List<Integer> credit = new ArrayList<Integer>();
        this.namedParameterJdbcTemplate.getJdbcOperations().query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                credit.add(resultSet.getInt("CREDIT"));
            }
        });
        return credit.get(0);
    }
}