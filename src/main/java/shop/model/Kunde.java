package shop.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement // required for XML binding
public class Kunde {

    public String kundenNr;

    public String vorname;

    public String nachname;

    public String geschlecht;

    public String gebDatum;

    public String bankverbindung_Iban;

}
