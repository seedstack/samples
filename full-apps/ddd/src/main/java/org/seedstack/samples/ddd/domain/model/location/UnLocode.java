/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.ddd.domain.model.location;

import com.google.common.base.Preconditions;
import java.util.regex.Pattern;
import org.mongodb.morphia.annotations.Embedded;
import org.seedstack.business.domain.BaseValueObject;

/**
 * United nations location code.
 * <p/>
 * http://www.unece.org/cefact/locode/
 * http://www.unece.org/cefact/locode/DocColumnDescription.htm#LOCODE
 */
@Embedded
public final class UnLocode extends BaseValueObject {
    private static final Pattern VALID_PATTERN = Pattern.compile("[a-zA-Z]{2}[a-zA-Z2-9]{3}");
    private String unlocode;

    private UnLocode() {
        // required by persistence
    }

    /**
     * Constructor.
     *
     * @param countryAndLocation Location string.
     */
    public UnLocode(final String countryAndLocation) {
        Preconditions.checkNotNull(countryAndLocation, "Country and location may not be null");
        Preconditions.checkState(VALID_PATTERN.matcher(countryAndLocation).matches(),
                countryAndLocation + " is not a valid UN/LOCODE (does not match pattern)");

        this.unlocode = countryAndLocation.toUpperCase();
    }

    /**
     * @return country code and location code concatenated, always upper case.
     */
    public String idString() {
        return unlocode;
    }
}
