package downloader_iwamoto.abs.co.jp.downloader;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Text;
    Button Stert;
    ImageView View;
    private DownLoadAsyncTask task;

    Toast no;
    Toast ok;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        no = Toast.makeText(this,"画像取得に失敗",Toast.LENGTH_LONG);
        ok = Toast.makeText(this,"ダウンロードが完了しました", Toast.LENGTH_LONG);

        //紐づけ
        Text = findViewById(R.id.text);
        Stert = findViewById(R.id.start);
        View = findViewById(R.id.view);

        //ダウンロード開始ボタンタップ時
        Stert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = Text.getText().toString();

                if (url.length() != 0) {
                    //EditTextに文字が入っている場合
                    task = new DownLoadAsyncTask();
                    //Lisntenerを設定
                    task.setListener(createListener());
                    task.execute(url);
                }else{
                    no.show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        task.setListener(null);
        super.onDestroy();
    }

    private DownLoadAsyncTask.Listener createListener(){
        return new DownLoadAsyncTask.Listener() {
            @Override
            public void onSuccess(Bitmap bpm) {
                View.setImageBitmap(bpm);
                if (bpm == null){
                    no.show();
                }else{
                    ok.show();
                }
                Log.d("BPMの中身", String.valueOf(bpm));
            }
        };
    }
}

