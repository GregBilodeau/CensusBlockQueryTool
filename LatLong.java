import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LatLong {

	public static void main(final String[] args) throws IOException {
				
		double latitude;
		double longitude;
		
		latitude = 28.35975;
		longitude = -81.421988;

		URL target = new URL("http://data.fcc.gov/api/block/2010/find?latitude=" + latitude +"&longitude=" + longitude + "&showall=true");
        InputStream inputStream = target.openStream();

        Reader in = new InputStreamReader(inputStream, "UTF-8");
        
        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(out);
        System.out.print("Using: " + latitude + "/" + longitude);
        
        try{
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(inputStream);
	    	
	    	doc.getDocumentElement().normalize();
	    		    	
	    	NodeList nl = doc.getElementsByTagName("Block");
	    	
	    	NodeList intersections = doc.getElementsByTagName("intersection"); 
	    	
	    	if(intersections.getLength() == 0){
	    		for(int i = 0; i < nl.getLength(); i++){
		    		org.w3c.dom.Node n = nl.item(i);
		    		
		    		if (n.getNodeType() == Node.ELEMENT_NODE) {

		    			Element eElement = (Element) n;

		    			System.out.print("\tFIPS : " + eElement.getAttribute("FIPS"));

		    		}
		    		
		    	}
	    	}else{
	    		for(int i = 0; i < intersections.getLength(); i++){
		    		org.w3c.dom.Node n = intersections.item(i);
		    		
		    		if (n.getNodeType() == Node.ELEMENT_NODE) {

		    			Element eElement = (Element) n;

		    			System.out.print("\tFIPS : " + eElement.getAttribute("FIPS"));

		    		}
		    		
		    	}
	    	}
	    	
        }catch(ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        in.close();
    }

}
