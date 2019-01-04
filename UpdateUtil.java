package com.mcmylx.liteboard.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateUtil {

    // * You just need to edit this resource id before you use this util
    // Your resource id
    private static final int RESOURCE_ID = 49266;

    // Website URL
    private static final String URL = "https://www.spigotmc.org/resources/%s/updates";

    // set the access user agent to cross the firewall...
    private static final String UA = "Mozilla/5.0 (Linux; Android 9.0; HM NOTE 1LTEW Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36 MicroMessenger/6. 0.0.54_r849063.501 NetType/WIFI";

    /**
     * Get the plugin's recent update
     *
     * @return Recent update
     */
    public static List<Update> getRecentUpdates() throws IOException {
        List<Update> updates = new ArrayList<>();
        Document document = Jsoup.connect(String.format(URL, RESOURCE_ID)).userAgent(UA).get();
        Element element = document.body();
        Element update_container = element.selectFirst("div[class=updateContainer]");
        Elements elements = update_container.getElementsByClass("primaryContent messageSimple resourceUpdate");
        for (Element update : elements) {
            String title = parseTitle(update);
            String content = parseContent(update);
            String date = parseDate(update);

            Update u = new Update(title, content, date);
            updates.add(u);
        }
        return updates;
    }

    /**
     * Parse the update date of element
     *
     * @param updateElement The update element which need to be parse
     * @return The time of the update element
     * @author RE
     */
    private static String parseDate(Element updateElement) {
        return updateElement.getElementsByClass("DateTime").html();
    }


    /**
     * Parse the update title of element
     *
     * @param updateElement The update element which need to be parse
     * @return The title of the update element
     * @author RE
     */
    private static String parseTitle(Element updateElement) {
        return updateElement.getElementsByClass("textHeading").first().getElementsByTag("a").html();
    }


    /**
     * Parse the update content of element
     *
     * @param updateElement The update element which need to be parse
     * @return The content of the update element
     * @author RE
     */
    private static String parseContent(Element updateElement) {
        Element articleE = updateElement.getElementsByTag("article").first();
        Element contentE = articleE.getElementsByClass("ugc baseHtml messageText").first();
        return contentE.text();
    }


    public static void main(String[] args) {
        try {
            List<Update> updates = UpdateUtil.getRecentUpdates();
            for (Update update : updates) {
                System.out.println("-------------------------------\n");
                System.out.println("Title: " + update.getTitle());
                System.out.println("Content: " + update.getContent());
                System.out.println("Update Date: " + update.getDate());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Update {
        private String title;
        private String content;
        private String date;

        public Update(String title, String content, String date) {
            this.title = title;
            this.content = content;
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public String getDate() {
            return date;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Update update = (Update) o;

            if (title != null ? !title.equals(update.title) : update.title != null) return false;
            if (content != null ? !content.equals(update.content) : update.content != null) return false;
            return date != null ? date.equals(update.date) : update.date == null;
        }

        @Override
        public int hashCode() {
            int result = title != null ? title.hashCode() : 0;
            result = 31 * result + (content != null ? content.hashCode() : 0);
            result = 31 * result + (date != null ? date.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Update{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", date='" + date + '\'' +
                    '}';
        }
    }
}
