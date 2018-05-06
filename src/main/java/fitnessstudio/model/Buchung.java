package fitnessstudio.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Buchungen")
public class Buchung {

    public String BuchungNr;

    public String Datum;

    public String Kurs;
}
