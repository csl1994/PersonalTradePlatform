package com.csl.daoImpl;

import com.csl.dao.IOrderDao;
import com.csl.domain.GoodsDO;
import com.csl.domain.OrderDO;
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
 * Created by csl on 2017/3/13.
 */
@Repository
public class OrderDaoImpl implements IOrderDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int save(final OrderDO orderDO) {
        final String sql = " INSERT INTO ORDERS VALUES (:ID,:sellerID,:buyerID,:goodsID,:datetime,:sellerGrade,:buyerGrade,:sellerStatus,:buyerStatus,:sellerName,:buyerName,:goodsName) ";
        return this.namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(orderDO));
    }

    public int remove(OrderDO orderDO) {
        return 0;
    }

    public int update(OrderDO orderDO) {
        final String sql = " UPDATE ORDERS SET SELLERGRADE=:sllerGrade,BUYERGRADE=:buyerGrade,SELLERSTATUS=:sellerStatus, " +
                " BUYERSTATUS=:buyStatus ,SELLERNAME=:sellerName,BUYERNAME=:buyerName,GOODSNAME=:goodsName " +
                " WHERE ID=:ID AND SELLERID=:sellerID AND BUYERID=:buyerID AND GOODSID=:goodsID ";
        return this.namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(orderDO));
    }

    public OrderDO find(String ID) {
        final String sql = " SELECT ID,SELLERID,BUYERID,GOODSID,ORDERDATETIME,SELLERGRADE,BUYERGRADE,SELLERSTATUS,BUYERSTATUS " +
                " ,SELLERNAME,BUYERNAME,GOODSNAME FROM ORDERS WHERE ID=:ID";
        final OrderDO orderDO = new OrderDO();
        this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("ID", ID),
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        orderDO.setID(resultSet.getString("ID"));
                        orderDO.setSellerID(resultSet.getString("SELLERID"));
                        orderDO.setBuyerID(resultSet.getString("BUYERID"));
                        orderDO.setGoodsID(resultSet.getString("GOODSID"));
                        orderDO.setDatetime(resultSet.getLong("ORDERDATETIME"));
                        orderDO.setSellerGrade(resultSet.getInt("SELLERGRADE"));
                        orderDO.setBuyerGrade(resultSet.getInt("BUYERGRADE"));
                        orderDO.setSellerStatus(resultSet.getString("SELLERSTATUS"));
                        orderDO.setBuyerStatus(resultSet.getString("BUYERSTATUS"));
                        orderDO.setSellerName(resultSet.getString("SELLERNAME"));
                        orderDO.setBuyerName(resultSet.getString("BUYERNAME"));
                        orderDO.setGoodsName(resultSet.getString("GOODSNAME"));
                    }
                });
        return orderDO;
    }

    public List<OrderDO> getAll() {
        return null;
    }

    public List<OrderDO> getBySellerID(String ID) {
        final String sql = " SELECT ID,SELLERID,BUYERID,GOODSID,ORDERDATETIME,SELLERGRADE,BUYERGRADE,SELLERSTATUS, " +
                "BUYERSTATUS FROM ORDERS WHERE SELLERID=:ID AND SELLERSTATUS <> 'delete' ORDER BY ORDERDATETIME DESC ";
        final List<OrderDO> orderDOList = new ArrayList<OrderDO>();
        this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("ID", ID),
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        OrderDO orderDO = new OrderDO();
                        orderDO.setID(resultSet.getString("ID"));
                        orderDO.setSellerID(resultSet.getString("SELLERID"));
                        orderDO.setBuyerID(resultSet.getString("BUYERID"));
                        orderDO.setGoodsID(resultSet.getString("GOODSID"));
                        orderDO.setDatetime(resultSet.getLong("ORDERDATETIME"));
                        orderDO.setSellerGrade(resultSet.getInt("SELLERGRADE"));
                        orderDO.setBuyerGrade(resultSet.getInt("BUYERGRADE"));
                        orderDO.setSellerStatus(resultSet.getString("SELLERSTATUS"));
                        orderDO.setBuyerStatus(resultSet.getString("BUYERSTATUS"));
                        orderDO.setSellerName(resultSet.getString("SELLERNAME"));
                        orderDO.setBuyerName(resultSet.getString("BUYERNAME"));
                        orderDO.setGoodsName(resultSet.getString("GOODSNAME"));
                        orderDOList.add(orderDO);
                    }
                });
        return orderDOList;
    }

    public List<OrderDO> getByBuyerID(String ID) {
        final String sql = " SELECT ID,SELLERID,BUYERID,GOODSID,ORDERDATETIME,SELLERGRADE,BUYERGRADE,SELLERSTATUS,BUYERSTATUS, " +
                "SELLERNAME,BUYERNAME,GOODSNAME FROM ORDERS WHERE BUYERID=:ID AND BUYERSTATUS <> 'delete' ORDER BY ORDERDATETIME DESC ";
        final List<OrderDO> orderDOList = new ArrayList<OrderDO>();
        this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("ID", ID),
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        OrderDO orderDO = new OrderDO();
                        orderDO.setID(resultSet.getString("ID"));
                        orderDO.setSellerID(resultSet.getString("SELLERID"));
                        orderDO.setBuyerID(resultSet.getString("BUYERID"));
                        orderDO.setGoodsID(resultSet.getString("GOODSID"));
                        orderDO.setDatetime(resultSet.getLong("ORDERDATETIME"));
                        orderDO.setSellerGrade(resultSet.getInt("SELLERGRADE"));
                        orderDO.setBuyerGrade(resultSet.getInt("BUYERGRADE"));
                        orderDO.setSellerStatus(resultSet.getString("SELLERSTATUS"));
                        orderDO.setBuyerStatus(resultSet.getString("BUYERSTATUS"));
                        orderDO.setSellerName(resultSet.getString("SELLERNAME"));
                        orderDO.setBuyerName(resultSet.getString("BUYERNAME"));
                        orderDO.setGoodsName(resultSet.getString("GOODSNAME"));
                        orderDOList.add(orderDO);
                    }
                });
        return orderDOList;
    }

    public int updateSellerStatus(String ID, String status) {
        final String sql = " UPDATE ORDERS SET SELLERSTATUS='" + status + "' WHERE ID=:ID ";
        return this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValue("ID", ID));
    }

    public int updateBuyerStatus(String ID, String status) {
        final String sql = " UPDATE ORDERS SET BUYERSTATUS='" + status + "' WHERE ID=:ID ";
        return this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource().addValue("ID", ID));
    }

    public int newOrder(String sellerID) {
        final String sql = " SELECT ID FROM ORDERS WHERE SELLERID=:sellerID AND SELLERSTATUS = 'unread' ";
        final List<String> orderID = new ArrayList<String>();
        this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource().addValue("sellerID", sellerID),
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        orderID.add(resultSet.getString("ID"));
                    }
                });
        return orderID.size();
    }
}
