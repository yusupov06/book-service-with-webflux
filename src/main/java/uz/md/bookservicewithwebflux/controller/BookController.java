package uz.md.bookservicewithwebflux.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.md.bookservicewithwebflux.dto.BookAddDto;
import uz.md.bookservicewithwebflux.dto.BookDto;
import uz.md.bookservicewithwebflux.dto.BookEditDto;
import uz.md.bookservicewithwebflux.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/add")
    public Mono<Long> add(@RequestBody BookAddDto book) {
        return bookService.add(book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Mono<BookDto> findById(@PathVariable("id") Long id) {
        return bookService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<BookDto> findAll() {
        return bookService.findAll();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Long> update(@RequestBody BookEditDto e) {
        return bookService.edit(e);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        bookService.deleteById(id).subscribe();
    }

}
