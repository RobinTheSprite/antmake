/*
 *
 * AntMake.java
 * Mark Underwood
 * https://github.com/RobinTheSprite
 * 12/21/2020
 *
 */

package src;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AntMake {

    public static void main(String[] args) {
        System.out.println("AntMake");
        System.out.println("Generating build script...");

        Scanner input = new Scanner(System.in);

        System.out.print("Project name: ");
        String projectName = input.nextLine();

        System.out.print("Library folder? (Y/n): ");
        String libFolder = input.nextLine();

        System.out.print("Compile with debug? (Y/n): ");
        String debug = input.nextLine();

        System.out.print("Name of jarfile: ");
        String jarName = input.nextLine();

        ManifestFile manifest = new ManifestFile();
        while (true) {
            System.out.println(
                "Add manifest header (keep pressing ENTER to stop)"
            );
            System.out.print("Name: ");
            String name = input.nextLine();
            System.out.print("Value: ");
            String value = input.nextLine();
            if (name.isEmpty() || value.isEmpty()) {
                break;
            }
            manifest.addHeader(name, value);
        }

        FileString file = new FileString(4);
        file.writeLine("<?xml version=\"1.0\"?>");
        file.writeLine(String.format(
            "<project name=\"%s\" default=\"main\" basedir=\".\">",
            projectName
        ));
        file.setTabDepth(1);

        file.writeLine();

        //Folder locations
        file.writeLine(AntFileFormatter.comment("Directories"));
        if (libFolder.matches("(?i)y") || libFolder.isEmpty()) {
            file.writeLine(AntFileFormatter.antProperty("lib.dir", "lib"));
        }
        file.writeLine(AntFileFormatter.antProperty("src.dir", "src"));
        file.writeLine(AntFileFormatter.antProperty("build.dir", "classes"));
        file.writeLine(AntFileFormatter.antProperty("dist.dir", "dist"));

        file.writeLine();

        //Delete old build files
        file.writeLine(AntFileFormatter.comment("Delete last build"));
        file.writeLine(AntFileFormatter.antTarget("clean"));
        file.setTabDepth(2);
        file.writeLine(AntFileFormatter.deleteDir("${build.dir}"));
        file.writeLine(AntFileFormatter.deleteDir("${dist.dir}"));
        file.setTabDepth(1);
        file.writeLine(AntFileFormatter.endTag("target"));

        file.writeLine();

        //Re-create build folders
        file.writeLine(AntFileFormatter.comment("Re-make build directories"));
        file.writeLine(AntFileFormatter.antTarget("mkdir"));
        file.setTabDepth(2);
        file.writeLine(AntFileFormatter.makeDir("${build.dir}"));
        file.writeLine(AntFileFormatter.makeDir("${dist.dir}"));
        file.setTabDepth(1);
        file.writeLine(AntFileFormatter.endTag("target"));

        file.writeLine();

        //Compile files
        file.writeLine(AntFileFormatter.comment("Compile project"));
        file.writeLine(
            AntFileFormatter.antTarget(
                "compile",
                "clean, mkdir"
            )
        );
        file.setTabDepth(2);
        if (debug.matches("(?i)y") || debug.isEmpty()) {
            file.writeLine(AntFileFormatter.javac(true));
        }
        file.setTabDepth(3);
        file.writeLine("<compilerarg value=\"-Xlint:all\" />");
        file.setTabDepth(2);
        file.writeLine(AntFileFormatter.endTag("javac"));
        file.setTabDepth(1);
        file.writeLine(AntFileFormatter.endTag("target"));

        file.writeLine();

        //Build jar file
        file.writeLine(AntFileFormatter.comment("Build jar file"));
        file.writeLine(AntFileFormatter.antTarget("jar", "compile"));
        file.setTabDepth(2);
        file.writeLine(AntFileFormatter.jar(jarName));
        file.setTabDepth(3);
        file.writeLine("<manifest>");
        file.setTabDepth(4);
        file.writeLine(
            AntFileFormatter.comment(
                "<attribute name=\"${name}\" value=\"${value}\" />"
            )
        );
        for (String header : manifest.getHeaders()) {
            file.writeLine(header);
        }
        file.setTabDepth(3);
        file.writeLine(AntFileFormatter.endTag("manifest"));
        file.setTabDepth(2);
        file.writeLine(AntFileFormatter.endTag("jar"));
        file.setTabDepth(1);
        file.writeLine(AntFileFormatter.endTag("target"));

        file.writeLine();

        //Main target
        file.writeLine(AntFileFormatter.comment("Main target"));
        file.writeLine(AntFileFormatter.antTarget("main", "jar"));
        file.setTabDepth(2);
        file.writeLine("<description>Main Target</description>");
        file.setTabDepth(1);
        file.writeLine(AntFileFormatter.endTag("target"));

        file.writeLine();

        file.setTabDepth(0);
        file.write(AntFileFormatter.endTag("project"));

        input.close();

        try(FileWriter writer = new FileWriter("build.xml")) {
            writer.write(file.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
