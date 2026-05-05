
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import java.util.Arrays;
import java.util.List;

@Command(name="cli", mixinStandardHelpOptions = true, subcommands = {ListCommand.class})
public class ShopCli implements Runnable{

    @Option(names = {"--url"}, description = "URL for the Vendure server", defaultValue = "${URL:-http://localhost:3000/shop-api}")
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

    @Option(names = {"--format"}, description = "Output format: table or json", defaultValue = "table")
    private String format;

    public String getFormat() {
        return format;
    }

    @Override
    public void run() {
        List<Product> products = Arrays.asList(
                new Product(1000, "Laptop", 2000),
                new Product(1100, "Mouse", 59.99),
                new Product(1200, "Keyboard", 219.99)
        );

        if("json".equalsIgnoreCase(format)) {
            System.out.println("[");
            for(Product p:products) {
                System.out.println(p.toJson());
            }
            System.out.println("]");
        } else {
            System.out.println("ID\t\tName\t\tPrice");
            System.out.println("-------------------");
            for(Product p:products) {
                System.out.println(p.toTable());
            }
        }
    }
}