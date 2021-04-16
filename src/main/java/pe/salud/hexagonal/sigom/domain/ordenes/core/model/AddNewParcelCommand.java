package pe.salud.hexagonal.sigom.domain.ordenes.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddNewParcelCommand {
    private String ordenNumber;
    private String ordenDetail;
    private String userName;
}
