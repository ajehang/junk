/*
 * Vm.java
 *
 * Created on February 9, 2011, 11:28 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ganglia_esper_vbox;
import com.sun.xml.ws.commons.virtualbox_3_2.*;
/**
 *
 * @author ali
 */
public class Vm {
    private String ClusterName;
    private String DnsName;
    public ISession oSession = null;
    public IMachine oMachine = null;
    String machine;
    /** Creates a new instance of Vm */
    public Vm(String cname, String dname) {
         this.ClusterName=cname;
         this.DnsName=dname;
    }
        public void startVM(String strVM, Main main)
    {


        try
        {
            oSession = main.mgr.getSessionObject(main.vbox);

            // first assume we were given a UUID
            try
            {
                oMachine = main.vbox.getMachine(strVM);
            }
            catch (Exception e)
            {
                try
                {
                    oMachine = main.vbox.findMachine(strVM);
                }
                catch (Exception e1)
                {
                }
            }

            if (oMachine == null)
            {
                System.out.println("Error: can't find VM \"" + strVM + "\"");
            }
            else
            {
                String uuid = oMachine.getId();
                String sessionType = "gui";
                String env = "DISPLAY=:0.0";
                IProgress oProgress =
                    main.vbox.openRemoteSession(oSession,
                                            uuid,
                                            sessionType,
                                            env);
                System.out.println("Session for VM " + uuid + " is opening...");
                oProgress.waitForCompletion(10000);                
                long rc = oProgress.getResultCode();
                if (rc != 0)
                    System.out.println("Session failed!");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (oSession != null)
            {
            //    oSession.close();
            }
        }
    }
public void cleanup(Main main)
{
        try
        {
            if (main.vbox != null)
            {
               IProgress iprog = oSession.getConsole().powerDown();
               iprog.waitForCompletion(-1);
      //          disconnect(main);
      //          main.vbox = null;
                System.out.println("Logged off.");
            }
       //     main.mgr.cleanupUnused();
       //     main.mgr = null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
 public void disconnect(Main main){
        
     main.mgr.disconnect(main.vbox);
     ClusterName = null;
     DnsName = null;
    
 }
 public void change(long msize, String strVM, Main main){
     oSession = main.mgr.getSessionObject(main.vbox);
     oMachine = main.vbox.getMachine(strVM);
     main.vbox.openSession(oSession, oMachine.getId());
     IMachine mutable = oSession.getMachine();
     mutable.setMemorySize(msize);
     long mem = mutable.getMemorySize();
     System.out.println("new memory size"+mem);
     mutable.saveSettings();
     oSession.close();
   }
 public String getClusterName(){
     return ClusterName;
 }
 public String getDnsName(){
     return DnsName;
 }
}
