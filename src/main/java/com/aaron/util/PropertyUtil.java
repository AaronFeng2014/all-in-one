package com.aaron.util;

import com.baidu.disconf.core.common.utils.ClassLoaderUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;

/**
 * 读取jdbc.properties里面的配置，必须在src目录下存在该文件，否则异常
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-10-24
 */
public final class PropertyUtil
{
    private static final Log LOG = LogFactory.getLog(PropertyUtil.class);

    private static final String[] PROPERTY_PATH = {"emailConfig.properties", "jdbc.properties", "exception-alert-config.properties"};

    private static final Properties JDBC_PROPERTIES;


    static
    {
        JDBC_PROPERTIES = new Properties();

        readProperties();
    }


    private PropertyUtil()
    {

    }


    private static void readProperties()
    {
        for (String propertyFile : PROPERTY_PATH)
        {

            try
            {
                URL resource = ClassLoaderUtil.getLoader().getResource(propertyFile);

                if (resource == null)
                {
                    resource = PropertyUtil.class.getResource(propertyFile);
                }
                if (resource == null)
                {
                    LOG.error("配置文件" + propertyFile + "在classPath未找到");
                    continue;
                }

                InputStream inputStream = resource.openStream();

                JDBC_PROPERTIES.load(inputStream);
            }
            catch (IOException e)
            {
                LOG.error("读取配置文件jdbc.properties出错", e);
            }
        }
    }


    /**
     * 根据key获取value,该方法经过优化，可以获取{}形式，保存在数据库中的value
     *
     * @param key String：取值的key
     * @return String：key对应的value，如不存在key，则返回空串
     */
    public static String getProperty(String key)
    {
        return getProperty(key, "");
    }


    /**
     * 根据key获取value,该方法经过优化，可以获取{}形式，保存在数据库中的value
     *
     * @param key String：取值的key
     * @param defaultValue String：key对应的value，如不存在key，则返回指定的字符串
     * @return String：key对应的value，如不存在key，则返回指定的字符串
     */
    public static String getProperty(String key, String defaultValue)
    {
        return JDBC_PROPERTIES.getProperty(key, defaultValue);
    }
}
