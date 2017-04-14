package backend;


import interfaces.Card;
import interfaces.CardStack;

import java.util.Stack;

public class ImpCardStack extends ImpCardDeck implements CardStack {

    private boolean ignoreFirst = false;

    public ImpCardStack(boolean ignoreFirst){
        this.stack = new Stack<Card>();
        this.ignoreFirst = ignoreFirst;
    }

    public ImpCardStack(){
        this.stack = new Stack<Card>();
    }

    @Override
    public CardStack pop(Card card) {
        int place = this.stack.search(card);
        if (place <= 0) {
            return null;
        }
        CardStack cd = new ImpCardStack(true);
        Stack<Card> tempStack = new Stack<Card>();
        for (int i = 0; i < place; i++) {
            tempStack.push(this.stack.pop());
        }
        while (!tempStack.isEmpty()) {
            cd.put(tempStack.pop());
        }
        return cd;
    }

    @Override
    public boolean put(Card card) {
        if (this.stack.size() != 0 && this.stack.peek().similarColorTo(card)) {
            return false;
        }


        if(!this.ignoreFirst && this.stack.size() == 0 && card.value() != 13){
            return false;
        }

        if(this.stack.size() != 0 && (card.value()+1) != this.stack.peek().value()) {
            return false;
        }

        this.stack.push(card);
        return true;
    }


    @Override
    public boolean put(CardStack stack) {
        if(stack.size() == 0){
            return false;
        }
        Stack<Card> fakeStack = ((ImpCardStack)stack).getClonedStack();
        Stack<Card> tmpStack = new Stack<Card>();
        while (!fakeStack.isEmpty()) {
            tmpStack.push(fakeStack.pop());
        }

        if(!this.put(tmpStack.pop())){
            return false;
        }

        while (!tmpStack.empty()) {
            this.stack.push(tmpStack.pop());
        }
        return true;
    }

    private Stack<Card> getClonedStack(){
        return (Stack<Card>)this.stack.clone();
    }
}
