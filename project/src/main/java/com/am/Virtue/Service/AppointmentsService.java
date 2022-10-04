package com.am.Virtue.Service;

import com.am.Virtue.entities.Appointments;
import com.am.Virtue.entities.OperationLanguage;

import java.util.List;

public interface AppointmentsService {
    public Appointments findAppointmentsByAccId(Long id);

    public Appointments findPsychologists_AppointmentsById(Long id);

    public Appointments findAppointmentsByDateAndTime(String date, String time);

    public List<Appointments> findAllByAccountId(Long id);


    public Appointments save(Appointments appointments);

    public Appointments deactivateAppointmentById(Long id);

    public List<Appointments> findAllByOperationLanguage(OperationLanguage operationLanguage);


}
