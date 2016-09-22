package net.liquidpineapple.pang.logger;

import lombok.Getter;

/**
 * @author Jurriaan Den Toonder
 * @date 2016/09/21.
 */
public enum LoggerTypes {
    INFO("[INFO]", 1),
    WARNING("[WARNING]", 2),
    ERROR("[ERROR]", 3);

    @Getter
    private String prefix;

    @Getter
    private int priority;

    LoggerTypes(String prefix, int priority) {
        this.prefix = prefix;
        this.priority = priority;
    }
}
