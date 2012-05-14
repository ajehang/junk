package ganglia_esper_vbox;
/*
 * localhost.java
 *
 * Created on December 7, 2010, 2:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author ali Imran Jehangiri
 */

import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class localhost {
    double boottime;
    double cpu_speed;
    double mem_cached;
    double mem_buffers;
    double mem_free;
    double mem_shared;
    double mem_total;
    double swap_free;
    double swap_total;
    double disk_free;
    double disk_total;
    double proc_run;
    double proc_total;
    /** Creates a new instance of localhost */
    public localhost() {
        this.boottime=0;
        this.cpu_speed=0;
        this.mem_buffers=0;
        this.mem_cached=0;
        this.mem_free=0;
        this.mem_shared=0;
        this.mem_total=0;
        this.swap_free=0;
        this.swap_total=0;
        this.disk_free=0;
        this.disk_total=0;
        this.proc_run=0;
        this.proc_total=0;
    }
              public void readAndProcessData(Vm vm, String report)throws ParserConfigurationException, SAXException, Exception{
               Date reportDate = new Date();
               String Metric;
               GangliaReportParser reportparser = new GangliaReportParser(report,reportDate);


              boottime = Double.parseDouble(reportparser.parselocalhost(vm,"boottime"));

              cpu_speed = Double.parseDouble(reportparser.parselocalhost(vm,"cpu_speed"));

              mem_buffers = Double.parseDouble(reportparser.parselocalhost(vm,"mem_buffers"));

              mem_cached = Double.parseDouble(reportparser.parselocalhost(vm,"mem_cached"));

              mem_free = Double.parseDouble(reportparser.parselocalhost(vm,"mem_free"));

              mem_shared = Double.parseDouble(reportparser.parselocalhost(vm,"mem_shared"));

              mem_total = Double.parseDouble(reportparser.parselocalhost(vm,"mem_total"));

              swap_free = Double.parseDouble(reportparser.parselocalhost(vm,"swap_free"));

              swap_total = Double.parseDouble(reportparser.parselocalhost(vm,"swap_total"));

              disk_free = Double.parseDouble(reportparser.parselocalhost(vm,"disk_free"));

              disk_total = Double.parseDouble(reportparser.parselocalhost(vm,"disk_total"));

              proc_run = Double.parseDouble(reportparser.parselocalhost(vm,"proc_run"));

              proc_total = Double.parseDouble(reportparser.parselocalhost(vm,"proc_total"));
              System.out.println("boottime"+boottime+"cpu speed"+cpu_speed+"mem_cached"+mem_buffers);
}
        public double getboottime(){return boottime;}
        public double getcpu_speed(){return cpu_speed;}
        public double getmem_buffers(){return mem_buffers;}
        public double getmem_cached(){return mem_cached;}
        public double getmem_free(){return mem_free;}
        public double getmem_shared(){return mem_shared;}
        public double getmem_total(){return mem_total;}
        public double getswap_free(){return swap_free;}
        public double getswap_total(){return swap_total;}
        public double getdisk_free(){return disk_free;}
        public double getdisk_total(){return disk_total;}
        public double getproc_run(){return proc_run;}
        public double getproc_total(){return proc_total;}
}
