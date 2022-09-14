package com.am.Virtue.Service;

import com.am.Virtue.entities.Appointments;

public interface AppointmentsService {
    public Appointments findAppointmentsByAccId(Long id);
    public Appointments findPsychologists_AppointmentsById(Long id);
    public Appointments save(Appointments appointments);

}
