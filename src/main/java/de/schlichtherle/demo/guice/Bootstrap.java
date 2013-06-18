/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice;

import com.google.inject.*;
import static com.google.inject.name.Names.named;
import static de.schlichtherle.demo.guice.inject.Contexts.context;
import de.schlichtherle.demo.guice.printer.*;
import java.io.*;
import java.util.concurrent.Callable;
import javax.annotation.concurrent.Immutable;

/**
 * Provides a {@link #main} method to configure Google Juice and start the
 * {@link Application}.
 *
 * @author Christian Schlichtherle
 */
@Immutable
public final class Bootstrap implements Callable<Void> {

    private final Injector injector = Guice.createInjector(
            printerModule(System.out),
            jobModule());

    private static Module printerModule(final PrintStream out) {
        return new AbstractModule() {
            @Override protected void configure() {
                bind(Printer.class).to(BanneredPrinter.class);
                bind(Printer.class)
                        .annotatedWith(context(BanneredPrinter.class))
                        .to(CheckedPrinter.class);
                bind(Printer.class)
                        .annotatedWith(context(CheckedPrinter.class))
                        .to(StandardPrinter.class);
                bind(PrintStream.class)
                        .annotatedWith(context(StandardPrinter.class))
                        .toInstance(out);
            }
        };
    }

    private static Module jobModule() {
        return new AbstractModule() {
            @Override protected void configure() {
                bind(Printer.Job.class).toInstance(Messages.helloWorld.job());
                bind(Printer.Job.class).annotatedWith(named("header"))
                        .toInstance(Messages.beginPrint.job());
                bind(Printer.Job.class).annotatedWith(named("footer"))
                        .toInstance(Messages.endPrint.job());
            }
        };
    }

    public static void main(String[] args) throws Exception {
        new Bootstrap().call();
    }

    @Override public Void call() throws Exception {
        return application().call();
    }

    private Application application() {
        return injector.getInstance(Application.class);
    }
}
