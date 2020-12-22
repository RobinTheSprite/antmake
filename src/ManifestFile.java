/*
 *
 * ManifestFile.java
 * Mark Underwood
 * https://github.com/RobinTheSprite
 * 12/21/2020
 *
 */

package src;

import java.util.ArrayList;
import java.util.HashMap;

public class ManifestFile {
    private HashMap<String, String> headers = new HashMap<>();

    private String headerString(String name) {
        return String.format(
            "<attribute name=\"%s\" value=\"%s\" />",
            name,
            headers.get(name)
        );
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public String removeHeader(String name) {
        String value = headers.get(name);

        headers.remove(name);

        return value;
    }

    public ArrayList<String> getHeaders() {
        ArrayList<String> headerStrings = new ArrayList<>();
        for (String name : headers.keySet()) {
            headerStrings.add(headerString(name));
        }

        return headerStrings;
    }
}
