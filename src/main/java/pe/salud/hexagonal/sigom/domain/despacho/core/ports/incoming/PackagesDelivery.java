package pe.salud.hexagonal.sigom.domain.despacho.core.ports.incoming;

import pe.salud.hexagonal.sigom.domain.despacho.application.AddNewGuiaDTO;
import pe.salud.hexagonal.sigom.domain.despacho.core.model.Guia;

import java.util.concurrent.ExecutionException;

public interface PackagesDelivery {
    void createGuia(AddNewGuiaDTO addNewGuiaDTO) throws ExecutionException, InterruptedException;

}
