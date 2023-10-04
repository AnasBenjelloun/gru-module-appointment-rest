package fr.paris.lutece.plugins.appointment.modules.rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.AppointmentSlotsSearchPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.InfoSlot;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.MeetingPointPOJO;
import fr.paris.lutece.util.httpaccess.HttpAccessException;

import java.util.List;
import java.util.Map;

public class AppointmentRestService implements IAppointmentRestService {
    @Override
    public Map<String, List<InfoSlot>> getAvailableTimeSlots(AppointmentSlotsSearchPOJO search) throws HttpAccessException, JsonProcessingException {
        AppointmentSlotsService _appointmentSlotsService = AppointmentSlotsService.getInstance();

        return _appointmentSlotsService.getAvailableTimeSlotsAsList(search);
    }

    @Override
    public List<MeetingPointPOJO> getManagedMeetingPoints() throws HttpAccessException, JsonProcessingException {
        AppointmentMeetingPointsService _appointmentMeetingPointsService = AppointmentMeetingPointsService.getInstance();

        return _appointmentMeetingPointsService.getManagedMeetingPoints();
    }
}
