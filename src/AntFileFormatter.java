/*
 *
 * AntFileFormatter.java
 * Mark Underwood
 * https://github.com/RobinTheSprite
 * 12/21/2020
 *
 */

package src;

class AntFileFormatter {
    public static String comment(String comment) {
        return String.format("<!-- %s -->", comment);
    }

    public static String antProperty(String name, String location) {
        return String.format(
            "<property name=\"%s\" location=\"%s\" />",
            name,
            location
        );
    }

    public static String antTarget(String targetName) {
        return String.format("<target name=\"%s\" >", targetName);
    }

    public static String antTarget(String targetName, String depends) {
        return String.format(
            "<target name=\"%s\" depends=\"%s\" >",
            targetName,
            depends
        );
    }

    public static String deleteDir(String dirName) {
        return String.format("<delete dir=\"%s\" />", dirName);
    }

    public static String makeDir(String dirName) {
        return String.format("<mkdir dir=\"%s\" />", dirName);
    }

    public static String javac(boolean debug) {
        return String.format(
            "<javac includeantruntime=\"false\" srcdir=\"${src.dir}\" destdir=\"${build.dir}\" debug=\"%s\">",
            (debug) ? "true" : "false"
        );
    }

    public static String jar(String jarName) {
        return String.format(
            "<jar destfile=\"${dist.dir}/%s\" basedir=\"${build.dir}\">",
            jarName
        );
    }

    public static String endTag(String tagName) {
        return String.format("</%s>", tagName);
    }
}
