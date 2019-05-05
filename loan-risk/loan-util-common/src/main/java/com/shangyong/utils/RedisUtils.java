
package com.shangyong.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

/**
 * redis操作工具类
 * @author zhangze
 *
 */
@Component
public class RedisUtils {

    static JedisPool jedisPool;

    @Autowired(required = true)
    public void setJedisPool(JedisPool jedisPool) {
    	RedisUtils.jedisPool = jedisPool;
	}

	public static Jedis getResource() {
        return jedisPool.getResource();
    }

    @SuppressWarnings("deprecation")
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResourceObject(jedis);
        }
    }
    
    @SuppressWarnings("deprecation")
    public static void returnBrokenResource(Jedis jedis) {
    	if (jedis != null) {
            jedisPool.returnBrokenResource(jedis);
        }
    }
    
	/**
	 * 删除key
	 * @param key 	于Redis中的key
	 */
	public void batchDel(String pre_str) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			Set<String> set = jedis.keys(pre_str +"*");  
	        Iterator<String> it = set.iterator();  
	        while (it.hasNext()) {  
	            String keyStr = it.next();  
	            System.out.println(keyStr);  
	            jedis.del(keyStr);  
	        }  
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}
	}
    
    /**
	 * 删除key
	 * @param key 	于Redis中的key
	 */
	public static void del(String key) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			// 获取redis中的value
			jedis.del(key);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	/**
	 * 判断key是否存在
	 * @param key 	于Redis中的key
	 */
	public static Boolean exists(String key) {
		Jedis jedis = null;
		Boolean flag = false;
		try {
			jedis = getResource();
			// 获取redis中的value
			flag = jedis.exists(key);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return flag;
	}
	
	/**
	 * 判断名称为key的hash中是否存在键为field的域(redis map操作)
	 * @param key 	于Redis中的key
	 * @param field 于Redis Map中的key
	 */
	public static Boolean hexists(String key, String field) {
		Jedis jedis = null;
		Boolean flag = false;
		try {
			jedis = getResource();
			// 获取redis中的value
			flag = jedis.hexists(key, field);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return flag;
	}
	
	/**
	 * 将 value 的值写入 Redis Map中(redis map操作)
	 * HSET 将哈希表 key 中的域 field 的值设为 value 。如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。如果域 field 已经存在于哈希表中，旧值将被覆盖。
	 * 如果 field 是哈希表中的一个新建域，并且值设置成功，返回 1 。如果哈希表中域 field 已经存在且旧值已被新值覆盖，返回 0 。
	 * @param key   于Redis中的key
	 * @param field 于Redis Map中的key
	 * @param value 存储数据
	 * 			
	 */
	public static void hset(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			// 获取redis中的value
			jedis.hset(key, field, value);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	/**
	 * 将 value 的值写入 Redis Map中(redis map操作)
	 * HSETNX 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。若域 field 已经存在，该操作无效。如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。
	 * 设置成功，返回 1 。如果给定域已经存在且没有操作被执行，返回 0.
	 * @param key     于Redis中的key
	 * @param field   于Redis Map中的key
	 * @param value   存储数据
	 * @param seconds 失效时间
	 */
	public static void hsetEx(String key, String field, String value, int seconds) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			// 获取redis中的value
			jedis.hset(key, field, value);
			jedis.expire(key, seconds);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	/**
	 * 删除HSETNX 将哈希表 key 中的域 field 的值
	 * @param key
	 * @param field
	 * @param value
	 * @param seconds
	 */
	public static void hdelEx(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			// 获取redis中的value
			jedis.hdel(key, field);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	/**
	 * 将 value 的值写入 Redis Map中(redis map操作) 永久有效
	 * HSETNX 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。若域 field 已经存在，该操作无效。如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。
	 * 设置成功，返回 1 。如果给定域已经存在且没有操作被执行，返回 0.
	 * @param key     于Redis中的key
	 * @param field   于Redis Map中的key
	 * @param value   存储数据
	 */
	public static void hsetEx(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			// 获取redis中的value
			jedis.hset(key, field, value);
			//移除给定key的生存时间(设置这个key永不过期)
			jedis.persist(key); 
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	 /**
	  * 返回哈希表key中所有域和值(redis map操作)
	  * @param key 于Redis中的key
	  * @return key对应的Map对象
	  */
	public static Map<String, String> hgetAll(String key) {
		Jedis jedis = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			jedis = getResource();
			// 获取redis中的value
			map = jedis.hgetAll(key);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return map;
	}
	
	
	
	/**
	 * 返回名称为key的hash中field对应的value(redis map操作)
	 * @param key 	于Redis中的key
	 * @param field 于Redis Map中的key
	 */
	public static String hget(String key, String field) {
		Jedis jedis = null;
		String value = "";
		try {
			jedis = getResource();
			// 获取redis中的value
			value = jedis.hget(key, field);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * 返回名称为key的hash中field对应的value(redis map操作)
	 * @param key 	于Redis中的key
	 * @param fields 于Redis Map中的key(可以传入数组)
	 */
	public static List<String> hmget(String key, String... fields) {
		Jedis jedis = null;
		List<String> value = null;
		try {
			jedis = getResource();
			// 获取redis中的value
			value = jedis.hmget(key, fields);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * 将数据写入redis List(String 类型的操作)
	 * @param key 	于Redis中的key
	 * @param values 存储数据
	 */
	public static void lpush(String key, String... values) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.lpush(key, values);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	/**
	 * 将数据写入redis List(String 类型的操作)
	 * @param key 		于Redis中的key
	 * @param seconds	有效期时间
	 * @param values 	存储数据
	 */
	public static void lpushEx(String key, int seconds, String... values) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.lpush(key, values);
			jedis.expire(key, seconds);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	/**
	 * 获取redis中 List(String 类型的操作)
	 * @param key 		于Redis中的key
	 * @param start		开始索引
	 * @param end 		结束索引
	 */
	public static List<String> lrange(String key, long start, long end) {
		Jedis jedis = null;
		List<String> values = null;
		try {
			jedis = getResource();
			values = jedis.lrange(key, start, end);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return values;
	}
	
	/**
	 * 获取redis中 List指定索引的值(String 类型的操作)
	 * @param key 		于Redis中的key
	 * @param index		索引
	 */
	public static String lindex(String key, long index) {
		Jedis jedis = null;
		String value = null;
		try {
			jedis = getResource();
			value = jedis.lindex(key, index);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * 获取redis中 List的长度(String 类型的操作)
	 * @param key 		于Redis中的key
	 */
	public static long llen(String key) {
		Jedis jedis = null;
		long length = -1;
		try {
			jedis = getResource();
			length = jedis.llen(key);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return length;
	}
	
	/**
	 * 将数据写入redis(String 类型的操作)
	 * @param key 	于Redis中的key
	 * @param value 存储数据
	 */
	public static void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.set(key, value);
		} catch (Throwable e) {
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	/**
	 * 将数据写入redis并设置失效日期(String 类型的操作)
	 * @param key 	     于Redis中的key
	 * @param seconds 超时时间（单位：秒）
	 * @param value	     存储数据
	 */
	public static void setEx(String key, int seconds, String value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.setex(key, seconds, value);
		} catch (Throwable e) {
			
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	/**
	 * 从redis中读取数据(String 类型的操作)
	 * @param key 	于Redis中的key
	 * @param value 存储数据
	 */
	public static String get(String key) {
		Jedis jedis = null;
		String value = "";
		try {
			jedis = getResource();
			// 获取redis中的value
			value = jedis.get(key);
		} catch (Throwable e) {
			
			// 异常情况释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			// 返还到连接池
			returnResource(jedis);
		}
		return value;
	}
	
	
	/**
	 * 获取集合数据
	 * @param key
	 * @return
	 */
	public static Set<String> keysList(String key){
		Jedis jedis = null;
		Set<String> result = null;
		try {
			jedis = getResource();
			result = jedis.keys(key);
		} catch (Exception e) {
			// 释放redis对象
			returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}
		return result;
	}

}
