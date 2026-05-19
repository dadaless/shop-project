package vendure.query;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import vendure.GraphQlQuery;
import vendure.model.Product;

public class ProductQuery extends GraphQlQuery<Product> {
  private final String id;
  private final String slug;

  private ProductQuery(String id, String slug) {
    this.id = id;
    this.slug = slug;
  }

  public static ProductQuery byId(String id) {
    return new ProductQuery(id, null);
  }

  public static ProductQuery bySlug(String slug) {
    return new ProductQuery(null, slug);
  }

  @Override
  public String buildQuery() {
    String arg = id != null ? "id: \"" + id + "\"" : "slug: \"" + slug + "\"";
    return "query { product(" + arg + ") { id name slug description } }";
  }

  @Override
  public Product parseResult(JsonNode dataNode, ObjectMapper mapper) throws Exception {
    return mapper.treeToValue(dataNode.get("product"), Product.class);
  }
}
