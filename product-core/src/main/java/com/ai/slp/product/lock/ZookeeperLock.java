package com.ai.slp.product.lock;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


/**
 * <ul>
 * <li> <b>目的:基于Zookeeper设计的分布式锁</b> <br />
 * <p>
 * </p>
 * </li>
 * <li><b>注意事项：</b></li>
 * <li><b>修改历史：</b><br />
 * <p>
 * 创建: 2017年4月5日 下午5:00:37<br />
 * 作者: <a herf="mailto:284654376@qq.com">周志明</a>
 * </p>
 * </li>
 * <li><b>已知问题：</b></li>
 * </ul>
 */
public class ZookeeperLock {
	private final Logger logger = LoggerFactory.getLogger(ZookeeperLock.class);
	private String root = "/lock-";
	private CountDownLatch countDownLatch = new CountDownLatch(1);

	private ZooKeeper zooKeeper;
	private String myPath;
	
	private int connectTimeout = 5000;
	private long lockTimeout = 30000;
	private int lockInterval = 10;

	public ZookeeperLock(String address, String lockName) {
		this(address, lockName,5000, 30000, 10);
	}
	
	public ZookeeperLock(String address, String lockName, int connectTimeout,long lockTimeout, int lockInterval) {
		this.connectTimeout = connectTimeout;
		this.lockTimeout = lockTimeout;
		this.lockInterval = lockInterval;
		if (StringUtils.isEmpty(address)) {
			throw new RuntimeException("zookeeper address can not be empty!");
		}
		if (StringUtils.isEmpty(lockName)) {
			throw new RuntimeException("lockName can not be empty!");
		}
		zooKeeper = connectServer(address);
		if (zooKeeper != null) {
			root += lockName;
			try {
				Stat stat = zooKeeper.exists(root, false);
				if (stat == null) {
					zooKeeper.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				}
			}
			catch (KeeperException e) {
				throw new RuntimeException(e);
			}
			catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}
	}

	/**
	 * 获取锁
	 *
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public void lock() {
		try {
			myPath = zooKeeper.create(root + "/lock_", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE,
					CreateMode.EPHEMERAL_SEQUENTIAL);
			judgeLock();
		}
		catch (KeeperException e) {
			logger.error(e.getMessage(), e);
		}
		catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 判断是否能够拥有该锁
	 *
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	private void judgeLock() throws KeeperException, InterruptedException {
		List<String> list = zooKeeper.getChildren(root, false);
		String[] nodes = list.toArray(new String[list.size()]);
		Arrays.sort(nodes);// 从小到大排序
		if (nodes.length > 0) {
			if (!myPath.equals(root + "/" + nodes[0])) {
				waitForLock(nodes[0]);
			}
			else {
				countDownLatch.countDown();
			}
		}
		else {
			countDownLatch.countDown();
		}
	}

	/**
	 * 等待锁(写法1)
	 *
	 * @param nodePath
	 * @throws InterruptedException
	 * @throws KeeperException
	 */
	private void waitForLock(String nodePath) throws InterruptedException, KeeperException {
		do{
			Stat stat = zooKeeper.exists(root + "/" + nodePath, false);
			if (stat == null) {
				judgeLock();
				break;
			}
			Thread.sleep(lockInterval);
			lockTimeout = lockTimeout - lockInterval;
			if(lockTimeout<=0){
				throw new RuntimeException(Thread.currentThread().getName()+"wartForLock timeout...");
			}
		}while(true);
		
	}

	/**
	 * 释放锁
	 */
	public void unlock() {
		if (StringUtils.isEmpty(myPath)) {
			logger.error("no need to unlock!");
		}
		try {
			zooKeeper.delete(myPath, -1);
		}
		catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
		catch (KeeperException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 尝试获得锁，能获得就立马获得锁并返回true，如果不能获得就立马返回false
	 *
	 * @return
	 */
	public boolean tryLock() {
		try {
			myPath = zooKeeper.create(root + "/lock_", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE,
					CreateMode.EPHEMERAL_SEQUENTIAL);
			List<String> list = zooKeeper.getChildren(root, false);
			String[] nodes = list.toArray(new String[list.size()]);
			Arrays.sort(nodes);// 从小到大排序
			if (myPath.equals(root + "/" + nodes[0])) {
				return true;
			}
			this.unlock();
		}
		catch (KeeperException e) {
			logger.error(e.getMessage(), e);
		}
		catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * 连接zookeeper服务器
	 *
	 * @param address
	 * @return
	 */
	private ZooKeeper connectServer(String address) {
		final CountDownLatch latch = new CountDownLatch(1);
		ZooKeeper zk = null;
		try {
			zk = new ZooKeeper(address, connectTimeout, new Watcher() {
				@Override
				public void process(WatchedEvent event) {
					if (event.getState() == Event.KeeperState.SyncConnected) {
						latch.countDown();
					}
				}
			});
			latch.await();
		}
		catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
		return zk;
	}
}
