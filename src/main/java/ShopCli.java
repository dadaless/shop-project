import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import java.util.Arrays;
import java.util.List;
import vendure.VendureClient;
import vendure.query.*;

@Command(
    name = "cli",
    mixinStandardHelpOptions = true,
    subcommands = {ListCommand.class})
public class ShopCli implements Runnable {

  @Option(
      names = {"--url"},
      description = "URL for the Vendure server",
      defaultValue = "${URL:-http://localhost:3000/shop-api}")
  private String url;

  public String getUrl() {
    return url;
  }

  @Override
  public void run() {
    System.out.println("Please provide a subcommand. Use --help for more information");
  }

  public static void main(String[] args) {
    int exitCode = new CommandLine(new ShopCli()).execute(args);
    System.exit(exitCode);
  }

  public void parse(String[] args) {
    new CommandLine(this).parseArgs(args);
  }

  public ListCommand getListCommand() {
    return new ListCommand();
  }
}

@Command(name = "list", description = "List products")
class ListCommand implements Runnable {

  @CommandLine.ParentCommand private ShopCli parent;

  @Option(
      names = {"--format"},
      description = "Output format: table or json",
      defaultValue = "table")
  private String format;

  public String getFormat() {
    return format;
  }

  @Override
  public void run() {
    try {
      VendureClient client = new VendureClient(parent.getUrl());
      List<vendure.model.Product> products = client.execute(new ProductsQuery()).getItems();

      if ("json".equalsIgnoreCase(format)) {
        System.out.println("[");
        for (vendure.model.Product p : products) {
          System.out.printf(
              "  {\"id\": \"%s\", \"name\": \"%s\", \"slug\": \"%s\"}%n",
              p.getId(), p.getName(), p.getSlug());
        }
        System.out.println("]");
      } else {
        System.out.println("ID\t\tName\t\tSlug");
        System.out.println("----------------------------");
        for (vendure.model.Product p : products) {
          System.out.printf("%s\t\t%s\t\t%s%n", p.getId(), p.getName(), p.getSlug());
        }
      }
    } catch (Exception e) {
      System.err.println("Error fetching products: " + e.getMessage());
    }
  }
}
