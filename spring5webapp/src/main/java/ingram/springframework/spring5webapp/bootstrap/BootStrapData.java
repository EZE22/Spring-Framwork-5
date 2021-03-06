package ingram.springframework.spring5webapp.bootstrap;

import ingram.springframework.spring5webapp.model.Author;
import ingram.springframework.spring5webapp.model.Book;
import ingram.springframework.spring5webapp.model.Publisher;
import ingram.springframework.spring5webapp.repositories.AuthorRepository;
import ingram.springframework.spring5webapp.repositories.BookRepository;
import ingram.springframework.spring5webapp.repositories.PublisherRepository;
import org.apache.catalina.util.ToStringUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setName("King Books");
        publisher.setCity("Rome");
        publisher.setState("GA");
        publisherRepository.save(publisher);

        Author adam = new Author("Adam", "Ingram");
        Book vape = new Book("Learn To Vape", "123");
        adam.getBooks().add(vape);
        vape.getAuthors().add(adam);

        vape.setPublisher(publisher);
        publisher.getBooks().add(vape);

        authorRepository.save(adam);
        bookRepository.save(vape);
        publisherRepository.save(publisher);

        Author rob = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "1234");
        rob.getBooks().add(noEJB);
        noEJB.getAuthors().add(rob);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rob);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher number of Books: " + publisher.getBooks().size());

    }
}
