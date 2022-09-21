package com.itany.netclass.transaction;

/**
 * 事务接口
 *
 * @author teacher
 * @date 2018-8-22
 */
public interface TransactionManager {

	/**
	 * 开启事务
	 *
	 * @author Thou
	 * @date 2022/8/30
	 */
	public void beginTransaction();

	/**
	 * 提交事务
	 *
	 * @author Thou
	 * @date 2022/8/30
	 */
	public void commit();

	/**
	 * 回滚事务
	 *
	 * @author Thou
	 * @date 2022/8/30
	 */
	public void rollback();
}
