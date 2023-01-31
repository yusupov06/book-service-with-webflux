package uz.md.bookservicewithwebflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import uz.md.bookservicewithwebflux.dao.BookRepository;
import uz.md.bookservicewithwebflux.dto.BookAddDto;
import uz.md.bookservicewithwebflux.dto.BookDto;
import uz.md.bookservicewithwebflux.entity.Book;
import uz.md.bookservicewithwebflux.service.BookService;
import uz.md.bookservicewithwebflux.service.BookServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataR2dbcTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@Slf4j
public class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void shouldAddBook() {

        BookAddDto addDto = new BookAddDto(
                "Reactive Spring",
                "book about reactive spring",
                500,
                List.of(1L));
        Book book = Book.fromDTO(addDto);
        when(bookRepository.save(book)).thenReturn(Mono.just(book));

        Mono<Long> add = bookService.add(addDto);


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
