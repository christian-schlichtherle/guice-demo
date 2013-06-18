/*
 * Copyright (C) 2013 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package de.schlichtherle.demo.guice;

import de.schlichtherle.demo.guice.job.ResourceBundleJob;
import de.schlichtherle.demo.guice.printer.Printer;
import java.util.ResourceBundle;

/**
 * Creates jobs for printing application messages.
 * The messages get lazily resolved from a private resource bundle which is
 * keyed by the name of the enumeration instance.
 *
 * @author Christian Schlichtherle
 */
enum Messages {
    beginPrint, endPrint;

    private static final ResourceBundle
            bundle = ResourceBundle.getBundle(Messages.class.getName());

    Printer.Job job() { return new ResourceBundleJob(name(), bundle); }
}
