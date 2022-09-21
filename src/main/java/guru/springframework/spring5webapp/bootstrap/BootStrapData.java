package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        publisher.setName("Piter");
        publisher.setCity("St. Petersburg");
        publisherRepository.save(publisher);

        Book java8 = new Book("java 8","45345345");
        Author schildt = new Author("herbert","schildt");
        java8.getAuthors().add(schildt);
        schildt.getBooks().add(java8);

        publisher.getBooks().add(java8);
        java8.setPublisher(publisher);

        authorRepository.save(schildt);
        bookRepository.save(java8);
        publisherRepository.save(publisher);

        Book effectiveJava = new Book("Effective Java","436646");
        Author bloch = new Author("josh","bloch");
        effectiveJava.getAuthors().add(bloch);
        publisher.getBooks().add(effectiveJava);
        effectiveJava.setPublisher(publisher);
        bloch.getBooks().add(effectiveJava);

        authorRepository.save(bloch);
        bookRepository.save(effectiveJava);
        publisherRepository.save(publisher);

        System.out.println("book count: " + bookRepository.count());
        System.out.println("publisher books count: " + publisher.getBooks().size());


    }
}
