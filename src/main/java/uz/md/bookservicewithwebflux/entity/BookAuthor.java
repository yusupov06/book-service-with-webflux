package uz.md.bookservicewithwebflux.entity;


import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("author_book")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class BookAuthor {
    @Column("id")
    private Long id;
    @Column("author_id")
    private Long authorId;
    @Column("book_id")
    private Long bookId;
    public BookAuthor(Long authorId, Long bookId){
        this.authorId = authorId;
        this.bookId = bookId;
    }
}
