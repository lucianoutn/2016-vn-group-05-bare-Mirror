 package Kvs;
 
 import java.util.Dictionary;
 
 import redis.clients.jedis.Jedis;
 
 public final class KvsCache {
 
	 private static Jedis jedis;
	 public static Jedis getJedis(){
		 if(jedis == null)
			 return new Jedis("localhost");
		 return jedis;
	 }
	 
 	public static void save(String key, String value ){
 		 
 		getJedis().set(key, value);
 	}
 	
 	public static String get(String key){
 	
 		String value = getJedis().get(key);
 	
 		return value;
 	}
 	
 }