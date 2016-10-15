package F5.Newton_juega_con_Redis;

import java.util.Dictionary;

import redis.clients.jedis.Jedis;
public class TestJedis {
   
	public static void main(String[] args) {
      //Connecting to Redis server on localhost
      Jedis jedis = new Jedis("localhost");
      System.out.println("Connection to server sucessfully");
      //check whether server is running or not
      jedis.set("1", "Hola Antonio");
      System.out.println(jedis.get("1"));
      
      
      while(true){
    	  Dictionary<K, V>
      }
      
      
	}  
   

}