package uz.md.bookservicewithwebflux.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import uz.md.bookservicewithwebflux.dto.BookAddDto;
import uz.md.bookservicewithwebflux.dto.BookEditDto;

import java.util.Collection;

@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Table
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class Book {
    @Id
    @JsonProperty("id")
    @Column("id")
    Long id;
    @JsonProperty("title")
    @Column("title")
    String title;
    @JsonProperty("description")
    @Column("description")
    String description;
    @JsonProperty("pageCount")
    @Column("pageCount")
    Integer pageCount;
    Collection<Long> authors;

    public static Book fromDTO(BookAddDto bookAddDto) {
        return Book.builder()
                .title(bookAddDto.getTitle())
                .description(bookAddDto.getDescription())
                .pageCount(bookAddDto.getPageCount())
                .build();
    }

    public static Book fromEditDto(Book book, BookEditDto dto) {
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        book.setPageCount(dto.getPageCount());
        return book;
    }
}
