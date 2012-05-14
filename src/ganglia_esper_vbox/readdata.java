/*
 * readdata.java
 *
 * Created on December 5, 2010, 11:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Ali Imran Jehangiri
 */

package ganglia_esper_vbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class readdata {
    
    /** Creates a new instance of readdata */
 public double readfile(String file) throws FileNotFoundException, IOException{
              FileInputStream stream = new FileInputStream(file);
              InputStreamReader iStrReader = new InputStreamReader(stream);
              BufferedReader bufReader = new BufferedReader(iStrReader);
              StreamTokenizer reader = new StreamTokenizer(bufReader);
              reader.whitespaceChars(':',':');
              reader.ordinaryChar('e');
              reader.ordinaryChar('-');
              reader.ordinaryChar('+');
              char sign;
              double returnvalue=0;

            try {
                                reader.nextToken();
                                reader.nextToken();
                                reader.nextToken();
                                returnvalue=reader.nval;
                          //      reader.nextToken();
                          //      reader.nextToken();
                          //      sign = (char)reader.ttype;
                          //      reader.nextToken();
                          //      if (sign == '-')
                          //      returnvalue = returnvalue * Math.pow(10,-reader.nval);
                          //      else
                          //      returnvalue = returnvalue * Math.pow(10,reader.nval);
                                stream.close();
            }
            catch (FileNotFoundException e) {
            System.out.println("DataIOTest: " + e);
        }
                        return returnvalue;
            
 }
}