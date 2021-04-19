package org.nta.test.repository;

import org.nta.test.model.Words;
import org.springframework.data.repository.CrudRepository;

public interface WordRepo extends CrudRepository<Words, Integer> {
}