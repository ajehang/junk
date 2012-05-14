/*
 * cputimelisteneragg.java
 *
 * Created on December 22, 2010, 10:59 AM
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
public class cputimelisteneragg implements UpdateListener{
    
    /** Creates a new instance of cputimelisteneragg */
    public cputimelisteneragg() {
    }
       public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        EventBean event = newEvents[0];
        System.out.println("Event Received:cpu user time"+event.get("cpu_user"));
    } 
}
