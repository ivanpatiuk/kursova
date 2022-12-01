package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

// Клас бухгалтер для передачі у відповіді запиту
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountantDTO {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
}
//************************************************
