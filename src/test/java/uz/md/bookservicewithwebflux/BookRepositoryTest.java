package uz.md.bookservicewithwebflux;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;
import uz.md.bookservicewithwebflux.dao.BookRepository;
import uz.md.bookservicewithwebflux.entity.Book;

@DataR2dbcTest
@ExtendWith(SpringExtension.class)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldSaveSingleBook() {

        Book book = Book.builder()
                .title("Book")
                .description("Book description")
                .pageCount(400)
                .build();

        Publisher<Book> setup = bookRepository
                .deleteAll()
                .thenMany(bookRepository.save(book));

        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();
    }


}
