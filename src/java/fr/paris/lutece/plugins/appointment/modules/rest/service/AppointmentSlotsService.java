package fr.paris.lutece.plugins.appointment.modules.rest.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.paris.lutece.plugins.appointment.modules.rest.business.providers.IAppointmentDataProvider;
import fr.paris.lutece.plugins.appointment.modules.rest.business.providers.SolrProvider;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.AppointmentSlotSolrPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.AppointmentSlotsSearchPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.InfoSlot;
import fr.paris.lutece.plugins.appointment.modules.rest.util.contsants.AppointmentRestConstants;
import fr.paris.lutece.portal.service.util.AppLogService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppointmentSlotsService {

    private static final String BEAN_SOLR_PROVIDER = "solr.provider";

    private static IAppointmentDataProvider _dataProvider;
    private static AppointmentSlotsService _instance;

    public AppointmentSlotsService() {
    }

    public static synchronized AppointmentSlotsService getInstance( )
    {
        if ( _instance == null )
        {
            _instance = new AppointmentSlotsService( );
            _instance.init( );
        }

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
        TypeReference<List<AppointmentSlotSolrPOJO>> typeReference = new TypeReference<List<AppointmentSlotSolrPOJO>>() {};
        List<AppointmentSlotSolrPOJO> solrResponse = mapper.readValue(response, typeReference);

        Map<String, List<InfoSlot>> availableTimeSlots = solrResponse.stream().collect(
                Collectors.groupingBy(
                        a -> a.getUidFormString(),
                        Collectors.mapping(
                                a -> new InfoSlot(LocalDateTime.parse(a.getDate(), AppointmentRestConstants.SOLR_RESPONSE_DATE_FORMATTER), a.getUrl()),
                                Collectors.toList())
                )
        );

        return availableTimeSlots;
    }

}
