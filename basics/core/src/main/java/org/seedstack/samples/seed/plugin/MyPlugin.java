/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.seed.plugin;

import com.google.inject.Module;
import io.nuun.kernel.api.plugin.InitState;
import io.nuun.kernel.api.plugin.context.Context;
import io.nuun.kernel.api.plugin.context.InitContext;
import io.nuun.kernel.api.plugin.request.ClasspathScanRequest;
import org.seedstack.seed.core.SeedRuntime;
import org.seedstack.seed.core.internal.AbstractSeedPlugin;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MyPlugin extends AbstractSeedPlugin {
    private final Set<Class<?>> annotatedClasses = new HashSet<>();

    @Override
    public String name() {
        return "my-plugin";
    }

    @Override
    protected void setup(SeedRuntime seedRuntime) {
        // Called before initialization: can be used to access the runtime environment context
    }

    @Override
    public Collection<ClasspathScanRequest> classpathScanRequests() {
        return classpathScanRequestBuilder().annotationType(SomeAnnotation.class).build();
    }

    @Override
    public InitState initialize(InitContext initContext) {
        // Called when the application is initialized
        annotatedClasses.addAll(initContext.scannedClassesByAnnotationClass().get(SomeAnnotation.class));
        return InitState.INITIALIZED;
    }

    @Override
    public Object nativeUnitModule() {
        return (Module) binder -> annotatedClasses.forEach(binder::bind);
    }

    @Override
    public void start(Context context) {
        // Called when the application is started
    }

    @Override
    public void stop() {
        // Called when the application is stopped
    }
}
