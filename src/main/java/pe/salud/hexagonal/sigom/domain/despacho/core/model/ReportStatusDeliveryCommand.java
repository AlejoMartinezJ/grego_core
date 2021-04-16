package pe.salud.hexagonal.sigom.domain.despacho.core.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class ReportStatusDeliveryCommand extends BaseCommand<String> {
    public String status;
    public String comment;
    public LocalDate statusDate;

    public ReportStatusDeliveryCommand(String id, String status, String comment, LocalDate statusDate) {
        super(id);
        this.status = status;
        this.comment = comment;
        this.statusDate = statusDate;
    }
}
