package zy.nav.example;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text);
        textView.setText("");
        /*Uri uri = Uri.parse("content://com/example/project:200/folder/subfolder/etc?name=bqt&age=28");
        textView.append("scheme = " + uri.getScheme());
        textView.append("\n");
        textView.append("host = " + uri.getHost());
        textView.append("\n");
        textView.append("Authority = " + uri.getAuthority());
        textView.append("\n");
        textView.append("path = " + uri.getPath());
        textView.append("\n");
        textView.append("query = " + uri.getQuery());
        textView.append("\n");
        textView.append("PathSegments = " + uri.getPathSegments());
        textView.append("\n");
        textView.append("Fragment = " + uri.getFragment());
        textView.append("\n");*/
        Uri uri = Uri.parse("scheme:/pay");
        textView.append(uri.getQueryParameterNames().toString());
        textView.append("\n");
        textView.append(TextUtils.isEmpty(uri.getScheme()) ? "scheme is null" : uri.getScheme());
        textView.append("\n");
        textView.append(TextUtils.isEmpty(uri.getHost()) ? "host is null" : uri.getHost());
        textView.append("\n");
        textView.append(uri.getPath());
        textView.append("\n");
        textView.append("uri.isHierarchical --- " + uri.isOpaque());
    }
}
