/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.catalog;

import javax.inject.Inject;
import javax.inject.Named;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.business.finder.Range;
import org.seedstack.business.finder.Result;
import org.seedstack.samples.catalog.interfaces.rest.product.ProductRepresentation;
import org.seedstack.samples.catalog.interfaces.rest.tags.TagFinder;
import org.seedstack.seed.testing.junit4.internal.JUnit4Runner;

@RunWith(JUnit4Runner.class)
public class TagJpaFinderIT {

    @Inject
    @Named("tag")
    private TagFinder tagFinder;

    @Test
    public void test_find() {
        Result<ProductRepresentation> result = tagFinder.find(new Range(0, 10), "tag2");
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getFullSize()).isGreaterThanOrEqualTo(0);
        Assertions.assertThat(result.getSize()).isGreaterThanOrEqualTo(0);
    }
}
