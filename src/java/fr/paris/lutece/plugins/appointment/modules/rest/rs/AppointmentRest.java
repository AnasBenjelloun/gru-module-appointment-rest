package fr.paris.lutece.plugins.appointment.modules.rest.rs;

import fr.paris.lutece.plugins.appointment.modules.rest.pojo.AppointmentSlotsSearchPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.InfoSlot;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.MeetingPointPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.service.AppointmentRestService;
import fr.paris.lutece.plugins.appointment.modules.rest.util.contsants.AppointmentRestConstants;
import fr.paris.lutece.plugins.appointment.service.AppointmentPlugin;
import fr.paris.lutece.plugins.rest.service.RestConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Path( RestConstants.BASE_PATH + AppointmentPlugin.PLUGIN_NAME )
public class AppointmentRest {

    @GET
    @Path( AppointmentRestConstants.SLASH + AppointmentRestConstants.PATH_API + AppointmentRestConstants.SLASH + AppointmentRestConstants.PATH_AVAILABLE_SLOTS)
    @Produces( MediaType.APPLICATION_JSON )
    public Map<String, List<InfoSlot>> getAvailableTimeSlots(@QueryParam( value = AppointmentRestConstants.JSON_TAG_MEETING_POINT_IDS ) List<String> appointementIds,
                                                      @QueryParam( value = AppointmentRestConstants.JSON_TAG_START_DATE ) String startDate,
                                                      @QueryParam( value = AppointmentRestConstants.JSON_TAG_END_DATE ) String endDate,
                                                      @QueryParam( value = AppointmentRestConstants.JSON_TAG_REASON ) String reason,
                                                      @QueryParam( value = AppointmentRestConstants.JSON_TAG_DOCUMENTS_NUMBER ) String documentsNumber
                                                      ) throws Exception {

        AppointmentSlotsSearchPOJO search = new AppointmentSlotsSearchPOJO(appointementIds,
                LocalDate.parse(startDate, AppointmentRestConstants.SEARCH_DATE_FORMATTER),
                LocalDate.parse(endDate, AppointmentRestConstants.SEARCH_DATE_FORMATTER),
                reason, Optional.ofNullable(documentsNumber).map(Integer::valueOf).orElse(null));
        Map<String, List<InfoSlot>> availableTimeSlots = AppointmentRestService.getInstance().getAvailableTimeSlots(search);
        return availableTimeSlots;
    }

    @GET
    @Path( AppointmentRestConstants.SLASH + AppointmentRestConstants.PATH_API + AppointmentRestConstants.SLASH + AppointmentRestConstants.PATH_MANAGED_MEETING_POINTS)
    @Produces( MediaType.APPLICATION_JSON )
    public List<MeetingPointPOJO> getManagedMeetingPoints( ) throws Exception {


        List<MeetingPointPOJO> managedMeetingPoints = AppointmentRestService.getInstance().getManagedMeetingPoints();
        return managedMeetingPoints;
    }
}
