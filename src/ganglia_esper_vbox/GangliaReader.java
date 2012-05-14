/* GangliaReader.java
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
import java.io.InputStream;
import java.net.Socket;

/**
 * Fetches data from Ganglia's gmond or gmetad daemon reader.
 */
public class GangliaReader {

    private String gmetadHostname;
    private int gmetadPort;

    public GangliaReader(String gmetadHostname, int gmetadPort) {
        this.gmetadHostname = gmetadHostname;
        this.gmetadPort = gmetadPort;
    }

    public String getReport() throws Exception {
        Socket socket = new Socket(gmetadHostname, gmetadPort);
        InputStream in = socket.getInputStream();

        StringBuilder report = new StringBuilder();
        byte[] buf = new byte[1024 * 1024];
        int len;

        while ((len = in.read(buf)) > 0) {
            report.append(new String(buf, 0, len));
        }

        if (report.length() == 0) {
            throw new Exception("No data returned from Ganglia.");
        }

        return report.toString();
    }
}
