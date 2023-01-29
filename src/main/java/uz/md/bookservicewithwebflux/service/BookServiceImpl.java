package uz.md.bookservicewithwebflux.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.md.bookservicewithwebflux.dao.AuthorRepository;
import uz.md.bookservicewithwebflux.dao.BookAuthorRepository;
import uz.md.bookservicewithwebflux.dao.BookRepository;
import uz.md.bookservicewithwebflux.dto.AuthorDto;
import uz.md.bookservicewithwebflux.dto.BookAddDto;
import uz.md.bookservicewithwebflux.dto.BookDto;
import uz.md.bookservicewithwebflux.dto.BookEditDto;
import uz.md.bookservicewithwebflux.entity.Author;
import uz.md.bookservicewithwebflux.entity.Book;
import uz.md.bookservicewithwebflux.entity.BookAuthor;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookAuthorRepository bookAuthorRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           BookAuthorRepository bookAuthorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookAuthorRepository = bookAuthorRepository;
    }


    @Override
    public Mono<Long> add(BookAddDto dto) {
        return Mono.just(dto)
                .map(Book::fromDTO)
                .flatMap(book -> bookRepository.save(book)
                        .flatMap(book1 -> {

                            dto.getAuthors()
                                    .forEach(aLong -> bookAuthorRepository
                                            .save(new BookAuthor(aLong, book1.getId()))
                                            .doOnError(System.out::println)
                                            .subscribe());

                            Flux<Author> collect = authorRepository
                                    .findAllById(dto.getAuthors());

                            return collect
                                    .collectList()
                                    .map(a -> book1.getId());
                        })
                );
    }

    @Override
    public Mono<Long> edit(BookEditDto dto) {
        return Mono.just(dto)
                .flatMap(bookEditDto -> bookRepository.findById(bookEditDto.getId()))
                .map(book -> Book.fromEditDto(book, dto))
                .flatMap(book -> bookRepository.save(book)
                        .flatMap(book1 -> {
                            Flux<Author> collect = authorRepository
                                    .findAllById(dto.getAuthors());
                            return collect
                                    .collectList()
                                    .map(a -> book1.getId());
                        })
                );
    }

    @Override
    public Mono<BookDto> findById(Long id) {
        return bookRepository
                .findById(id)
                .log()
                .map(BookDto::toDto)
                .flatMap(dto -> authorRepository.findAllByBookId(dto.getId())
                        .map(AuthorDto::toDto)
                        .collectList()
                        .map(authors -> {
                            dto.setAuthors(authors);
                            return dto;
                        })
                );
    }

    @Override
    public Flux<BookDto> findAll() {
        return bookRepository
                .findAll()
                .map(BookDto::toDto)
                .flatMap(dto -> authorRepository.findAllByBookId(dto.getId())
                        .map(AuthorDto::toDto)
                        .collectList()
                        .map(authors -> {
                            dto.setAuthors(authors);
                            return dto;
                        })
                );
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return bookRepository.deleteById(id);
    }
}
