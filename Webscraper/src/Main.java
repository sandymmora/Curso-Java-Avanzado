
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> links = new ArrayList<>();
        links.add("https://jsbin.com/zulukilabu/edit?js,console");
        links.add("https://learngitbranching.js.org/?locale=es_MX");
        links.add("https://www.figma.com/login");
        links.add("https://jsbin.com/zulukilabu/edit?js,console");
        links.add("https://learngitbranching.js.org/?locale=es_MX");
        links.add("https://www.figma.com/login");
        links.add("https://jsbin.com/zulukilabu/edit?js,console");
        links.add("https://learngitbranching.js.org/?locale=es_MX");
        links.add("https://www.figma.com/login");

        //Utilizando Parallel
        Long timeStart = System.nanoTime();
        links.stream().forEach(link -> getWebContent(link));
        Long timeEnd = System.nanoTime();
        System.out.println("Diferencia "+ (timeEnd - timeStart));

        links.stream().parallel().forEach(link -> getWebContent(link));


    }

    private synchronized static String getWebContent(String link) {
        System.out.println("INIT");
        System.out.println(link);
        try{
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            String encoding = connection.getContentEncoding();
            InputStream input = connection.getInputStream();

            Stream<String> lines = new BufferedReader(new InputStreamReader(input))
                    .lines();
            System.out.println("END");
            return lines.collect(Collectors.joining());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return "";
    }
}