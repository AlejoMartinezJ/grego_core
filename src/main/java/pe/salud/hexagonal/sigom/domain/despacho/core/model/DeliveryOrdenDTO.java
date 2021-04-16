package pe.salud.hexagonal.sigom.domain.despacho.core.model;

import java.time.LocalDate;

public class DeliveryOrdenDTO {
    private String guiaNumber;
    private LocalDate guiaStartDate;

    public String getGuiaNumber() {
        return guiaNumber;
    }

    public void setGuiaNumber(String guiaNumber) {
        this.guiaNumber = guiaNumber;
    }
    public LocalDate getGuiaStartDate() {
        return guiaStartDate;
    }

    public void setGuiaStartDate(LocalDate guiaStartDate) {
        this.guiaStartDate = guiaStartDate;
    }
}
