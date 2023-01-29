package uz.md.bookservicewithwebflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import uz.md.bookservicewithwebflux.dao.BookRepository;
import uz.md.bookservicewithwebflux.dto.BookAddDto;
import uz.md.bookservicewithwebflux.dto.BookDto;
import uz.md.bookservicewithwebflux.entity.Book;
import uz.md.bookservicewithwebflux.service.BookService;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@Slf4j
public class BookServiceTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setup()  {
    }

    @Test
    public void shouldAddBook() {

        BookAddDto addDto = new BookAddDto(
                "Reactive Spring",
                "book about reactive spring",
                500,
                List.of(1L));
        Book book = Book.fromDTO(addDto);
        when(bookRepository.save(book)).thenReturn(Mono.just(book));

    }

    @Test
    public void shouldGetUserById() {
        Book book = Book.builder()
                .title("book2")
                .description("book2")
                .pageCount(12)
                .build();

        Mono<BookDto> bookMono = bookService.findById(4L);

        StepVerifier
                .create(bookMono)
                .consumeNextWith(newBook -> {
                    assertEquals(newBook.getId(), 4L);
                    assertEquals(newBook.getTitle(), "book2");
                    assertEquals(newBook.getDescription(), "book2");
                    assertEquals(newBook.getPageCount(), 12);
                })
                .verifyComplete();
    }
}
