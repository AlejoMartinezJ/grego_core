package pe.salud.hexagonal.sigom.domain.despacho.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.salud.hexagonal.sigom.domain.despacho.core.model.Guia;
import pe.salud.hexagonal.sigom.domain.despacho.core.ports.QueryTrackingAdapter;
import pe.salud.hexagonal.sigom.domain.despacho.infrastructure.GuiaRepository;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/")
public class GuiaTrackingController {

    private final QueryTrackingAdapter queryTrackingAdapter;
    private final GuiaRepository database;
    public GuiaTrackingController(QueryTrackingAdapter queryTrackingService, GuiaRepository database) {
        this.queryTrackingAdapter = queryTrackingService;
        this.database = database;
    }

    @GetMapping("/{guia}/events")
    public List<Object> listEventsForAccount(@PathVariable(value = "guia") String guiaNumber){
        Guia guia = database.findDistinctByGuiaNumber(guiaNumber);
        return queryTrackingAdapter.listEventsForTrack(guia.getTrack_number());
    }
}
