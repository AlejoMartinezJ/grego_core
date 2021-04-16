package pe.salud.hexagonal.sigom.domain.ordenes.application;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.salud.hexagonal.sigom.domain.ordenes.core.ParcelAdapter;
import pe.salud.hexagonal.sigom.domain.ordenes.core.model.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ParcelCommandController {

    @Autowired
    private final ParcelAdapter service;

    @GetMapping(value = "/{query}", produces = "application/json")
    public ResponseEntity<List<ParcelDTO>> searchForParcel(@PathVariable String query){
        List<ParcelDTO> result = service.listAllParcel(query);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<ParcelDTO>> searchForAll(){
        List<ParcelDTO> result = service.listAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping(value="/add", produces = "application/json")
    public ResponseEntity<Parcel> addNewParcel(@RequestBody AddNewParcelCommand addNewParcelCommand){
        System.out.println(addNewParcelCommand.getOrdenNumber());
        Parcel parcel = service.handle(addNewParcelCommand);
        return new ResponseEntity<Parcel>(parcel, HttpStatus.CREATED);
    }
    @GetMapping(value = "delete/{id}", produces = "application/json")
    public ResponseEntity<Long> searchForParcel(@PathVariable Long id) throws NotFoundException {
        service.deleteParcel(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    @PostMapping(value="/change", produces = "application/json")
    public ResponseEntity<Parcel> changeParcel(@RequestBody ChangeParcelCommand changeParcelCommand) throws NotFoundException {
        System.out.println(changeParcelCommand.getId());
        Parcel parcel = service.updateParcel(changeParcelCommand);
        return new ResponseEntity<>(parcel, HttpStatus.CREATED);
    }
}
