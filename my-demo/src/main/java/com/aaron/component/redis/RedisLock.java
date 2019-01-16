package com.aaron.component.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2018-12-12
 */
public final class RedisLock
{

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLock.class);

    private static final long DEFAULT_ACQUIRE_TIMEOUT = 1000L;

    private static final long LOCK_KEY__TIMEOUT = 5000L;

    private static RedisTemplate<String, Object> redisTemplate;


    private RedisLock()
    {

    }


    /**
     * redis分布式锁，支持重入特性
     *
     * @return 获取锁成功时返回true，否则返回false
     */
    public static boolean lock(String lockKey)
    {
        return lock(lockKey, DEFAULT_ACQUIRE_TIMEOUT, LOCK_KEY__TIMEOUT);
    }


    /**
     * redis分布式锁，支持重入特性，支持不同线程获取
     *
     * @param lockKey String：生成锁的资源id
     * @param acquireTimeout Long：获取锁这一动作的超时时间，单位是毫秒，超过时间还没有获取到锁，将返回false
     * @param timeout Long：锁的超时时间，单位是毫秒，超过这个时间自动释放锁
     *
     * @return 获取成功返回true，其他返回false
     */
    public static boolean lock(String lockKey, long acquireTimeout, long timeout)
    {
        /**
         * 当前线程id，用于计算重入
         */
        long currenntThreadId = Thread.currentThread().getId();

        long beginTimeMills = System.currentTimeMillis();

        /**
         * 当前时间-进入时间 小于超时时间，就继续循环获取锁
         */
        while (System.currentTimeMillis() - beginTimeMills <= acquireTimeout)
        {

            //redisTemplate.opsForHash().get;

            Map<String, String> entries = redisTemplate.<String, String>opsForHash().entries(lockKey);

            //锁持有者
            String owner = entries.get("owner");

            //加锁次数
            String state = entries.get("state");

            //过期时间
            String expiredTime = entries.get("expiredTime");

            String createTime = entries.get("createTime");

            if (false)
            {
                return true;
            }
        }

        LOGGER.warn("获取锁超时失败，实际使用时间：{}， 期望最大时间：{}", System.currentTimeMillis() - beginTimeMills, acquireTimeout);

        return false;
    }


    /**
     * 释放锁
     *
     * @param lockKey String：生成锁的资源id
     */
    public static void releaseLock(String lockKey)
    {
        try
        {
            redisTemplate.delete(lockKey);
        }
        catch (Exception e)
        {
            LOGGER.error("释放锁失败，lockKey：{}", lockKey);
        }
    }
}