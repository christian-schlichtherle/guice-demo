/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice;

import com.google.inject.*;
import com.google.inject.binder.LinkedBindingBuilder;
import static de.schlichtherle.demo.guice.inject.Contexts.inClass;
import de.schlichtherle.demo.guice.printer.*;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.util.concurrent.Callable;

/**
 * Provides a {@link #main} method to configure Google Juice and start the
 * {@link Application}.
 *
 * @author Christian Schlichtherle
 */
public final class Bootstrap implements Callable<Void> {

    private final Injector injector = Guice.createInjector(module());

    private static Module module() {
        return new AbstractModule() {
            @Override protected void configure() {
                bind(Printer.class).to(BanneredPrinter.class);
                bind(Printer.class, inClass(BanneredPrinter.class)).to(CheckedPrinter.class);
                bind(Printer.class, inClass(CheckedPrinter.class)).to(StandardPrinter.class);
                bind(PrintStream.class, inClass(StandardPrinter.class)).toInstance(System.out);
                bind(Printer.Job.class).to(HelloWorldJob.class);
            }

            <T> LinkedBindingBuilder<T> bind(Class<T> clazz, Annotation qualifier) {
                return bind(Key.get(clazz, qualifier));
            }
        };
    }

    public static void main(String[] args) throws Exception {
        new Bootstrap().call();
    }

    @Override public Void call() throws Exception { return main().call(); }

    private Application main() { return injector.getInstance(Application.class); }
}
