package vendure.query;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import vendure.GraphQlQuery;
import vendure.model.ProductList;

public class ProductsQuery extends GraphQlQuery<ProductList> {

  @Override
  public String buildQuery() {
    return "query { products { items { id name slug description } totalItems } }";
  }

  @Override
  public ProductList parseResult(JsonNode dataNode, ObjectMapper mapper) throws Exception {
    return mapper.treeToValue(dataNode.get("products"), ProductList.class);
  }
}
