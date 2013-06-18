/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice;

import de.schlichtherle.demo.guice.printer.BanneredPrinter;
import de.schlichtherle.demo.guice.printer.CheckedPrinter;
import de.schlichtherle.demo.guice.printer.Printer;
import de.schlichtherle.demo.guice.printer.StandardPrinter;
import java.io.PrintStream;
import java.util.concurrent.Callable;
import javax.annotation.concurrent.Immutable;
import javax.inject.Provider;

/**
 * Provides a {@link #main} method to configure Google Juice and start the
 * {@link Application}.
 *
 * @author Christian Schlichtherle
 */
@Immutable
public final class Bootstrap implements Callable<Void> {

    private final Printer.Job $beginPrintJob = Messages.beginPrint.job();
    private final Printer.Job $helloWorldJob = Messages.helloWorld.job();
    private final Printer.Job $endPrintJob = Messages.endPrint.job();

    public static void main(String[] args) throws Exception {
        new Bootstrap().call();
    }

    @Override public Void call() throws Exception {
        return $application().call();
    }

    Application $application() {
        return new Application($printer(), $printerJobProvider());
    }

    Printer $printer() {
        return new BanneredPrinter($printerForBanneredPrinter(),
                $printerJobNamedHeader(), $printerJobNamedFooter());
    }

    Printer $printerForBanneredPrinter() {
        return new CheckedPrinter($printerForCheckedPrinter());
    }

    Printer $printerForCheckedPrinter() {
        return new StandardPrinter($printStreamForStandardPrinter());
    }

    PrintStream $printStreamForStandardPrinter() { return System.out; }

    Provider<Printer.Job> $printerJobProvider() {
        return new Provider<Printer.Job>() {
            @Override public Printer.Job get() { return $printerJob(); }
        };
    }

    Printer.Job $printerJob() { return $helloWorldJob; }

    Printer.Job $printerJobNamedHeader() { return $beginPrintJob; }

    Printer.Job $printerJobNamedFooter() { return $endPrintJob; }
}
