package com.csl.daoImpl;

import com.csl.dao.IGoodsDao;
import com.csl.domain.*;
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
public class GoodsDaoImpl implements IGoodsDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final int PAGE_SIZE = 16;

    public int save(final GoodsDO goodsDO) {
        final String sql = " INSERT INTO GOODS VALUES (:ID,:name,:description,:price,:kind,:status,:attentionDegree, " +
                " :datetime,:createDate,:color,:length,:width,:height) ";
        int result = 0;
        if (this.namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(goodsDO)) > 0
                && goodsDO.getImageUrl().length() > 0) {
            result = this.saveImage(goodsDO.getID(), goodsDO.getImageUrl());
        }
        return result;
    }

    public int save(final String userID, final GoodsDO goodsDO) {
        final String sql = " INSERT INTO GOODS VALUES (:ID,:name,:description,:price,:kind,:status,:attentionDegree, " +
                " :datetime,:createDate,:color,:length,:width,:height) ";
        if (this.namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(goodsDO)) > 0
                && goodsDO.getImageUrl().length() > 0) {
            this.saveImage(goodsDO.getID(), goodsDO.getImageUrl());
        }
        return this.buildOwnerRelate(goodsDO.getID(), userID);
    }

    public int attentionGoods(final String userID, final String goodsID) {
        final String sql = " INSERT INTO ATTENTION VALUES (:USERID,:GOODSID) ";
        return this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValue("USERID", userID).addValue("GOODSID", goodsID));
    }

    public int removeAttention(final String userID, final String goodsID) {
        final String sql = " DELETE FROM ATTENTION WHERE USERID='" + userID + "' AND GOODSID='" + goodsID + "' ";
        return this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource());
    }

    public int isAttendByUser(final String userID, final String goodsID) {
        final String sql = " SELECT USERID FROM ATTENTION WHERE USERID=:USERID AND GOODSID=:GOODSID ";
        final List<String> userIDList = new ArrayList<String>();
        this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("USERID", userID).addValue("GOODSID", goodsID)
                , new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        userIDList.add(resultSet.getString("USERID"));
                    }
                });
        return userIDList.size();
    }

    //删除没卖的
    public int remove(final GoodsDO goodsDO) {
        final String sql = " DELETE FROM GOODS WHERE ID=:ID ";
        return this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValue("ID", goodsDO.getID()));
    }

    //删除没卖的
    public int remove(final String ID) {
        final String sql = " DELETE FROM GOODS WHERE ID=:ID ";
        return this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValue("ID", ID));
    }

    //删除卖的
    public int removeSoldGoods(final String ID) {
        final String sql = " DELETE FROM OWNER WHERE GOODSID=:ID ";
        return this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValue("ID", ID));
    }

    public int getCount(final String userID, final String region, final GoodsKind goodsKind,String text) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT COUNT(*) FROM GOODS LEFT JOIN OWNER ON OWNER.GOODSID=GOODS.ID LEFT JOIN USER ON USER.ID= OWNER.USERID WHERE ");
        if (region != null && goodsKind != null) {
            sql.append(" REGION='" + region + "' AND KIND='" + goodsKind.name() + "' AND ");
        } else if (region != null && goodsKind == null) {
            sql.append(" REGION='" + region + "' AND ");
        } else if (region == null && goodsKind != null) {
            sql.append(" KIND='" + goodsKind.name() + "' AND ");
        }
        sql.append(" STATUS <> '" + GoodsStatus.sold + "' AND USER.ID <> '" + userID + "' ");
        if(text != null && text != ""){
            sql.append(" AND (GOODS.NAME like '%" + text + "%' or DESCRIPTION like '%" + text + "%') ");
        }
        final List<Integer> result = new ArrayList<Integer>();
        this.namedParameterJdbcTemplate.getJdbcOperations().query(sql.toString(),
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        result.add(resultSet.getInt(1));
                    }
                });
        return result.get(0);
    }

    public int update(final GoodsDO goodsDO) {
        final String sql = " UPDATE GOODS SET NAME=:name,DESCRIPTION=:description,PRICE=:price, "
                + " KIND=:kind,STATUS=:status,ATTENTIONDEGREE=:attentionDegree,DATETIME=:datetime,CREATEDATE=:createDate, " +
                " COLOR=:color,LENGTH=:length,WIDTH=:width,HEIGHT=:height WHERE ID=:ID ";
        int result = 0;
        if (this.namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(goodsDO)) > 0
                && goodsDO.getImageUrl().length() > 0) {
            result = this.updateImage(goodsDO.getID(), goodsDO.getImageUrl());
        }
        return result;
    }

    public int updateStatus(final String goodsID, final String status) {
        final String sql = " UPDATE GOODS SET STATUS=:status WHERE ID=:goodsID";
        return this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValue("status", status).addValue("goodsID", goodsID));
    }

    public GoodsDO find(final String ID) {
        final String sql = " SELECT ID,NAME,DESCRIPTION,PRICE,KIND,STATUS,ATTENTIONDEGREE,DATETIME,CREATEDATE,COLOR, " +
                " LENGTH,WIDTH,HEIGHT,URL FROM GOODS LEFT JOIN IMAGE ON ID=GOODSID WHERE ID=:ID ";
        final GoodsDO goodsDO = new GoodsDO();
        this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("ID", ID),
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        goodsDO.setID(ID);
                        goodsDO.setName(resultSet.getString("NAME"));
                        goodsDO.setDescription(resultSet.getString("DESCRIPTION"));
                        goodsDO.setPrice(resultSet.getInt("PRICE"));
                        goodsDO.setKind(resultSet.getString("KIND"));
                        goodsDO.setDatetime(resultSet.getLong("DATETIME"));
                        goodsDO.setStatus(resultSet.getString("STATUS"));
                        goodsDO.setAttentionDegree(Integer.parseInt(resultSet.getString("ATTENTIONDEGREE")));
                        goodsDO.setImageUrl(resultSet.getString("URL"));
                        goodsDO.setCreateDate(resultSet.getLong("CREATEDATE"));
                        goodsDO.setColor(resultSet.getString("COLOR"));
                        goodsDO.setLength(resultSet.getInt("LENGTH"));
                        goodsDO.setWidth(resultSet.getInt("WIDTH"));
                        goodsDO.setHeight(resultSet.getInt("HEIGHT"));
                    }
                });
        return goodsDO;
    }

    public List<GoodsDO> getByOwnerID(final String ID) {
        final String sql = " SELECT ID,NAME,DESCRIPTION,PRICE,KIND,STATUS,ATTENTIONDEGREE,DATETIME,CREATEDATE," +
                " COLOR,LENGTH,WIDTH,HEIGHT,URL FROM OWNER LEFT JOIN GOODS ON OWNER.GOODSID=GOODS.ID LEFT JOIN " +
                " IMAGE ON GOODS.ID=IMAGE.GOODSID WHERE USERID=:ID ORDER BY DATETIME DESC ";
        return this.executeSelect(sql, new MapSqlParameterSource().addValue("ID", ID));
    }

    public List<GoodsDO> getBySellerID(final String ID) {
        final String sql = " SELECT GOODS.GOODSID,NAME,DESCRIPTION,PRICE,KIND,STATUS,ATTTENTIONDEGREE,DATETIME," +
                " CREATEDATE,COLOR,LENGTH,WIDTH,HEIGHT,URL FROM ORDER LEFT JOIN GOODS LEFT JOIN IMAGE WHERE " +
                " SELLERID:=ID ORDER BY ORDERDATETIME DESC ";
        return this.executeSelect(sql, new MapSqlParameterSource().addValue("ID", ID));
    }

    public List<GoodsDO> getByBuyerID(final String ID) {
        final String sql = " SELECT GOODS.GOODSID,NAME,DESCRIPTION,PRICE,KIND,ATTTENTIONDEGREE,DATETIME,CREATEDATE," +
                " COLOR,LENGTH,WIDTH,HEIGHT,URL FROM ORDER LEFT JOIN GOODS LEFT JOIN IMAGE WHERE BUYERID:=ID " +
                " ORDER BY ORDERDATETME DESC ";
        return this.executeSelect(sql, new MapSqlParameterSource().addValue("ID", ID));
    }

    public List<GoodsDO> getByAttentionID(final String ID) {
        String sql = " SELECT GOODS.ID,NAME,DESCRIPTION,PRICE,KIND,STATUS,ATTENTIONDEGREE,DATETIME,CREATEDATE," +
                " COLOR,LENGTH,WIDTH,HEIGHT,URL FROM  GOODS LEFT JOIN ATTENTION ON ATTENTION.GOODSID=GOODS.ID " +
                " LEFT JOIN IMAGE ON GOODS.ID=IMAGE.GOODSID WHERE ATTENTION.USERID=:ID ORDER BY GOODS.DATETIME DESC ";
        return this.executeSelect(sql, new MapSqlParameterSource().addValue("ID", ID));
    }

    public List<GoodsDO> getAll() {
        final String sql = " SELECT ID,NAME,DESCRIPTION,PRICE,KIND,STATUS,ATTTENTIONDEGREE,DATETIME,CREATEDATE," +
                " COLOR,LENGTH,WIDTH,HEIGHT,URL FROM GOODS LEFT JOIN IMAGE ORDER BY DATETIME DESC ";
        return this.executeSelect(sql);
    }

    public List<GoodsDO> getTop5(String userID) {
        final String sql = " SELECT ID,NAME,DESCRIPTION,PRICE,KIND,STATUS,ATTENTIONDEGREE,DATETIME,CREATEDATE," +
                " COLOR,LENGTH,WIDTH,HEIGHT,URL FROM GOODS LEFT JOIN IMAGE ON ID=GOODSID " +
                " LEFT JOIN OWNER ON ID = OWNER.GOODSID WHERE OWNER.USERID <> '" + userID +
                "' AND STATUS <> 'sold' ORDER BY ATTENTIONDEGREE DESC, DATETIME DESC LIMIT 0,5 ";
        return this.executeSelect(sql);
    }

    //1.默认排序，无地区无种类
    public List<GoodsDO> getGoods(final boolean isInit, final int page, final String userID, String text) {
        final String sql = this.generateSelectSql(isInit, page, null, null, null, SortKind.NONE, userID, text);
        return this.executeSelect(sql);
    }

    //2.默认排序，无地区有种类
    public List<GoodsDO> getGoods(final GoodsKind kind, final boolean isInit, final int page, final String userID, String text) {
        String sql = this.generateSelectSql(isInit, page, null, kind, null, SortKind.NONE, userID, text);
        return this.executeSelect(sql);
    }

    //3.默认排序，有地区无种类
    public List<GoodsDO> getGoods(final String region, final boolean isInit, final int page, final String userID, String text) {
        final String sql = this.generateSelectSql(isInit, page, region, null, null, SortKind.NONE, userID, text);
        return this.executeSelect(sql);
    }

    //4.默认排序，有地区有种类
    public List<GoodsDO> getGoods(final String region, final GoodsKind kind, final boolean isInit, final int page
            , final String userID, String text) {
        final String sql = this.generateSelectSql(isInit, page, region, kind, null, SortKind.NONE, userID, text);
        return this.executeSelect(sql);
    }

    //5.非默认排序（升降），无地区无种类
    public List<GoodsDO> getGoods(final String orderBy, final SortKind sortKind, final boolean isInit, final int page
            , final String userID, String text) {
        String sql = this.generateSelectSql(isInit, page, null, null, orderBy, sortKind, userID, text);
        return this.executeSelect(sql);
    }

    //6.非默认排序（升降），无地区有种类
    public List<GoodsDO> getGoods(final GoodsKind kind, final String orderBy, final SortKind sortKind, final boolean isInit
            , final int page, final String userID, String text) {
        String sql = this.generateSelectSql(isInit, page, null, kind, orderBy, sortKind, userID, text);
        return this.executeSelect(sql);
    }

    //7.非默认排序（升降），有地区无种类
    public List<GoodsDO> getGoods(final String region, final String orderBy, final SortKind sortKind, final boolean isInit
            , final int page, final String userID, String text) {
        String sql = this.generateSelectSql(isInit, page, region, null, orderBy, sortKind, userID, text);
        return this.executeSelect(sql);
    }

    //8.非默认排序（升降），有地区有种类
    public List<GoodsDO> getGoods(final String region, final GoodsKind kind, final String orderBy, final SortKind sortKind
            , final boolean isInit, final int page, final String userID, String text) {
        String sql = this.generateSelectSql(isInit, page, region, kind, orderBy, sortKind, userID, text);
        return this.executeSelect(sql);
    }

    public int saveImage(final String goodsID, final String url) {
        final String sql = " INSERT INTO IMAGE VALUES (:ID,:URL) ";
        return this.namedParameterJdbcTemplate.update(sql,
                new MapSqlParameterSource().addValue("ID", goodsID).addValue("URL", url));
    }

    public int updateImage(final String goodsID, final String url) {
        final String sql = " UPDATE IMAGE SET URL=:URL WHERE GOODSID=:GOODSID ";
        return this.namedParameterJdbcTemplate.update(sql,
                new MapSqlParameterSource().addValue("GOODSID", goodsID).addValue("URL", url));
    }

    private int buildOwnerRelate(final String goodsID, final String userID) {
        final String sql = " INSERT OWNER VALUES (:USERID,:GOODSID)";
        return this.namedParameterJdbcTemplate.update(sql,
                new MapSqlParameterSource().addValue("GOODSID", goodsID).addValue("USERID", userID));
    }

    private String generateSelectSql(boolean isInit, int page, String region, GoodsKind kind, String orderBy, SortKind sortKind
            , final String userID, String text) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT GOODS.ID,GOODS.NAME,DESCRIPTION,PRICE,KIND,STATUS,ATTENTIONDEGREE,DATETIME,CREATEDATE," +
                " COLOR,LENGTH,WIDTH,HEIGHT,URL FROM GOODS LEFT JOIN IMAGE ON GOODS.ID=IMAGE.GOODSID LEFT JOIN OWNER " +
                " ON OWNER.GOODSID=GOODS.ID LEFT JOIN USER ON USER.ID= OWNER.USERID WHERE ");
        if (region != null && kind != null) {
            sql.append(" REGION='" + region + "' AND KIND='" + kind.name() + "' AND ");
        } else if (region != null && kind == null) {
            sql.append(" REGION='" + region + "' AND ");
        } else if (region == null && kind != null) {
            sql.append(" KIND='" + kind.name() + "' AND ");
        }
        sql.append(" STATUS <> '" + GoodsStatus.sold + "' AND USER.ID <> '" + userID + "' ");
        if (text != "" && text != null) {
            sql.append(" AND (GOODS.NAME like '%" + text + "%' or DESCRIPTION like '%" + text + "%') ");
        }
        if (sortKind == SortKind.NONE) {
            sql.append(" ORDER BY DATETIME DESC ");
        } else {
            sql.append(" ORDER BY " + orderBy + " " + sortKind + ", DATETIME DESC ");
        }
        if (isInit) {
            sql.append(" LIMIT 0, " + GoodsDaoImpl.PAGE_SIZE);
        } else {
            sql.append(" LIMIT " + (page - 1) * GoodsDaoImpl.PAGE_SIZE + " , " + GoodsDaoImpl.PAGE_SIZE);
        }
        return sql.toString();
    }

    private List<GoodsDO> executeSelect(final String sql) {
        final List<GoodsDO> goodsDOList = new ArrayList<GoodsDO>();
        this.namedParameterJdbcTemplate.getJdbcOperations().query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                GoodsDO goodsDO = new GoodsDO();
                goodsDO.setID(resultSet.getString("ID"));
                goodsDO.setName(resultSet.getString("NAME"));
                goodsDO.setDescription(resultSet.getString("DESCRIPTION"));
                goodsDO.setPrice(resultSet.getInt("PRICE"));
                goodsDO.setKind(resultSet.getString("KIND"));
                goodsDO.setDatetime(resultSet.getLong("DATETIME"));
                goodsDO.setStatus(resultSet.getString("STATUS"));
                goodsDO.setAttentionDegree(Integer.parseInt(resultSet.getString("ATTENTIONDEGREE")));
                goodsDO.setImageUrl(resultSet.getString("URL"));
                goodsDO.setCreateDate(resultSet.getLong("CREATEDATE"));
                goodsDO.setColor(resultSet.getString("COLOR"));
                goodsDO.setLength(resultSet.getInt("LENGTH"));
                goodsDO.setWidth(resultSet.getInt("WIDTH"));
                goodsDO.setHeight(resultSet.getInt("HEIGHT"));
                goodsDOList.add(goodsDO);
            }
        });
        return goodsDOList;
    }

    private List<GoodsDO> executeSelect(final String sql, final MapSqlParameterSource mapSqlParameterSource) {
        final List<GoodsDO> goodsDOList = new ArrayList<GoodsDO>();
        this.namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                GoodsDO goodsDO = new GoodsDO();
                goodsDO.setID(resultSet.getString("ID"));
                goodsDO.setName(resultSet.getString("NAME"));
                goodsDO.setDescription(resultSet.getString("DESCRIPTION"));
                goodsDO.setPrice(resultSet.getInt("PRICE"));
                goodsDO.setKind(resultSet.getString("KIND"));
                goodsDO.setDatetime(resultSet.getLong("DATETIME"));
                goodsDO.setStatus(resultSet.getString("STATUS"));
                goodsDO.setAttentionDegree(Integer.parseInt(resultSet.getString("ATTENTIONDEGREE")));
                goodsDO.setImageUrl(resultSet.getString("URL"));
                goodsDO.setCreateDate(resultSet.getLong("CREATEDATE"));
                goodsDO.setColor(resultSet.getString("COLOR"));
                goodsDO.setLength(resultSet.getInt("LENGTH"));
                goodsDO.setWidth(resultSet.getInt("WIDTH"));
                goodsDO.setHeight(resultSet.getInt("HEIGHT"));
                goodsDOList.add(goodsDO);
            }
        });
        return goodsDOList;
    }
}