package org.nta.test.nopac;

import org.apache.log4j.Logger;
import org.h2.tools.Server;
import org.nta.test.model.Words;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Map;

@Component
public class Saver {
    private static final Logger LOGGER = MyLogger.getMyLogger(Main.class);
    @Autowired
    private CrudRepository<Words, Long> crudRepository;

    public Saver() {
        LOGGER.info("Создан Saver");
        try {
            Server.createTcpServer().start();
        } catch (SQLException throwable) {
            LOGGER.warn(throwable);
        }
        // this.crudRepository = run.getBean(CrudRepository.class);
    }

    public void saveToDB(Map<String, Integer> map) {
        LOGGER.info("Сохраняем в базу");
        for (String s : map.keySet()) {
            Words words = new Words();
            words.setString(s);
            words.setCount(map.get(s));
            crudRepository.save(words);
        }
    }

    public void getFromDB() {
        LOGGER.info("Читаем из базы данных");
        Iterable<Words> all = crudRepository.findAll();
        all.forEach(t -> System.out.println(t.getString() + " " + t.getCount()));

    }
}
