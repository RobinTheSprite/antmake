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
     * Create a new FileString with the given tab size.
     * @param tabSize How many spaces to put in one tab.
     */
    public FileString(int tabSize) {
        setTabSize(tabSize);
    }

    /**
     * Increase or decrease the number of spaces in one tab.
     * @param size How many total spaces to put in one tab.
     */
    public void setTabSize(int size) {
        tab = "";
        for (int i = 0; i < size; ++i) {
            tab += " ";
        }
    }

    /**
     * Increase or deacrease the indentation level. The next line
     * written will have the given number of tabs prepended.
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
     * Write some amount of text, followed by a newline.
     * @param line The text to be written to the line
     */
    public void writeLine(String line) {
        for (int i = 0; i < tabDepth; ++i) {
            write(tab);
        }

        write(line + "\n");
    }

    /**
     * Write some text to the file.
     * @param str The text to be written.
     */
    public void write(String str) {
        file.append(str);
    }

    /**
     * Get the file, for the purpose of writing it to the screen,
     * or for writing it to disk.
     * @return String containing the entire text of the file.
     */
    public String getFile() {
        return file.toString();
    }
}
