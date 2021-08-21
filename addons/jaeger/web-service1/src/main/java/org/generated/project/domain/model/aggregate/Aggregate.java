package org.generated.project.domain.model.aggregate;

import org.seedstack.business.domain.BaseAggregateRoot;


public class Aggregate extends BaseAggregateRoot<String> {
    private String id;


    public Aggregate(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
