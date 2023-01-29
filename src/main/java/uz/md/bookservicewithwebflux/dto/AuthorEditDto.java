package uz.md.bookservicewithwebflux.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorEditDto extends AuthorAddDto {
    private Long id;

    public AuthorEditDto(Long id, String firstName, String lastName) {
        super(firstName, lastName);
        this.id = id;
    }

}
