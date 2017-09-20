package ml.mmrc.sniffer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private String title;
    private String url;
    private String price;
    private String image;
}
