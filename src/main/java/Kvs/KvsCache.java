 package Kvs;
 
 import java.util.Dictionary;
 
 import redis.clients.jedis.Jedis;
 
 public final class KvsCache {
 
	 
	 public static int trues = 0;
	 
	 private static Jedis jedis;
	 public static Jedis getJedis(){
		 if(jedis == null){
			 jedis =  new Jedis("localhost");
			 jedis.flushAll();
		 }
		  
		 return jedis;
	 }
	 
 	public static void save(String key, String value ){
 		 
 		getJedis().set(key, value);
 	}
 	
 	public static boolean get(String key){
 	
 		String value = getJedis().get(key);
 		
 		if (value != null && value.equals("true")){
 			trues = trues + 1;
 			
 			return true;
 		}
 		return false;
 					
 		
 	}

	public static void clear() {
		if (jedis != null){
			jedis.flushAll();
			jedis = null;
			trues = 0;
		}
		
	}
 	
 }