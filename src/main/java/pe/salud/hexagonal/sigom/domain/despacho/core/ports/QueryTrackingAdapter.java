package pe.salud.hexagonal.sigom.domain.despacho.core.ports;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;
import pe.salud.hexagonal.sigom.domain.despacho.core.ports.incoming.TrackDelivery;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryTrackingAdapter implements TrackDelivery {

    private final EventStore eventStore;

    public QueryTrackingAdapter(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public List<Object> listEventsForTrack(String trackNumber) {
        return eventStore.readEvents(trackNumber).asStream().map( s -> s.getPayload()).collect(Collectors.toList());

    }



}
