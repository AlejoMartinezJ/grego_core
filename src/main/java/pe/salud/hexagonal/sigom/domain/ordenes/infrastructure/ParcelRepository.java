package pe.salud.hexagonal.sigom.domain.ordenes.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.salud.hexagonal.sigom.domain.ordenes.core.model.Parcel;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {
}
