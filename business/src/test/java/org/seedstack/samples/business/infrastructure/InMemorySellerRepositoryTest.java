/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.business.infrastructure;

import org.seedstack.samples.business.domain.seller.Seller;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDate;

/**
 * @author pierre.thirouin@ext.mpsa.com (Pierre Thirouin)
 */
public class InMemorySellerRepositoryTest {

    private InMemorySellerRepository underTest = new InMemorySellerRepository();

    @Test
    public void testRepository() {
        Seller seller = new Seller(1L, LocalDate.now());
        underTest.save(seller);
        Seller loadedSeller = underTest.load(1L);
        Assertions.assertThat(loadedSeller).isEqualTo(seller);

        underTest.delete(seller);
        Seller missingSeller = underTest.load(1L);
        Assertions.assertThat(missingSeller).isNull();
    }
}
