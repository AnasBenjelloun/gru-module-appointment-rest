package fr.paris.lutece.plugins.appointment.modules.rest.service;

import fr.paris.lutece.plugins.appointment.modules.rest.pojo.AppointmentSlotsSearchPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.InfoSlot;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.MeetingPointPOJO;

import java.util.List;
import java.util.Map;

public class AppointmentRestService implements IAppointmentRestService {
    @Override
    public Map<String, List<InfoSlot>> getAvailableTimeSlots(AppointmentSlotsSearchPOJO search) throws Exception {
        AppointmentSlotsService _appointmentSlotsService = AppointmentSlotsService.getInstance();

        return _appointmentSlotsService.getAvailableTimeSlotsAsList(search);
    }

    @Override
    public List<MeetingPointPOJO> getManagedMeetingPoints() throws Exception {
        AppointmentMeetingPointsService _appointmentMeetingPointsService = AppointmentMeetingPointsService.getInstance();

        return _appointmentMeetingPointsService.getManagedMeetingPoints();
    }
}
