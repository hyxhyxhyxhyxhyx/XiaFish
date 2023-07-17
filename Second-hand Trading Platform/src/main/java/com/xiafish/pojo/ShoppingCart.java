package com.xiafish.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    private Integer shoppingCartId;
    private Integer userId;
    private Integer goodsId;
    private Integer collectNum;
    private LocalDateTime collectTime;
}