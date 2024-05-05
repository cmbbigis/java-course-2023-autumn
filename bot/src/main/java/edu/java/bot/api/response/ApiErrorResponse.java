package edu.java.bot.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class ApiErrorResponse {
    private String description;
    private String code;
    private String exceptionName;
    private String exceptionMessage;
    private List<String> stacktrace;

    @Schema(description = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Schema(description = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Schema(description = "exceptionName")
    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    @Schema(description = "exceptionMessage")
    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Schema(description = "stacktrace")
    public List<String> getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(List<String> stacktrace) {
        this.stacktrace = stacktrace;
    }
}
