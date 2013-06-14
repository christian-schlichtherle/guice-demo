# Guice Demo

This standalone project demonstrates how to setup and run a Guice Injector for
printing "Hello world!" to standard output, together with some decorations to
set the message apart from others.

The project provides interfaces and implementations for printers and jobs.
Some implementations are decorators for printers which separate the content
produced by print jobs and do some error checking.
When bootstrapping, a Guice injector is configured which wires a print job
for printing "Hello world!" to a decorated printer.

## Building

You need Java SE 6 and Apache Maven 3.0.4 or higher installed.
Change to the repository directory and run:

    mvn clean install

## Running

This project creates a standalone JAR.
After building, you can run it like this:

    java -jar target/guice-demo-*-shaded.jar

The output should be:

    ---------- BEGIN PRINT ----------
    Hello world!
    ----------  END PRINT  ----------
