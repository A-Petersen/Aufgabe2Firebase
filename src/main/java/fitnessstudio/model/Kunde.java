package fitnessstudio.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement // required for XML binding
public class Kunde {

    public String AnschriftID;

    public String Bankverbindung_Iban;

    public String GebDatum;

    public String Geschlecht;

    public String KundeNr;

    public String Nachname;

    public String Vorname;

    @XmlElementWrapper(name="Buchungen")
    @XmlElement(name="Buchungen")
    public List<Buchung> Buchungen;

}
