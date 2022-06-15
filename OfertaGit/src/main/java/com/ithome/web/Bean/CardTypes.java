package com.ithome.web.Bean;

public class CardTypes {
    private int CardTypeid;
    private String CardType;

    public CardTypes(int cardTypeid, String cardType) {
        CardTypeid = cardTypeid;
        CardType = cardType;
    }

    public CardTypes() {
    }

    public int getCardTypeid() {
        return CardTypeid;
    }

    public void setCardTypeid(int cardTypeid) {
        CardTypeid = cardTypeid;
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }
}
