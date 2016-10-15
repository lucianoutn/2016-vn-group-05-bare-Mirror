package F5;

import java.util.Dictionary;

import redis.clients.jedis.Jedis;

public final class KvsCache {

	public static void save(String key, String value ){
		Jedis jedis = new Jedis("localhost"); 
		jedis.set(key, value);
	}
	
	public static String get(String key){
		Jedis jedis = new Jedis("localhost"); 
		String value = jedis.get(key);
		return value;
	}
	
}
