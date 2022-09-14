package com.am.Virtue.repository;

import com.am.Virtue.entities.Account;
import com.am.Virtue.entities.Appointments;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentsRepo extends CrudRepository<Appointments, Long> {
    public Appointments findAppointmentsByPsychologistsId(Long id);

    public Appointments findAppointmentsByAccount_Id(Long id);


    public Appointments save(Appointments appointments);
}
