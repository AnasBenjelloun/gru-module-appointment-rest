package fr.paris.lutece.plugins.appointment.modules.rest.business.providers;

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
    String getAvailableTimeSlot(List<String> appointmentIds, LocalDate startDate, LocalDate endDate, Integer documentNumber ) throws Exception;

    /**
     * getManagedMeetingPoints
     * returns the list of Meeting Points we handle
     *
     * @return a JSON string
     * @throws java.lang.Exception
     */
    String getManagedMeetingPoints( ) throws Exception;

}
