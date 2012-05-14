/*
 * cpuloadlisteneragg.java
 *
 * Created on December 22, 2010, 10:53 AM
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
public class cpuloadlisteneragg implements UpdateListener{
    public boolean load;
    /** Creates a new instance of cpuloadlisteneragg */
    public cpuloadlisteneragg() {
        load=false;
    }
    
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        EventBean event = newEvents[0];
        System.out.println("Event Received: five minute load"+event.get("load_five"));
        load = true;
    }
}
