package ua.training.model.observer;

import java.util.HashSet;
import java.util.Set;

public class ObservablePeriodical {
    int articleId;
    Set<UserObserver> subscribers = new HashSet<>();

    public void addObserver(UserObserver userObserver) {
        subscribers.add(userObserver);
    }

    public void removeObserver(UserObserver userObserver) {
        subscribers.remove(userObserver);
    }

    public void notifySubscribers() {
        for (UserObserver subscriber : subscribers) {
            subscriber.update();
        }
    }
}
