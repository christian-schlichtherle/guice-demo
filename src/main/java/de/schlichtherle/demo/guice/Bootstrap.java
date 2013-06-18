/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice;

import com.google.inject.*;
import static com.google.inject.name.Names.named;
import de.schlichtherle.demo.guice.guicer.GuiceContext;
import static de.schlichtherle.demo.guice.inject.Contexts.context;
import de.schlichtherle.demo.guice.printer.*;
import java.io.PrintStream;
import java.util.Date;
import java.util.Locale;
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

    private final Injector injector = new GuiceContext()
            .injector()
            .module()
                .bind(Printer.class)
                    .to(BanneredPrinter.class)
                    .inject()
                .bind(Printer.class)
                    .annotatedWith(context(BanneredPrinter.class))
                    .to(CheckedPrinter.class)
                    .inject()
                .bind(Printer.class)
                    .annotatedWith(context(CheckedPrinter.class))
                    .to(StandardPrinter.class)
                    .inject()
                .bind(PrintStream.class)
                    .annotatedWith(context(StandardPrinter.class))
                    .toInstance(System.out)
                    .inject()
                .inject()
            .module()
                .bind(Printer.Job.class)
                    .to(TimeOfDayJob.class)
                    .inject()
                .bind(Printer.Job.class)
                    .annotatedWith(named("header"))
                    .toInstance(Messages.beginPrint.job())
                    .inject()
                .bind(Printer.Job.class)
                    .annotatedWith(named("footer"))
                    .toInstance(Messages.endPrint.job())
                    .inject()
                .bind(Locale.class)
                    .toInstance(Locale.getDefault())
                    .inject()
                .bindConstant()
                    .annotatedWith(named("duration"))
                    .to(4)
                    .inject()
                .bindConstant().annotatedWith(named("interval"))
                    .to(1)
                    .inject()
                .inject()
            .module(jobModule())
            .build();

    private static Module jobModule() {
        return new Module() {
            @Override public void configure(Binder binder) { }

            @Provides TimeOfDayJob timeOfDayJob(
                    Provider<Date> clock,
                    Locale locale,
                    @Named("duration") int durationSeconds,
                    @Named("interval") int intervalSeconds) {
                return new TimeOfDayJob.Builder()
                        .clock(clock)
                        .locale(locale)
                        .durationSeconds(durationSeconds)
                        .intervalSeconds(intervalSeconds)
                        .build();
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
