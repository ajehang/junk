/*
 * Main.java
 *
 * Created on February 8, 2011, 10:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
/**
 *
 * @author Ali Imran Jehangiri
 */


package ganglia_esper_vbox;
import com.espertech.esper.client.*;
import listener.*;
import java.util.Date;
import com.sun.xml.ws.commons.virtualbox_3_2.*;
import javax.xml.ws.Holder;
import java.util.Scanner;
public class Main {
        IWebsessionManager mgr;
        IVirtualBox vbox;
    /** Creates a new instance of Main */
    public Main() {
        mgr = new IWebsessionManager("http://localhost:18083/");
        vbox = mgr.logon("ali", "ali786");
        System.out.println("Initialized connection to VirtualBox version " + vbox.getVersion());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                EPServiceProvider esperEngine;
        try{
       int start_mon;
       Main mc = new Main();
       Vm vm2 = new Vm("unspecified","dynam248.itmc.tu-dortmund.de");
       vm2.change(256,"f42e4b83-c532-40fd-9982-d05a68db18c5",mc);
       Thread.sleep(9000);
       vm2.startVM("f42e4b83-c532-40fd-9982-d05a68db18c5",mc);
       System.out.println("To start monitoring type 1");
       Scanner in = new Scanner(System.in);
       start_mon=in.nextInt();
       if(start_mon == 1){
       responsetime r= new responsetime();
       cputime cpu=new cputime();
       cpuload cload = new cpuload();
       localhost loc=new localhost();
       traffic traf=new traffic();
       Configuration config = new Configuration();
       config.addEventType("cpuload",cpuload.class.getName());
       config.addEventType("responsetime",responsetime.class.getName());
       config.addEventType("cputime",cputime.class.getName());
       config.addEventType("localhost",localhost.class.getName());
       config.addEventType("traffic",traffic.class.getName());
       esperEngine = EPServiceProviderManager.getDefaultProvider(config);
       System.out.println("Esper engine=" + esperEngine.toString());
       EPStatement statement = null;
       String stmt=null;
       stmt = "select bytes_out from traffic where bytes_out > (10*(select avg(bytes_out) from traffic.win:length(6)))";
       statement = esperEngine.getEPAdministrator().createEPL(stmt);
       statement.addListener(new trafficlisteneragg());
       
       stmt = "select response from responsetime where response > 1";
       statement = esperEngine.getEPAdministrator().createEPL(stmt);
       statement.addListener(new responsetimelisteneragg());
       
       stmt = "select mem_free from localhost where mem_free < 1000";
       statement = esperEngine.getEPAdministrator().createEPL(stmt);
       statement.addListener(new localhostlisteneragg());
       
       stmt = "select load_five from cpuload where load_five > 5";
       statement = esperEngine.getEPAdministrator().createEPL(stmt);
       cpuloadlisteneragg cpl = new cpuloadlisteneragg();
       statement.addListener(cpl);
       
    //   Vm vm1 = new Vm("unspecified","dynam138.itmc.tu-dortmund.de");
       String report;
       GangliaReader gangliaReader = new GangliaReader("localhost", 8651);
       for(int i=1; i<300;i++){
            report = gangliaReader.getReport();
          //  System.out.println(""+report);         
            Date reportDate = new Date();
            loc.readAndProcessData(vm2,report);
            traf.readAndProcessData(vm2,report);
            cload.readAndProcessData(vm2,report);
            cpu.readAndProcessData(vm2,report);       
            r.readAndProcessData();
            esperEngine.getEPRuntime().sendEvent(cload);
            if (cpl.load == true){
                vm2.cleanup(mc);
                Thread.sleep(9000);
                vm2.change(512,"f42e4b83-c532-40fd-9982-d05a68db18c5",mc);
                Thread.sleep(9000);
                vm2.startVM("f42e4b83-c532-40fd-9982-d05a68db18c5",mc);
                break;
            } 
            esperEngine.getEPRuntime().sendEvent(r);
            esperEngine.getEPRuntime().sendEvent(cpu);
            esperEngine.getEPRuntime().sendEvent(loc); 
            esperEngine.getEPRuntime().sendEvent(traf);
            Thread.sleep(3000);
         }
       }
        }
        catch (Exception e) {
                e.printStackTrace();
            }
    }
    
}
