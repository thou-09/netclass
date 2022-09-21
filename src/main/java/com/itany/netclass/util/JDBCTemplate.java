package com.itany.netclass.util;

import com.itany.netclass.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JDBCTemplate {

    public List query(String sql, RowMapper rm, Object... params) throws DataAccessException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            // 设置参数
            for(int i = 0; i < params.length; i++){
                // 在设置参数的时候,根据参数类型的不同,使用不同的方法
                // 可以判断当前参数的类型,效率较高
                // 需要判断可能出现的所有类型,比较麻烦
//                if(params[i] instanceof String){
//                    ps.setString(i+1,(String)params[i]);
//                }
                // 也可以直接将所有的参数当做Object类型来实现,效率较低
                ps.setObject(i+1,params[i]);
            }
            // 获取结果集
            rs = ps.executeQuery();
            // 将结果集映射成具体的对象,考虑对象与集合
            // 对象可以当成对象集合来实现
            // 只是集合中只有一个对象
            while(rs.next()){
                Object obj = rm.mapRow(rs);
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("数据访问异常");
        } finally {
            JDBCUtil.close(ps,rs);
        }
        return list;
    }


    public void update(String sql,Object... params) throws DataAccessException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for(int i = 0; i < params.length; i++){
                ps.setObject(i+1,params[i]);
            }
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("数据保存异常");
        } finally {
            JDBCUtil.close(ps,null);
        }
    }

}
