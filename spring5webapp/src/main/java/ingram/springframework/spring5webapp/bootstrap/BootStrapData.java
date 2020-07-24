package ingram.springframework.spring5webapp.bootstrap;

import ingram.springframework.spring5webapp.model.Author;
import ingram.springframework.spring5webapp.model.Book;
import ingram.springframework.spring5webapp.repositories.AuthorRepository;
import ingram.springframework.spring5webapp.repositories.BookRepository;
import org.apache.catalina.util.ToStringUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author adam = new Author("Adam", "Ingram");
        Book vape = new Book("Learn To Vape", "123");
        adam.getBooks().add(vape);
        vape.getAuthors().add(adam);

        authorRepository.save(adam);
        bookRepository.save(vape);

        Author rob = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "1234");
        rob.getBooks().add(noEJB);
        noEJB.getAuthors().add(rob);

        authorRepository.save(rob);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());

    }
}
