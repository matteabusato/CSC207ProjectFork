package Card.View;

import Card.Method.CardController;

public class CardPresenter {
    private final CardView cardView;

    public CardPresenter(CardController controller) {
        this.cardView = new CardView(controller);
    }

    public void showView() {
        cardView.frame.setVisible(true);
        CardController.refresh(cardView);
    }

    public void disposeView() {
        cardView.setVisible(false);
        cardView.dispose();
    }
}
