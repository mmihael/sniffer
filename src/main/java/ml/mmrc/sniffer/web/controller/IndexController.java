package ml.mmrc.sniffer.web.controller;

import ml.mmrc.sniffer.model.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    private List<Item> response = null;

    @GetMapping(path = "/")
    public List<Item> getResponse() {
        return response;
    }

    public void setResponse(List<Item> response) {
        this.response = response;
    }
}
