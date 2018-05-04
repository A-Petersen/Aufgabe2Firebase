package fitnessstudio.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement // required for XML binding
public class Kunde {

    public String AnschriftID;

    public String KundeNr;

    public String Bankverbindung_Iban;

    public String Geschlecht;

    public String Nachname;

    public String Vorname;

    public String GebDatum;

}
