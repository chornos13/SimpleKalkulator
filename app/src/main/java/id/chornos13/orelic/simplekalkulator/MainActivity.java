package id.chornos13.orelic.simplekalkulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView txt_jumlah;
    private EditText et_input1, et_input2, et_input3;
    private CheckBox cb_input1, cb_input2, cb_input3;
    private Button btn_tambah, btn_kurang, btn_kali, btn_bagi;
    private List<Double> list_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListener();
    }

    private void initViews()
    {
        txt_jumlah = findViewById(R.id.txt_jumlah);
        et_input1 = findViewById(R.id.input1);
        et_input2 = findViewById(R.id.input2);
        et_input3 = findViewById(R.id.input3);
        cb_input1 = findViewById(R.id.cb_1);
        cb_input2 = findViewById(R.id.cb_2);
        cb_input3 = findViewById(R.id.cb_3);
        btn_tambah = findViewById(R.id.btn_tambah);
        btn_bagi = findViewById(R.id.btn_bagi);
        btn_kali = findViewById(R.id.btn_kali);
        btn_kurang = findViewById(R.id.btn_kurang);
    }

    private void initListener()
    {
        btn_tambah.setOnClickListener(this);
        btn_bagi.setOnClickListener(this);
        btn_kali.setOnClickListener(this);
        btn_kurang.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(isValidInput())
        {
            switch (view.getId())
            {
                case R.id.btn_bagi:
                    txt_jumlah.setText(formatHasil(doBagi()));
                    break;
                case R.id.btn_tambah:
                    txt_jumlah.setText(formatHasil(doTambah()));
                    break;
                case R.id.btn_kali:
                    txt_jumlah.setText(formatHasil(doKali()));
                    break;
                case R.id.btn_kurang:
                    txt_jumlah.setText(formatHasil(doKurang()));
                    break;
            }
        }
    }

    private boolean isValidInput()
    {
        if(countAvailableInput(cb_input1.isChecked(), cb_input2.isChecked(), cb_input3.isChecked()) >= 2)
        {
            return true;
        }
        else
        {
            Toast.makeText(this, "Error, input checklist tidak boleh kurang dari 2", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private int countAvailableInput(Boolean... booleans)
    {
        list_input = new ArrayList<>();
        for(int i = 0; i < booleans.length; i++)
        {
            if(booleans[i])
            {
                switch (i)
                {
                    case 0:
                        list_input.add(Double.parseDouble(et_input1.getText().toString()));
                        break;
                    case 1:
                        list_input.add(Double.parseDouble(et_input2.getText().toString()));
                        break;
                    case 2:
                        list_input.add(Double.parseDouble(et_input3.getText().toString()));
                        break;
                }
            }
        }
        return list_input.size();
    }

    private double doTambah()
    {
        double jumlah = list_input.get(0);
        for(int i = 1; i < list_input.size(); i++)
        {
            jumlah += list_input.get(i);
        }
        return jumlah;
    }

    private double doKurang()
    {
        double jumlah = list_input.get(0);
        for(int i = 1; i < list_input.size(); i++)
        {
            jumlah -= list_input.get(i);
        }
        return jumlah;
    }

    private double doKali()
    {
        double jumlah = list_input.get(0);
        for(int i = 1; i < list_input.size(); i++)
        {
            jumlah *= list_input.get(i);
        }
        return jumlah;
    }

    private double doBagi()
    {
        double jumlah = list_input.get(0);
        for(int i = 1; i < list_input.size(); i++)
        {
            jumlah /= list_input.get(i);
        }
        return jumlah;
    }

    public String formatHasil(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }
}
