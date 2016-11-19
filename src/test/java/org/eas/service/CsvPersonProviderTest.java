package org.eas.service;

import org.junit.Test;

/**
 * @author eas
 */
public class CsvPersonProviderTest {

    @Test
    public void test() throws Exception {
        CsvPersonProvider csvPersonProvider = new CsvPersonProvider();
        csvPersonProvider.get("persons.csv").forEach(System.out::println);
    }
}