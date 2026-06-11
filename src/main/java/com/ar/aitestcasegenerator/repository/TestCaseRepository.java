package com.ar.aitestcasegenerator.repository;

import com.ar.aitestcasegenerator.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {

}