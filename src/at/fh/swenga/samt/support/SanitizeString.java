package at.fh.swenga.samt.support;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;

public class SanitizeString extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Document doc = Jsoup.parse(text);
		Cleaner cleaner = new Cleaner(Whitelist.none());
		Document clean = cleaner.clean(doc);
		clean.outputSettings().charset("UTF-8");
		clean.outputSettings().escapeMode(EscapeMode.xhtml);
		System.out.println(StringEscapeUtils.unescapeXml(clean.body().html()));
		setValue(StringEscapeUtils.unescapeXml(clean.body().html()));
	}

}
