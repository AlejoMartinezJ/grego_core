package pe.salud.hexagonal.sigom.domain.despacho.core.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class CreateDeliveryCommand extends BaseCommand<String> {
    public final String guiaNumber;
    public final LocalDate deliveryStartDate;

    public CreateDeliveryCommand(String id, String guiaNumber, LocalDate deliveryStartDate) {
        super(id);
        this.guiaNumber = guiaNumber;
        this.deliveryStartDate = deliveryStartDate;
    }
}
