package shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Anschrift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long anschriftId;

    @Column(nullable = false)
    public String strasse;

    @Column(nullable = false)
    public Integer hausnummer;

    @Column(nullable = false)
    public Integer plz;

    @Column(nullable = false)
    public String ort;

    public String email;

    @OneToOne(mappedBy = "kunde")
    @JsonIgnore
    @XmlTransient
    public Kunde kunde;

    @Transient
    public String jsonWebToken;


}
