/*
 *
 * FileString.java
 * Mark Underwood
 * https://github.com/RobinTheSprite
 * 12/21/2020
 *
 */

package src;

public class FileString {
    private StringBuilder file = new StringBuilder();
    private int tabDepth = 0;
    private String tab = new String();

    public FileString(int tabSize) {
        setTabSize(tabSize);
    }

    public void setTabSize(int size) {
        tab = "";
        for (int i = 0; i < size; ++i) {
            tab += " ";
        }
    }

    public void setTabDepth(int depth) {
        this.tabDepth = depth;
    }

    public void writeLine() {
        write("\n");
    }

    public void writeLine(String line) {
        for (int i = 0; i < tabDepth; ++i) {
            write(tab);
        }

        write(line + "\n");
    }

    public void write(String str) {
        file.append(str);
    }

    public String getFile() {
        return file.toString();
    }
}
