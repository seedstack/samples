/*
 *  Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *  
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.camel.domain.model.person;

import org.seedstack.business.domain.BaseAggregateRoot;

import java.io.Serializable;

public class Person extends BaseAggregateRoot<PersonId> implements Serializable {
    private final PersonId id;
    private String firstName;
    private String lastName;

    public Person(PersonId id) {
        this.id = id;
    }

    @Override
    public PersonId getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void changeName(String firstName, String lastName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name is missing");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name is missing");
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
