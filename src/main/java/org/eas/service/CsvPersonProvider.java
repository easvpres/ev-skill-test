package org.eas.service;

import org.eas.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author eas
 */
@Component
public class CsvPersonProvider {
    private static final Logger logger = LoggerFactory.getLogger(CsvPersonProvider.class);

    public static final String SEPARATOR = ",";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<Person> get(String csvFile) {
        List<Person> result = new ArrayList<>();
        String absFilePath = getClass().getClassLoader().getResource(csvFile).getPath();
        try (BufferedReader br = new BufferedReader(new FileReader(absFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(SEPARATOR);
                try {
                    result.add(new Person(columns[0], LocalDate.parse(columns[1], DATE_TIME_FORMATTER)));
                } catch (DateTimeParseException e) {
                    logger.error("can't parse date", e);
                }
            }
        } catch (IOException e) {
            logger.error("csv read error " + absFilePath, e);
        }
        return result;
    }
}
