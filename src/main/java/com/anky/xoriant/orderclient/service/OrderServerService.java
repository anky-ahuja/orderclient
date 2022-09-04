package com.anky.xoriant.orderclient.service;

import com.anky.xoriant.orderclient.client.impl.OrderServerApiImpl;
import com.anky.xoriant.orderclient.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServerService {

    @Autowired
    private OrderServerApiImpl orderServerApiImpl;

    public Book getBookData(int bookId) {
        return orderServerApiImpl.getBookByIdFromServer(bookId);
    }
}
