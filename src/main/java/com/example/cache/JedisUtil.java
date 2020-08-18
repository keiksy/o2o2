package com.example.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.SafeEncoder;

import java.util.Set;

public class JedisUtil {
    public Keys KEYS;
    public String STRINGS;
    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPoolWriper jedisPoolWriper) {
        this.jedisPool = jedisPoolWriper.getJedisPool();
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public class Keys {

        public String flushAll() {
            Jedis jedis = getJedis();
            String sdata = jedis.flushAll();
            jedis.close();
            return sdata;
        }

        public long del(String... keys) {
            Jedis jedis = getJedis();
            long count = jedis.del(keys);
            jedis.close();
            return count;
        }

        public boolean exists(String key) {
            Jedis sjedis = getJedis();
            boolean exis = sjedis.exists(key);
            sjedis.close();
            return exis;
        }

        public Set<String> keys(String pattern) {
            Jedis jedis = getJedis();
            Set<String> set = jedis.keys(pattern);
            jedis.close();
            return set;
        }
    }

    public class Strings {

        public String get(String key) {
            Jedis sjedis = getJedis();
            String value = sjedis.get(key);
            sjedis.close();
            return value;
        }

        public String set(byte[] key, byte[] value) {
            Jedis jedis = getJedis();
            String status = jedis.set(key, value);
            jedis.close();
            return status;
        }

        public String set(String key, String value) {
            return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }
    }
}


