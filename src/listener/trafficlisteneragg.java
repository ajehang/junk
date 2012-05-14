/*
 * trafficlisteneragg.java
 *
 * Created on December 22, 2010, 10:55 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package listener;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
/**
 *
 * @author ali
 */
public class trafficlisteneragg implements UpdateListener{
    
    /** Creates a new instance of trafficlisteneragg */
    public trafficlisteneragg() {
    }
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        EventBean event = newEvents[0];
        System.out.println("Event Received: bytes out"+event.get("bytes_out"));
    }
}
