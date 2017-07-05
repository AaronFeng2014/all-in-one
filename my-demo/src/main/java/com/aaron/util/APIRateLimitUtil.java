package com.aaron.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <p>
 * API rate limiter utility
 * </p>
 */
public class APIRateLimitUtil
{

    private static final Log log = LogFactory.getLog(APIRateLimitUtil.class);

    private static String script_id = null;

    private static JedisPool jedisPool;


    public static boolean canVisit(String key, long limit, long interval)
    {
        boolean access;
        Jedis jedis;
        try
        {
            jedis = jedisPool.getResource();
            if (script_id == null)
            {
                script_id = jedis.scriptLoad(getLuaScript());
            }
            long intervalInMills = interval * 1000;
            Object result = jedis.evalsha(script_id, 1, key, String.valueOf(System.currentTimeMillis()), String.valueOf(limit),
                                          String.valueOf(intervalInMills));
            if (null == result)
            {
                return false;
            }
            long resultLong = (Long)result;
            access = (resultLong == 1);
        }
        catch (Exception e)
        {
            log.error("从redis中查询访问次数失败，原因是：", e);
            return true;
        }
        finally
        {
            //RedisConnectionUtil.closeConnection(jedis);
        }
        return access;
    }


    private static String getLuaScript() throws IOException
    {
        InputStream inputStream = APIRateLimitUtil.class.getResourceAsStream("/rataLimit.lua");
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        StringBuilder stringBuffer = new StringBuilder();
        while (line != null)
        {
            stringBuffer.append(line);
            line = bufferedReader.readLine();
        }
        return stringBuffer.toString();
    }


    public void setJedisPool(JedisPool jedisPool)
    {
        APIRateLimitUtil.jedisPool = jedisPool;
    }
}
