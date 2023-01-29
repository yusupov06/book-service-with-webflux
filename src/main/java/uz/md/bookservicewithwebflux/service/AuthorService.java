package uz.md.bookservicewithwebflux.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.md.bookservicewithwebflux.dto.AuthorAddDto;
import uz.md.bookservicewithwebflux.dto.AuthorDto;
import uz.md.bookservicewithwebflux.dto.AuthorEditDto;

public interface AuthorService {

    Mono<Long> add(AuthorAddDto author);
    Mono<Long> edit(AuthorEditDto author);

    Mono<AuthorDto> findById(Long id);

    Flux<AuthorDto> findByFirstName(String name);

    Flux<AuthorDto> findAll();

    Mono<Void> deleteById(Long id);
}
