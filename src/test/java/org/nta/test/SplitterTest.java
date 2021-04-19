package org.nta.test;


import org.h2.tools.Server;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.nta.test.model.Words;
import org.nta.test.services.ReadFromUrlRunner;
import org.nta.test.api.Reader;
import org.nta.test.services.Splitter;
import org.nta.test.repository.WordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.support.TransactionTemplate;


import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.io.*;
import java.net.URL;

import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

@SpringBootTest
@EntityScan("org.nta")
class SplitterTest {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private WordRepo wordRepo;
    @Autowired
    private CrudRepository<Words, Integer> crudRepository;

    @BeforeAll
    public static void startServer() throws SQLException {
        Server.createTcpServer().start();
    }

    @Test
    public void get() {
        Words words = new Words();
        words.setCount(2);
        words.setString("first");
        wordRepo.save(words);
        words.setString("second");
        wordRepo.save(words);
        words.setString("third");
        crudRepository.save(words);
    }


    @Test
    public void testSplitting() {

        Splitter splitter = new Splitter();
        Map<String, Integer> words = splitter.split("\n \t \r Покажу вам как работает консоль psql. Как записать вывод записей базы PostgreSQL в тестовой файл? Для этого вам нужно создать папку с нужными правами. Если прав не будет хватать, то консоль psql не сможет записать вывод в файл. Воспользуйтесь утилитой chown. Сначала создайте файл. Потом перенаправьте вывод записей в файл. Теперь выполните sql-запрос. Для примера используйте простой SELECT. Далее прочтите файл при помощи команды cat. Приступим! Если не знаете как войти в консоль psql, то читайте статью как создать базу данных постгрес на linux сервере Debian Ubuntu.");
        words.entrySet().forEach(System.out::println);
        Assertions.assertEquals(63, words.size());
        Assertions.assertEquals(88, words.values().stream().mapToInt(integer -> integer).sum());
    }


    @Test
    public void exampleWithJsoup() throws Exception {
        String html = "<p>An <a href='https://simbirsoft.com'><b>example</b></a> link.</p>";
        Document doc = Jsoup.connect("https://simbirsoft.com").get();
        String textContents = doc.select("a").text();
        String text = doc.body().text();
        Splitter splitter = new Splitter();
        Map<String, Integer> split = splitter.split(text);
        Map<String, Integer> split2 = splitter.split(textContents);
        System.out.println(split.size());
        System.out.println(split2.size());
    }

    @Test
    public void my() throws Exception {
        URL yahoo = new URL("https://www.simbirsoft.com/");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yahoo.openStream()));

        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {

            stringBuilder.append(inputLine).append("\n");
        }

        in.close();

        String s = stringBuilder.toString();
        String substring = stringBuilder.substring(stringBuilder.toString().indexOf("<body"));
        String replaceAll = substring
                .replaceAll("(?s)<!--.+?-->|<script.+?</script>|<.+?>", "")
                .replaceAll("&nbsp;|  +", " ")
                .replaceAll("\r\n ", "\r\n")
                .replaceAll("[\r\n\t]+", "\r\n");
        Splitter splitter = new Splitter();

    }


    @Test
    public void with() throws Exception {

        Splitter splitter = new Splitter();
        URL url = new URL("https://www.simbirsoft.com/");
        Reader<String> read = new ReadFromUrlRunner(splitter);
        read.setUrl(url);
        read.read();
        Map<String, Integer> map = read.getMap();
    }



    @Test
    public void withFileCopy() throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(new FileReader("C:\\Users\\Tatyana\\Downloads\\application\\TestProjectSimbirSoft\\src\\test\\java\\page.txt"))) {
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
            }
        }
        String substring = stringBuilder.substring(stringBuilder.toString().indexOf("<body"))
                .replaceAll("(?s)<!--.+?-->|<script.+?</script>|<.+?>", "")
                .replaceAll("&nbsp;|  +", " ")
                .replaceAll("\r\n ", "\r\n")
                .replaceAll("[\r\n\t]+", "\r\n");
        Splitter splitter = new Splitter();
        Map<String, Integer> split = splitter.split(substring);
        split.entrySet().forEach(System.out::println);

    }
}