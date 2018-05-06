package fitnessstudio.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Anschrift")
public class Anschrift {

    public String AnschriftNr;

    public String Straße;

    public Integer HausNr;

    public Integer PLZ;

    public String Ort;

    public String eMail;



}
