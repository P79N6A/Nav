package zy.nav;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.LinkedHashSet;
import java.util.Set;

public final class NavConfig {

    private boolean debug;

    private Set<String> baseUrlList;

    private NavConfig() {
        this.baseUrlList = new LinkedHashSet<>();
    }

    public static NavConfig getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Set<String> getBaseUrlList() {
        return baseUrlList;
    }

    public boolean isDebug() {
        return debug;
    }

    private void update(Builder builder) {
        this.baseUrlList = builder.baseUrlList;
        this.debug = builder.debug;
    }

    private static class InstanceHolder {
        private static final NavConfig INSTANCE = new NavConfig();
    }

    public static class Builder {

        private boolean debug;

        private Set<String> baseUrlList;

        private Builder() {
            baseUrlList = new LinkedHashSet<>();
        }

        public Builder debug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public Builder addBaseUrl(@NonNull String url) {
            if (TextUtils.isEmpty(url)) {
                throw new IllegalArgumentException("url is null or empty");
            }
            Uri uri = Uri.parse(url);
            if (TextUtils.isEmpty(uri.getScheme())) {
                throw new IllegalArgumentException("scheme is empty");
            }
            if (TextUtils.isEmpty(uri.getHost())) {
                throw new IllegalArgumentException("host is empty");
            }
            if (url.endsWith("/")) {
                url = url.substring(0, url.length() - 1);
            }
            this.baseUrlList.add(url);
            return this;
        }

        public NavConfig build() {
            NavConfig instance = NavConfig.getInstance();
            instance.update(this);
            return instance;
        }
    }

}
