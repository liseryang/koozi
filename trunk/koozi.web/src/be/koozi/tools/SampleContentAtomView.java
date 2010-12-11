package be.koozi.tools;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;

public class SampleContentAtomView extends AbstractAtomFeedView {
 
    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Feed feed, HttpServletRequest request) {
        feed.setId("tag:springsource.com");
        feed.setTitle("Sample Content");
        @SuppressWarnings("unchecked")
        List<SampleContent> contentList = (List<SampleContent>)model.get("sampleContentList");
        for (SampleContent content : contentList) {
            Date date = content.getPublicationDate();
            if (feed.getUpdated() == null || date.compareTo(feed.getUpdated()) > 0) {
                feed.setUpdated(date);
            }
        }
    }
 
    @Override
    protected List<Entry> buildFeedEntries(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        @SuppressWarnings("unchecked")
        List<SampleContent> contentList = (List<SampleContent>)model.get("sampleContentList");
        List<Entry> entries = new ArrayList<Entry>(contentList.size());
 
        for (SampleContent content : contentList) {
            Entry entry = new Entry();
            String date = String.format("%1$tY-%1$tm-%1$td", content.getPublicationDate());
            // see http://diveintomark.org/archives/2004/05/28/howto-atom-id#other
             entry.setId(String.format("tag:springsource.com,%s:%d", date, content.getId()));
            entry.setTitle(String.format("On %s, %s wrote", date, content.getAuthor()));
            entry.setUpdated(content.getPublicationDate());
 
            Content summary = new Content();
            summary.setValue(content.getText());
            entry.setSummary(summary);
 
            entries.add(entry);
        }
 
        return entries;
 
    }
}
