package fr.paris.lutece.plugins.appointment.modules.rest.util.contsants;

import java.time.format.DateTimeFormatter;

public class AppointmentRestConstants {
    /** The Constant SLASH. */
    public static final String SLASH = "/";

    /** The Constant PATH_API. */
    public static final String PATH_API = "api";

    /** The Constant PATH_AVAILABLE_SLOTS. */
    public static final String PATH_AVAILABLE_SLOTS = "availableTimeSlots";
    public static final String PATH_MANAGED_MEETING_POINTS = "getManagedMeetingPoints";

    public static final String PARAMETER_XPAGE = "page";
    public static final String XPAGE_APPOINTMENT_ANTS = "appointmentants";
    public static final String PARAMETER_VIEW = "view";
    public static final String VIEW_APPOINTMENT_ANTS = "predemandeForm";
    public static final String PARAMETER_ID_FORM = "id_form";
    public static final String PARAMETER_STARTING_DATE = "starting_date_time";

    public static final String JSON_TAG_MEETING_POINT_IDS = "meeting_point_ids";
    public static final String JSON_TAG_START_DATE = "start_date";
    public static final String JSON_TAG_END_DATE = "end_date";
    public static final String JSON_TAG_REASON = "reason";
    public static final String JSON_TAG_DOCUMENTS_NUMBER = "documents_number";

    public static final String SOLR_QUERY_SELECT = "/select";
    public static final String SOLR_QUERY_Q = "?q=";
    public static final String SOLR_QUERY_Q_VALUE = "*:*";
    public static final String SOLR_QUERY_FIELD = "&fl=";
    public static final String SOLR_QUERY_FILTER_QUERY = "&fq=";
    public static final String SOLR_QUERY_FILTER_ROWS = "&rows=";
    public static final String SOLR_QUERY_GROUP = "&group=true";
    public static final String SOLR_QUERY_GROUP_FIELD = "&group.field=";
    public static final String SOLR_QUERY_COMMA = ",";
    public static final String SOLR_QUERY_COLON = ":";
    public static final String SOLR_QUERY_TO = " TO ";
    public static final String SOLR_QUERY_LB = "[";
    public static final String SOLR_QUERY_RB = "]";
    public static final String SOLR_QUERY_RP = ")";
    public static final String SOLR_QUERY_LP = "(";



    public static final DateTimeFormatter SEARCH_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter SOLR_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
    public static final DateTimeFormatter SOLR_RESPONSE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
}
