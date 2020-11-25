/*
 * Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.oauth.api;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.seedstack.feign.FeignApi;

/**
 * Feign descriptor for the secured API
 */
@FeignApi
public interface SecuredApi {

    /**Request for the accountInfo secured api on the distant server*/
    @RequestLine("GET /accountInfo")
    @Headers("Authorization: Bearer {accessToken}")
    AccountInfo accountInfo(@Param("accessToken") String accessToken);

}
