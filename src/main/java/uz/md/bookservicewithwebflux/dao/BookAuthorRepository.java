package uz.md.bookservicewithwebflux.dao;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import uz.md.bookservicewithwebflux.entity.BookAuthor;

@Repository
public interface BookAuthorRepository extends R2dbcRepository<BookAuthor, Long> {
}
