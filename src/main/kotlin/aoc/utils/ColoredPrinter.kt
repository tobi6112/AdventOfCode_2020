package aoc.utils

import org.apache.commons.lang3.SystemUtils;

object ColoredPrinter {
    fun print(string: String, foregroundColor: Color = Color.NONE, backgroundColor: Color = Color.NONE, padding: Int = 0) {
        kotlin.io.print(" ".repeat(padding) + "${foregroundColor.codeForeground}${backgroundColor.codeBackground}$string\u001B[0m")
    }

    fun println(string: String, foregroundColor: Color = Color.NONE, backgroundColor: Color = Color.NONE) {
        print(string, foregroundColor, backgroundColor)
        kotlin.io.print(System.lineSeparator())
    }

    fun isSupported(): Boolean {
        if(SystemUtils.IS_OS_LINUX) {
            return System.console() != null && System.getenv()["TERM"] != null;
        } else if(SystemUtils.IS_OS_WINDOWS) {
            return true; //TODO not implemented yet
        }
        return false;
    }
}