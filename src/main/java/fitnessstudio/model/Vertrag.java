package fitnessstudio.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement // required for XML binding
public class Vertrag {

    public String VertragsNr;

    public String Vertragsart;

    public Date ageschlossenAm;

    public Date gekündigtZum;

    public float Monatsbeitrag;

}
