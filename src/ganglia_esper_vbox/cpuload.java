
/*
 * cpuload.java
 *
 * Created on December 7, 2010, 12:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Ali Imran Jehangiri, GWDG
 */
package ganglia_esper_vbox;

import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
public class cpuload {
    private double load_one;
    private double load_five;
    private double load_fifteen;
    /** Creates a new instance of cpuload */
    public cpuload() {
        this.load_one=0;
        this.load_five=0;
        this.load_fifteen=0;
    }
public void readAndProcessData(Vm vm, String report)throws ParserConfigurationException, SAXException, Exception{
              
                  Date reportDate = new Date();
                  String Metric;
                  GangliaReportParser reportparser = new GangliaReportParser(report,reportDate);
                  load_one = Double.parseDouble(reportparser.parsetraffic(vm,"load_one"));
                  load_five = Double.parseDouble(reportparser.parsetraffic(vm,"load_five"));
                  load_fifteen = Double.parseDouble(reportparser.parsetraffic(vm,"load_fifteen"));
                  System.out.println("load1m= "+load_one+"load5min "+load_five+"load15min"+load_fifteen);    
}

         public double getload_one(){return load_one;}
         public double getload_five(){return load_five;}
         public double getload_fifteen(){return load_fifteen;}
}
