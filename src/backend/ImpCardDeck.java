package backend;

import java.util.Stack;
import interfaces.CardDeck;

public class ImpCardDeck implements CardDeck {

    protected Stack<interfaces.Card> stack;
    private int maxSize;
    private interfaces.Card.Color color = null;

    public ImpCardDeck(){
        this.stack = new Stack<interfaces.Card>();
        this.maxSize = 13;
    }

    public ImpCardDeck(int maxSize) {
        int byColor = maxSize / 4;
        this.maxSize = maxSize;
        this.stack = new Stack<interfaces.Card>();
        for (interfaces.Card.Color color : interfaces.Card.Color.values()) {
            for (int i = 1; i <= byColor; i++) {
                stack.push(new ImpCard(color, i));
            }
        }
    }

    public ImpCardDeck(int maxSize, interfaces.Card.Color color) {
        this.maxSize = maxSize;
        this.stack = new Stack<interfaces.Card>();
        this.color = color;
    }

    @Override
    public interfaces.Card get() {
        if (this.isEmpty()) {
            return null;
        }
        return this.stack.peek();
    }

    @Override
    public interfaces.Card get(int index) {
        if (this.isEmpty() && (index) >= (this.stack.size())) {
            return null;
        }

        int iMax = this.stack.size() - index;

        Stack<interfaces.Card> tempDeck = new Stack<interfaces.Card>();
        for (int i = 0; i < iMax; i++) {
            tempDeck.push(this.stack.pop());
        }

        interfaces.Card peekCard = tempDeck.peek();

        for (int i = 0; i < iMax; i++) {
            this.stack.push(tempDeck.pop());
        }

        return peekCard;
    }

    @Override
    public boolean isEmpty() {
        return this.stack.size() == 0;
    }

    @Override
    public interfaces.Card pop() {
        if (this.isEmpty()) {
            return null;
        }
        return this.stack.pop();
    }

    @Override
    public boolean put(interfaces.Card card) {
        if (stack.size() >= this.maxSize) {
            return false;
        }
        if (this.color != null && (this.color != card.color() ||
                (this.stack.size() == 0 && card.value() != 1) ||
                (this.stack.size() != 0 && (this.stack.peek().value() + 1) != card.value())
        )) {
            return false;
        }

        this.stack.push(card);
        return true;
    }

    @Override
    public int size() {
        return this.stack.size();
    }
}
