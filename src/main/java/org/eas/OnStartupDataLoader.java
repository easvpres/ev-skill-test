package org.eas;

import org.eas.model.Person;
import org.eas.service.CsvPersonProvider;
import org.eas.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class OnStartupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private PersonService personService;
    @Autowired
    private CsvPersonProvider csvPersonProvider;

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent ) {
        for (Person person : csvPersonProvider.get("persons.csv")) {
            personService.save(person);
        }
    }
}