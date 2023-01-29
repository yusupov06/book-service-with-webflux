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
import uz.md.bookservicewithwebflux.dto.AuthorAddDto;
import uz.md.bookservicewithwebflux.dto.AuthorEditDto;

import java.util.Collection;

@Table
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class Author {

    @Id
    @JsonProperty("id")
    @Column("id")
    Long id;
    @JsonProperty("firstName")
    @Column("first_name")
    String firstName;
    @JsonProperty("lastName")
    @Column("last_name")
    String lastName;
    Collection<Long> books;

    public static Author fromAddDTO(AuthorAddDto dto, Book book) {
        return Author.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
    }

    @Override
    public String toString() {
        return "Author [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }

    public static Author fromDTO(AuthorAddDto dto) {
        return Author.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
    }

    public static Author fromEditDTO(Author author, AuthorEditDto authorEditDto) {
        author.setFirstName(authorEditDto.getFirstName());
        author.setLastName(authorEditDto.getLastName());
        return author;
    }
}