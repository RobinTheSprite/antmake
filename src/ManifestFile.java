/**
 *
 * ManifestFile.java
 * @author Mark Underwood
 * https://github.com/RobinTheSprite
 * 12/21/2020
 *
 */

package src;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores and converts Java manifest headers to XML snippets
 * suitable for ant scripts.
 */
public class ManifestFile {
    private HashMap<String, String> headers = new HashMap<>();

    /**
     * Convert a stored header into an attribute element
     * @param name
     * @return String containing an Ant attribute element.
     */
    private String headerString(String name) {
        return String.format(
            "<attribute name=\"%s\" value=\"%s\" />",
            name,
            headers.get(name)
        );
    }

    /**
     * Add a header to the manifest file. A header must have
     * both a name and a value.
     * @param name
     * @param value
     */
    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    /**
     * Remove a header from the manifest file.
     * @param name
     * @return String containing the removed value.
     */
    public String removeHeader(String name) {
        String value = headers.get(name);

        headers.remove(name);

        return value;
    }

    /**
     *
     * @return An ArrayList of Strings, which contain the Ant
     * attribute elements for the currently stored headers.
     */
    public ArrayList<String> getHeaders() {
        ArrayList<String> headerStrings = new ArrayList<>();
        for (String name : headers.keySet()) {
            headerStrings.add(headerString(name));
        }

        return headerStrings;
    }
}
