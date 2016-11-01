package com.example.janna.chat_2;


import android.app.Activity;
        import android.database.DataSetObserver;
        import android.os.Bundle;
        import android.view.KeyEvent;
        import android.view.View;
        import android.widget.AbsListView;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ListView;
        import android.widget.Toast;

        import butterknife.BindView;
        import butterknife.ButterKnife;
        import butterknife.OnClick;


public class MainActivity extends Activity {
    private static final String TAG = "ChatActivity";
    private ChatArrayAdapter chatArrayAdapter;
    @BindView(R.id.msgview) ListView mListView;
    @BindView(R.id.msg) EditText mChatText;
    @BindView(R.id.send) ImageButton mButtonSend;
    private boolean side;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.right);
        mListView.setAdapter(chatArrayAdapter);
        mChatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    sendChatMessage(true);
                    return true;
                }
                return false;
            }
        });
        mListView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        mListView.setAdapter(chatArrayAdapter);
    }
    @OnClick(R.id.send)
    public void sendMessage(View view){
        sendChatMessage(true);
    }
    private boolean sendChatMessage(Boolean esMio) {
        String mText = mChatText.getText().toString();
        if(!mText.trim().matches("")){
            chatArrayAdapter.add(new ChatMessage(esMio, mChatText.getText().toString()));
            /*
            INTERNET CODE


             */
        }else{
            Toast.makeText(this,"Please enter something on the chat box", Toast.LENGTH_SHORT).show();
        }
        //side=!side;
        mChatText.setText("");
        return true;
    }
}