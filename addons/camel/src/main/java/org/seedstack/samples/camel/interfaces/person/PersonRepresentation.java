/*
 *  Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.camel.interfaces.person;

import com.google.common.base.Strings;
import org.seedstack.samples.camel.domain.model.person.Person;
import org.seedstack.samples.camel.domain.model.person.PersonId;

/**
 * Represents a person
 */
public class PersonRepresentation {
    private String mail;
    private String firstName;
    private String lastName;

    public PersonRepresentation(Person person){
        this.mail= person.getId().getEmail();
        this.firstName=person.getFirstName();
        this.lastName=person.getLastName();
    }

    /**
     * Empty constructor
     */
    public PersonRepresentation(){
    }

    public Person asPerson(){
        if(Strings.isNullOrEmpty(mail) || Strings.isNullOrEmpty(firstName) || Strings.isNullOrEmpty(lastName)){
            throw new UnsupportedOperationException("Person fields are missing");
        }
        PersonId identifier = new PersonId(mail);
        Person person = new Person(identifier);
        person.changeName(firstName, lastName);
        return person;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
