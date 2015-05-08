package org.exoplatform.bch.activity.client.retrofit;

/**
 * Created by bdechateauvieux on 5/8/15.
 */
public class HttpRestClientException extends RuntimeException {
    private final int status;

    public HttpRestClientException(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
