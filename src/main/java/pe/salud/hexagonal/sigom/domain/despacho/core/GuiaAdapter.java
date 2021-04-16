package pe.salud.hexagonal.sigom.domain.despacho.core;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import pe.salud.hexagonal.sigom.domain.despacho.application.AddNewGuiaDTO;
import pe.salud.hexagonal.sigom.domain.despacho.core.model.CreateDeliveryCommand;
import pe.salud.hexagonal.sigom.domain.despacho.core.model.*;
import pe.salud.hexagonal.sigom.domain.despacho.core.ports.incoming.DeliveryUseCases;
import pe.salud.hexagonal.sigom.domain.despacho.core.ports.incoming.PackagesDelivery;
import pe.salud.hexagonal.sigom.domain.despacho.infrastructure.GuiaRepository;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class GuiaAdapter implements PackagesDelivery, DeliveryUseCases {

    private GuiaRepository database;
    private final CommandGateway commandGateway;
    public GuiaAdapter(GuiaRepository guiaRepository, CommandGateway commandGateway) {
        this.database = guiaRepository;
        this.commandGateway = commandGateway;
    }

    @Override
    public void createGuia(AddNewGuiaDTO dto) throws ExecutionException, InterruptedException {
        Guia existingGuia = database.findByGuiaNumberAndOrdenNumber(dto.guia, dto.orden);
        if( existingGuia == null){
            String track = (String) commandGateway.send(new CreateDeliveryCommand(UUID.randomUUID().toString(), dto.guia, LocalDate.now())).get();
            Guia guia = new Guia(dto.orden, dto.guia,dto.user, track);
            database.save(guia);
        }
    }

    @Override
    public CompletableFuture<String> reportStatusOfDelivery(String trackNumber, DeliveryStatusDTO statusDTO) {
        return commandGateway.send(new ReportStatusDeliveryCommand(trackNumber, statusDTO.getStatus(), statusDTO.getComment(),statusDTO.getStatusDate()));

    }
}
