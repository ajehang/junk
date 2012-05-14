package ganglia_esper_vbox;
/*
 * responsetime.java
 *
 * Created on December 5, 2010, 7:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Ali Imran Jehangiri
 */

import java.io.FileNotFoundException;
import java.io.IOException;
public class responsetime {
                  double response;
                  
    /** Creates a new instance of responsetime */
    public responsetime() {
                      this.response=0;

    }
        public void readAndProcessData() throws FileNotFoundException, IOException{
              // reading part
              double rtime;
              readdata r4= new readdata();
              rtime = r4.readfile("/tmp/responsetime");
              response= rtime;
              System.out.println("response time:"+response);

    }
                      public double getresponse(){return response;}

}
