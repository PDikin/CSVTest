package csvtest.csvWorker;

import csvtest.model.Employee;
import csvtest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;
import java.io.FileReader;
import java.util.Map;

@Component
public class CsvParser {

    @Autowired
    EmployeeRepository employeeRepository;

    public void readWithCsvMapReader(String name) throws Exception {
        ICsvMapReader mapReader = null;
        try {
            mapReader = new CsvMapReader(new FileReader(name), CsvPreference.STANDARD_PREFERENCE);

            // the header columns are used as the keys to the Map
            final String[] header = mapReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();

            Map<String, Object> customerMap;
            while( (customerMap = mapReader.read(header, processors)) != null ) {
//                System.out.println(String.format("lineNo=%s, rowNo=%s, customerMap=%s", mapReader.getLineNumber(),
//                        mapReader.getRowNumber(), customerMap));
                String s = (String) customerMap.get(header[0]);
                String[] mas = s.split(";");

                //ssoid	ts	grp	type	subtype	url	orgid	formid	code	ltpa	sudirresponse	ymdh
                Employee employee = Employee.builder()
                        .ssoid(mas[0])
                        .ts(mas[1])
                        .grp(mas[2])
                        .type(mas[3])
                        .subtype(mas[4])
                        .url(mas[5])
                        .orgid(mas[6])
                        .formid(mas[7])
                        .code(mas[8])
                        .ltpa(mas[9])
                        .sudirresponsea(mas[10])
                        .ymdh(mas[11])
                        .build();
                System.out.println(employee);

                employeeRepository.save(employee);

                System.out.println(s);
            }


        }
        finally {
            if( mapReader != null ) {
                mapReader.close();
            }
        }
    }

    private static CellProcessor[] getProcessors() {
        return new CellProcessor[]{
                new Optional()
        };
    }
}
