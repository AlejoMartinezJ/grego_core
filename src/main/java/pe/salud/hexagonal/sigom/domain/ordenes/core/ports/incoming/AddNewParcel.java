package pe.salud.hexagonal.sigom.domain.ordenes.core.ports.incoming;

import javassist.NotFoundException;
import pe.salud.hexagonal.sigom.domain.ordenes.core.model.AddNewParcelCommand;
import pe.salud.hexagonal.sigom.domain.ordenes.core.model.ChangeParcelCommand;
import pe.salud.hexagonal.sigom.domain.ordenes.core.model.Parcel;

public interface AddNewParcel {
    Parcel handle(AddNewParcelCommand addNewParcelCommand);
    Parcel updateParcel(ChangeParcelCommand changeParcelCommand) throws NotFoundException;
}
