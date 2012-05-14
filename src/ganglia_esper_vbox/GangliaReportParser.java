/**
 * Copyright (c) 2008-2011, XLAB d.o.o.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of XLAB d.o.o. nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL XLAB d.o.o. BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * @author         Damjan Murn - damjan.murn@xlab.si
 * @version        $Rev: 627 $
 * @lastrevision   $Date: 2011-02-04 15:35:40 +0100 (Fri, 04 Feb 2011) $
 * @filesource     $URL: https://sla-at-soi.svn.sourceforge.net/svnroot/sla-at-soi/platform/trunk/infrastructure-monitoring/infrastructure-monitoring-agent/src/main/java/org/slasoi/infrastructure/monitoring/monitors/ganglia/GangliaReportParser.java $
 */

package ganglia_esper_vbox;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;


/**
 * Parses Ganglia status report.
 */
public class GangliaReportParser {
    private static Logger log = Logger.getLogger(GangliaReportParser.class);
    private Date reportDate;
    private Document xmlDocument;
    private XPath xPath;

    public GangliaReportParser(String report, Date reportDate) throws ParserConfigurationException, IOException, SAXException {       
        this.reportDate = reportDate;
        xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(report)));
        xPath = XPathFactory.newInstance().newXPath();
    }

    /**
     * Parses the report.
     *
     * @return parsed report.
     * @throws Exception
     */


    private void parseAvailabilityMetric(Vm vm) throws Exception {
        Boolean reachable;
        try {
            InetAddress address = InetAddress.getByName(vm.getDnsName());
            reachable = address.isReachable(3000);
        } catch (Exception e) {
            reachable = false;
        }

    }

    public String parsetraffic(Vm vm, String metric) throws Exception {
        String MetricName = metric;
        String expression = String.format("/GANGLIA_XML/GRID/CLUSTER[@NAME='%s']/HOST[@NAME='%s']/METRIC[@NAME='%s']/@VAL", vm.getClusterName(), vm.getDnsName(), MetricName);
        XPathExpression xPathExpression = xPath.compile(expression);
        String value = (String) xPathExpression.evaluate(xmlDocument, XPathConstants.STRING);
        if (value.equals("")) {
            value = null;
        }
        return value;
    }

    public String parselocalhost(Vm vm, String metric) throws Exception {
        // extract Resident Set Size metric of the vm
        String MetricName = metric;
        String expression = String.format("/GANGLIA_XML/GRID/CLUSTER[@NAME='%s']/HOST[@NAME='%s']/METRIC[@NAME='%s']/@VAL", vm.getClusterName(), vm.getDnsName(), MetricName);
        XPathExpression xPathExpression = xPath.compile(expression);
        String value = (String) xPathExpression.evaluate(xmlDocument, XPathConstants.STRING); // unit: MB
        if (value.equals("")) {
            value = null;
        }
        return value;
    }

    public String parsecpu(Vm vm, String metric) throws Exception {
        String MetricName = metric;
        long epoch = System.currentTimeMillis()/1000;
        String expression = String.format("/GANGLIA_XML/GRID/CLUSTER[@NAME='%s']/HOST[@NAME='%s']/METRIC[@NAME='%s']/@VAL", vm.getClusterName(), vm.getDnsName(), MetricName);
        XPathExpression xPathExpression = xPath.compile(expression);
        String value = (String) xPathExpression.evaluate(xmlDocument, XPathConstants.STRING);
        if (value.equals("")) {
            value = null;
        }
        return value;
    }

}
