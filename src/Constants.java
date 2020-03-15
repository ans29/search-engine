import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Constants
{
    public static final String input_file = "Data/wiki-search-smallest.xml";
//    public static final String input_file = "Data/wiki-search-smaller.xml";
//    public static final String input_file = "Data/wiki-search-small.xml";
    public static final String output_file = "Data/inverted-index.txt";
    
    
    static String[] arr = {"\"", "/ref"};
    public static final Set<String> special_tags_in_text = new HashSet<>(Arrays.asList(arr));
}
