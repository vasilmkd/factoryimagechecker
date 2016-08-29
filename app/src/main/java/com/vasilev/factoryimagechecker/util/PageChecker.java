package com.vasilev.factoryimagechecker.util;

import android.support.annotation.NonNull;
import android.util.Log;

import com.vasilev.factoryimagechecker.util.function.Predicate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class PageChecker {

    private static final String LOG_TAG = PageChecker.class.getSimpleName();

    private PageChecker() {}

    public static boolean fromUrl(@NonNull String url, @NonNull Predicate<String> predicate) {
        final Optional<String> result = download(url);
        if (result.isPresent()) {
            final String page = result.get();
            return predicate.test(page);
        }
        return false;
    }

    @NonNull
    private static Optional<String> download(@NonNull String url) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;

        try {
            final URL siteUrl = new URL(url);
            connection = (HttpURLConnection) siteUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            inputStream = connection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            final StringBuilder builder = new StringBuilder();
            String line;
            do {
                line = bufferedReader.readLine();
                builder.append(line);
            } while (line != null);

            return Optional.of(builder.toString());
        } catch (IOException e) {
            return Optional.empty();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Could not close buffered reader", e);
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Could not close input stream", e);
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
