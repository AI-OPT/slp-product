package com.ai.slp.product.lock;

/**
 * <ul>
 * <li> <b>目的:分布式锁</b> <br />
 * <p>
 * </p>
 * </li>
 * <li><b>注意事项：</b></li>
 * <li><b>修改历史：</b><br />
 * <p>
 * 创建: 2017年4月5日 下午5:00:07<br />
 * 作者: <a herf="mailto:284654376@qq.com">周志明</a>
 * </p>
 * </li>
 * <li><b>已知问题：</b></li>
 * </ul>
 */
public interface DistributedLock {
	/**
	 * 释放锁
	 */
	public void unlock();

	/**
	 * 尝试获得锁，能获得就立马获得锁，如果不能获得就立马返回
	 */
	public boolean tryLock();

	/**
	 * 尝试获得锁，一直阻塞，直到获得锁为止
	 */
	public void lock();
}
