package com.performizeit.mjstack.dataSource;

import com.performizeit.mjstack.api.DataSource;
import com.performizeit.mjstack.api.Plugin;
import com.performizeit.mjstack.monads.Param;
import com.performizeit.mjstack.parser.ThreadDump;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.io.File;
@Plugin(name="visualvm", params ={@Param(name="path")},description = "Read profiling session from xml export of visualvm")
public class VisualVMDataSourcePlugin implements DataSource {
    String fileName;

    public VisualVMDataSourcePlugin(String fileName) {
        this.fileName = fileName;
    }
	public ArrayList<ThreadDump> getThreadDumps() {
        File fXmlFile = new File(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            //ExportedView tree node
            Element e = doc.getDocumentElement();
            NodeList nl = e.getChildNodes();
            for (int i=0; i<nl.getLength(); i++) {
                System.out.println("aa["+nl.item(i).getNodeName()+"]");
            }

            //doc.getDocumentElement()
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

		return new ArrayList<ThreadDump>();
	}
}
