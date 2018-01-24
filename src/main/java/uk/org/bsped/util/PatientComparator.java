package uk.org.bsped.util;


import uk.org.bsped.model.Patient;

import java.util.Comparator;

public class PatientComparator implements Comparator<Patient> {

    @Override
    public int compare(Patient p1, Patient p2) {
        return p1.getPatientId().compareTo(p2.getPatientId());
    }

}
