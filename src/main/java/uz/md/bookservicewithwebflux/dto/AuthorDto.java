package uz.md.bookservicewithwebflux.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.md.bookservicewithwebflux.entity.Author;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;

    public static AuthorDto toDto(Author author){
        return new AuthorDto(author.getId(), author.getFirstName(), author.getLastName());
    }

}
