package pe.salud.hexagonal.sigom.domain.despacho.core.ports.incoming;

import pe.salud.hexagonal.sigom.domain.despacho.core.model.DeliveryOrdenDTO;
import pe.salud.hexagonal.sigom.domain.despacho.core.model.DeliveryStatusDTO;

import java.util.concurrent.CompletableFuture;

public interface DeliveryUseCases {
    public CompletableFuture<String> reportStatusOfDelivery(String track,DeliveryStatusDTO deliveryStatusDTO);

}
