package com.csl.daoImpl;

import com.csl.dao.IOrderDao;
import com.csl.domain.OrderDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by csl on 2017/3/13.
 */
@Repository
public class OrderDaoImpl implements IOrderDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int save(final OrderDO orderDO) {
        final String sql = " INSERT INTO ORDER VALUES (:ID,:BUYERID,:GOODSID,:DATETIME,:COMMENT,:SELLERLEVEL,:BUYERLEVEL) ";
        return this.namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(orderDO));
    }

    public int remove(OrderDO orderDO) {
        return 0;
    }

    public int update(OrderDO orderDO) {
        return 0;
    }

    public OrderDO find(String ID) {
        return null;
    }

    public List<OrderDO> getAll() {
        return null;
    }

    public List<OrderDO> getBySellerID(String ID) {
        return null;
    }

    public List<OrderDO> getByBuyerID(String ID) {
        return null;
    }
}
