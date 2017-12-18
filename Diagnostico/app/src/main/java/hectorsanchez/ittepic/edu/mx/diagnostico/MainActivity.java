package hectorsanchez.ittepic.edu.mx.diagnostico;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mLog;
    Button run_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button runButton = (Button) findViewById(R.id.run_button);
        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runCode(v);
            }
        });
        findViewById(R.id.clear_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearLog(v);
                    }
                });

        mLog = (TextView) findViewById(R.id.log);
        mLog.setMovementMethod(new ScrollingMovementMethod());
        mLog.setText("");
        logMessage("onCreate");
    }

    public void runCode(View view) {
        logMessage("runCode");
    }
    public void clearLog(View view) {
        mLog.setText("");
        mLog.scrollTo(0, 0);
    }
    private void logMessage(String message){
        mLog.append(message+"\n");
    }

    @Override
    protected void onStart() {
        logMessage("onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        logMessage("onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        logMessage("onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        logMessage("onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        logMessage("onResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        logMessage("onRestart");
        super.onRestart();
    }
}
