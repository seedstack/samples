/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.samples.guice;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import org.aopalliance.intercept.MethodInvocation;
import org.seedstack.seed.Install;

@Install
public class MyModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Greeter.class);
        bind(Greeter.class).annotatedWith(Names.named("toto")).toInstance(new Greeter());

        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Trace.class), this::traceMethod);
    }

    private Object traceMethod(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("--> invoking " + methodInvocation.getMethod().getName());
        try {
            return methodInvocation.proceed();
        } finally {
            System.out.println("<-- invoked " + methodInvocation.getMethod().getName());
        }
    }
}
