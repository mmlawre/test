package Utils;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;;

public class FileReader {
    public static String readTxtFileLines(final String fileName) throws IOException, URISyntaxException {
        File txtFile;
        txtFile = new File("" + fileName + ".txt");
        final StringBuilder contentBuilder = new StringBuilder();
        try (java.io.FileReader fileReader = new java.io.FileReader(txtFile)) {
            final BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                contentBuilder.append(" ").append(data);
            }
            bufferedReader.close();
        } catch (final IOException ioException) {
            System.out.println("Exception occured while reading data from txt:" + ioException.getLocalizedMessage());
        }
        return contentBuilder.toString().trim();
    }

    public static List<String> readXMLFile(final String fileName, final String parenttag) {
        List<String> reportValues= new ArrayList<>();
        try {
            File file = new File(fileName);
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            NodeList reportList = document.getElementsByTagName(parenttag);
            for(int i=0;i<reportList.getLength()-1;i++) {
                Node reportNode = reportList.item(i);
                    Element reportElement = (Element) reportNode;
                    NodeList childNodes = reportElement.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Node childNode = childNodes.item(j);
                        if(childNode.getTextContent()!=null){
                            reportValues.add(childNode.getTextContent());
                        }
                    }}
        } catch (Exception e) {
            System.out.println("Exception occured while reading data from xml:" + e.getLocalizedMessage());
        }
        return reportValues;
    }
}
