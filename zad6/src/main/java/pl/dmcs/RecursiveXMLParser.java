package pl.dmcs;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dawid on 09.12.2018 at 16:59.
 */
public class RecursiveXMLParser {
    public static void main(String[] args) throws Exception {
        new RecursiveXMLParser().findNodeNames("../../nasa.xml");
    }

    private void findNodeNames(String path) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(this.getClass().getResourceAsStream(path));
        Set<String> nodeNames = new HashSet<>();

        findNodeNames(nodeNames, document.getDocumentElement());
        nodeNames.forEach(System.out::println);
    }

    private void findNodeNames(final Set<String> nodeNames, final Element element) {
        final NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node n = children.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                nodeNames.add(n.getNodeName());
                findNodeNames(nodeNames, (Element) n);
            }
        }
    }
}
