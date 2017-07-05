package com.aaron.algorithm.hash;

import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性hash算法
 *
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2017-05-13
 */
public class AgreementHash
{
    private static final int VIRTUAL_HOST_SIZE = 1000000;

    private static final String SEPARATOR = "#";

    private static List<String> realHost = new ArrayList<>();

    private static TreeMap<Long, String> virtualHost = new TreeMap<>();


    static
    {
        realHost.add("192.130.10.101");
        realHost.add("192.130.10.201");
        realHost.add("192.130.10.301");
        realHost.add("192.130.10.401");
        realHost.add("192.130.10.501");
        realHost.add("192.130.10.701");
        realHost.add("192.130.10.801");
        realHost.add("192.130.10.901");

        for (String ip : realHost)
        {
            for (int i = 0; i < VIRTUAL_HOST_SIZE; i++)
            {
                long hashCode = (ip + SEPARATOR + i).hashCode();
                virtualHost.put(Math.abs(hashCode), ip);
            }
        }
    }


    public static void main(String[] args)
    {
        Random random = new Random();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 1000000; i++)
        {
            long hash = (random.nextInt(2109000000));

            SortedMap<Long, String> subMap = virtualHost.tailMap(hash);

            String host;
            if (MapUtils.isEmpty(subMap))
            {
                host = virtualHost.firstEntry().getValue();
            }
            else
            {
                host = subMap.get(subMap.firstKey());
            }

            if (map.containsKey(host))
            {
                map.put(host, map.get(host) + 1);
            }
            else
            {
                map.put(host, 1);
            }
            System.out.println("缓存对象[" + hash + "]的hash值=[" + hash + "],被路由到节点[" + host + "]");

        }
        System.out.println(map);
    }
}
