package ganglia_esper_vbox;
/*
 * cputime.java
 *
 * Created on December 5, 2010, 9:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author ali Imran Jehangiri, GWDG
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


public class cputime {
              double cpu_idle;
              double cpu_wio;
              double cpu_nice;
              double cpu_system;
              double cpu_user;
              double cpu_aidle;
              
    /** Creates a new instance of cputime */
    public cputime() {
              cpu_idle = 0;
              cpu_wio = 0;
              cpu_nice = 0;
              cpu_system = 0;
              cpu_user = 0;
              cpu_aidle = 0;

    }
              public void readAndProcessData(Vm vm, String report)throws ParserConfigurationException, SAXException, Exception{
              // reading part
              Date reportDate = new Date();
              String Metric;
              GangliaReportParser reportparser = new GangliaReportParser(report,reportDate);

              cpu_idle = Double.parseDouble(reportparser.parsetraffic(vm,"cpu_idle"));
              cpu_wio = Double.parseDouble(reportparser.parsetraffic(vm,"cpu_wio"));
              cpu_nice = Double.parseDouble(reportparser.parsetraffic(vm,"cpu_nice"));
              cpu_system = Double.parseDouble(reportparser.parsetraffic(vm,"cpu_system"));
              cpu_user = Double.parseDouble(reportparser.parsetraffic(vm,"cpu_user"));
              cpu_aidle = Double.parseDouble(reportparser.parsetraffic(vm,"cpu_aidle"));             

              System.out.println("cpu0idle= "+cpu_idle+"cpu_wio "+cpu_wio+"cpu0nice "+cpu_nice+"cpu0system "+cpu_system+"cpu0user "+cpu_user);
     //         System.out.println("cpu1idle= "+cpu1idle+"cpu1iowait "+cpu1iowait+"cpu1nice "+cpu1nice+"cpu1system "+cpu1system+"cpu1user "+cpu1user);
    }
              public double getcpu_idle(){return cpu_idle;}

              public double getcpu_wio(){return cpu_wio;}

              public double getcpu_nice(){return cpu_nice;}

              public double getcpu_system(){return cpu_system;}

              public double getcpu_user(){return cpu_user;}
              public double getcpu_aidle(){return cpu_aidle;}              

}
