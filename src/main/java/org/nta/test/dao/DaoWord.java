package org.nta.test.dao;

import org.nta.test.model.Words;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import java.util.List;

/**
 * Тебе это не надо, если у тебя есть репозиторий!
 */

@Repository
public class DaoWord {
    public DaoWord() {
    }

    @Autowired
    private EntityManager entityManager;

    public void save(Words words){
        entityManager.persist(words);
    }

    public List<Words> findAll(){
        return entityManager.createQuery("select t from " + Words.class.getSimpleName()+ " t").getResultList();
    }

}
