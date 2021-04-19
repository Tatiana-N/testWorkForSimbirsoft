package org.nta.test;


import org.h2.tools.Server;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.nta.test.model.Words;
import org.nta.test.services.Read_From_Url_Runner;
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
        check(split);
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

    public Map<String, Integer> withStringsCopy() {
        String testString = " <div class=\"phrases__item\"><div>\"Оперативная реакция на наши пожелания\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Мы просто полагались на их опыт и получили блестящие результаты\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Свернут горы, чтобы удовлетворить наши высокие требования\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Очень гибкий подход к решению задач\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Моментально реагируют на любые запросы\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Искренне хотят сделать продукт на максимум!\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Предлагают альтернативные варианты реализации с учетом сроков и бюджета\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Показали очень высокий уровень разработки\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Спасли наш проект, с которым не справилась другая команда\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Мы довольны вкладом в успех проекта и человеческими отношениями\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Всегда готовы предоставить нам профессиональную команду\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Проявляют инициативность в создании качественного продукта\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Создали комфортные условия для партнерских отношений\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Все задачи, какими бы сложными они ни были, выполняются качественно и в срок\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"«Прозрачный» процесс разработки дает возможность всегда быть в курсе хода работ\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Мы очень довольны подходом СимбирСофт к анализу, разработке, контролю качества и управлению\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Внесли ясность в те требования, которые мы сами не очень себе представляли\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Собранность и выполнение задач в очень короткие сроки\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Всегда корректное и приятное общение\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Компетентные, ответственные и доброжелательные профессионалы\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Всегда выполняют работу на самом высоком уровне\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Нас впечатлили этапы аналитики, подготовки прототипов и дизайна\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"В СимбирСофт всегда предлагают креативные и инновационные подходы к решению сложных задач\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Быстро погрузились в наши бизнес-процессы и показали отличный результат\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Опыт СимбирСофт внес свой вклад в общий успех нашего сотрудничества\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Мы получаем хорошую отдачу инвестиций от этого сотрудничества\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Соблюдены все обязательства по срокам реализации и качеству работы\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Предлагали пути улучшения логики работы приложения и увеличения производительности\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Превзошли ожидания и выполнили даже больше запланированного\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Команда работала очень быстро, что позволило уложиться в обещанные сроки\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Готовы обсуждать смену приоритетов действий и разбивку по срокам\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Буквально через несколько месяцев мы получили первые результаты работ\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Мы прекрасно понимали, какой результат ожидать по окончанию этапа\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Они полностью оправдали наши ожидания\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Быстро включились в проект, освоив незнакомую технологическую платформу\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Мы опирались на их опыт и преданность делу\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Предельно адаптировали всех участников команды к нашим требованиям\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Они буквально прочитали наши мысли и предоставили прототип самого высокого качества\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Нацелены на долгосрочную работу\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Мы увидели качественные результаты после первой недели работы\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Первый адаптер был готов через несколько недель после заключения договора\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Их ежедневная вовлеченность в наши задачи дала нам понять, что мы выбрали отличного партнера \"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Создали реально работающий продукт за разумные деньги\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Всегда слышат, чего хочет клиент\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Мы с уверенностью смотрим в будущее\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"В ходе работ максимально учитывались пожелания заказчика\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Все поставленные задачи были выполнены в срок\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Дают четкое понимание того, какой результат ожидать\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Они креативно мыслят, чтобы достичь целей клиента наилучшим образом\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Мы ценим интересные идеи по разработке нашего сервиса\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Высокая организованность процессов и ведения проектов\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Темп работы команды точно соответствовал нашим ожиданиям и оценкам\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Всегда делают все возможное, чтобы удовлетворить наши высокие требования\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Благодаря им мы разработали 6 крупных продуктов\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Оперативно подключили необходимых специалистов\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Хорошо сработанная команда с высоким потенциалом к взаимовыгодному сотрудничеству\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Через два месяца после начала работы первая версия системы была готова\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Адаптировались под потребности нашего агентства\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Преимущество компании в наличии собственных сертифицированных экспертов\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Всегда можем рассчитывать на дополнительных специалистов в течение нескольких часов\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Предоставляют специалистов, которые становятся частью нашей команды\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Разработали для нас несколько мобильных приложений для интернета вещей\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Мы получили все демонстрационные варианты вовремя и с работающим функционалом\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Реализовали мобильное приложение в точности так, как я планировал\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Мы получили готовый продукт, отвечающий нашим ожиданиям\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Я доволен теми IT продуктами, которые мы получаем в результате разработки\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Качество и демонстрация прототипа превзошли все наши ожидания\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Заботятся о потребностях и бизнес целях заказчика\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Всегда находят творческие и нетривиальные подходы к решению задач\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Проактивность специалистов приятно удивляет\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Наш проект был исключительно успешным\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Мы успешно завершили десятки проектов, сотрудничая с ними\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Удалось быстро вникнуть в необходимую предметную область\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Всегда быстро реагируют на пожелания\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Выполнили работу на высоком уровне и эффективно решили наши задачи\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Я не знаю более клиентоориентированной IT-компании\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Мы ценим их подход к анализу, разработке, обеспечению качества и управлению\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Нам очень понравилось сотрудничать с ними\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Вся необходимая для разработки информация поступала вовремя\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Ориентируют свой бизнес в соответствии с тенденциями мирового IT-рынка\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Качественно разработали компоненты нашего флагманского продукта\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Очень довольны сотрудничеством в сфере мобильной разработки\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Еженедельно выделенный менеджер демонстрировал нам текущую версию продукта\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Достойный партнер для реализации сложных и ответственных проектов\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"С менеджментом всегда можно найти общий язык\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Укладываются в сжатые сроки\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Подходят к выполнению задач с душой и выполняют их в сроки\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Взяли на себя ведущую роль в разработке\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Мы получили качественные результаты уже через два месяца\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Всегда найдут способ разработать лучший продукт даже в условиях стресса\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Ответственная и профессиональная команда\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Способны решать задачи широкого технологического спектра\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Уделяют внимание деталям\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Наши пользователи остались довольны приложением\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"В графическом интерфейсе реализуются все самые смелые пожелания\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Задачи были выполнены гораздо быстрее запланированного срока\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Высоко квалифицированная команда\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"С их помощью мы прошли инвестиционный раунд и выиграли грант Сколково\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Уверенное владение английским языком\"</div></div>\n" +
                "                    <div class=\"phrases__item\"><div>\"Мы полностью видели и  контролировали весь процесс разработки\"</div></div> " +
                " С 2001 годаSimbirSoftSimbirSoftIT company that caresРЕШЕНИЯ\n" +
                " УСЛУГИ\n" +
                " ПОРТФОЛИО\n" +
                " О НАС\n" +
                " КАРЬЕРА\n" +
                " БЛОГ\n" +
                "RU / ENG\n" +
                "Напишите нам8-800-200-9924Ок\n" +
                "Наш сайт использует файлы cookie. Оставаясь на www.simbirsoft.com, вы подтверждаете свое согласие на использование данных файлов\n" +
                "\n" +
                "\n" +
                "РАЗРАБОТКА ПРОГРАММНОГО ОБЕСПЕЧЕНИЯ\n" +
                "ДЛЯ РОСТА\n" +
                "ВАШЕГО БИЗНЕСА\n" +
                "ЛИДЕРЫ В РАЗРАБОТКЕ СОВРЕМЕННЫХ IT-РЕШЕНИЙ НА ЗАКАЗ\n" +
                "SimbirSoft — компания по разработке программного обеспечения. С 2001 года мы занимаемся профессиональной разработкой софта на заказ. Наши специалисты оказывают услуги по созданию качественного продукта, отвечающего всем требованиям бизнеса.\n" +
                "\n" +
                "ЧЕМ МЫ МОЖЕМ ПОМОЧЬ\n" +
                "\n" +
                "Разработать IT‑продукт «под ключ»\n" +
                "Быстро разберёмся в вашей задаче и предоставим результат в минимальные сроки. Организуем цикл разработки, тестирования, внедрения и сопровождения системы.\n" +
                "\n" +
                "Обеспечить надежную работу ваших IT‑систем\n" +
                "Выявим уязвимости, проверим нагрузку и работоспособность, протестируем юзабилити. Ваш сайт, приложение и другое ПО принесут максимальную выгоду.\n" +
                "\n" +
                "Оказать услуги IT‑аутсорсинга\n" +
                "Оперативно сформируем и подключим команды по аналитике, тестированию, дизайну и разработке. Спроектируем архитектуру, проведём интеграцию систем, окажем техническую поддержку production.\n" +
                "\n" +
                "«Спасти» продукт\n" +
                "Восстановим систему после неудачной разработки, выпустим продукт в релиз и организуем процесс стабильного развития продукта.\n" +
                "ПОЧЕМУ С НАМИ ВЫГОДНО\n" +
                "Выстраиваем процессы там, где их нет; подстраиваемся там, где они есть\n" +
                "Вы получаете нужное IT-решение, а мы заботимся о технических деталях проекта\n" +
                "Технологическая экспертиза для решения комплексных задач\n" +
                "\n" +
                "Вы приобретаете единого партнера для ваших\n" +
                "IT-потребностей\n" +
                "Работа со скоростью потребностей вашего бизнеса\n" +
                "Вы достигаете бизнес-цели и получаете результат в ожидаемый срок\n" +
                "Первый результат в первый месяц работ\n" +
                "Вы оперативно видите отдачу на вложения\n" +
                "100% релизов выполнено в срок\n" +
                "Вы видите четкий результат на всех этапах\n" +
                "в предсказуемые сроки\n" +
                "Сокращаем time-to-market\n" +
                "Вы получаете преимущество по скорости выхода вашего IT-продукта\n" +
                "НАМ ДОВЕРЯЮТ\n" +
                "Посмотреть все\n" +
                "100 ФРАЗ О НАС\n" +
                "Читать все\n" +
                "НАШИ НАГРАДЫ\n" +
                "Подробнее\n" +
                "\n" +
                "НЕ ЗНАЕТЕ, С ЧЕГО НАЧАТЬ?\n" +
                "Напишите нам\n" +
                "НОВОСТИ\n" +
                "SimbirSoft и Oracle проведут конкурс «Базы данных» на олимпиаде «IT-Планета»\n" +
                "SimbirSoft и Oracle проведут конкурс «Базы данных» на олимпиаде «IT-Планета»\n" +
                "\n" +
                "  22.03.2021\n" +
                "Skillbox и SimbirSoft выпустили расширенный курс по кросс-платформенной разработке на Flutter\n" +
                "Skillbox и SimbirSoft выпустили расширенный курс по кросс-платформенной разработке на Flutter\n" +
                "\n" +
                "  18.03.2021\n" +
                "SimbirSoft в сотне мировых IT-лидеров\n" +
                "SimbirSoft в сотне мировых IT-лидеров\n" +
                "\n" +
                "  12.03.2021\n" +
                "Все новости\n" +
                "С 2001 годаSimbirSoftIT company that cares\n" +
                "С 2001 года СимбирСофт разрабатывает уникальные программные решения для компаний из России, США и стран Европы.\n" +
                "8-800-200-9924\n" +
                "REQUEST@SIMBIRSOFT.COM\n" +
                "РЕШЕНИЯ\n" +
                "Для банков\n" +
                "Для страховых\n" +
                "Оценка IT-проектов\n" +
                "Data Science\n" +
                "Автоматизация бизнеса\n" +
                "ShopChat\n" +
                "Food Tech\n" +
                "Для медицинской сферы\n" +
                "Для промышленности\n" +
                "УСЛУГИ\n" +
                "IT-продукт под ключ\n" +
                "Аутсорсинг\n" +
                "Модернизация ПО\n" +
                "«Спасти» продукт\n" +
                "Обеспечение качества\n" +
                "Техподдержка production и разработки\n" +
                "Jira Service\n" +
                "IT-архитектура\n" +
                "Внедрение Битрикс24\n" +
                "ПОРТФОЛИО\n" +
                "Технологии\n" +
                "Отрасли\n" +
                "О НАС\n" +
                "О компании\n" +
                "Награды\n" +
                "Клиенты\n" +
                "Наши процессы\n" +
                "Новости\n" +
                "Менеджмент\n" +
                "Контакты\n" +
                "Социальная ответственность\n" +
                "Блог\n" +
                "ТЕХНОЛОГИИ\n" +
                "Android\n" +
                "Bitrix\n" +
                "C#/.NET\n" +
                "C++\n" +
                "DevOps\n" +
                "Go\n" +
                "iOS\n" +
                "Java\n" +
                "JavaScript\n" +
                "PHP\n" +
                "Python\n" +
                "QA\n" +
                "SDET\n" +
                "© 2001 - 2021 SimbirSoft\n" +
                "ISO 9001:2015\n" +
                "      ";
        Splitter splitter = new Splitter();
        return splitter.split(testString.replaceAll("(?s)<!--.+?-->|<script.+?</script>|<.+?>", "")
                .replaceAll("&nbsp;| +", " ")
                .replaceAll("\r\n ", "\r\n")
                .replaceAll("[\r\n\t]+", "\r\n"));
    }

    @Test
    public void with() throws Exception {

        Splitter splitter = new Splitter();
        URL url = new URL("https://www.simbirsoft.com/");
        Reader<String> read = new Read_From_Url_Runner(splitter);
        read.setUrl(url);
        read.read();
        Map<String, Integer> map = read.getMap();
        check(map);
    }

    public void check(Map<String, Integer> map) {
        Map<String, Integer> stringIntegerMap = withStringsCopy();
        map.keySet().removeAll(stringIntegerMap.keySet());
        map.keySet().forEach(System.out::println);
        Assertions.assertTrue(map.keySet().size() < 10);


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