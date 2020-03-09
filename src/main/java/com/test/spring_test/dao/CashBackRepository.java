package com.test.spring_test.dao;

import com.test.spring_test.model.CashBack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashBackRepository extends CrudRepository<CashBack,Long> {
}
