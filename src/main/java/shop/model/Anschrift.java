package shop.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Anschrift {

    public String anschriftId;

    public String strasse;

    public Integer hausnummer;

    public Integer plz;

    public String ort;

    public String email;

    public Kunde kunde;



}
