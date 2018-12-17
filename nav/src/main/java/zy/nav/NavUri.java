package zy.nav;

import android.net.Uri;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class NavUri {

    List<Uri> uriList;

    private NavUri(String url) {
        this.uriList = new ArrayList<>();
        Utils.requireUrlNotEmpty(url);
        Uri uri = Uri.parse(url);
        if (TextUtils.isEmpty(uri.getScheme())
                || TextUtils.isEmpty(uri.getHost())) {
            Set<String> baseUrlList = NavConfig.getInstance().getBaseUrlList();
            for (String baseUrl : baseUrlList) {
                uriList.add(Uri.parse(baseUrl + uri.getPath()));
            }
        } else {
            uriList.add(uri);
        }
    }

    static NavUri build(String url) {
        return new NavUri(url);
    }
}
