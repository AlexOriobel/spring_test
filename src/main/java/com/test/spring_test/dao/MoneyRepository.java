package com.test.spring_test.dao;

import com.test.spring_test.model.Money;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyRepository extends CrudRepository<Money, Long> {
}
