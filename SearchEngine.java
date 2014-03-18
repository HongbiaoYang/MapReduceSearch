import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by Bill on 3/17/14.
 */
public class SearchEngine {

    private final String indexPath = "index";
    private HashMap<String, String> indexMap;

    public SearchEngine() throws IOException {
        indexMap = new HashMap<String, String>();

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(indexPath));

                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                String key = null;
                String value = null;

                while (line != null) {

                    StringTokenizer stringTokenizer = new StringTokenizer(line, "\t");

                    if (stringTokenizer.hasMoreTokens()) {
                        key = stringTokenizer.nextToken();
                    }

                    if (stringTokenizer.hasMoreTokens()) {
                        value = stringTokenizer.nextToken();
                    }

                    if (key == null || value == null) {
                        System.out.println("Null Key or Value! k=" + key + " v=" + value);
                    }

                    indexMap.put(key, value);

                    line = br.readLine();

                }

        }
        catch (FileNotFoundException e) {
            System.out.println("Error:" + e.toString());
        }
        finally {
            br.close();
        }
    }

    public String search(String key) {

        String value = indexMap.get(key);

        if (value == null) {
            return "404 Not Found!";
        }

        StringBuilder result = new StringBuilder();
        StringTokenizer itr = new StringTokenizer(value, ";");
        String record;
        int count = 0;

        while (itr.hasMoreTokens()) {
            record = itr.nextToken();
            result.append(record);
            result.append("\n");
            count ++;
        }

        result.append("\nAbout "+count+" results,");

        return result.toString();
    }

    public static void main(String[] args) throws Exception {

        long timeZero = System.currentTimeMillis();

        if (args.length < 1) {
            System.out.println("Usage: java SearchEngine word");
            return;
        }

        SearchEngine se = new SearchEngine();
        String key = args[0];
        String result = se.search(key.toLowerCase());

        float elapseTime =  System.currentTimeMillis() - timeZero;

        System.out.print("Results for "+key + ":\n\n"+ result + "(" + elapseTime + " milliseconds)");
    }
}
