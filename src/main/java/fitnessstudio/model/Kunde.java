package fitnessstudio.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement // required for XML binding
public class Kunde {

    public String AnschriftID;

    public String Bankverbindung_Iban;

    public String GebDatum;

    public String Geschlecht;

    public String KundeNr;

    public String Nachname;

    public String Vorname;



}
