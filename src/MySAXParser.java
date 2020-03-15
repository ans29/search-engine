import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;

public class MySAXParser extends DefaultHandler
{
	Page page;
	long start;
	StringBuilder text_content;
	public static final String WIKI_TITLE = "title";
	public static final String WIKI_TEXT = "text";
	enum Tags {title, id, text_body, unknown};
	Tags tag;
	
	public void parseXml(String fileName) throws ParserConfigurationException, SAXException, IOException
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		parser.parse(new FileInputStream(fileName), this);
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
	{
		if (tag != Tags.unknown &&  length>3)  //tag==Tags.text_body &&
		{
			String readText = new String(ch, start, length);
			
			if (!Constants.special_tags_in_text.contains(readText))
			{
				readText.replace("\n", " ");
				readText.replace("\\p{Space}", " ");
				text_content.append(readText);
				System.out.println(tag + "--" + readText);
			}
		}
	}
	
	@Override
	public void startDocument()
	{
		System.out.println("\n\t PARSING XML FILE...");
		start = System.currentTimeMillis();
		text_content = new StringBuilder();
		tag = Tags.unknown;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)
	{
		if (WIKI_TITLE.equals(qName))
		{
			tag = Tags.title;
			text_content = new StringBuilder();
		}
		if (WIKI_TEXT.equals(qName))
		{
			tag = Tags.text_body;
			text_content = new StringBuilder();
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
	{
		if (tag != Tags.unknown)
			tag = Tags.unknown;
		if (WIKI_TEXT.equals(qName))
			page = new Page(text_content);
	}
	
	@Override
	public void endDocument()
	{
		Long diff = (System.currentTimeMillis()  - start);
		System.out.println("\n\t PARSING COMPLETED IN " + diff.toString() + " MILLI-SEC.");
	}
}