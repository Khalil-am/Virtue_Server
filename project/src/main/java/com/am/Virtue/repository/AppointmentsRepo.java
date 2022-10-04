package com.am.Virtue.repository;

import com.am.Virtue.entities.*;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface AppointmentsRepo extends CrudRepository<Appointments, Long> {
    public Appointments findAppointmentsByPsychologistsId(Long id);

    public Appointments findAppointmentsByAccount_Id(Long id);

    public Appointments findAppointmentsByAppoDateAndAppoTime(String date,String time);
    //might break because of status
    public Appointments findAppointmentsByIdAndStatus(Long id, Status status);
    public List<Appointments> findAllByAccountId(Long id );

    public List<Appointments> findAllByOperationLanguage(OperationLanguage operationLanguage );
    public Appointments save(Appointments appointments);
}
