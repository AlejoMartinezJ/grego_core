package pe.salud.hexagonal.sigom.domain.ordenes.core.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="document")
@EqualsAndHashCode
@ToString
public class Document {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="type", unique=true)
    private String typeDocument;

    @Column(name="imageLink", columnDefinition="TEXT")
    @EqualsAndHashCode.Exclude
    private String imageLink;

    public Document(String type, String imageLink) {
        this.typeDocument = type;
        this.imageLink = imageLink;
    }

    private Document() {
    }
}
