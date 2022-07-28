package com.mysql;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;

public class SupplierDaoImp {

    public Supplier getSupplierByName(String name) throws Exception {
        Supplier s = new Supplier();
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = "select * from supplier where name = ?";

        return qr.query(sql, new BeanHandler<Supplier>(Supplier.class), name);
    }
}
