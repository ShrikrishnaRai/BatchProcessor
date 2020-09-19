package com.shree.batch.config.person;

import com.shree.batch.dao.entity.PersonEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;


public class PersonItemProcessor implements ItemProcessor<PersonEntity, PersonEntity> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public PersonEntity process(final PersonEntity personEntity) throws Exception {
        final String firstName = personEntity.getFirstName().toUpperCase();
        final String lastName = personEntity.getLastName().toUpperCase();

        final PersonEntity transformedPersonEntity = new PersonEntity(firstName, lastName);

        log.info("Converting (" + personEntity + ") into (" + transformedPersonEntity + ")");

        return transformedPersonEntity;
    }
}
