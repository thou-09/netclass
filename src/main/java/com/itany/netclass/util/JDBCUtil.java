package com.itany.netclass.util;

import com.itany.netclass.exception.DataAccessException;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {

    private static ThreadLocal<Connection> local;
    private static DataSource ds;

    static{
        local = new ThreadLocal<Connection>();
        Properties p = new Properties();
        try {
            // 可以通过类加载路径进行查找,便于跨平台
            p.load(JDBCUtil.class.getClassLoader().getResourceAsStream("datasource.properties"));
            ds = BasicDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("JDBCUtil初始化失败");
        }
    }


    /**
     * 获取连接,单例的连接
     * @return
     * @throws DataAccessException
     */
    public static Connection getConnection() throws DataAccessException {
        // 从数据库连接池中获取连接
        Connection conn = local.get();
        try {
            // 如果数据库连接池中没有连接
            if(null == conn){
                // 开启一个新的连接
                conn = ds.getConnection();
                // 并将开启的连接存放在数据库连接池中
                // 便于下次使用
                local.set(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("获取连接失败");
        }
        return conn;
    }

    /**
     * 释放连接
     * 释放dao操作完成之后的连接
     * 由于事务操作中仍然需要使用连接
     * 因此,此处的释放只释放状态集与结果集
     * @param ps
     * @param rs
     */
    public static void close(PreparedStatement ps, ResultSet rs) throws DataAccessException {
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                throw new DataAccessException("释放状态集失败");
            }
        }
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("释放结果集失败");
            }
        }
    }

    /**
     * 释放连接
     * 事务操作完成之后,释放连接
     * 同时释移除据库连接池中的连接
     * 便于下次业务开启新的连接
     * 不再与上一次连接存在关联
     */
    public static void close() throws DataAccessException {
        // 获取此时数据库连接池中的连接
        Connection conn = local.get();
        // 如果没有获取到连接,说明已经释放了,则不需要再次释放连接
        try {
            // 如果获取到了,则需要手动进行释放
            if(conn != null){
                // 则释放连接
                conn.close();
                // 且移除数据库连接池中的连接
                local.remove();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("释放连接失败");
        }
    }
}
