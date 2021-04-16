package pe.salud.hexagonal.sigom.domain.ordenes.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParcelDTO {
    private Long id;
    private String ordenNumber;
    private String ordenDetail;
    private String carrier;
    private int    packages;
    private String status;
}
