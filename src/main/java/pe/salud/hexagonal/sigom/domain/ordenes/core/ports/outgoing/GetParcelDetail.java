package pe.salud.hexagonal.sigom.domain.ordenes.core.ports.outgoing;

import pe.salud.hexagonal.sigom.domain.ordenes.core.model.Parcel;
import pe.salud.hexagonal.sigom.domain.ordenes.core.model.ParcelDTO;

import java.util.List;

public interface GetParcelDetail {
    Parcel getParcel(Long parcelId);
    List<ParcelDTO> listAllParcel(String user);
}
