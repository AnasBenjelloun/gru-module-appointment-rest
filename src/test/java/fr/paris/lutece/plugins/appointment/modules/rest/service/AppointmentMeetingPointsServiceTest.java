package fr.paris.lutece.plugins.appointment.modules.rest.service;

import fr.paris.lutece.plugins.appointment.modules.rest.pojo.MeetingPointPOJO;
import fr.paris.lutece.plugins.appointment.modules.rest.pojo.SolrMeetingPointPOJO;
import fr.paris.lutece.test.LuteceTestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AppointmentMeetingPointsServiceTest extends LuteceTestCase {
    @Test
    public void testTransform() {

        SolrMeetingPointPOJO pojo1 = new SolrMeetingPointPOJO();
        pojo1.setTitle("Pojo1");
        pojo1.setUid("uid1");
        pojo1.setAddressText("adresse1 12345 ville1");
        pojo1.setGeoloc("11,22");

        SolrMeetingPointPOJO pojo2 = new SolrMeetingPointPOJO();
        pojo2.setTitle("Pojo2");
        pojo2.setUid("uid2");
        pojo2.setAddressText("adresse complete");
        pojo2.setGeoloc("22,33");

        List<SolrMeetingPointPOJO> solrMeetings = Arrays.asList(pojo1,pojo2);

        List<MeetingPointPOJO> manegedPoints = AppointmentMeetingPointsService.getInstance().transform(solrMeetings);
        assertEquals(2, solrMeetings.size());
        assertEquals("11", manegedPoints.get(0).getLatitude());
        assertEquals("22", manegedPoints.get(0).getLongitude());
        assertEquals("ville1", manegedPoints.get(0).getCityName());
        assertEquals("12345", manegedPoints.get(0).getZipCode());
        assertEquals("adresse1", manegedPoints.get(0).getPublicAdress());

        assertEquals("22", manegedPoints.get(1).getLatitude());
        assertEquals("33", manegedPoints.get(1).getLongitude());
        assertNull(manegedPoints.get(1).getCityName());
        assertNull(manegedPoints.get(1).getZipCode());
        assertEquals("adresse complete", manegedPoints.get(1).getPublicAdress());
    }

}
