package pe.salud.hexagonal.sigom.domain.ordenes.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="parcel")
@EqualsAndHashCode
@Getter
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="orden_number")
    private String ordenNumber;

    @Column(name="orden_detail", columnDefinition="TEXT")
    private String ordenDetail;

    @Column(name="carrier_name")
    private String carrier;
    @ManyToMany(fetch = FetchType.EAGER, cascade= {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name="parcel_document",
            joinColumns=@JoinColumn(name="parcel_id"),
            inverseJoinColumns=@JoinColumn(name="document_id"))
    private Set<Document> documents;

    @Column(name="packages_count")
    private int packages;

    @Column(name="userName")
    private String userName;

    @Column(name="status")
    private String status;

    public void setPackages(int packages) {
        this.packages = packages;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Parcel(String ordenNumber, String ordenDetail, String userName){
        this.ordenNumber = ordenNumber;
        this.ordenDetail = ordenDetail;
        this.userName = userName;
        this.status = "CREATED";
        this.packages = 0;
        this.carrier = "";
        this.documents = new HashSet<Document>();
    }
    private Parcel(){

    }

    @Override
    public String toString() {
        return "Parcel{" +
                "id=" + id +
                ", ordenNumber='" + ordenNumber + '\'' +
                ", ordenDetail='" + ordenDetail + '\'' +
                ", carrier='" + carrier + '\'' +
                ", documents=" + documents +
                ", packages=" + packages +
                ", userName='" + userName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
