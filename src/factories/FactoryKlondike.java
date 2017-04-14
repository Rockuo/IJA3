package factories;


import abstractFactories.AbstractFactorySolitaire;
import backend.ImpCard;
import backend.ImpCardDeck;
import backend.ImpCardStack;
import interfaces.CardDeck;
import interfaces.CardStack;

public class FactoryKlondike extends AbstractFactorySolitaire {

    @Override
    public interfaces.Card createCard(interfaces.Card.Color color, int value) {
        return (value >= 1 && value <= 13) ? new ImpCard(color, value) : null;
    }

    @Override
    public CardDeck createCardDeck() {
        return new ImpCardDeck(52);
    }

    @Override
    public CardDeck createTargetPack(interfaces.Card.Color color) {
        return new ImpCardDeck(13,color);
    }

    @Override
    public CardStack createWorkingPack() {
        return new ImpCardStack();
    }
}
