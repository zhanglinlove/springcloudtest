package com.springtest.spring_redission.config;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedissonLock {

	@Autowired
	private Redisson redisson;
	
	/**强制解锁时间设置*/
    private long LOCK_TIME = 2L;
    /**等待时间**/
    private long WAIT_TIME = 3L;
    /**休眠时间**/
    private long SLEEP_TIME = 100L;
    
    public boolean tryLock(String lockName) {
    	RLock lock = redisson.getLock(lockName);
    	try {
			return lock.tryLock(LOCK_TIME, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public void lock(String lockName) {
    	RLock lock = redisson.getLock(lockName);
    	lock.lock(LOCK_TIME, TimeUnit.SECONDS);
    }
    
    public void unlock(String lockName) {
    	RLock lock = redisson.getLock(lockName);
    	lock.unlock();
    }
}
