package uz.md.bookservicewithwebflux.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.md.bookservicewithwebflux.dto.AuthorAddDto;
import uz.md.bookservicewithwebflux.dto.AuthorDto;
import uz.md.bookservicewithwebflux.dto.AuthorEditDto;
import uz.md.bookservicewithwebflux.service.AuthorService;


@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/add")
    public Mono<Long> add(@RequestBody AuthorAddDto author) {
        return authorService.add(author);
    }

    @GetMapping("/{id}")

    public Mono<AuthorDto> findById(@PathVariable("id") Long id) {
        return authorService.findById(id);
    }


    @GetMapping("/name/{name}")
    public Flux<AuthorDto> findByName(@PathVariable("name") String name) {
        return authorService.findByFirstName(name);
    }

    @GetMapping()
    public Flux<AuthorDto> findAll() {
        return authorService.findAll();
    }

    @PutMapping(value = "/edit")
    public Mono<Long> update(@RequestBody AuthorEditDto e) {
        return authorService.edit(e);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        authorService.deleteById(id).subscribe();
    }

}
