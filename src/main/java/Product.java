public class Product {
  private final int id;
  private final String name;
  private final double price;

  public Product(int id, String name, double price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  public int getId() {
    return id;
  }

  public double getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return super.toString();
  }

  public String toJson() {
    return String.format(
        "{\"id\": %d, \"name\": %s, \"price\": %f}", this.id, this.name, this.price);
  }

  public String toTable() {
    return String.format("%d\t\t%s\t\t%f", this.id, this.name, this.price);
  }
}
