package com.topnetwork.common.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.base.core.context.mvc.Constants;

/**
 * @ClassName RedisLock
 * @Description
 * @Author bran
 * @Date 2020/5/14 10:28
 */
@Component("redisLock")
public class RedisLock {

//    public static final String LOCK_PREFIX = Constants.REDISPREFIX + "redis_lock:";
	public String getLockPrefix() {
		return Constants.REDISPREFIX + "redis_lock:";
	}
	
    /**
     * ms
     */
    public static final int LOCK_EXPIRE = 2000;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 最终加强分布式锁
     *
     * @param key key值
     * @return 是否获取到
     */
    public boolean lock(String key) {
        String lock = getLockPrefix() + key;
        // 利用lambda表达式
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {

            long expireAt = System.currentTimeMillis() + LOCK_EXPIRE + 1;
            Boolean acquire = connection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());


            if (acquire) {
                return true;
            } else {

                byte[] value = connection.get(lock.getBytes());

                if (Objects.nonNull(value) && value.length > 0) {

                    long expireTime = Long.parseLong(new String(value));

                    if (expireTime < System.currentTimeMillis()) {
                        // 如果锁已经过期
                        byte[] oldValue = connection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + LOCK_EXPIRE + 1).getBytes());
                        // 防止死锁
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });
    }

    /**
     * 删除锁
     *
     * @param key
     */
    public void delete(String key) {
        String lock = getLockPrefix() + key;
        redisTemplate.delete(lock);
    }

}

