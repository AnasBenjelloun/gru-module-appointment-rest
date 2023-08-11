package fr.paris.lutece.plugins.appointment.modules.rest.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.paris.lutece.plugins.appointment.modules.rest.business.providers.IAppointmentDataProvider;
import fr.paris.lutece.plugins.appointment.modules.rest.business.providers.SolrProvider;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.*;
import fr.paris.lutece.plugins.appointment.modules.rest.util.contsants.AppointmentRestConstants;
import fr.paris.lutece.portal.service.util.AppLogService;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AppointmentMeetingPointsService {

    private static final String BEAN_SOLR_PROVIDER = "solr.provider";

    private static IAppointmentDataProvider _dataProvider;
    private static AppointmentMeetingPointsService _instance;
    private static final Pattern ZIP_CITY_PATTERN = Pattern.compile("(.*)(\\d{5})\\s+(.+)");


    public AppointmentMeetingPointsService() {
    }

    public static synchronized AppointmentMeetingPointsService getInstance( )
    {
        if ( _instance == null )
        {
            _instance = new AppointmentMeetingPointsService( );
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

    public List<MeetingPointPOJO> getManagedMeetingPoints( ) throws Exception
    {
        List<MeetingPointPOJO> manegedPoints = new ArrayList<>();

        String response =  _dataProvider.getManagedMeetingPoints(  );

        ObjectMapper objectMapper = new ObjectMapper();
        SolrResponseMeetingPointPOJO solrResponse = objectMapper.readValue(response, SolrResponseMeetingPointPOJO.class);
        List<SolrMeetingPointPOJO> solrMeetings = new ArrayList<>();

        for (SolrResponseMeetingPointPOJO.Group group : solrResponse.getGrouped().getGroupedByUidForm().getGroups()) {
            if(group.getGroupValue() != null) {
                solrMeetings.addAll(group.getDocList().getDocs());
            }
        }

        manegedPoints = transform(solrMeetings);

        return manegedPoints;
    }

    public List<MeetingPointPOJO> transform(List<SolrMeetingPointPOJO> solrMeetings) {
        List<MeetingPointPOJO> meetingPoints = new ArrayList<>();

        for (SolrMeetingPointPOJO solrMeeting : solrMeetings) {
            MeetingPointPOJO meeting = new MeetingPointPOJO();
            meeting.setId(StringUtils.substringBetween(solrMeeting.getUidFormString(), "_", "_"));
            if (solrMeeting.getGeoloc() != null && solrMeeting.getGeoloc().contains(",")) {
                String[] geoloc = solrMeeting.getGeoloc().split(",");
                meeting.setLatitude(geoloc[0].trim());
                meeting.setLongitude(geoloc[1].trim());
            }

            Matcher matcher = ZIP_CITY_PATTERN.matcher(solrMeeting.getAddressText());
            if (matcher.find()) {
                meeting.setPublicAdress(matcher.group(1).trim());
                meeting.setZipCode(matcher.group(2));
                meeting.setCityName(matcher.group(3).trim());
            }
            meetingPoints.add(meeting);
        }

        return meetingPoints;
    }

}
