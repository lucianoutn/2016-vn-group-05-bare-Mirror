package Mdb;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.Function;

import org.bson.Document;

public class DocumentBuilder {

  private HashMap<String, Object> map = new HashMap<>();

  public DocumentBuilder with(String key, Object value) {
    map.put(key, value);
    return this;
  }

  public <A> DocumentBuilder with(String key, A value,
      Function<A, ? extends Object> mapper) {
    map.put(key, mapper.apply(value));
    return this;
  }

  public <A> DocumentBuilder withList(String key, Collection<A> values,
      Function<A, ? extends Object> mapper) {
    map.put(key, values.stream().map(mapper).collect(toList()));
    return this;
  }

  public Document build() {
    return new Document(map);
  }

}