package ru.netology.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardInfo {
    private String number;
    private String viewedNum;
    private int balance;
}