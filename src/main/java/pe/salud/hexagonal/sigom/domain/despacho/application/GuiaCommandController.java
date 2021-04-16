package pe.salud.hexagonal.sigom.domain.despacho.application;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.salud.hexagonal.sigom.domain.despacho.core.GuiaAdapter;
import pe.salud.hexagonal.sigom.domain.despacho.core.model.CreateDeliveryCommand;
import pe.salud.hexagonal.sigom.domain.despacho.core.model.DeliveryStatusDTO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GuiaCommandController {

    @Autowired
    private final GuiaAdapter service;

    @PostMapping(value="/add/guia", produces = "application/json")
    public ResponseEntity<String> addNewGuia(@RequestBody AddNewGuiaDTO dto) throws ExecutionException, InterruptedException {
        service.createGuia(dto);
        return new ResponseEntity<>("Guia Created", HttpStatus.CREATED);
    }

    @PutMapping(value = "/report/{track}")
    public CompletableFuture<String> sendStatusOfDelivery(@PathVariable(value = "track") String trackNumber,
                                                          @RequestBody DeliveryStatusDTO statusDTO){
        return service.reportStatusOfDelivery(trackNumber, statusDTO);
    }

}
