package pe.salud.hexagonal.sigom.domain.despacho.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name="guia")
@EqualsAndHashCode
@Getter
public class Guia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="guia_number")
    private String guiaNumber;

    @Column(name="orden_number")
    private String ordenNumber;

    @Column(name="track_number")
    private String track_number;

    @Column(name="user_name")
    private String userName;

    public void setTrack_number(String track_number) {
        this.track_number = track_number;
    }

    private Guia() {
    }
    public Guia(String nroOrden, String nroGuia, String user, String trackId){
        this.ordenNumber = nroOrden;
        this.guiaNumber = nroGuia;
        this.track_number = trackId;
        this.userName = user;
    }
    @Override
    public String toString() {
        return "Guia{" +
                "id=" + id +
                ", guiaNumber='" + guiaNumber + '\'' +
                ", ordenNumber='" + ordenNumber + '\'' +
                ", track_number='" + track_number + '\'' +
                '}';
    }
}
