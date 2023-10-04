package fr.paris.lutece.plugins.appointment.modules.rest.rs;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.AppointmentSlotsSearchPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.InfoSlot;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.MeetingPointPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.rs.filter.QueryParamValidator;
import fr.paris.lutece.plugins.appointment.modules.rest.rs.filter.ValidationErrorResponse;
import fr.paris.lutece.plugins.appointment.modules.rest.service.IAppointmentRestService;
import fr.paris.lutece.plugins.appointment.modules.rest.util.contsants.AppointmentRestConstants;
import fr.paris.lutece.plugins.appointment.service.AppointmentPlugin;
import fr.paris.lutece.plugins.rest.service.RestConstants;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.httpaccess.HttpAccessException;
import org.apache.commons.collections.CollectionUtils;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Path( RestConstants.BASE_PATH + AppointmentPlugin.PLUGIN_NAME )
public class AppointmentRest {

    public static final String SOLR_EXCEPTION = "appointment-rest.solr.exception.message";
    public static final String JSON_EXCEPTION = "appointment-rest.json.exception.message";

    @Inject
    IAppointmentRestService appointmentRestService;

    @GET
    @Path( AppointmentRestConstants.SLASH + AppointmentRestConstants.PATH_API + AppointmentRestConstants.SLASH + AppointmentRestConstants.PATH_AVAILABLE_SLOTS)
    @Produces( MediaType.APPLICATION_JSON )
    public Response getAvailableTimeSlots(@Context ContainerRequestContext request, @QueryParam( value = AppointmentRestConstants.JSON_TAG_MEETING_POINT_IDS ) List<String> appointementIds,
                                          @QueryParam( value = AppointmentRestConstants.JSON_TAG_START_DATE ) String startDate,
                                          @QueryParam( value = AppointmentRestConstants.JSON_TAG_END_DATE ) String endDate,
                                          @QueryParam( value = AppointmentRestConstants.JSON_TAG_REASON ) String reason,
                                          @QueryParam( value = AppointmentRestConstants.JSON_TAG_DOCUMENTS_NUMBER ) String documentsNumber
                                        ) {
        ValidationErrorResponse errors = QueryParamValidator.validate(request);
        if(CollectionUtils.isNotEmpty(errors.getDetail())) {
            return Response.status(422).entity(errors).build();
        }
        AppointmentSlotsSearchPOJO search = new AppointmentSlotsSearchPOJO(appointementIds,
                LocalDate.parse(startDate, AppointmentRestConstants.SEARCH_DATE_FORMATTER),
                LocalDate.parse(endDate, AppointmentRestConstants.SEARCH_DATE_FORMATTER),
                reason, Optional.ofNullable(documentsNumber).map(Integer::valueOf).orElse(null));
        Map<String, List<InfoSlot>> availableTimeSlots;
        try {
            availableTimeSlots = appointmentRestService.getAvailableTimeSlots(search);
        } catch (HttpAccessException e) {
            throw new WebApplicationException(Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity(AppPropertiesService.getProperty(SOLR_EXCEPTION))
                    .build());
        } catch (JsonProcessingException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(AppPropertiesService.getProperty(JSON_EXCEPTION))
                    .build());
        }
        return Response.ok(availableTimeSlots).build();
    }

    @GET
    @Path( AppointmentRestConstants.SLASH + AppointmentRestConstants.PATH_API + AppointmentRestConstants.SLASH + AppointmentRestConstants.PATH_MANAGED_MEETING_POINTS)
    @Produces( MediaType.APPLICATION_JSON )
    public List<MeetingPointPOJO> getManagedMeetingPoints( ) {
        List<MeetingPointPOJO> managedMeetingPoints;
        try {
            managedMeetingPoints = appointmentRestService.getManagedMeetingPoints();
        } catch (HttpAccessException e) {
            throw new WebApplicationException(Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity(AppPropertiesService.getProperty(SOLR_EXCEPTION))
                    .build());
        } catch (JsonProcessingException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(AppPropertiesService.getProperty(JSON_EXCEPTION))
                    .build());
        }
        return managedMeetingPoints;
    }
}
