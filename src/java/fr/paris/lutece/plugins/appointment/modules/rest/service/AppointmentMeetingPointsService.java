package fr.paris.lutece.plugins.appointment.modules.rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.paris.lutece.plugins.appointment.modules.rest.business.providers.IAppointmentDataProvider;
import fr.paris.lutece.plugins.appointment.modules.rest.business.providers.SolrProvider;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.MeetingPointPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.SolrMeetingPointPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.SolrResponseMeetingPointPOJO;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.httpaccess.HttpAccessException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppointmentMeetingPointsService {

    private final static String PROPERTY_WEBSITE_URL = "appointment-rest.website.url";

    private static IAppointmentDataProvider _dataProvider;
    private static AppointmentMeetingPointsService _instance;
    private static String _strWebsiteURL;
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

        _strWebsiteURL = AppPropertiesService.getProperty( PROPERTY_WEBSITE_URL, MeetingPointPOJO.DEFAULT_WEBSITE_URL_RDV );
    }

    public List<MeetingPointPOJO> getManagedMeetingPoints( ) throws HttpAccessException, JsonProcessingException {
        List<MeetingPointPOJO> manegedPoints;

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
            } else {
                meeting.setPublicAdress(solrMeeting.getAddressText());
            }

            meeting.setName(solrMeeting.getTitle());
            meeting.setWebsite(_strWebsiteURL);
            meetingPoints.add(meeting);
        }

        return meetingPoints;
    }

}
