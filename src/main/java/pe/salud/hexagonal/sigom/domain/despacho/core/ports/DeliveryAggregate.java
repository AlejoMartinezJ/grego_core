package pe.salud.hexagonal.sigom.domain.despacho.core.ports;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import pe.salud.hexagonal.sigom.domain.despacho.core.model.CreateDeliveryCommand;
import pe.salud.hexagonal.sigom.domain.despacho.core.model.ReportStatusDeliveryCommand;
import pe.salud.hexagonal.sigom.domain.despacho.core.ports.incoming.DeliveryCreatedEvent;
import pe.salud.hexagonal.sigom.domain.despacho.core.ports.incoming.DeliveryReportedEvent;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Aggregate
public class DeliveryAggregate {
    @AggregateIdentifier
    private String id;
    private String guiaNumber;
    private LocalDate guiaStartDate;
    private String deliveryStatus;
    private String deliveryComment;
    private LocalDate deliveryStatusDate;
    public DeliveryAggregate() {
    }
    @CommandHandler
    public DeliveryAggregate(CreateDeliveryCommand createDeliveryCommand){
        AggregateLifecycle.apply(new DeliveryCreatedEvent(createDeliveryCommand.id, createDeliveryCommand.guiaNumber, createDeliveryCommand.deliveryStartDate, "PAQUETES EN ALMACEN READY FOR PICK UP"));
    }

    @EventSourcingHandler
    protected void on(DeliveryCreatedEvent deliveryCreatedEvent){
        this.id = deliveryCreatedEvent.id;
        this.guiaNumber = deliveryCreatedEvent.guiaNumber;
        this.guiaStartDate = deliveryCreatedEvent.startDate;
        this.deliveryComment = deliveryCreatedEvent.deliveryComment;

        //AggregateLifecycle.apply(new DeliveryActivatedEvent(this.id, Status.ACTIVATED));
    }
    @CommandHandler
    protected void on(ReportStatusDeliveryCommand reportCommand){
        AggregateLifecycle.apply(new DeliveryReportedEvent(reportCommand.id, reportCommand.status, reportCommand.comment, reportCommand.statusDate));
    }
    @EventSourcingHandler
    protected void on(DeliveryReportedEvent reportEvent){
        this.deliveryStatus = reportEvent.deliveryStatus;
        this.deliveryComment = reportEvent.deliveryComment;
        this.guiaStartDate = reportEvent.deliveryStatusDate;
    }

}
