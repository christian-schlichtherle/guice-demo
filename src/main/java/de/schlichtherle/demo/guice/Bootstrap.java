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

    private final Printer.Job _beginPrintJob = Messages.beginPrint.job();
    private final Printer.Job _helloWorldJob = Messages.helloWorld.job();
    private final Printer.Job _endPrintJob = Messages.endPrint.job();

    public static void main(String[] args) throws Exception {
        new Bootstrap().call();
    }

    @Override public Void call() throws Exception {
        return _application().call();
    }

    Application _application() {
        return new Application(_printer(), _printerJobProvider());
    }

    Printer _printer() {
        return new BanneredPrinter(_printerForBanneredPrinter(),
                _printerJobNamedHeader(), _printerJobNamedFooter());
    }

    Printer _printerForBanneredPrinter() {
        return new CheckedPrinter(_printerForCheckedPrinter());
    }

    Printer _printerForCheckedPrinter() {
        return new StandardPrinter(_printStreamForStandardPrinter());
    }

    PrintStream _printStreamForStandardPrinter() { return System.out; }

    Provider<Printer.Job> _printerJobProvider() {
        return new Provider<Printer.Job>() {
            @Override public Printer.Job get() { return _printerJob(); }
        };
    }

    Printer.Job _printerJob() { return _helloWorldJob; }
    Printer.Job _printerJobNamedHeader() { return _beginPrintJob; }
    Printer.Job _printerJobNamedFooter() { return _endPrintJob; }
}
