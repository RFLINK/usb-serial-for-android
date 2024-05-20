package com.hoho.android.usbserial.examples;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.fragment, new DevicesFragment(), "devices").commit();
        else
            onBackStackChanged();
    }
    */
    String[] choices920mhz = {"CH1","CH2","CH3","CH4","CH5","CH6","CH7","CH8","CH9","CH10","CH11","CH12","CH13","CH14","CH15"
            ,"CH16","CH17","CH18","CH19","CH20","CH21","CH22","CH23","CH24","CH25","CH26","CH27","CH28","CH29","CH30","CH31"
            ,"CH32","CH33","CH34","CH35","CH36","CH37","CH38","CH39","CH40","CH41","CH42","CH43","CH44","CH45","CH46","CH47"
            ,"CH48","CH49","CH50","CH51","CH52","CH53","CH54","CH55","CH56","CH57","CH58","CH59","CH60","CH61"};
    String[] choices860mhz = {"CH100","CH101","CH102","CH103","CH104","CH105","CH106","CH107","CH108","CH109","CH110","CH111"
            ,"CH112","CH113","CH114","CH115","CH116","CH117","CH118","CH119","CH120","CH121","CH122","CH123","CH124","CH125"
            ,"CH126","CH127","CH128","CH129","CH130","CH131","CH132","CH133","CH134","CH135"};

    Spinner spinnerCH;
    @Override
    public void onBackStackChanged() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(getSupportFragmentManager().getBackStackEntryCount()>0);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent.getAction())) {
            TerminalFragment terminal = (TerminalFragment)getSupportFragmentManager().findFragmentByTag("terminal");
            if (terminal != null)
                terminal.status("USB device detected");
        }
        super.onNewIntent(intent);
    }

    @Override
    //レイアウトファイルの設定
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //変調方式の選択肢を指定
        Spinner spinnerHencho = findViewById(R.id.spinnerHencho);
        String[] choices = {"LoRa","FSK","GFSK"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, choices);// ArrayAdapterを作成して、選択肢のリストをスピナーにセット
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// ドロップダウンリストのレイアウトを指定
        spinnerHencho.setAdapter(adapter);
        // 初期値を設定
        int initialPosition = adapter.getPosition("LoRa");
        spinnerHencho.setSelection(initialPosition);

        //周波数グループの選択肢を指定
        Spinner spinnerShuhasuu = findViewById(R.id.spinnerShuhasuu);
        String[] choices1 = {"916.0-928.0","862.0-870.0"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, choices1);// ArrayAdapterを作成して、選択肢のリストをスピナーにセット
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// ドロップダウンリストのレイアウトを指定
        spinnerShuhasuu.setAdapter(adapter1);
        // 初期値を設定
        initialPosition = adapter1.getPosition("916.0-928.0");
        spinnerShuhasuu.setSelection(initialPosition);
        // spinnerShuhasuuにリスナーを設定
        spinnerShuhasuu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                updateSpinnerCH(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 必要に応じて処理を追加
            }
        });

        //CHの選択肢を指定
        spinnerCH = findViewById(R.id.spinnerCH);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, choices920mhz);// ArrayAdapterを作成して、選択肢のリストをスピナーにセット
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// ドロップダウンリストのレイアウトを指定
        spinnerCH.setAdapter(adapter2);
        // 初期値を設定
        initialPosition = adapter2.getPosition("CH24");
        spinnerCH.setSelection(initialPosition);

        //拡散率の選択肢を指定
        Spinner spinnerKakusan = findViewById(R.id.spinnerKakusan);
        String[] choices3 = {"SF6","SF7","SF8","SF9","SF10","SF11","SF12"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, choices3);// ArrayAdapterを作成して、選択肢のリストをスピナーにセット
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// ドロップダウンリストのレイアウトを指定
        spinnerKakusan.setAdapter(adapter3);
        // 初期値を設定
        initialPosition = adapter3.getPosition("SF10");
        spinnerKakusan.setSelection(initialPosition);

        //帯域の選択肢を指定
        Spinner spinnerTaiiki = findViewById(R.id.spinnerTaiiki);
        String[] choices4 = {"BW125","BW250","BW500"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, choices4);// ArrayAdapterを作成して、選択肢のリストをスピナーにセット
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// ドロップダウンリストのレイアウトを指定
        spinnerTaiiki.setAdapter(adapter4);
        // 初期値を設定
        initialPosition = adapter4.getPosition("BW125");
        spinnerTaiiki.setSelection(initialPosition);

        //通信速度の選択肢を指定
        Spinner spinnerSokudo = findViewById(R.id.spinnerSokudo);
        String[] choices5 = {"50Kbps","100Kbps","200Kbps"};
        ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, choices5);// ArrayAdapterを作成して、選択肢のリストをスピナーにセット
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// ドロップダウンリストのレイアウトを指定
        spinnerSokudo.setAdapter(adapter5);
        // 初期値を設定
        initialPosition = adapter5.getPosition("50Kbps");
        spinnerSokudo.setSelection(initialPosition);

        //ホワイトニングの選択肢を指定
        Spinner spinnerWhite = findViewById(R.id.spinnerWhite);
        String[] choices6 = {"ON","OFF"};
        ArrayAdapter<String> adapter6 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, choices6);// ArrayAdapterを作成して、選択肢のリストをスピナーにセット
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// ドロップダウンリストのレイアウトを指定
        spinnerWhite.setAdapter(adapter6);
        // 初期値を設定
        initialPosition = adapter6.getPosition("OFF");
        spinnerWhite.setSelection(initialPosition);

    }
    private void updateSpinnerCH(String selectedItem) {
        String[] newChItems;

        // 選択された項目に応じて新しいアイテムリストを設定
        switch (selectedItem) {
            case "916.0-928.0":
                newChItems = choices920mhz;
                break;
            case "862.0-870.0":
                newChItems = choices860mhz;
                break;
            default:
                newChItems = choices920mhz;
                break;
        }

        // 新しいアイテムリストでArrayAdapterを作成
        ArrayAdapter<String> chAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, newChItems);
        chAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // AdapterをspinnerCHにセット
            spinnerCH.setAdapter(chAdapter);


            // 初期値を設定
            if (selectedItem.equals("916.0-928.0")) {
                spinnerCH.setSelection(newChItems.length > 23 ? 23 : 0); // CH24 が存在する場合、そのインデックスに設定
            } else if (selectedItem.equals("862.0-870.0")) {
                spinnerCH.setSelection(newChItems.length > 0 ? 0 : 0); // 適切な初期値に設定
            }
    }

}
