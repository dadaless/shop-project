import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import vendure.model.Product;
import vendure.model.ProductList;
import vendure.query.ProductQuery;
import vendure.query.ProductsQuery;

import static org.junit.jupiter.api.Assertions.*;

class VendureServiceTest {
  private final ObjectMapper mapper = new ObjectMapper();

  // --- ProductsQuery: query string ---

  @Test
  void productsQuery_containsExpectedFields() {
    String q = new ProductsQuery().buildQuery();
    assertTrue(q.contains("products"));
    assertTrue(q.contains("items"));
    assertTrue(q.contains("totalItems"));
    assertTrue(q.contains("id"));
    assertTrue(q.contains("name"));
  }

  // --- ProductsQuery: JSON → ProductList ---

  @Test
  void productsQuery_parsesProductList() throws Exception {
    String json =
        """
            {
              "products": {
                "items": [
                  { "id": "1", "name": "Laptop", "slug": "laptop", "description": "A laptop" }
                ],
                "totalItems": 1
              }
            }
            """;
    JsonNode dataNode = mapper.readTree(json);
    ProductList result = new ProductsQuery().parseResult(dataNode, mapper);

    assertEquals(1, result.getTotalItems());
    assertEquals(1, result.getItems().size());
    assertEquals("Laptop", result.getItems().get(0).getName());
    assertEquals("laptop", result.getItems().get(0).getSlug());
  }

  // --- ProductQuery: query string ---

  @Test
  void productQuery_byId_containsId() {
    String q = ProductQuery.byId("42").buildQuery();
    assertTrue(q.contains("product"));
    assertTrue(q.contains("id: \"42\""));
  }

  @Test
  void productQuery_bySlug_containsSlug() {
    String q = ProductQuery.bySlug("laptop").buildQuery();
    assertTrue(q.contains("slug: \"laptop\""));
    assertFalse(q.contains("id:"));
  }

  // --- ProductQuery: JSON → Product ---

  @Test
  void productQuery_parsesProduct() throws Exception {
    String json =
        """
            {
              "product": {
                "id": "1", "name": "Laptop", "slug": "laptop", "description": "A laptop"
              }
            }
            """;
    JsonNode dataNode = mapper.readTree(json);
    Product result = ProductQuery.byId("1").parseResult(dataNode, mapper);

    assertEquals("1", result.getId());
    assertEquals("Laptop", result.getName());
    assertEquals("laptop", result.getSlug());
  }
}
