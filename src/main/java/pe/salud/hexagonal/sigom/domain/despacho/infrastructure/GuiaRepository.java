package pe.salud.hexagonal.sigom.domain.despacho.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.salud.hexagonal.sigom.domain.despacho.core.model.Guia;

public interface GuiaRepository extends JpaRepository<Guia, Long> {
    Guia findByGuiaNumberAndOrdenNumber(String guia, String orden);
    Guia findDistinctByGuiaNumber(String guia);
}
