package cs240.lib.Model.cards;

import cs240.lib.Model.colors.TrainCardColor;

/**
 * Created by David on 2/21/2018.
 */

public class TrainCard {
    private final TrainCardColor color;

    public TrainCard(TrainCardColor color) {
        this.color = color;
    }

    public TrainCardColor getColor() {
        return color;
    }
}
