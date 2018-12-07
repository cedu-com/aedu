package net.chinaedu.aedu.sample;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;

import net.chinaedu.aedu.image.loader.AeduImageLoaderFactory;
import net.chinaedu.aedu.network.http.AeduHttpServiceBuilder;
import net.chinaedu.aedu.network.http.AeduHttpServiceParameter;
import net.chinaedu.aedu.network.http.IAeduHttpResponse;
import net.chinaedu.aedu.network.http.IAeduHttpService;
import net.chinaedu.aedu.utils.LogUtils;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView mTv;

    private ImageView mIv;

    private Button btnNetworkTest;

    public static class MessageEvent {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtils.d("yyyyyyy onCreate!!!");

        setContentView(R.layout.activity_main);
        mTv = (TextView) findViewById(R.id.tv);
        mTv.setOnClickListener(this);
        mIv = (ImageView) findViewById(R.id.iv);

        btnNetworkTest = (Button) findViewById(R.id.btn_network_test);
        btnNetworkTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AeduHttpServiceParameter parameter = new AeduHttpServiceParameter.Builder()
                        .connectTimeout(20)
                        .readTimeout(20)
                        .writeTimeout(20)
                        .timeUnit(TimeUnit.SECONDS)
                        .build();
                IAeduHttpService service = AeduHttpServiceBuilder.create("http://news-at.zhihu.com/api/4/", parameter).build();
                service.get("version/android/2.3.0", null, null, new TestCallbackIml());

            }
        });

        AeduImageLoaderFactory.getDefault()
                .load("http://www.jcodecraeer.com/uploads/20140731/67391406772378.png")
                .placeHolder(new ColorDrawable(Color.parseColor("#ff999999")))
//                .error(getResources().getDrawable(R.mipmap.aedu_alert))
                .resize(50, 50)
                .into(this, mIv);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        EventBus.getDefault().unregister(this);
    }

    public void onClick(View view) {
//        EventBus.getDefault().post(new MessageEvent(), "my_tag");
    }

    abstract class TestCallback<T> implements IAeduHttpService.Callback<T> {

    }

    class TestCallbackIml extends TestCallback<Bean<String>> implements View.OnFocusChangeListener {
        @Override
        public void onSuccess(IAeduHttpResponse<Bean<String>> response) {
            System.out.println("yyy response getMsg=" + response.body().getMsg());
            Toast.makeText(MainActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {

        }
    }

    class TestCallbackIml1 implements IAeduHttpService.Callback<Bean<String>> {
        @Override
        public void onSuccess(IAeduHttpResponse<Bean<String>> response) {
            System.out.println("yyy response getMsg=" + response.body().getMsg());
            Toast.makeText(MainActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    class Bean<T> {

        /**
         * status : 1
         * msg : 【更新】
         * <p>
         * - 极大提升性能及稳定性
         * - 部分用户无法使用新浪微博登录
         * - 部分用户无图模式无法分享至微信及朋友圈
         * url : http://zhstatic.zhihu.com/pkg/store/daily/zhihu-daily-zhihu-2.6.0(744)-release.apk
         * latest : 2.6.0
         */

        @SerializedName("status")
        private int status;
        @SerializedName("msg")
        private String msg;
        @SerializedName("url")
        private String url;
        @SerializedName("latest")
        private T latest;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public T getLatest() {
            return latest;
        }

        public void setLatest(T latest) {
            this.latest = latest;
        }
    }
}
