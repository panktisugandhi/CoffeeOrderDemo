package com.example.ganesh.coffeeorderdemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.preference.DialogPreference;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.attr.name;
import static android.R.string.yes;
import static com.example.ganesh.coffeeorderdemo.R.id.etname;

public class MainActivity extends AppCompatActivity {
    CheckBox chcream,chchoco;
    EditText etname;
    int quantity=1;
    int price;
    String pricemsg;
    TextView tvqun,ordersum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW); //this line indicate to hide the keyboard when i have to open my application
        Button submitorder = (Button) findViewById(R.id.btnorder);
        submitorder.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                etname = (EditText) findViewById(R.id.etname);
                String name = etname.getText().toString();
                chcream = (CheckBox) findViewById(R.id.checkboxcream);
                chchoco =  (CheckBox) findViewById(R.id.checkboxchoco);
                boolean hascreame = chcream.isChecked();
                boolean hasechocolate = chchoco.isChecked();
                int price= calculateprice(hascreame,hasechocolate);
                displaymsg(createOrderSummery(name,price,hascreame,hasechocolate));
                AlertDialog.Builder msgalert = new AlertDialog.Builder(MainActivity.this);
                msgalert.setTitle("Final Order");
                msgalert.setMessage("\n"+pricemsg);
                msgalert.setCancelable(false);
                msgalert.setPositiveButton("yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        etname.setText("");
                        chcream.setChecked(false);
                        chchoco.setChecked(false);
                        ordersum.setText("$0");
                        Toast.makeText(MainActivity.this, "Thank you! for Order", Toast.LENGTH_SHORT).show(); }});
                msgalert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                });
                AlertDialog alertDialog = msgalert.create();
                alertDialog.show();
//                Toast.makeText(MainActivity.this, "Thank you! for Order", Toast.LENGTH_SHORT).show();
            }
        });
        Button btninc = (Button) findViewById(R.id.btnplus);
        btninc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (quantity == 100){
                    Toast.makeText(MainActivity.this, "You cannot have more then 100 coffee... ", Toast.LENGTH_SHORT).show();
                    return;
                }
                quantity=quantity+1;
                displayquantity(quantity);
            }
        });
        Button btndec =(Button) findViewById(R.id.btnmin);
        btndec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int quantity=2;
                if (quantity == 1){
                    Toast.makeText(MainActivity.this, "You cannot have less then 1 coffee...", Toast.LENGTH_SHORT).show();
                    return;
                }
                quantity=quantity-1;
                displayquantity(quantity);
            }
        });
    }
    private void displayquantity(int numberofcoffees){

        tvqun = (TextView) findViewById(R.id.totalTV);
        tvqun.setText("" +numberofcoffees);

    }
    private String createOrderSummery(String name,int price,boolean addwhippedcream,boolean addchocolate){
        pricemsg="Name: "+name;
        pricemsg = pricemsg+"\nAdd Whipped cream? "+addwhippedcream;
        pricemsg = pricemsg+"\nAdd Chocolate? "+addchocolate;
        pricemsg=pricemsg+"\nQuantity: "+quantity;
        pricemsg=pricemsg+ "\nTotal price:  $" +(price);
        pricemsg = pricemsg+ " \nThank you! ";
        return pricemsg;
    }

    private int calculateprice(boolean addwhippedcream,boolean addchocolate){
        int baseprice=5;
        if (addwhippedcream){
            baseprice = baseprice+1;
        }
        if (addchocolate){
            baseprice = baseprice+3;
        }

        price=quantity*baseprice;
        return price;
    }

    private void displaymsg(String msg)
    {
        ordersum = (TextView) findViewById(R.id.order_summary);
        ordersum.setText(msg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.item1:
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_SUBJECT,"Coffee Order information:");
//                sendIntent.putExtra(Intent.EXTRA_TEXT,"\n"+pricemsg);
//                sendIntent.setType("text/plain");
//                startActivity(sendIntent);
////                Intent shareIntent = new Intent(Intent.ACTION_SEND);
////                shareIntent.setType("text/plain");
////                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Coffee Order information:");
////                shareIntent.putExtra(Intent.EXTRA_TEXT,"\n"+pricemsg);
////                startActivity(Intent.createChooser(shareIntent, "Share Via"));
//            case R.id.item2:
//                Intent intent=new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://github.com/panktisugandhi"));
//                startActivity(intent);
//
             if(item.getItemId()==R.id.item1){
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT,"Coffee Order information:");
                sendIntent.putExtra(Intent.EXTRA_TEXT,"\n"+pricemsg);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
             }
            if (item.getItemId()==R.id.item2){
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/panktisugandhi"));
                startActivity(intent);
            }
        return super.onOptionsItemSelected(item);
    }
}
