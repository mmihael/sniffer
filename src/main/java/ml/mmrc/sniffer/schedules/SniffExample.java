package ml.mmrc.sniffer.schedules;

import ml.mmrc.sniffer.model.Item;
import ml.mmrc.sniffer.web.controller.IndexController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class SniffExample {

    @Autowired
    private IndexController indexController;

    private RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedDelay = 300_000, initialDelay = 5_000)
    public void testSniff() {

        List<Item> indexControllerResponse = new ArrayList<>();

        for (int i = 1; i < 16; i++) {
            String response = restTemplate.getForObject("http://www.njuskalo.hr/auti/vw-golf-vi?page=" + i, String.class);
            Document doc = Jsoup.parse(response);

            Elements itemContainers = doc.select(".EntityList-items");
            itemContainers.forEach(itemContainer -> {

                Elements items = itemContainer.select(".EntityList-item");
                items.forEach(item -> {

                    Elements titles = item.select(".entity-title");
                    if (titles.size() > 0) {
                        Element title = titles.get(0);
                        String text = title.text();
                        String url = title.childNodeSize() > 0 ? "http://www.njuskalo.hr" + title.child(0).attr("href") : null;
                        Elements priceElement = item.select(".price.price--hrk");
                        String price = priceElement.size() > 0 ? priceElement.get(0).text() : null;
                        Elements imageElement = item.select("img");
                        String image = imageElement.size() > 0 ? imageElement.attr("data-src") : null;
                        System.out.println();
                        System.out.println("-----------------------------------------------------------");
                        System.out.println(text);
                        System.out.println(url);
                        System.out.println("-----------------------------------------------------------");
                        if (price != null && text.toLowerCase().contains("golf")) { indexControllerResponse.add(new Item(text, url, price, image)); }
                    }
                });
            });
        }

        indexController.setResponse(indexControllerResponse);
    }
}
