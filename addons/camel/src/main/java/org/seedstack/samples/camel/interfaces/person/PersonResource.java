/*
 *  Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.camel.interfaces.person;

import org.seedstack.business.domain.Repository;
import org.seedstack.business.specification.Specification;
import org.seedstack.business.util.inmemory.InMemory;
import org.seedstack.samples.camel.application.message.MessageService;
import org.seedstack.samples.camel.application.message.MessageServiceImpl;
import org.seedstack.samples.camel.domain.model.person.Person;
import org.seedstack.samples.camel.domain.model.person.PersonId;
import org.seedstack.seed.SeedException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * person resources - provides an API for Person
 */
@Path("person")
public class PersonResource {

    @Inject
    @InMemory
    private Repository<Person, PersonId> personRepository;
    @Inject
    private MessageService messageService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PersonRepresentation> getFullPersonList(){
        List<PersonRepresentation> finalList= new ArrayList<>();
        personRepository.get(getAllPersonSpecification()).forEach(person -> {
            finalList.add(new PersonRepresentation(person));
        });
        return finalList;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AddPersonResponse addPerson(PersonRepresentation personRepresentation){
        try {
            personRepository.addOrUpdate(personRepresentation.asPerson());
            return new AddPersonResponse(AddPersonResponse.STATUS_OK);
        }
        catch (Exception e){
            return new AddPersonResponse(AddPersonResponse.STATUS_FAIL);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/queue")
    public AddPersonResponse messageAddPerson(PersonRepresentation personRepresentation){
        try {
            messageService.sendPersonMessage(personRepresentation.asPerson(), MessageService.QUEUE_STD_NAME);
            messageService.sendPersonMessage(personRepresentation.asPerson(), MessageService.QUEUE_CAMEL_NAME);
            return new AddPersonResponse(AddPersonResponse.STATUS_OK);
        }
        catch(Exception e){
            return new AddPersonResponse(AddPersonResponse.STATUS_FAIL);
        }
    }

    private Specification<Person> getAllPersonSpecification(){
        return personRepository.getSpecificationBuilder().of(Person.class).all().build();
    }
}
