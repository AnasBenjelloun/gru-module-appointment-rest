package fr.paris.lutece.plugins.appointment.modules.rest.rs.filter;

import fr.paris.lutece.plugins.appointment.modules.rest.util.contsants.AppointmentRestConstants;
import fr.paris.lutece.portal.service.util.AppPropertiesService;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class QueryParamValidator {

    public static final String MEETING_IDS_REQUIRED = "appointment-rest.validation.required.meetingsids";
    public static final String START_DATE_REQUIRED = "appointment-rest.validation.required.startdate";
    public static final String START_DATE_INVALID = "appointment-rest.validation.invalid.startdate";
    public static final String END_DATE_REQUIRED = "appointment-rest.validation.required.enddate";
    public static final String END_DATE_INVALID = "appointment-rest.validation.invalid.enddate";

    public static ValidationErrorResponse validate(ContainerRequestContext requestContext) {

        MultivaluedMap< String, String > queryParameters = requestContext.getUriInfo().getQueryParameters();

        ValidationErrorResponse errors = new ValidationErrorResponse();
        if (!queryParameters.containsKey(AppointmentRestConstants.JSON_TAG_MEETING_POINT_IDS)) {
            errors.addDetail(new ValidationErrorResponse.Detail(AppointmentRestConstants.JSON_TAG_MEETING_POINT_IDS, AppPropertiesService.getProperty(MEETING_IDS_REQUIRED,"Champs meeting_point_ids requis"), "Required"));
        }
        if (!queryParameters.containsKey(AppointmentRestConstants.JSON_TAG_START_DATE)) {
            errors.addDetail(new ValidationErrorResponse.Detail(AppointmentRestConstants.JSON_TAG_START_DATE, AppPropertiesService.getProperty(START_DATE_REQUIRED,"Champs start_date requis"), "Required"));
        } else if(!isValideDate(queryParameters.getFirst(AppointmentRestConstants.JSON_TAG_START_DATE))) {
            errors.addDetail(new ValidationErrorResponse.Detail(AppointmentRestConstants.JSON_TAG_START_DATE, AppPropertiesService.getProperty(START_DATE_INVALID,"Format du champs start_date invalide"), "Invalid"));
        }
        if (!queryParameters.containsKey(AppointmentRestConstants.JSON_TAG_END_DATE)) {
            errors.addDetail(new ValidationErrorResponse.Detail(AppointmentRestConstants.JSON_TAG_END_DATE, AppPropertiesService.getProperty(END_DATE_REQUIRED,"Champs end_date requis"), "Required"));
        } else if(!isValideDate(queryParameters.getFirst(AppointmentRestConstants.JSON_TAG_END_DATE))) {
            errors.addDetail(new ValidationErrorResponse.Detail(AppointmentRestConstants.JSON_TAG_END_DATE, AppPropertiesService.getProperty(END_DATE_INVALID,"Format du champs end_date invalide"), "Invalid"));
        }
        return errors;
    }

    public static boolean isValideDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, AppointmentRestConstants.SEARCH_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
