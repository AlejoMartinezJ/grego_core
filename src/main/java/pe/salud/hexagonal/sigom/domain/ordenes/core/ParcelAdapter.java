package pe.salud.hexagonal.sigom.domain.ordenes.core;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import pe.salud.hexagonal.sigom.domain.ordenes.core.model.*;
import pe.salud.hexagonal.sigom.domain.ordenes.core.ports.incoming.AddNewParcel;
import pe.salud.hexagonal.sigom.domain.ordenes.core.ports.incoming.DeleteParcel;
import pe.salud.hexagonal.sigom.domain.ordenes.core.ports.outgoing.GetParcelDetail;
import pe.salud.hexagonal.sigom.domain.ordenes.infrastructure.ParcelRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParcelAdapter implements AddNewParcel, GetParcelDetail, DeleteParcel {

    private ParcelRepository database;

    public ParcelAdapter(ParcelRepository database) {
        this.database = database;
    }

    @Override
    public Parcel handle(AddNewParcelCommand command) {
        Parcel parcel = new Parcel(command.getOrdenNumber(), command.getOrdenDetail(),command.getUserName());
        Parcel parcelSaved = database.save(parcel);
        return parcelSaved;
    }

    @Override
    public Parcel updateParcel(ChangeParcelCommand changeParcelCommand) throws NotFoundException {
        Optional<Parcel> obj = database.findById(changeParcelCommand.getId());
        if(obj.isPresent()){
            Parcel parcel = obj.get();
            parcel.setCarrier(changeParcelCommand.getCarrier());
            parcel.setPackages(changeParcelCommand.getPackages());
            parcel.setStatus(changeParcelCommand.getStatus());
            Parcel updatedParcel = database.save(parcel);
            return updatedParcel;
        } else {
            throw new NotFoundException("Parcel not found");
        }
    }

    @Override
    public Parcel getParcel(Long parcelId) {
        return database.getOne(parcelId);
    }

    @Override
    public List<ParcelDTO> listAllParcel(String user) {
        return database.findAll().stream()
                .filter(parcel -> parcel.getUserName().equals(user))
                .map(parcel -> new ParcelDTO(parcel.getId(),
                                             parcel.getOrdenNumber(),
                                             parcel.getOrdenDetail(),
                                             parcel.getCarrier(),
                                             parcel.getPackages(), parcel.getStatus()))

                .collect(Collectors.toList());

    }
    public List<ParcelDTO> listAll(){
        return database.findAll().stream()
                .map(parcel -> new ParcelDTO(parcel.getId(),
                                             parcel.getOrdenNumber(),
                                             parcel.getOrdenDetail(),
                                             parcel.getCarrier(),
                                             parcel.getPackages(), parcel.getStatus() ))
                .collect(Collectors.toList());
    }

    @Override
    public Parcel deleteParcel(Long id) throws NotFoundException {
        Optional<Parcel> obj = database.findById(id);
        if(obj.isPresent()){
            Parcel parcel = obj.get();
            database.delete(parcel);
            return parcel;
        } else {
            throw new NotFoundException("Parcel not found");
        }
    }

}
