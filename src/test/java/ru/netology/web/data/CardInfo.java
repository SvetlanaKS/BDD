package ru.netology.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardInfo {
    private String number;
    private String viewedNum;
    private int balance;

    public static void transferFromCardToCard(CardInfo fromCard, CardInfo toCard, int amount) {
        fromCard.setBalance(fromCard.getBalance() - amount);
        toCard.setBalance(toCard.getBalance() + amount);
    }
}