# Guice Demo

This standalone project demonstrates how to setup and run a Guice Injector for
printing a localized time-of-the-day message to some output stream, together
with some decorations to set the message apart from others.

The project provides interfaces and implementations for printers and jobs.
Some implementations are decorators for printers which separate the content
produced by print jobs and do some error checking.
When bootstrapping, a Guice injector is configured which wires a print job
for printing the localized time-of-the-day message to a decorated printer.

## Building

You need Java SE 6 and Apache Maven 3.0.4 or higher installed.
Change to the repository directory and run:

    $ mvn clean install

## Running

After building, you can run the standalone JAR like this:

    $ java -jar target/guice-demo-*-shaded.jar

For the UK, the output should be similar to this:

    ---------- BEGIN PRINT ----------
    At the third stroke, it will be three fifty-nine and twenty-six seconds. BEEP, BEEP, BEEP.
    ----------  END PRINT  ----------

You may notice that this message is similar to the messages of BT's popular
[Speaking Clock](http://en.wikipedia.org/wiki/Speaking_clock).

You can set different combinations of countries and languages,
e.g. the following command:

    $ java -Duser.language=de -Duser.country=DE -jar target/guice-demo-*-shaded.jar

... should produce a message which is similar to the messages of the Deutsche
Telekom Zeitansage:

    ---------- DRUCK ANFANG ----------
    Beim nächsten Ton ist es sechzehn Uhr, fünf Minuten und siebenundvierzig Sekunden. PIIIEP.
    ----------  DRUCK ENDE  ----------

## License

This demo project is covered by the
[Eclipse Software License, Version 1.0](http://www.eclipse.org/legal/epl-v10.html).
