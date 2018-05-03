package shuja.com.reqrestester;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import shuja.com.reqrestester.controllers.UserAdapter;
import shuja.com.reqrestester.network.facade.UserEntityFacade;
import shuja.com.reqrestester.model.CreateUserDTO;
import shuja.com.reqrestester.model.UserEntity;
import shuja.com.reqrestester.utility.Callback;

public class MainActivity extends AppCompatActivity {
    private ListView lvUserList;
    private UserEntity userEntity;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvUserList = findViewById(R.id.lv_users);

        UserEntityFacade.getUsers(new Callback<UserEntity>() {
            @Override
            public void onCallback(@Nullable UserEntity data, @Nullable Exception exception) {
                if(data!=null && !isDestroyed() && exception==null)
                {
                    userEntity = data;
                    bindDataToViews();
                }
                else{
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                    alertDialogBuilder.setTitle("Error");
                    alertDialogBuilder.setMessage("Oops! Something went wrong when fetching data from web, try again later!");
                    alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        });

    }

    private void bindDataToViews() {
        UserAdapter userAdapter = new UserAdapter(this, userEntity.getData());

        lvUserList.setAdapter(userAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_add:
                final AlertDialog.Builder mAlertDailogBuilder = new AlertDialog.Builder(this);
                final View view = getLayoutInflater().inflate(R.layout.add_user_prompt, null);
                final EditText etName = view.findViewById(R.id.et_name);
                final EditText etJob = view.findViewById(R.id.et_job);
                Button btnAdd = view.findViewById(R.id.btn_add);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CreateUserDTO createUserDTO = new CreateUserDTO(etName.getText().toString(), etJob.getText().toString());
                        UserEntityFacade.addUser(createUserDTO, new Callback<String>() {
                            @Override
                            public void onCallback(@Nullable String data, @Nullable Exception exception) {
                                if(data!=null && !isDestroyed() && exception == null){
                                    if(!data.isEmpty()){
                                        alertDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Added user successfully!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        });

                    }
                });

                mAlertDailogBuilder.setView(view);
                alertDialog = mAlertDailogBuilder.create();
                alertDialog.show();
                return true;
        }
        return true;
    }

}
