import java.util.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class XMLInputHandler extends DefaultHandler{
    private List<Employee> guards;

    private Employee currEmployee;

    private String currentTag;
    @Override
    public void startDocument() throws SAXException {
        currentTag = "";
        guards = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTag = qName.toLowerCase();
        if (currentTag.equals("guard"))
        {
            currEmployee = new Guard();
        }
        else if (currentTag.equals("cleaner"))
        {
            currEmployee = new Cleaner();
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        switch (currentTag) {
            case "name":
                currEmployee.setName(new String(ch, start,length));
                break;
            case "job":
                currEmployee.setJob(new String(ch, start,length));
                break;
            case "salary":
                currEmployee.setSalary(new String(ch, start,length));
                break;
            case "securedobject":
                ((Guard)currEmployee).setSecuredObject(new String(ch, start,length));
                break;
            case "roomscleaned":
                ((Cleaner)currEmployee).setRoomsCleaned(Integer.parseInt(new String(ch,start, length)));
                break;
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (qName.toLowerCase().equals("guard") || qName.toLowerCase().equals("cleaner")) {
            guards.add(currEmployee);
        }
        currentTag = "";
    }

    public List<Employee> getGuards()
    {
        return guards;
    }
}
