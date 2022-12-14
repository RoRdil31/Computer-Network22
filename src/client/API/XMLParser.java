//공공데이터 
package client.API;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class XMLParser {
	public static String XML() {
		try {
			String url = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey"
					+ "=u%2BODDQoDaxZqzvTsTqocCuxaJtVQj%2B%2F7U4lIAUOm6Sq"
					+ "T2iH20dqq0RR9PFzOOoMBuxQPvR0ILBMH0M385TLD6g%3D%3D&p"
					+ "ageNo=1&numOfRows=10&startCreateDt=20201201&endCreateDt=20221213";

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			// builder create
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			// build a factory for reading documents.

			Document doc = dBuilder.parse(url);
			doc.getDocumentElement().normalize();

			// Through the created builder, xml document is parsed

			NodeList nList = doc.getElementsByTagName("body");
			// The task of selecting all elements with the name body

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				// System.out.println("\nCurrent Element :" + nNode.getNodeName());

				// Get element while traversing node
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					eElement.getAttribute("items");
					eElement.getAttribute("item");
					// Get data if element tag name is the same
					String a = eElement.getElementsByTagName("decideCnt").item(0).getTextContent();

					a = "확진자수: " + a + "\n";
					String b = eElement.getElementsByTagName("deathCnt").item(0).getTextContent();

					b = "사망자 수: " + b + "\n";
					String c = eElement.getElementsByTagName("stateDt").item(0).getTextContent();

					c = "기준일: " + c;

					return a + " " + b + " " + c;

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
