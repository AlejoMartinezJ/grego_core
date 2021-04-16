package pe.salud.hexagonal.sigom.domain.despacho.core.ports.incoming;

import java.util.List;

public interface TrackDelivery {
    public List<Object> listEventsForTrack(String trackNumber);

}
