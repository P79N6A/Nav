package zy.nav;

import android.content.Intent;

public final class Response {

    private boolean success;

    private String message;

    private final Intent intent;

    public Response() {
        this.intent = new Intent();
    }

    public static Response newResponse() {
        return new Response();
    }

    public Intent intent() {
        return intent;
    }

    public boolean success() {
        return success;
    }

    public String message() {
        return message;
    }

    public Response success(boolean success) {
        this.success = success;
        if (success) {
            message("success");
        }
        return this;
    }

    public Response message(String message) {
        this.message = message;
        return this;
    }

    static Response failure(String message) {
        return newResponse().success(false).message(message);
    }
}
