package com.trilogyed.invoiceconfig.util;

import com.trilogyed.invoiceconfig.model.Console;
import com.trilogyed.invoiceconfig.model.Game;

import com.trilogyed.invoiceconfig.model.TShirt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

// use feign client to call catalog API when needed
@FeignClient(name = "gamestore-catalog")
public interface GameStoreInvoiceFeignClient {
    @RequestMapping(value = "/game/{id}", method = RequestMethod.GET)
    public Game getGameById(@PathVariable long id);

    @RequestMapping(value = "/tshirt/{id}", method = RequestMethod.GET)
    public TShirt getTShirtById(@PathVariable long id);

    @RequestMapping(value = "/console/{id}", method = RequestMethod.GET)
    public Console getConsoleById(@PathVariable long id);
}

