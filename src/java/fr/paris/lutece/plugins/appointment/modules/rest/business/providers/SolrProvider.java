package fr.paris.lutece.plugins.appointment.modules.rest.business.providers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.AppointmentSlotSolrPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.util.contsants.AppointmentRestConstants;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.httpaccess.HttpAccess;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;

public class SolrProvider implements IAppointmentDataProvider {

    // Constants
    private static final String PROVIDER_NAME = "solr.provider";
    private static final String PROPERTY_ENCODE_URI_ENCODING = "search.encode.uri.encoding";
    private static final String DEFAULT_URI_ENCODING = "ISO-8859-1";
    private final static String PROPERTY_SOLR_BASE_URL = "appointment-rest.solr.base_url";

    private static SolrProvider _instance;
    private static String _strBaseUrl;
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
    }

    @Override
    public String getAvailableTimeSlot(List<String> appointmentIds, LocalDate startDate, LocalDate endDate) throws Exception {
        HttpAccess httpAccess = new HttpAccess( );

        StringBuilder query = generateSolarQuery(startDate, endDate);

        String strUrl = _strBaseUrl + query;

        String response = httpAccess.doGet( strUrl );
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree( response );
        return jsonNode.get("response").get("docs").toString();
    }

    private static StringBuilder generateSolarQuery(LocalDate startDate, LocalDate endDate) {
        StringBuilder query = new StringBuilder();
        query.append(AppointmentRestConstants.SOLR_QUERY_SELECT + AppointmentRestConstants.SOLR_QUERY_Q + encoder(AppointmentRestConstants.SOLR_QUERY_Q_VALUE));
        query.append(AppointmentRestConstants.SOLR_QUERY_FIELD);
        query.append(encoder(AppointmentSlotSolrPOJO.SOLR_FIELD_UID + AppointmentRestConstants.SOLR_QUERY_COMMA + AppointmentSlotSolrPOJO.SOLR_FIELD_DATE + AppointmentRestConstants.SOLR_QUERY_COMMA + AppointmentSlotSolrPOJO.SOLR_FIELD_URL));
        query.append(AppointmentRestConstants.SOLR_QUERY_FILTER_QUERY);
        query.append(AppointmentSlotSolrPOJO.SOLR_FIELD_DATE + encoder(AppointmentRestConstants.SOLR_QUERY_COLON));
        query.append(encoder(AppointmentRestConstants.SOLR_QUERY_LB) + startDate.atStartOfDay().format(AppointmentRestConstants.SOLR_DATE_FORMATTER) + encoder(AppointmentRestConstants.SOLR_QUERY_TO) + endDate.atStartOfDay().format(AppointmentRestConstants.SOLR_DATE_FORMATTER) + encoder(AppointmentRestConstants.SOLR_QUERY_RB));
        return query;
    }

    @Override
    public String getManagedMeetingPoints() throws Exception {
        return null;
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
