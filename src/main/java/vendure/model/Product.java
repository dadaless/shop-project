package vendure.model;

public class Product {
  private String id;
  private String name;
  private String slug;
  private String description;

  public Product() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String n) {
    this.name = n;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String s) {
    this.slug = s;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String d) {
    this.description = d;
  }
}
