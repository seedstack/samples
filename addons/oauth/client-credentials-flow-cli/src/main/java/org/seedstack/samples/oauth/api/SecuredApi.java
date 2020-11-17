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
