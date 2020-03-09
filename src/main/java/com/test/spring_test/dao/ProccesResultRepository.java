package com.test.spring_test.dao;

import com.test.spring_test.dto.ProcessResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProccesResultRepository extends CrudRepository<ProcessResult,Long> {
}
