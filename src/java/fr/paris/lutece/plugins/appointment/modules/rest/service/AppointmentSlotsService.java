package fr.paris.lutece.plugins.appointment.modules.rest.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.paris.lutece.plugins.appointment.modules.rest.business.providers.IAppointmentDataProvider;
import fr.paris.lutece.plugins.appointment.modules.rest.business.providers.SolrProvider;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.AppointmentSlotsSearchPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.InfoSlot;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.SolrAppointmentSlotPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.util.contsants.AppointmentRestConstants;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.url.UrlItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.net.URLEncodedUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppointmentSlotsService {

    public static final String LUTECE_BASE_URL = "appointment-rest.lutece.base.url";

    private static IAppointmentDataProvider _dataProvider;
    private static AppointmentSlotsService _instance;
    private static String _baseUrl;

    public AppointmentSlotsService() {
    }

    public static synchronized AppointmentSlotsService getInstance( )
    {
        if ( _instance == null )
        {
            _instance = new AppointmentSlotsService( );
            _instance.init( );
        }
        _baseUrl = AppPropertiesService.getProperty(LUTECE_BASE_URL);

        return _instance;
    }

    private synchronized void init( )
    {
        if ( _dataProvider == null )
        {
            _dataProvider = SolrProvider.getInstance();
            AppLogService.info( "DatatProvider loaded : " + _dataProvider.getName( ) );
        }
    }

    public Map<String, List<InfoSlot>> getAvailableTimeSlotsAsList(AppointmentSlotsSearchPOJO search) throws Exception
    {
        String response =  _dataProvider.getAvailableTimeSlot( search.getAppointmentIds(), search.getStartDate(), search.getEndDate() );

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<SolrAppointmentSlotPOJO>> typeReference = new TypeReference<List<SolrAppointmentSlotPOJO>>() {};
        List<SolrAppointmentSlotPOJO> solrResponse = mapper.readValue(response, typeReference);

        return solrResponse.stream().collect(
                Collectors.groupingBy(
                        a -> StringUtils.substringBetween(a.getUidFormString(), "_", "_"),
                        Collectors.mapping(
                                a -> new InfoSlot(LocalDateTime.parse(a.getDate(), AppointmentRestConstants.SOLR_RESPONSE_DATE_FORMATTER), buildUrl(a.getUrl())),
                                Collectors.toList())
                )
        );
    }

    public String buildUrl(String strUrl) {
        List<NameValuePair> params;
        try {
            params = URLEncodedUtils.parse(new URI(strUrl), Charset.forName("UTF-8"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Map<String, String> mapParamaters = params.stream().collect(
                Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));

        UrlItem url = new UrlItem( _baseUrl );
        url.addParameter( AppointmentRestConstants.PARAMETER_XPAGE, AppointmentRestConstants.XPAGE_APPOINTMENT_ANTS );
        url.addParameter( AppointmentRestConstants.PARAMETER_VIEW, AppointmentRestConstants.VIEW_APPOINTMENT_ANTS );
        url.addParameter( AppointmentRestConstants.PARAMETER_ID_FORM, mapParamaters.get(AppointmentRestConstants.PARAMETER_ID_FORM) );
        url.addParameter( AppointmentRestConstants.PARAMETER_STARTING_DATE, mapParamaters.get(AppointmentRestConstants.PARAMETER_STARTING_DATE) );
        return url.getUrl( );

    }

}
