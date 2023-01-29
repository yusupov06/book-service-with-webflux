package uz.md.bookservicewithwebflux.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookAddDto {
    private String title;
    private String description;
    private Integer pageCount;
    private List<Long> authors;
}
