package vendure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class GraphQlQuery<T> {
  public abstract String buildQuery();

  public abstract T parseResult(JsonNode dataNode, ObjectMapper mapper) throws Exception;
}
