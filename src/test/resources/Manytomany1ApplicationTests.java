import com.qlam.demo.model.Book;
import com.qlam.demo.model.Publisher;
import com.qlam.demo.repository.BookRepository;
import com.qlam.demo.repository.PublisherRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Manytomany1ApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(Manytomany1Application.class);
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    @Transactional
    public void contextLoads() {
        // save a couple of books
        Publisher publisherA = new Publisher("Publisher A");
        Publisher publisherB = new Publisher("Publisher B");
        Publisher publisherC = new Publisher("Publisher C");

        bookRepository.save(new HashSet<Book>() {
            {
                add(new Book("Book A", new HashSet<Publisher>() {
                    {
                        add(publisherA);
                        add(publisherB);
                    }
                }));

                add(new Book("Book B", new HashSet<Publisher>() {
                    {
                        add(publisherA);
                        add(publisherC);
                    }
                }));
            }
        });

        // fetch all books
        for (Book book : bookRepository.findAll()) {
            logger.info("\n" + book.toString());
        }

        // save a couple of publishers
        Book bookA = new Book("Book A");
        Book bookB = new Book("Book B");

        publisherRepository.save(new HashSet<Publisher>() {
            {
                add(new Publisher("Publisher A", new HashSet<Book>() {
                    {
                        add(bookA);
                        add(bookB);
                    }
                }));

                add(new Publisher("Publisher B", new HashSet<Book>() {
                    {
                        add(bookA);
                        add(bookB);
                    }
                }));
            }
        });

        // fetch all publishers
        for (Publisher publisher : publisherRepository.findAll()) {
            logger.info("\n" + publisher.toString());
        }

    }

}
