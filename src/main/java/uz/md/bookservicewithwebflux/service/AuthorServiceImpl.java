package uz.md.bookservicewithwebflux.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.md.bookservicewithwebflux.dao.AuthorRepository;
import uz.md.bookservicewithwebflux.dto.AuthorAddDto;
import uz.md.bookservicewithwebflux.dto.AuthorDto;
import uz.md.bookservicewithwebflux.dto.AuthorEditDto;
import uz.md.bookservicewithwebflux.dto.BookDto;
import uz.md.bookservicewithwebflux.entity.Author;
import uz.md.bookservicewithwebflux.entity.Book;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public Mono<Long> add(AuthorAddDto dto) {
        return Mono.just(dto)
                .map(Author::fromDTO)
                .flatMap(author -> authorRepository
                        .save(author)
                        .map(Author::getId));
    }

    @Override
    public Mono<Long> edit(AuthorEditDto editDto) {
        return Mono.just(editDto)
                .flatMap(authorEditDto -> authorRepository.findById(authorEditDto.getId()))
                .map(author1 -> Author.fromEditDTO(author1, editDto))
                .flatMap(author -> authorRepository
                        .save(author)
                        .map(Author::getId));
    }

    @Override
    public Mono<AuthorDto> findById(Long id) {
        return authorRepository
                .findById(id)
                .log()
                .doOnError(throwable -> log.error(String.valueOf(throwable)))
                .map(AuthorDto::toDto);
    }

    @Override
    public Flux<AuthorDto> findByFirstName(String name) {
        return authorRepository
                .findByFirstName(name)
                .map(AuthorDto::toDto);
    }

    @Override
    public Flux<AuthorDto> findAll() {
        return authorRepository
                .findAll()
                .map(AuthorDto::toDto);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return authorRepository.deleteById(id);
    }
}
