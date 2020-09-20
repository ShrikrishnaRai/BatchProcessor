package com.shree.batch.repository;

import com.shree.batch.dao.entity.PersonEntity;
import com.shree.batch.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
