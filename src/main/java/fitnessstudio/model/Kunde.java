package fitnessstudio.model;

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

    public List<Buchung> Buchungen;

}
