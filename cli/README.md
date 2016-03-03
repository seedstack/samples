# Command-line (CLI) sample

An "Hello World"-style CLI command, demonstrating command-line usage and testing

# Running it

## Locally

If you have [Maven 3](http://maven.apache.org/) installed, you can clone the whole samples repository, go into the batch sub-directory 
and run it locally:

    mvn seedstack:run -Dargs="hello world"

This should display the following line among logs:

    Hello world!

An alternative is to execute the [Capsule](http://www.capsule.io/) automatically created by the [SeedStack Maven plugin](http://seedstack.org/docs/seed/maven-plugin/) on build:

    mvn clean install
    java -jar target/cli-sample-capsule.jar hello world
    
# Usage

This command does little more than printing `Hello <name>` on the console. You specify the name as the unique argument of
the command:

    java -jar target/cli-sample-capsule.jar hello <name>

Some options are also available:

* `-t fr=Bonjour` or `--translation fr=Bonjour`: adds a `fr` translation for `Hello` (this option can be repeated).
* `-U` or `--upper-case`: outputs the message in upper case.
* `-l fr` or `--lang fr`: translate `Hello` to the `fr` translation.

Fancy example:

    java -jar target/cli-sample-capsule.jar hello world --translation fr=Bonjour de=Hallo --lang de -U

# Copyright and license

This source code is copyrighted by [The SeedStack Authors](https://github.com/seedstack/seedstack/blob/master/AUTHORS) and
released under the terms of the [Mozilla Public License 2.0](https://www.mozilla.org/MPL/2.0/). 
