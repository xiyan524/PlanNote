package com.example.jenice.myplanapp.mine;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;
import com.example.jenice.myplanapp.R;

import static com.example.jenice.myplanapp.R.id.btn_mine_create_apply_changeto21;

public class MineCreateApplyNext extends AppCompatActivity implements View.OnClickListener{

    private Button btnchangeto21,btnOk;
    private ImageView ivBack;
    private LinearLayout lldays;
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,
            btn13,btn14,btn15,btn16,btn17,btn18,btn19,btn20,btn21;
    private AVUser user = AVUser.getCurrentUser();
    private String planName;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_create_apply_next);

        planName = getIntent().getStringExtra("name");

        btnchangeto21 = (Button) findViewById(btn_mine_create_apply_changeto21);
        lldays = (LinearLayout) findViewById(R.id.ll_mine_create_apply_duration_2);
        btn1 = (Button) findViewById(R.id.btn_mine_create_apply_day_1);
        btn2 = (Button) findViewById(R.id.btn_mine_create_apply_day_2);
        btn3 = (Button) findViewById(R.id.btn_mine_create_apply_day_3);
        btn4 = (Button) findViewById(R.id.btn_mine_create_apply_day_4);
        btn5 = (Button) findViewById(R.id.btn_mine_create_apply_day_5);
        btn6 = (Button) findViewById(R.id.btn_mine_create_apply_day_6);
        btn7 = (Button) findViewById(R.id.btn_mine_create_apply_day_7);
        btn8 = (Button) findViewById(R.id.btn_mine_create_apply_day_8);
        btn9 = (Button) findViewById(R.id.btn_mine_create_apply_day_9);
        btn10 = (Button) findViewById(R.id.btn_mine_create_apply_day_10);
        btn11 = (Button) findViewById(R.id.btn_mine_create_apply_day_11);
        btn12 = (Button) findViewById(R.id.btn_mine_create_apply_day_12);
        btn13 = (Button) findViewById(R.id.btn_mine_create_apply_day_13);
        btn14 = (Button) findViewById(R.id.btn_mine_create_apply_day_14);
        btn15 = (Button) findViewById(R.id.btn_mine_create_apply_day_15);
        btn16 = (Button) findViewById(R.id.btn_mine_create_apply_day_16);
        btn17 = (Button) findViewById(R.id.btn_mine_create_apply_day_17);
        btn18 = (Button) findViewById(R.id.btn_mine_create_apply_day_18);
        btn19 = (Button) findViewById(R.id.btn_mine_create_apply_day_19);
        btn20 = (Button) findViewById(R.id.btn_mine_create_apply_day_20);
        btn21 = (Button) findViewById(R.id.btn_mine_create_apply_day_21);
        ivBack = (ImageView) findViewById(R.id.iv_mine_create_apply__next_back);
        btnOk = (Button) findViewById(R.id.btn_mine_create_apply_next_ok);

        btnOk.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        btnchangeto21.setOnClickListener(this);
        btn1.setOnClickListener(this);btn2.setOnClickListener(this);btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);btn5.setOnClickListener(this);btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);btn8.setOnClickListener(this);btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);btn11.setOnClickListener(this);btn12.setOnClickListener(this);
        btn13.setOnClickListener(this);btn14.setOnClickListener(this);btn15.setOnClickListener(this);
        btn16.setOnClickListener(this);btn17.setOnClickListener(this);btn18.setOnClickListener(this);
        btn19.setOnClickListener(this);btn20.setOnClickListener(this);btn21.setOnClickListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("是否取消创建计划？");
        builder.setMessage("是否放弃本次创建的计划？放弃后该计划不会保存！");
        builder.setNegativeButton("点错了，继续创建",new cancelOnClickImpl());
        builder.setPositiveButton("确认取消",new cancelOnClickImpl());
        dialog = builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_mine_create_apply_next_ok:
                String str = "点击填写计划详情";
                if (btn1.getText().toString().equals(str)||btn2.getText().toString().equals(str)||btn3.getText().toString().equals(str)
                        ||btn4.getText().toString().equals(str)||btn10.getText().toString().equals(str)||btn16.getText().toString().equals(str)
                        ||btn5.getText().toString().equals(str)||btn11.getText().toString().equals(str)||btn17.getText().toString().equals(str)
                        ||btn6.getText().toString().equals(str)||btn12.getText().toString().equals(str)||btn18.getText().toString().equals(str)
                        ||btn7.getText().toString().equals(str)||btn13.getText().toString().equals(str)||btn19.getText().toString().equals(str)
                        ||btn8.getText().toString().equals(str)||btn14.getText().toString().equals(str)||btn20.getText().toString().equals(str)
                        ||btn9.getText().toString().equals(str)||btn15.getText().toString().equals(str)||btn21.getText().toString().equals(str)){
                    Toast.makeText(getApplicationContext(), "请将每日计划详情填写完整", Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(MineCreateApplyNext.this,MineCreate.class));
                }
                break;
            case R.id.iv_mine_create_apply__next_back:
                dialog.show();
                break;
            //选择21天
            case R.id.btn_mine_create_apply_changeto21:
                if (btnchangeto21.getText().equals("创建21天计划")){
                    lldays.setVisibility(View.VISIBLE);
                    btnchangeto21.setText("创建7天计划");
                }else if (btnchangeto21.getText().equals("创建7天计划")){
                    lldays.setVisibility(View.GONE);
                    btnchangeto21.setText("创建21天计划");
                }
                break;
            case R.id.btn_mine_create_apply_day_1:
                Intent intent1 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("day","1");
                bundle1.putString("planName",planName);
                intent1.putExtras(bundle1);
                startActivityForResult(intent1,101);
                break;
            case R.id.btn_mine_create_apply_day_2:
                Intent intent2 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("day","2");
                bundle2.putString("planName",planName);
                intent2.putExtras(bundle2);
                startActivityForResult(intent2,101);
                break;
            case R.id.btn_mine_create_apply_day_3:
                Intent intent3 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle3 = new Bundle();
                bundle3.putString("day","3");
                bundle3.putString("planName",planName);
                intent3.putExtras(bundle3);
                startActivityForResult(intent3,101);
                break;
            case R.id.btn_mine_create_apply_day_4:
                Intent intent4 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle4 = new Bundle();
                bundle4.putString("day","4");
                bundle4.putString("planName",planName);
                intent4.putExtras(bundle4);
                startActivityForResult(intent4,101);
                break;
            case R.id.btn_mine_create_apply_day_5:
                Intent intent5 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle5 = new Bundle();
                bundle5.putString("day","5");
                bundle5.putString("planName",planName);
                intent5.putExtras(bundle5);
                startActivityForResult(intent5,101);
                break;
            case R.id.btn_mine_create_apply_day_6:
                Intent intent6 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle6 = new Bundle();
                bundle6.putString("day","6");
                bundle6.putString("planName",planName);
                intent6.putExtras(bundle6);
                startActivityForResult(intent6,101);
                break;
            case R.id.btn_mine_create_apply_day_7:
                Intent intent7 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle7 = new Bundle();
                bundle7.putString("day","7");
                bundle7.putString("planName",planName);
                intent7.putExtras(bundle7);
                startActivityForResult(intent7,101);
                break;
            case R.id.btn_mine_create_apply_day_8:
                Intent intent8 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle8 = new Bundle();
                bundle8.putString("day","8");
                bundle8.putString("planName",planName);
                intent8.putExtras(bundle8);
                startActivityForResult(intent8,101);
                break;
            case R.id.btn_mine_create_apply_day_9:
                Intent intent9 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle9 = new Bundle();
                bundle9.putString("day","9");
                bundle9.putString("planName",planName);
                intent9.putExtras(bundle9);
                startActivityForResult(intent9,101);
                break;
            case R.id.btn_mine_create_apply_day_10:
                Intent intent10 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle10 = new Bundle();
                bundle10.putString("day","10");
                bundle10.putString("planName",planName);
                intent10.putExtras(bundle10);
                startActivityForResult(intent10,101);
                break;
            case R.id.btn_mine_create_apply_day_11:
                Intent intent11 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle11 = new Bundle();
                bundle11.putString("day","11");
                bundle11.putString("planName",planName);
                intent11.putExtras(bundle11);
                startActivityForResult(intent11,101);
                break;
            case R.id.btn_mine_create_apply_day_12:
                Intent intent12 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle12 = new Bundle();
                bundle12.putString("day","12");
                bundle12.putString("planName",planName);
                intent12.putExtras(bundle12);
                startActivityForResult(intent12,101);
                break;
            case R.id.btn_mine_create_apply_day_13:
                Intent intent13 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle13 = new Bundle();
                bundle13.putString("day","13");
                bundle13.putString("planName",planName);
                intent13.putExtras(bundle13);
                startActivityForResult(intent13,101);
                break;
            case R.id.btn_mine_create_apply_day_14:
                Intent intent14 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle14 = new Bundle();
                bundle14.putString("day","14");
                bundle14.putString("planName",planName);
                intent14.putExtras(bundle14);
                startActivityForResult(intent14,101);
                break;
            case R.id.btn_mine_create_apply_day_15:
                Intent intent15 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle15 = new Bundle();
                bundle15.putString("day","15");
                bundle15.putString("planName",planName);
                intent15.putExtras(bundle15);
                startActivityForResult(intent15,101);
                break;
            case R.id.btn_mine_create_apply_day_16:
                Intent intent16 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle16 = new Bundle();
                bundle16.putString("day","16");
                bundle16.putString("planName",planName);
                intent16.putExtras(bundle16);
                startActivityForResult(intent16,101);
                break;
            case R.id.btn_mine_create_apply_day_17:
                Intent intent17 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle17 = new Bundle();
                bundle17.putString("day","17");
                bundle17.putString("planName",planName);
                intent17.putExtras(bundle17);
                startActivityForResult(intent17,101);
                break;
            case R.id.btn_mine_create_apply_day_18:
                Intent intent18 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle18 = new Bundle();
                bundle18.putString("day","18");
                bundle18.putString("planName",planName);
                intent18.putExtras(bundle18);
                startActivityForResult(intent18,101);
                break;
            case R.id.btn_mine_create_apply_day_19:
                Intent intent19 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle19 = new Bundle();
                bundle19.putString("day","19");
                bundle19.putString("planName",planName);
                intent19.putExtras(bundle19);
                startActivityForResult(intent19,101);
                break;
            case R.id.btn_mine_create_apply_day_20:
                Intent intent20 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle20 = new Bundle();
                bundle20.putString("day","20");
                bundle20.putString("planName",planName);
                intent20.putExtras(bundle20);
                startActivityForResult(intent20,101);
                break;
            case R.id.btn_mine_create_apply_day_21:
                Intent intent21 = new Intent(MineCreateApplyNext.this,MineCreateApplyDetail.class);
                Bundle bundle21 = new Bundle();
                bundle21.putString("day","21");
                bundle21.putString("planName",planName);
                intent21.putExtras(bundle21);
                startActivityForResult(intent21,101);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Bundle bundle = data.getExtras();
            String day = bundle.getString("day");
            if (day.equals("1")){
                btn1.setText(bundle.getString("name"));
            }else if (day.equals("2")){
                btn2.setText(bundle.getString("name"));
            }else if (day.equals("3")){
                btn3.setText(bundle.getString("name"));
            }else if (day.equals("4")){
                btn4.setText(bundle.getString("name"));
            }else if (day.equals("5")){
                btn5.setText(bundle.getString("name"));
            }else if (day.equals("6")){
                btn6.setText(bundle.getString("name"));
            }else if (day.equals("7")){
                btn7.setText(bundle.getString("name"));
            }else if (day.equals("8")){
                btn8.setText(bundle.getString("name"));
            }else if (day.equals("9")){
                btn9.setText(bundle.getString("name"));
            }else if (day.equals("10")){
                btn10.setText(bundle.getString("name"));
            }else if (day.equals("11")){
                btn11.setText(bundle.getString("name"));
            }else if (day.equals("12")){
                btn12.setText(bundle.getString("name"));
            }else if (day.equals("13")){
                btn13.setText(bundle.getString("name"));
            }else if (day.equals("14")){
                btn14.setText(bundle.getString("name"));
            }else if (day.equals("15")){
                btn15.setText(bundle.getString("name"));
            }else if (day.equals("16")){
                btn16.setText(bundle.getString("name"));
            }else if (day.equals("17")){
                btn17.setText(bundle.getString("name"));
            }else if (day.equals("18")){
                btn18.setText(bundle.getString("name"));
            }else if (day.equals("19")){
                btn19.setText(bundle.getString("name"));
            }else if (day.equals("20")){
                btn20.setText(bundle.getString("name"));
            }else if (day.equals("21")){
                btn21.setText(bundle.getString("name"));
            }
        }
    }

    private class cancelOnClickImpl implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == dialog.BUTTON_NEGATIVE){
                dialog.dismiss();
            }else if (which == dialog.BUTTON_POSITIVE){
                //未将计划从数据库中删除
                startActivity(new Intent(MineCreateApplyNext.this,MineCreateApply.class));
            }
        }
    }
}
