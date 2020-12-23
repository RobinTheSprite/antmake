/**
 *
 * AntFileFormatter.java
 * @author Mark Underwood
 * https://github.com/RobinTheSprite
 * 12/21/2020
 *
 */

package src;

/**
 * Static methods for creating XML formatted one-line strings
 * to put into ant scripts.
 */
class AntFileFormatter {
    /**
     * Make an XML comment.
     * @param comment The text of the comment.
     * @return A string containing the comment text formatted
     *         as an XML comment.
     */
    public static String comment(String comment) {
        return String.format("<!-- %s -->", comment);
    }

    /**
     * Make an Ant property.
     * @param name The name of the property to be formatted.
     * @param location The location that the property points to.
     * @return String containing an ant property element with the
     *         specified name and location.
     */
    public static String antProperty(String name, String location) {
        return String.format(
            "<property name=\"%s\" location=\"%s\" />",
            name,
            location
        );
    }

    /**
     * Make the opening tag of an Ant property.
     * @param targetName
     * @return String containing the opening tag of the specified target.
     */
    public static String antTarget(String targetName) {
        return String.format("<target name=\"%s\" >", targetName);
    }

    /**
     * Make the opening tag of an Ant property with dependencies.
     * @param targetName
     * @param depends The targets that this target depends on, formatted
     *                as a comma-separated list.
     * @return String containing the opening tag of the specified target
     *         and the specified dependencies.
     */
    public static String antTarget(String targetName, String depends) {
        return String.format(
            "<target name=\"%s\" depends=\"%s\" >",
            targetName,
            depends
        );
    }

    /**
     * Make an Ant delete task.
     * @param dirName The name of the directory to be deleted. This can
     *                be a property name enclosed in ${}.
     * @return String containing an ant delete task for the specified
     *         directory.
     */
    public static String deleteDir(String dirName) {
        return String.format("<delete dir=\"%s\" />", dirName);
    }

    /**
     * Make an Ant mkdir task.
     * @param dirName The name of the directory to be created. This can
     *                be a property name enclosed in ${}.
     * @return String containing an ant mkdir task for the specified
     *         directory.
     */
    public static String makeDir(String dirName) {
        return String.format("<mkdir dir=\"%s\" />", dirName);
    }

    /**
     * Make the opening tag of an Ant javac task.
     * @param debug Boolean indicating whether or not the compiler
     *              should include debug information, like file lines
     *              in stacktraces.
     * @return String containing the opening tag of an Ant javac task.
     *         The ant runtime libraries are not included, and the debug
     *         option is set as specified.
     */
    public static String javac(boolean debug) {
        return String.format(
            "<javac includeantruntime=\"false\" srcdir=\"${src.dir}\" destdir=\"${build.dir}\" debug=\"%s\">",
            (debug) ? "true" : "false"
        );
    }

    /**
     * Make the opening tag of an Ant jar task.
     * @param jarName What the jar file will be called. Must include
     *                file extension.
     * @return String containing the opening tag of an Ant jar task,
     *         using the specified name.
     */
    public static String jar(String jarName) {
        return String.format(
            "<jar destfile=\"${dist.dir}/%s\" basedir=\"${build.dir}\">",
            jarName
        );
    }

    /**
     * Make the closing tag of any element.
     * @param tagName The element that is being closed.
     * @return String containing the closing tag of the specified
     *         element.
     */
    public static String endTag(String tagName) {
        return String.format("</%s>", tagName);
    }
}
