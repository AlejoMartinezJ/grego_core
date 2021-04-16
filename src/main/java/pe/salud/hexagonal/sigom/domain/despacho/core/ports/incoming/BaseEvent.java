package pe.salud.hexagonal.sigom.domain.despacho.core.ports.incoming;

public class BaseEvent<T> {
    public final T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}
