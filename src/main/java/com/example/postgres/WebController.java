package com.example.postgres;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    private final JdbcTemplate jdbcTemplate;

    public WebController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/getOrderItems")
    public List<String> getTuples() {
        TcpConnectionPoolFactory.createConnectionPool();
        return this.jdbcTemplate.queryForList("SELECT ID,SURNAME FROM mrisk").stream()
                .map((m) -> m.values().toString())
                .collect(Collectors.toList());
    }

    @GetMapping("/setOrderItems")
    public String setOrders() {
        jdbcTemplate.batchUpdate("INSERT INTO order_items values (8,89,'pants',1)");
        jdbcTemplate.batchUpdate("INSERT INTO order_items values (9,88,'shorts',1)");
        jdbcTemplate.batchUpdate("INSERT INTO order_items values (11,87,'capris',1)");
        jdbcTemplate.batchUpdate("INSERT INTO order_items values (17,86,'salwar',1)");
       return "orders updated";
    }
}
