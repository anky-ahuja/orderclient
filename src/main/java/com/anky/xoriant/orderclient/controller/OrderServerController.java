package com.anky.xoriant.orderclient.controller;

import com.anky.xoriant.orderclient.model.Book;
import com.anky.xoriant.orderclient.service.OrderServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/client")
public class OrderServerController {

    @Autowired
    private OrderServerService orderServerService;

    @GetMapping(
            path = "/getBookByIdFromServer",
            produces = "application/json"
    )
    public Book getBookByIdFromServer(@RequestParam int bookId) {
        return orderServerService.getBookData(bookId);
    }

}
