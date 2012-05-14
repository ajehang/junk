/*
 * localhostlisteneragg.java
 *
 * Created on December 22, 2010, 10:58 AM
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
public class localhostlisteneragg implements UpdateListener{
    
    /** Creates a new instance of localhostlisteneragg */
    public localhostlisteneragg() {
    }
      public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        EventBean event = newEvents[0];
        System.out.println("Event Received: mem free"+event.get("mem_free"));
    }  
}
