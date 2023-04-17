package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Application;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.retrofit2.createAccountModel;
import com.example.myapplication.retrofit2.masterRetrofit2;
import com.google.gson.annotations.SerializedName;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {
    /*
    private ArrayList<Integer> imageList = new ArrayList<>(
            Arrays.asList(
                    R.drawable.ic_launcher_background,
                    R.drawable.ic_launcher_foreground
            )
    );
    */

    ExecutorService executorService = Executors.newFixedThreadPool(4);


    private static String ip = "192.168.1.7";
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "COMPANY";
    private static String username = "HWANG";
    private static String password = "hjh135";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private Connection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        /*
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.viewPager.setOffscreenPageLimit(5);
        binding.viewPager.setPageTransformer(new ZoomInPageTransformer());
        binding.viewPager.setAdapter(new PagerAdapter().setImageList(imageList));
        */

        // mssql
        Button get_btn = findViewById(R.id.button1);
        get_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println("start thread...");
                Log.d("TESTLOG","start thread...");
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                RequestThread thread = new RequestThread();
                thread.start();
            }
        });


/*
        String str;

        try {
            str = new Http().execute("test=value&test 1=value1").get();
            Log.d("결과값 데이터 >>", str);
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
*/
/*
        ViewPager2 viewpager2 = (ViewPager2) findViewById(R.id.viewPager2);

        List<Model> stringArrayList = new ArrayList<>();
        stringArrayList.add(new Model("AAAAAAAA", "test"));
        stringArrayList.add(new Model("BBBBBBBB", "test"));
        stringArrayList.add(new Model("CCCCCCCC", "test"));
        stringArrayList.add(new Model("DDDDDDDD", "test"));

        MyAdapter adapter = new MyAdapter(stringArrayList, this);

        viewpager2.setAdapter(adapter);
*/
        /*
        String url = "http://apis.data.go.kr/1613000/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList?serviceKey=zHfs9G4Ov6Oa8b8xIEKrSgJlA79ZaKBQdKaGv5kGdHBgA%2Bv%2BEG%2Fq%2F9A7EXT7JrvAmyfkUV7E7mn%2FHSniwdqHTA%3D%3D&cityCode=31170&nodeId=GGB226000039&numOfRows=10&pageNo=1&_type=xml";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            URL uri= new URL(url);

            InputStream inputStream = uri.openStream();

            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new InputStreamReader(inputStream, "UTF-8"));

            String tag;
            xmlPullParser.next();

            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:
                        System.out.print("<" + xmlPullParser.getName() + "> ");
                        xmlPullParser.next();
                        System.out.print(xmlPullParser.getText());
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        System.out.println(" <" + xmlPullParser.getName() + ">");
                }
                eventType = xmlPullParser.next();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        */

        // retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://769d0923-3721-4993-b2fa-fb8bb5605ab4.mock.pstmn.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service1 = retrofit.create(RetrofitService.class);
/*
        Call<PostResult> call = service1.getPosts("1");

        call.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                System.out.println("============Retrofit=============");
                if (response.isSuccessful()) {
                    PostResult result = response.body();
                    System.out.println("onResponse: 성공\n결과 : " + result.toString());
                } else {
                    System.out.println("onResponse: 실패");
                }
            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                System.out.println("onFailure: " + t.getMessage());
            }
        });*/

        PostResult post_test = new PostResult(
                "test_id",
                "test_pw",
                "test_nickname",
                "test_message"
        );
        service1.setPostField(post_test.id, post_test.pw, post_test.nickname, post_test.statemessage).enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                if (response.isSuccessful()) {
                    PostResult result = response.body();
                    System.out.println("onResponse: 성공\n결과 : " + result.toString());
                } else {
                    System.out.println("onResponse: 실패");
                }
            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                System.out.println("onFailure: " + t.getMessage());
            }
        });
        System.out.println("111");

        /////////////////////////////////////////////////////////////
        //oracle

        Button res_btn = (Button) findViewById(R.id.button2);
        EditText et_id = (EditText) findViewById(R.id.editText_id);
        EditText et_pw = (EditText) findViewById(R.id.editText_pw); // pw 123

        res_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                masterRetrofit2 retrofit = new masterRetrofit2();
//
//                retrofit.createAccount("test_id001", "test_pw001").enqueue(new Callback<createAccountModel>() {
//                    @Override
//                    public void onResponse(Call<createAccountModel> call, Response<createAccountModel> response) {
//                        Log.d("TESTLOG02","result : "+response.body().toString());
//                    }
//
//                    @Override
//                    public void onFailure(Call<createAccountModel> call, Throwable t) {
//                        Log.d("TESTLOG02","Failed"+t.toString());
//                    }
//                });


                try {
                    System.out.println("============Oracle=============");

                    System.out.println("start oracle thread...");
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    OracleThread thread = new OracleThread();
                    thread.start();

                    /*
                    String result;
                    String id = et_id.getText().toString();
                    String pw = et_pw.getText().toString();

                    RegisterActivity task = new RegisterActivity();
                    result = task.execute(id, pw).get();*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class OracleThread extends Thread {
        @Override
        public void run() {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.1.7:1521/xe", "C##HJONGHA", "qwer1234");

                System.out.println("Oracle Connected");

                Statement statement = connection.createStatement();

                int res = statement.executeUpdate("CREATE TABLE MYDB (data1 VARCHAR2(100), data2 VARCHAR2(100));");
                if (res > 0) {
                    System.out.println("CREATE 성공");
                } else {
                    System.out.println("CREATE 실패");
                }

                int res2 = statement.executeUpdate("INSERT INTO MYDB VALUES('test1', 'test2')");
                if (res2 > 0) {
                    System.out.println("INSERT 성공");
                } else {
                    System.out.println("INSERT 실패");
                }

                StringBuffer stringBuffer = new StringBuffer();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM MYDB");

                while (resultSet.next()) {
                    stringBuffer.append(resultSet.getString(0)+", ");
                    stringBuffer.append(resultSet.getString(1)+"\n");
                }

                System.out.println(stringBuffer.toString());
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    /*
    public class RegisterActivity extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;

        @Override
        protected String doInBackground(String... strings) {
            try {
                Log.d("TESTLOG","php connecting...");
                String str;

                URL url = new URL("http://192.168.1.7:1433/nalab/androidDB.jsp");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

                sendMsg = "id=" + strings[0] + "&pw=" + strings[1];

                osw.write(sendMsg);
                osw.flush();

                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();

                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else {
                    System.out.println("통신 실패");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return receiveMsg;
        }
    }*/


    // DTO 모델 - PostResult Class 선언
    public class PostResult {

        @SerializedName("id")
        private String id;

        @SerializedName("pw")
        private String pw;
        // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함

        @SerializedName("nickname")
        private String nickname;
        // @SerializedName()로 변수명을 입치시켜주면 클래스 변수명이 달라도 알아서 매핑시켜줌

        @SerializedName("statemessage")
        private String statemessage;

        public PostResult(String id, String pw, String nickname, String statemessage) {
            this.id = id;
            this.pw = pw;
            this.nickname = nickname;
            this.statemessage = statemessage;
        }

        // toString()을 Override 해주지 않으면 객체 주소값을 출력함
        @Override
        public String toString() {
            return "PostResult{" +
                    "id=" + id +
                    ", pw=" + pw +
                    ", nickname='" + nickname + '\'' +
                    ", statemessage='" + statemessage + '\'' +
                    '}';
        }
    }

    public interface RetrofitService {

        // @GET( EndPoint-자원위치(URI) )
        @GET("register")
        Call<PostResult> getPosts(@Path("post") String post);

        @POST("register")
        Call<PostResult> setPosts(@Body PostResult post);

        @FormUrlEncoded
        @POST("register")
        Call<PostResult> setPostField(
                @Field("id") String id,
                @Field("pw") String pw,
                @Field("nickname") String nickname,
                @Field("statemessage") String statemessage
        );
    }


/*
    public class configs extends Application {
        private String Url = "";
        private String strPhone = "";

        public String getUrl() {
            return Url;
        }

        public void setUrl(String url) {
            Url = url;
        }

        public String getStrPhone() {
            return strPhone;
        }

        public void setStrPhone(String strPhone) {
            this.strPhone = strPhone;
        }
    }

    public class Http extends AsyncTask<String, Void, String> {
        private configs conf;
        private String ip;
        public void setip(String ip) { this.ip = ip; }
        @Override
        protected String doInBackground(String... sId) {
            String sResult = "Error";
            try {
                conf = new configs();
                conf.setUrl(ip);

                URL url = new URL(conf.getUrl());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);
                conn.setUseCaches(false);

                conn.setRequestMethod("POST");
                String body = sId[0];

                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                osw.write(body);
                osw.flush();

                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuilder builder = new StringBuilder();
                String str;

                while ((str = reader.readLine()) != null) {
                    builder.append(str);
                }
                sResult = builder.toString();
            }
            catch (MalformedURLException e) {
                Log.d("TAG : MalformedURLException", e.toString());
                e.printStackTrace();
            }
            catch (IOException e) {
                Log.d("TAG : IOException", e.toString());
                e.printStackTrace();
            }
            return sResult;
        }
    }

 */

    class RequestThread extends Thread {
        @Override
        public void run() {
            try {
                Class.forName(Classes);
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("============MsSQL=============");
                System.out.println("SUCCESS");

                if (connection != null) {
                    Statement statement = null;
                    try {
                        statement = connection.createStatement();
//                        int res = statement.executeUpdate("INSERT INTO EMPLOYEE VALUES (1235, 'test123', '사원', 3011, 1500000, 3)");
//                        if (res > 0) {
//                            System.out.println("INSERT 성공");
//                        } else {
//                            System.out.println("INSERT 실패");
//                        }

                        ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEE");

                        while (resultSet.next()) {
//                            System.out.println(resultSet.getString(1));
                            for (int index=1; index<7; index++) {
                                try {
                                    //Log.d("TESTLOG",resultSet.getString(index).replaceAll("\n", "") + "\t");
                                    System.out.print(resultSet.getString(index).replaceAll("\n", "") + "\t");
                                } catch (NullPointerException e) {
                                    //Log.d("TESTLOG", "NULL\t");
                                    System.out.print("NULL\t");
                                }
                            }
                            System.out.println();
                            Log.d("TESTLOG", "========================================================");
                        }
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("Connection is null");
                }
                /*
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                InputStream caInput = new BufferedInputStream(new FileInputStream("load-der.crt"));
                Certificate ca;
                try {
                    ca = cf.generateCertificate(caInput);
                    System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
                } finally {
                    caInput.close();
                }

                String keyStoreType = KeyStore.getDefaultType();
                KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                keyStore.load(null, null);
                keyStore.setCertificateEntry("ca", ca);

                String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
                tmf.init(keyStore);

                SSLContext context = SSLContext.getInstance("TLS");
                context.init(null, tmf.getTrustManagers(), null);
*/
                ///////////////////////////////////////////////////////
/*
                URL url = new URL("http://apis.data.go.kr/1613000/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList?serviceKey=zHfs9G4Ov6Oa8b8xIEKrSgJlA79ZaKBQdKaGv5kGdHBgA%2Bv%2BEG%2Fq%2F9A7EXT7JrvAmyfkUV7E7mn%2FHSniwdqHTA%3D%3D&cityCode=31170&nodeId=GGB226000039&numOfRows=10&pageNo=1&_type=xml");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                if (conn != null) {
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    //conn.setDoOutput(true);
                    //disableSslVerification();

                    int resCode = conn.getResponseCode();
                    if (resCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String line = null;
                        while (true) {
                            line = reader.readLine();
                            if (line == null)
                                break;
                            System.out.println(line);
                        }
                        reader.close();
                    }
                    conn.disconnect();
                }
/////////////////////////////////////////////////////////////
                conn = (HttpURLConnection) url.openConnection();
                if (conn != null) {
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    String body = "test123123";
                    OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

                    int resCode = conn.getResponseCode();
                    if (resCode == HttpURLConnection.HTTP_OK) {
                        osw.write(body);
                        //osw.flush();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder builder = new StringBuilder();
                        String str;

                        while ((str = reader.readLine()) != null) {
                            builder.append(str);
                        }
                        System.out.println(builder.toString());
                    }
                }*/
            } catch (Exception e) {
                System.out.println("error");
                e.printStackTrace();
            }
        }
    }
/*
    public void disableSslVerification(){
        // TODO Auto-generated method stub
        try
        {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType){
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType){
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session){
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }*/
    /*
    class ZoomInPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;

        private float imageMargin = getResources().getDimensionPixelOffset(R.dimen.image_margin);
        private float imageSize = getResources().getDimensionPixelSize(R.dimen.image_size);
        private float screenWidth = getResources().getDisplayMetrics().widthPixels;
        private float offsetPx = screenWidth - imageMargin - imageSize;

        @Override
        public void transformPage(@NonNull View page, float position) {
            page.setTranslationX(position * -offsetPx);
            if (position < -1)
                return;
            if (position <= 1) {
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position * getEase(Math.abs(position))));
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
            }
            else {
                page.setScaleX(MIN_SCALE);
                page.setScaleY(MIN_SCALE);
            }
        }

        private float getEase(float position) {
            float sqt = position * position;
            return sqt / (2.0f * (sqt - position) + 1.0f);
        }
    }

    private class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.ViewHolder> {
        private ArrayList<Integer> imageList;

        private class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                this.imageView = itemView.findViewById(R.id.imageView);
            }
        }

        public PagerAdapter setImageList(ArrayList<Integer> imageList) {
            this.imageList = imageList;
            return this;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if (imageList == null)
                return;
            Drawable image = ContextCompat.getDrawable(holder.imageView.getContext(), imageList.get(position));
            Glide.with(holder.imageView.getContext()).load(image).override(250, 250).into(holder.imageView);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
        }

        @Override
        public int getItemCount() {
            if (imageList == null) {
                return 0;
            }
            else {
                return imageList.size();
            }
        }
    }

    public abstract class Result<T> {
        private Result() {}

        public final class Success<T> extends Result<T> {
            public T data;

            public Success(T data) {
                this.data = data;
            }
        }

        public final class Error<T> extends Result<T> {
            public Exception exception;

            public Error(Exception exception) {
                this.exception = exception;
            }
        }
    }

     */

    /*
    public class LoginRepository {
        private final String loginUrl = "";
        private final LoginResponseParser responseParser;
        public LoginRepository(LoginResponseParser responseParser) {
            this.responseParser = responseParser;
        }

        public Result<LoginResponse> makeLoginRequest(String jsonBody) {
            try {
                URL url = new URL(loginUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type", "application/json; chatset=utf-8");
                httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.getOutputStream().write(jsonBody.getBytes("utf-8"));

                LoginResponse
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
     */


}