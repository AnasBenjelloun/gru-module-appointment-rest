package fr.paris.lutece.plugins.appointment.modules.rest.service;

import fr.paris.lutece.plugins.appointment.modules.rest.pojo.AppointmentSlotsSearchPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.InfoSlot;

import java.util.List;
import java.util.Map;

public class AppointmentRestService implements IAppointmentRestService {
    private static AppointmentRestService _instance;

    public static synchronized AppointmentRestService getInstance( )
    {
        if ( _instance == null )
        {
            _instance = new AppointmentRestService( );
        }

        return _instance;
    }
    @Override
    public Map<String, List<InfoSlot>> getAvailableTimeSlots(AppointmentSlotsSearchPOJO search) throws Exception {
        AppointmentSlotsService _appointmentSlotsService = AppointmentSlotsService.getInstance();

        Map<String, List<InfoSlot>> listeAvailableTimeSlots = _appointmentSlotsService.getAvailableTimeSlotsAsList(search);

        return listeAvailableTimeSlots;
    }
}
