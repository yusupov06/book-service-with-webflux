package uz.md.bookservicewithwebflux.service;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import uz.md.bookservicewithwebflux.dto.BookDto;
import uz.md.bookservicewithwebflux.dto.BookAddDto;
import uz.md.bookservicewithwebflux.dto.BookEditDto;

public interface BookService {

    Mono<Long> add(BookAddDto dto);

    Mono<Long> edit(BookEditDto dto);

    Mono<BookDto> findById(Long id);

    Flux<BookDto> findAll();

    Mono<Void> deleteById(Long id);

}
