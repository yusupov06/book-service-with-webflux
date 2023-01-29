package uz.md.bookservicewithwebflux.dao;

import org.springframework.stereotype.Repository;
import uz.md.bookservicewithwebflux.entity.Book;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
@Repository
public interface BookRepository extends R2dbcRepository<Book, Long> {
}
