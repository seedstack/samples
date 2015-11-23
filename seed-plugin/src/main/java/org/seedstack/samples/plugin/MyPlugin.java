package org.seedstack.samples.plugin;

import com.google.inject.Module;
import io.nuun.kernel.api.plugin.InitState;
import io.nuun.kernel.api.plugin.context.InitContext;
import io.nuun.kernel.api.plugin.request.ClasspathScanRequest;
import io.nuun.kernel.core.AbstractPlugin;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MyPlugin extends AbstractPlugin {
    private final Set<Class<?>> annotatedClasses = new HashSet<>();

    @Override
    public String name() {
        return "my-plugin";
    }

    @Override
    public InitState init(InitContext initContext) {
        annotatedClasses.addAll(initContext.scannedClassesByAnnotationClass().get(Service.class));
        return InitState.INITIALIZED;
    }

    @Override
    public Collection<ClasspathScanRequest> classpathScanRequests() {
        return classpathScanRequestBuilder().annotationType(Service.class).build();
    }

    @Override
    public Object nativeUnitModule() {
        return (Module) binder -> annotatedClasses.forEach(binder::bind);
    }
}
