/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice.guicer;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.PrivateBinder;
import com.google.inject.PrivateModule;
import static de.schlichtherle.demo.guice.guicer.ModuleContainer.emptyList;
import java.util.List;

/**
 *
 * @author Christian Schlichtherle
 */
public abstract class ModuleBuilder<Target>
extends ModuleContainer<ModuleBuilder<Target>>
implements Builder<Module>, Injection<Target> {

    private List<Installable<PrivateBinder>> exposings = emptyList();
    private List<Installable<Binder>> bindings = emptyList();

    public final <Type> TypeExposing<Type, ModuleBuilder<Target>> expose(
            Class<Type> clazz) {
        return new TypeExposing<Type, ModuleBuilder<Target>>() {
            @Override public ModuleBuilder<Target> inject() {
                return ModuleBuilder.this.expose(this);
            }
        }.bind(clazz);
    }

    final ModuleBuilder<Target> expose(final Installable<PrivateBinder> exposing) {
        exposings.add(exposing);
        return this;
    }

    public final <Type> TypeBinding<Type, ModuleBuilder<Target>> bind(
            Class<Type> clazz) {
        return new TypeBinding<Type, ModuleBuilder<Target>>() {
            @Override public ModuleBuilder<Target> inject() {
                return ModuleBuilder.this.bind(this);
            }
        }.bind(clazz);
    }

    public final <Type> ConstantBinding<Type, ModuleBuilder<Target>> bindConstant() {
        return new ConstantBinding<Type, ModuleBuilder<Target>>() {
            @Override public ModuleBuilder<Target> inject() {
                return ModuleBuilder.this.bind(this);
            }
        };
    }

    final ModuleBuilder<Target> bind(final Installable<Binder> binding) {
        bindings.add(binding);
        return this;
    }

    @Override public final Module build() {
        final List<Installable<PrivateBinder>> exposings = swapExposings();
        if (exposings.isEmpty()) {
            return new AbstractModule() {
                @Override protected void configure() {
                    installTo(binder());
                }
            };
        } else {
            return new PrivateModule() {
                @Override protected void configure() {
                    final PrivateBinder binder = binder();
                    for (Installable<PrivateBinder> binding : exposings)
                        binding.installTo(binder);
                    installTo(binder);
                }
            };
        }
    }

    final void installTo(final Binder binder) {
        for (Module module : swapModules())
            binder.install(module);
        for (Installable<Binder> binding : swapBindings())
            binding.installTo(binder);
    }

    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    final List<Installable<PrivateBinder>> swapExposings() {
        try { return this.exposings; }
        finally { this.exposings = emptyList(); }
    }

    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    final List<Installable<Binder>> swapBindings() {
        try { return this.bindings; }
        finally { this.bindings = emptyList(); }
    }
}
