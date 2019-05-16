package filereaders;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFile {

    public String readFileIntoSqlString(String filePathInResources){
        Path path = null;
        try {
            path = Paths.get(getClass().getClassLoader()
                    .getResource(filePathInResources).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }

        Stream<String> lines = null;
        try {
            lines = Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }

        String data = lines.filter(StringUtils::isNotBlank).collect(Collectors.joining(", "));
        //lines.forEach(System.out::println);
        lines.close();

        String outStr;

        if(filePathInResources.contains("insert")){
        outStr = data.replace("table={","INSERT INTO  ")
                .replace("},","(");
        outStr = outStr.replaceFirst(",",") VALUES (");
        outStr = outStr.replace(",","),(");
        outStr = outStr.replace('|',',').concat(");");
        }
        else {

        outStr = data.replace("table={", "CREATE TABLE  IF NOT EXISTS ")
                .replace("},"," (")
                .replace('|',' ').concat(");");}

        System.out.println(data);
        System.out.println(outStr);
        return outStr;

    }
}
