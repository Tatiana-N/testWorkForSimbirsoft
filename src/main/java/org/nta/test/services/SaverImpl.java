package org.nta.test.services;

import org.apache.log4j.Logger;
import org.h2.tools.Server;
import org.nta.test.api.Saver;
import org.nta.test.model.Words;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Map;

@Component
public class SaverImpl implements Saver<String> {
    private static final Logger LOGGER = MyLogger.getMyLogger(Main.class);
    @Autowired
    private CrudRepository<Words, Integer> crudRepository;

    public SaverImpl() {
        LOGGER.info("Создан Saver");
        try {
            Server.createTcpServer().start();
        } catch (SQLException throwable) {
            LOGGER.warn(throwable);
        }
    }

    @Override
    public void saveToDB(Map<String, Integer> map) {
        LOGGER.info("Сохраняем в базу");
        for (String s : map.keySet()) {
            Words words = new Words();
            words.setString(s);
            words.setCount(map.get(s));
            crudRepository.save(words);
        }
    }

    @Override
    public void getFromDB() {
        LOGGER.info("Читаем из базы данных");
        Iterable<Words> all = crudRepository.findAll();
        all.forEach(t -> System.out.format("%s%-20s%s%5d%s", "|   ", t.getString(), "|", t.getCount(), "\n"));

    }
}
