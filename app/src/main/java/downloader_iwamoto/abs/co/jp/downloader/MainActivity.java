package downloader_iwamoto.abs.co.jp.downloader;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Text;
    Button Start;
    ImageView View;
    private DownLoadAsyncTask Task;

    Toast No;
    Toast Ok;
    String Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        No = Toast.makeText(this, "画像取得に失敗",Toast.LENGTH_SHORT);
        Ok = Toast.makeText(this,"ダウンロードが完了しました",Toast.LENGTH_SHORT);

        //紐づけ
        Text = findViewById(R.id.text);
        Start = findViewById(R.id.start);
        View = findViewById(R.id.view);

        //ダウンロード開始ボタンタップ時
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Url = Text.getText().toString();

                if(Url.length() != 0){
                    //EditTextの文字が入っていた場合
                    Task = new DownLoadAsyncTask();
                    //Lisntenerを設定
                    Task.setListener(createListener());
                    Task.execute(Url);
                }else{
                    No.show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        Task.setListener(null);
        super.onDestroy();
    }

    private DownLoadAsyncTask.Listener createListener(){
        return new DownLoadAsyncTask.Listener() {
            @Override
            public void onSuccess(Bitmap bmp) {
                View.setImageBitmap(bmp);
                if (bmp == null) {
                    No.show();
                }else{
                    Ok.show();
                }
            }
        };
    }

}
