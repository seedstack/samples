/*
 *  Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.camel.infrastructure;

import javax.inject.Inject;

import org.seedstack.business.domain.Repository;
import org.seedstack.business.util.inmemory.InMemory;
import org.seedstack.samples.camel.domain.model.person.Person;
import org.seedstack.samples.camel.domain.model.person.PersonId;
import org.seedstack.seed.LifecycleListener;
import org.seedstack.seed.Logging;
import org.slf4j.Logger;

public class SampleDataGenerator implements LifecycleListener {
    @Inject
    @InMemory
    private Repository<Person, PersonId> personRepository;
    @Logging
    private Logger logger;

    @Override
    public void started() {
        logger.info("Adding data to repository");
        personRepository.addOrUpdate(create("bill.evans@some.org", "Bill", "EVANS"));
        personRepository.addOrUpdate(create("ella.fitzgerald@some.org", "Ella", "FITZGERALD"));
        personRepository.addOrUpdate(create("miles.davis@some.org", "Miles", "DAVIS"));
    }

    private Person create(String email, String firstName, String lastName) {
        Person person = new Person(new PersonId(email));
        person.changeName(firstName, lastName);
        return person;
    }
}
