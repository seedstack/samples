/*
 *  Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *   License, v. 2.0. If a copy of the MPL was not distributed with this
 *   file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.camel.interfaces.person;

/**
 * Response for adding a person to the repository
 */
public class AddPersonResponse {
    public static final String STATUS_OK="OK";
    public static final String STATUS_FAIL="Fail";

    private String status;

    public AddPersonResponse(String status){
        if(!status.equals(STATUS_FAIL) && !(status.equals(STATUS_OK))){
            throw new IllegalArgumentException("Incorrect status");
        }
        this.status=status;
    }

    public String getStatus() {
        return status;
    }
}
