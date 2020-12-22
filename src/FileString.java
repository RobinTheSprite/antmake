/*
 *
 * FileString.java
 * Mark Underwood
 * https://github.com/RobinTheSprite
 * 12/21/2020
 *
 */

package src;

/**
 * Creates a string suitable for writing to a file.
 */
public class FileString {
    private StringBuilder file = new StringBuilder();
    private int tabDepth = 0;
    private String tab = new String();

    /**
     *
     * @param tabSize How many spaces to put in one tab
     */
    public FileString(int tabSize) {
        setTabSize(tabSize);
    }

    /**
     *
     * @param size How many spaces to put in one tab
     */
    public void setTabSize(int size) {
        tab = "";
        for (int i = 0; i < size; ++i) {
            tab += " ";
        }
    }

    /**
     *
     * @param depth How many tab levels to append to the beginning
     *              of each line.
     */
    public void setTabDepth(int depth) {
        this.tabDepth = depth;
    }

    /**
     * Writes an empty line
     */
    public void writeLine() {
        write("\n");
    }

    /**
     *
     * @param line The text to be written to the line
     */
    public void writeLine(String line) {
        for (int i = 0; i < tabDepth; ++i) {
            write(tab);
        }

        write(line + "\n");
    }

    /**
     *
     * @param str The text to be written.
     */
    public void write(String str) {
        file.append(str);
    }

    /**
     *
     * @return String containing the entire text of the file.
     */
    public String getFile() {
        return file.toString();
    }
}
