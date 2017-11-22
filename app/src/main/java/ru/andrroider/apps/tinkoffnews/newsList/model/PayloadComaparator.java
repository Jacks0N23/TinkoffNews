package ru.andrroider.apps.tinkoffnews.newsList.model;

import java.util.Comparator;

import ru.andrroider.apps.tinkoffnews.newsList.model.dto.Payload;

public class PayloadComaparator implements Comparator<Payload> {
    @Override
    public int compare(Payload o1, Payload o2) {
        if (o1.getPublicationDate() != null && o2.getPublicationDate() != null) {
            if (o1.getPublicationDate().getMilliseconds()
                    < o2.getPublicationDate().getMilliseconds())
                return 1;

            else if (o1.getPublicationDate().getMilliseconds()
                    < o2.getPublicationDate().getMilliseconds())
                return -1;

            else
                return 0;
        }
        return -1;
    }
}