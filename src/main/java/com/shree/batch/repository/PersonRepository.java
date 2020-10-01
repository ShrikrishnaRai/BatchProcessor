package com.shree.batch.repository;

import com.shree.batch.dao.entity.PersonEntity;
import com.shree.batch.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<PersonEntity, Long> {

    @Query(value = "SELECT * from people p where p.email=email", nativeQuery = true)
    Optional<PersonEntity> findPersonEntitiesByEmail(@Param("email") String email);


    @Query(value = "SELECT * from people p where p.first_name like %:firstName%", nativeQuery = true)
    Optional<PersonEntity> findPersonEntitiesByFirstName(String firstName);
}
