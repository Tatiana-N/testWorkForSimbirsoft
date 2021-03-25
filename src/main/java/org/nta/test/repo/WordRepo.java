package org.nta.test.repo;

import org.nta.test.model.Words;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WordRepo extends CrudRepository<Words, Long> {
    List<Words> findWordsByString(String name);

//    @Query("select p from Animal a left join Person p on a.person = p where a.disType = :type and p.name like :nameLike")
//    List<Person> onlySpecAnimal(@Param("type") String type, @Param("nameLike") String nameLike);
}