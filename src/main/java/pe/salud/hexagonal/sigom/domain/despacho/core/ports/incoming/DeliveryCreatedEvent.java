package pe.salud.hexagonal.sigom.domain.despacho.core.ports.incoming;

import java.time.LocalDate;

public class DeliveryCreatedEvent extends BaseEvent<String>{
    public final String guiaNumber;
    public final LocalDate startDate;
    public final String deliveryComment;

    public DeliveryCreatedEvent(String id, String guiaNumber, LocalDate startDate, String deliveryComment) {
        super(id);
        this.guiaNumber = guiaNumber;
        this.startDate = startDate;
        this.deliveryComment = deliveryComment;
    }
}
