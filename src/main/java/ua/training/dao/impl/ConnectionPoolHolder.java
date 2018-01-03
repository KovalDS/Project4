package ua.training.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;
    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:mysql://localhost:3306/project4db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false");
                    ds.setUsername("root");
                    ds.setPassword("toor");
                    ds.setMinIdle(30);
                    ds.setMaxIdle(50);
                    ds.setMaxOpenPreparedStatements(10000);
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }


}
