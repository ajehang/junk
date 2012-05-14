/*
 * traffic.java
 *
 * Created on December 15, 2010, 1:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
/**
 *
 * @author ali
 */
package ganglia_esper_vbox;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class traffic {
    private double bytes_out;
    private double bytes_in;
    private double pkts_in;
    private double pkts_out;
    /** Creates a new instance of traffic */
    public traffic() {
        this.bytes_out=0;
    }
    public void readAndProcessData(Vm vm, String report) throws FileNotFoundException, IOException, ParserConfigurationException, SAXException, Exception{
              
               Date reportDate = new Date();
               String Metric;

              GangliaReportParser reportparser = new GangliaReportParser(report,reportDate);
              bytes_out = Double.parseDouble(reportparser.parsetraffic(vm,"bytes_out"));
              bytes_in = Double.parseDouble(reportparser.parsetraffic(vm,"bytes_in"));
              pkts_out = Double.parseDouble(reportparser.parsetraffic(vm,"pkts_out"));
              pkts_in = Double.parseDouble(reportparser.parsetraffic(vm,"pkts_in"));
              System.out.println("bytes_out "+bytes_out);
}
 public double getbytes_out(){return bytes_out;}   
 public double getbytes_in(){return bytes_in;}   
 public double getpkts_out(){return pkts_out;}   
  public double getpkts_in(){return pkts_in;}   
}
