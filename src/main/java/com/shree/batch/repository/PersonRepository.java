package com.shree.batch.repository;

import com.shree.batch.dao.entity.PersonEntity;
import com.shree.batch.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<PersonEntity, Long> {

    Person findByFirstName(String firstName);
}
