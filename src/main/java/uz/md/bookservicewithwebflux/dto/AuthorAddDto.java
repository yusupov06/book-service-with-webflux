package uz.md.bookservicewithwebflux.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorAddDto {
    private String firstName;
    private String lastName;
}
