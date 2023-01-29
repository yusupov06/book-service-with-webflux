package uz.md.bookservicewithwebflux.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.md.bookservicewithwebflux.entity.Book;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDto {
    private Long id;
    private String title;
    private String description;
    private Integer pageCount;
    private List<AuthorDto> authors;

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getDescription(), book.getPageCount(), null);
    }

    public void addAuthor(AuthorDto dto) {
        authors.add(dto);
    }
}
