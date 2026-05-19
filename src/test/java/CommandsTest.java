import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import picocli.CommandLine;

import static org.testng.AssertJUnit.assertEquals;

public class CommandsTest {
  @Test
  public void testURLOptionIsParsed() {
    String[] args = {"--url", "http://localhost:3000/shop-api", "list"};
    ShopCli app = new ShopCli();

    app.parse(args);

    assertEquals("http://localhost:3000/shop-api", app.getUrl());
  }

  @Test
  public void testListCommandWithJsonFormat() {
    ShopCli app = new ShopCli();
    CommandLine cmd = new CommandLine(app);

    cmd.parseArgs("list", "--format", "json");

    ListCommand listCmd = cmd.getSubcommands().get("list").getCommand();
    assertEquals("json", listCmd.getFormat());
  }
}
