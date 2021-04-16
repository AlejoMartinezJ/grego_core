package pe.salud.hexagonal.sigom.domain.despacho.core.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class DeliveryStatusDTO {
    private String status;
    private String comment;
    private LocalDate statusDate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(LocalDate statusDate) {
        this.statusDate = statusDate;
    }
}
