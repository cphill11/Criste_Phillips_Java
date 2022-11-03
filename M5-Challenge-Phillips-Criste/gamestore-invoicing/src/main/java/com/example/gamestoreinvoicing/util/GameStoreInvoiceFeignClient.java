package com.trilogyed.invoiceconfig.util;

import com.trilogyed.invoiceconfig.model.Invoice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "gamestore-invoice")
public interface GameStoreInvoiceFeignClient {
    @RequestMapping(value = "invoice", method = RequestMethod.GET)
    public Invoice getInvoiceById(@PathVariable long invoiceId);

    @GetMapping("/invoice/{id}")
    public Invoice getInvoiceById(@PathVariable Long id);

    @PostMapping("/invoice")
    public Invoice createInvoice(@RequestBody Invoice invoice);

}

