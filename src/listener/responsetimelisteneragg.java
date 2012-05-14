/*
 * responsetimelisteneragg.java
 *
 * Created on December 22, 2010, 11:02 AM
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
public class responsetimelisteneragg implements UpdateListener{
    
    /** Creates a new instance of responsetimelisteneragg */
    public responsetimelisteneragg() {
    }
        public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        EventBean event = newEvents[0];
        System.out.println("Event received: response time"+event.get("response"));
    }
    
}
