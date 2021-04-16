package pe.salud.hexagonal.sigom.domain.ordenes.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeParcelCommand {
    private Long id;
    private String carrier;
    private int packages;
    private String status;
}
