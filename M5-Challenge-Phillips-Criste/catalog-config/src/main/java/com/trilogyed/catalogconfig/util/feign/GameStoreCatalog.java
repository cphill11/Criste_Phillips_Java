package com.trilogyed.catalogconfig.util.feign;

import com.trilogyed.catalogconfig.model.Console;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "gamestore-catalog")
public interface GameStoreCatalog {
    @RequestMapping(value = "console", method = RequestMethod.GET)
    public Console getConsoleById(@PathVariable long consoleId);
}
