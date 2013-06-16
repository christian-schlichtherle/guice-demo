/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice;

import com.google.inject.*;
import static com.google.inject.name.Names.named;
import static de.schlichtherle.demo.guice.inject.Contexts.context;
import de.schlichtherle.demo.guice.job.TimeOfDayJob;
import de.schlichtherle.demo.guice.printer.*;
import java.io.*;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.concurrent.Callable;

/**
 * Provides a {@link #main} method to configure Google Juice and start the
 * {@link Application}.
 *
 * @author Christian Schlichtherle
 */
public final class Bootstrap implements Callable<Void> {

    private final Injector injector = Guice.createInjector(
            jobModule(),
            teePrinterModule(),
            filePrinterModule(named("primary"), new File("print.log")),
            standardPrinterModule(named("secondary"), System.out));

    private static Module jobModule() {
        return new AbstractModule() {
            @Override protected void configure() {
                bind(Printer.Job.class).to(TimeOfDayJob.class);
            }

            @Provides Locale locale() { return Locale.getDefault(); }
        };
    }

    private static Module teePrinterModule() {
        return new AbstractModule() {
            @Override protected void configure() {
                bind(Printer.class).to(TeePrinter.class);
            }
        };
    }

    private static Module filePrinterModule(
            final Annotation annotation,
            final File file) {
        return new PrivateModule() {
            @Override protected void configure() {
                expose(Printer.class).annotatedWith(annotation);
                bind(Printer.class).annotatedWith(annotation).to(BanneredPrinter.class);
                bind(Printer.class).annotatedWith(context(BanneredPrinter.class)).to(CheckedPrinter.class);
                bind(Printer.class).annotatedWith(context(CheckedPrinter.class)).to(FilePrinter.class);
                bind(File.class).annotatedWith(context(FilePrinter.class)).toInstance(file);
                bindConstant().annotatedWith(named("append")).to(true);
            }
        };
    }

    private static Module standardPrinterModule(
            final Annotation annotation,
            final PrintStream out) {
        return new PrivateModule() {
            @Override protected void configure() {
                expose(Printer.class).annotatedWith(annotation);
                bind(Printer.class).annotatedWith(annotation).to(BanneredPrinter.class);
                bind(Printer.class).annotatedWith(context(BanneredPrinter.class)).to(CheckedPrinter.class);
                bind(Printer.class).annotatedWith(context(CheckedPrinter.class)).to(StandardPrinter.class);
                bind(PrintStream.class).annotatedWith(context(StandardPrinter.class)).toInstance(out);
            }
        };
    }

    public static void main(String[] args) throws Exception {
        new Bootstrap().call();
    }

    @Override public Void call() throws Exception { return main().call(); }

    private Application main() {
        return injector.getInstance(Application.class);
    }
}
