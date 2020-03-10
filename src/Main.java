import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main
{
    public static void main(String[] args)
    {
      readFile (Constants.input_file);
    }


    private static void readFile(String fileName)
    {
        Path filePath = Paths.get (fileName);

        try (Stream<String> lines = Files.lines(filePath))
        {

          List<String> filteredLines = lines
              .filter(s -> s.contains("gandhi"))
              .collect(Collectors.toList());

          filteredLines.forEach(System.out::println);

        }
        catch (IOException e) {

          e.printStackTrace();
        }
    }
}