package pe.salud.hexagonal.sigom.domain.despacho.core.ports.incoming;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class DeliveryReportedEvent extends BaseEvent<String>{
    public final String deliveryStatus;
    public final String deliveryComment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public final LocalDate deliveryStatusDate;

    public DeliveryReportedEvent(String id, String deliveryStatus, String deliveryComment, LocalDate deliveryStatusDate) {
        super(id);
        this.deliveryStatus = deliveryStatus;
        this.deliveryComment = deliveryComment;
        this.deliveryStatusDate = deliveryStatusDate;
    }
}
