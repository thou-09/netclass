package com.itany.netclass.transaction.impl;

import com.itany.netclass.exception.DataAccessException;
import com.itany.netclass.transaction.TransactionManager;
import com.itany.netclass.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * mybatis的事务处理器
 *
 * @author teacher
 * @date 2018-8-22
 */
public class MyBatisTransactionManager implements TransactionManager {

	@Override
	public void beginTransaction() {
		try {
			MyBatisUtil.getSession();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("开启事务失败", e);
		}
	}

	@Override
	public void commit() {
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSession();
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("提交事务失败", e);
		} finally {
			MyBatisUtil.closeSession();
		}
	}

	@Override
	public void rollback() {
		SqlSession session = null;
		try {
			session = MyBatisUtil.getSession();
			session.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("回滚事务失败", e);
		} finally {
			MyBatisUtil.closeSession();
		}
	}
}
