# AntMake

## A tool for generating an ant script for a simple project

For months at work, I copied the same ant build script from project to project, changing parts where needed. This felt tedious, and I thought that a tool to generate an ant build script might be useful. It would not speed up my work a whole lot, but it would at least make it feel less tedious.

The result of my wonderings is AntMake, whose build script is itself generated using itself. The name is an amalgamation of "Ant" and "CMake," which similarly builds the build system. Antmake takes a little input from the user, and generates a very basic build file. I typically do not need more than that, so "basic" was the goal here. With a little bit of scripting, I could run this as a command from the command line.

### Templating

You may wonder why I do not just write some kind of template file. Well, if I did that, I would either have to copy that template around, or it would have to become a dependency of the AntMake jar file. I wanted this program to be totally portable, so all of the templating is baked into the Java code.
