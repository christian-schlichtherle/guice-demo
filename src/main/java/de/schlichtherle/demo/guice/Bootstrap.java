/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice;

import com.google.inject.*;
import static com.google.inject.name.Names.named;
import static de.schlichtherle.demo.guice.inject.Contexts.context;
import de.schlichtherle.demo.guice.job.*;
import de.schlichtherle.demo.guice.printer.*;
import java.io.*;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

/**
 * Provides a {@link #main} method to configure Google Juice and start the
 * {@link Application}.
 *
 * @author Christian Schlichtherle
 */
@Immutable
public final class Bootstrap implements Callable<Void> {

    private final Injector injector = Guice.createInjector(
            printerModule(new File("print.log"), System.out),
            jobModule());

    private static Module printerModule(final File file, final PrintStream out) {
        return new AbstractModule() {
            @Override protected void configure() {
                bind(Printer.class).to(TeePrinter.class);
                install(filePrinterModule(named("primary"), file));
                install(standardPrinterModule(named("secondary"), out));
            }
        };
    }

    private static Module filePrinterModule(
            final Annotation annotation,
            final File file) {
        return new PrivatePrinterModule() {
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
        return new PrivatePrinterModule() {
            @Override protected void configure() {
                expose(Printer.class).annotatedWith(annotation);
                bind(Printer.class).annotatedWith(annotation).to(BanneredPrinter.class);
                bind(Printer.class).annotatedWith(context(BanneredPrinter.class)).to(CheckedPrinter.class);
                bind(Printer.class).annotatedWith(context(CheckedPrinter.class)).to(StandardPrinter.class);
                bind(PrintStream.class).annotatedWith(context(StandardPrinter.class)).toInstance(out);
            }
        };
    }

    private static Module jobModule() {
        return new AbstractModule() {
            @Override protected void configure() {
                bind(Printer.Job.class).to(BufferedJob.class);
                bind(Printer.Job.class).annotatedWith(context(BufferedJob.class)).to(TimeOfDayJob.class);
                bindConstant().annotatedWith(named("initialCapacity")).to(1024);
            }

            @Provides Locale locale() { return Locale.getDefault(); }

            @Provides @Named("duration") int duration() { return 0; }

            @Provides @Named("interval") int interval() { return 1; }
        };
    }

    public static void main(String[] args) throws Exception {
        new Bootstrap().call();
    }

    @Override public Void call() throws Exception { return main().call(); }

    private Application main() {
        return injector.getInstance(Application.class);
    }

    private static abstract class PrivatePrinterModule extends PrivateModule {

        @Provides @Named("header") Printer.Job header(ResourceBundle bundle) {
           return new ResourceBundleJob("beginPrint", bundle);
        }

        @Provides @Named("footer") Printer.Job footer(ResourceBundle bundle) {
           return new ResourceBundleJob("endPrint", bundle);
        }

        @Provides ResourceBundle bundle() { return Messages.bundle; }
    }
}
