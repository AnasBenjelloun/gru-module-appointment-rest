package fr.paris.lutece.plugins.appointment.modules.rest.business.providers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.SolrAppointmentSlotPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.SolrMeetingPointPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.util.contsants.AppointmentRestConstants;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.httpaccess.HttpAccess;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class SolrProvider implements IAppointmentDataProvider {

    // Constants
    private static final String PROVIDER_NAME = "solr.provider";
    private static final String PROPERTY_ENCODE_URI_ENCODING = "search.encode.uri.encoding";
    private static final String DEFAULT_URI_ENCODING = "ISO-8859-1";
    private final static String PROPERTY_SOLR_BASE_URL = "appointment-rest.solr.base_url";
    private final static String PROPERTY_SOLR_ROWS = "appointment-rest.solr.rows";

    private static SolrProvider _instance;
    private static String _strBaseUrl;
    private static String _strRows;
    @Override
    public String getName() {
        return PROVIDER_NAME;
    }

    public synchronized static SolrProvider getInstance( )
    {
        if ( _instance == null )
        {
            _instance = new SolrProvider( );
            _instance.init( );
        }

        return _instance;
    }

    private synchronized void init( )
    {
        if ( _strBaseUrl == null )
        {
            _strBaseUrl = AppPropertiesService.getProperty(PROPERTY_SOLR_BASE_URL);
        }
        _strRows = AppPropertiesService.getProperty( PROPERTY_SOLR_ROWS, "10000" );
    }

    @Override
    public String getAvailableTimeSlot(List<String> appointmentIds, LocalDate startDate, LocalDate endDate) throws Exception {
        HttpAccess httpAccess = new HttpAccess( );

        StringBuilder query = generateAvailableTimeSlotSolrQuery(appointmentIds, startDate, endDate);

        String strUrl = _strBaseUrl + query;

        String response = httpAccess.doGet( strUrl );
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree( response );
        return jsonNode.get("response").get("docs").toString();
    }

    private static StringBuilder generateAvailableTimeSlotSolrQuery(List<String> appointmentIds, LocalDate startDate, LocalDate endDate) {
        StringBuilder query = new StringBuilder();
        query.append(AppointmentRestConstants.SOLR_QUERY_SELECT + AppointmentRestConstants.SOLR_QUERY_Q).append(encoder(AppointmentRestConstants.SOLR_QUERY_Q_VALUE));
        query.append(AppointmentRestConstants.SOLR_QUERY_FIELD);
        query.append(encoder(SolrAppointmentSlotPOJO.SOLR_FIELD_UID + AppointmentRestConstants.SOLR_QUERY_COMMA + SolrAppointmentSlotPOJO.SOLR_FIELD_DATE + AppointmentRestConstants.SOLR_QUERY_COMMA + SolrAppointmentSlotPOJO.SOLR_FIELD_URL));
        query.append(AppointmentRestConstants.SOLR_QUERY_FILTER_QUERY);
        query.append(SolrAppointmentSlotPOJO.SOLR_FIELD_DATE).append(encoder(AppointmentRestConstants.SOLR_QUERY_COLON));
        query.append(encoder(AppointmentRestConstants.SOLR_QUERY_LB)).append(startDate.atStartOfDay().format(AppointmentRestConstants.SOLR_DATE_FORMATTER)).append(encoder(AppointmentRestConstants.SOLR_QUERY_TO)).append(LocalTime.MAX.atDate(endDate).format(AppointmentRestConstants.SOLR_DATE_FORMATTER)).append(encoder(AppointmentRestConstants.SOLR_QUERY_RB));
        query.append(AppointmentRestConstants.SOLR_QUERY_FILTER_QUERY);
        query.append(SolrAppointmentSlotPOJO.SOLR_FIELD_UID).append(encoder(AppointmentRestConstants.SOLR_QUERY_COLON)).append(AppointmentRestConstants.SOLR_QUERY_LP);
        query.append(appointmentIds.stream().map(SolrProvider::getUIDfromID).collect(Collectors.joining(StringUtils.SPACE)));
        query.append(AppointmentRestConstants.SOLR_QUERY_RP);
        query.append(AppointmentRestConstants.SOLR_QUERY_FILTER_ROWS + _strRows);
        return query;
    }

    @Override
    public String getManagedMeetingPoints() throws Exception {
        HttpAccess httpAccess = new HttpAccess( );

        StringBuilder query = generateManagedMeetingPoints();

        String strUrl = _strBaseUrl + query;

        return httpAccess.doGet( strUrl );
    }

    private static StringBuilder generateManagedMeetingPoints() {
        StringBuilder query = new StringBuilder();
        query.append(AppointmentRestConstants.SOLR_QUERY_SELECT + AppointmentRestConstants.SOLR_QUERY_Q).append(encoder(AppointmentRestConstants.SOLR_QUERY_Q_VALUE));
        query.append(AppointmentRestConstants.SOLR_QUERY_FIELD);
        query.append(encoder(SolrMeetingPointPOJO.SOLR_FIELD_UID + AppointmentRestConstants.SOLR_QUERY_COMMA + SolrMeetingPointPOJO.SOLR_FIELD_TITLE + AppointmentRestConstants.SOLR_QUERY_COMMA + SolrMeetingPointPOJO.SOLR_FIELD_ADDRESS + AppointmentRestConstants.SOLR_QUERY_COMMA + SolrMeetingPointPOJO.SOLR_FIELD_GEOLOC));
        query.append(AppointmentRestConstants.SOLR_QUERY_GROUP);
        query.append(AppointmentRestConstants.SOLR_QUERY_GROUP_FIELD + SolrMeetingPointPOJO.SOLR_FIELD_UID);
        return query;
    }

    public static String getUIDfromID( String strID )
    {
        return "Lutece_" + strID + "_appointment";
    }


    public static String encoder( String strSource )
    {
        String strEncoded = StringUtils.EMPTY;

        try
        {
            strEncoded = URLEncoder.encode( strSource, getEncoding( ) );
        }
        catch( UnsupportedEncodingException e )
        {
            AppLogService.error( e.getMessage( ), e );
        }

        return strEncoded;
    }

    public static String getEncoding( )
    {
        return AppPropertiesService.getProperty( PROPERTY_ENCODE_URI_ENCODING, DEFAULT_URI_ENCODING );
    }
}
