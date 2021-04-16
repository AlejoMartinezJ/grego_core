package pe.salud.hexagonal.sigom.domain.ordenes.core.ports.incoming;

import javassist.NotFoundException;
import pe.salud.hexagonal.sigom.domain.ordenes.core.model.Parcel;

public interface DeleteParcel {
    Parcel deleteParcel(Long id) throws NotFoundException;
}
