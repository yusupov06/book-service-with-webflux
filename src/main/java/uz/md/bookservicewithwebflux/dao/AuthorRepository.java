package uz.md.bookservicewithwebflux.dao;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import uz.md.bookservicewithwebflux.entity.Author;

@Repository
public interface AuthorRepository extends R2dbcRepository<Author, Long> {
    Flux<Author> findByFirstName(String firstName);

    Flux<Author> findByLastName(String lastName);

    @Query("""
            select a from author_book
                     join author a on a.id = author_book.author_id
                     join book b on b.id = author_book.book_id
            where b.id = :book_id;
            """)
    Flux<Author> findAllByBookId(Long book_id);
}
