package vendure.model;

import java.util.List;

public class ProductList {
  private List<Product> items;
  private int totalItems;

  public ProductList() {}

  public List<Product> getItems() {
    return items;
  }

  public void setItems(List<Product> i) {
    this.items = i;
  }

  public int getTotalItems() {
    return totalItems;
  }

  public void setTotalItems(int t) {
    this.totalItems = t;
  }
}
