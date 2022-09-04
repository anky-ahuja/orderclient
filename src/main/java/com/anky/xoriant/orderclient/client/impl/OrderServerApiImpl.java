package com.anky.xoriant.orderclient.client.impl;

import com.anky.xoriant.orderclient.client.OrderServer;
import com.anky.xoriant.orderclient.model.Book;
import com.anky.xoriant.orderclient.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServerApiImpl {

    private final OrderServer orderServer;

    public OrderServerApiImpl(OrderServer orderServer) {
        this.orderServer = orderServer;
    }

    public Book getBookByIdFromServer(int bookId) {
        String path = Constant.ORDER_SERVER_URI
                + "?bookId="
                +bookId;
        Book bookResponse = orderServer.get(path, Book.class).block();

        assert bookResponse!=null;
        return bookResponse;
    }
}
