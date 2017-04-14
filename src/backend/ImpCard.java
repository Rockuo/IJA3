package backend;

public class ImpCard implements interfaces.Card {

    private int value;
    private Color color;
    private boolean faceUp = false;

    public ImpCard(Color color, int value) {
        this.color = color;
        this.value = value;
    }

    @Override
    public int value() {
        return this.value;
    }

    @Override
    public boolean isTurnedFaceUp() {
        return this.faceUp;
    }

    @Override
    public boolean turnFaceUp() {
        if (this.faceUp) {
            return false;
        }
        this.faceUp = true;
        return true;
    }


    @Override
    public Color color() {
        return this.color;
    }

    @Override
    public boolean similarColorTo(interfaces.Card c) {
        return (
                (this.color == Color.HEARTS || this.color == Color.DIAMONDS) &&
                        (c.color() == Color.HEARTS || c.color() == Color.DIAMONDS)
        ) || (
                (this.color == Color.CLUBS || this.color == Color.SPADES) &&
                        (c.color() == Color.CLUBS || c.color() == Color.SPADES)
        );
    }

    @Override
    public int compareValue(interfaces.Card c) {
        return this.value - c.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImpCard impCard = (ImpCard) o;

        return this.color == impCard.color && this.faceUp == impCard.faceUp && this.value == impCard.value;
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (faceUp ? 1 : 0);
        return result;
    }
}
