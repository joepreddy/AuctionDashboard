package DataStructures;

import DataStructures.CsvInterfaces.Factory;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by rhys on 23/02/17.
 */
public class CsvPaserFactory implements Factory {
    private CsvBeanReader beanReader = null;
    private String[] headers = null;

    public CsvPaserFactory open(Class clsStructure, String fileName) throws IOException {

        FileReader FR = new FileReader("click_log.csv");


        this.beanReader = new CsvBeanReader(FR, CsvPreference.STANDARD_PREFERENCE);

        //remove all spaces and replace them with under scores in the headers
        this.headers = Arrays.stream(beanReader.getHeader(true))
                .map(s -> s.replace(" ", "_"))
                .collect(Collectors.toList())
                .toArray(new String[]{});


        return this;
    }

    public CsvPaserFactory close() throws IOException {
        if (beanReader != null) {
            beanReader.close();
        }
        return this;
    }

    public Object next() throws IOException {
        if (beanReader != null) {
            return beanReader.read(ClickLog.class, headers);
        }
        return null;
    }
}
    /* if the first line is the header
    String[] header = reader.readNext();
            for (String l:header) {
                    System.out.print(l+"\t\t" );
                    }
                    System.out.println();
// iterate over reader.readNext until it returns null
                    String[] line;
                    while ((line = reader.readNext())!=null)
                    {
                    for (String l:line) {
                    System.out.print(l+"\t\t");
                    }
                    LocalDate localDate = LocalDate.parse(line[0].split(" ")[0]);
                    LocalTime localTime = LocalTime.parse(line[0].split(" ")[1]);
                    LocalDateTime localDateTime = localDate.atTime(localTime);
                    Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
                    System.out.print(instant);
                    System.out.println();
                    } */