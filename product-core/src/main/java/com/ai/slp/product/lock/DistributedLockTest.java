package com.ai.slp.product.lock;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * <ul>
 * <li> <b>目的:</b> <br />
 * <p>
 * </p>
 * </li>
 * <li><b>注意事项：</b></li>
 * <li><b>修改历史：</b><br />
 * <p>
 * 创建: 2017年4月5日 下午5:00:27<br />
 * 作者: <a herf="mailto:284654376@qq.com">周志明</a>
 * </p>
 * </li>
 * <li><b>已知问题：</b></li>
 * </ul>
 */
public class DistributedLockTest {
	private static int count = 0;
//	private static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) throws IOException, InterruptedException {
//		testLock();
		testTryLock();
	}

	private static void testLock() {
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					ZookeeperLock lock = new ZookeeperLock(
							"127.0.0.1:2181", "block");
					try {
						lock.lock();
						System.out.println(Thread.currentThread().getName() + "在执行" + (count++));
//						Thread.sleep(300);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					finally {
						lock.unlock();
					}
				}
			}.start();
		}
	}
	
	private static void testTryLock() {
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					try {
						ZookeeperLock lock = new ZookeeperLock(
								"127.0.0.1:2181", "unblock");
						if (lock.tryLock()) {
							try{
								System.out.println(Thread.currentThread().getName() + "在执行" + (count++));
//								Thread.sleep(1000);
							}finally {
								lock.unlock();
							}
						}else{
							System.out.println("can not trylock");
						}
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
	}
}
