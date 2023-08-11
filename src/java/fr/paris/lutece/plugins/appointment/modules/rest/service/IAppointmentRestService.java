package fr.paris.lutece.plugins.appointment.modules.rest.service;

import fr.paris.lutece.plugins.appointment.modules.rest.pojo.AppointmentSlotsSearchPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.InfoSlot;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.MeetingPointPOJO;

import java.util.List;
import java.util.Map;

public interface IAppointmentRestService {
    public Map<String, List<InfoSlot>> getAvailableTimeSlots(AppointmentSlotsSearchPOJO search) throws Exception;
    public List<MeetingPointPOJO> getManagedMeetingPoints( ) throws Exception;
}
