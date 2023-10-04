package fr.paris.lutece.plugins.appointment.modules.rest.business.providers;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.paris.lutece.util.httpaccess.HttpAccessException;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentDataProvider {

    /**
     * get provider name
     *
     * @return
     */
    String getName( );

    /**
     * getAvailableTimeSlot
     * returns available time slots.
     *
     * @param appointmentIds
     * @param startDate
     * @param endDate
     * @return a JSON string
     * @throws java.lang.Exception
     */
    String getAvailableTimeSlot(List<String> appointmentIds, LocalDate startDate, LocalDate endDate, Integer documentNumber ) throws HttpAccessException, JsonProcessingException;

    /**
     * getManagedMeetingPoints
     * returns the list of Meeting Points we handle
     *
     * @return a JSON string
     * @throws java.lang.Exception
     */
    String getManagedMeetingPoints( ) throws HttpAccessException;

}
