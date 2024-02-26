package com.example.warframedemo1;
import android.app.ProgressDialog;
import android.content.Context;
public class showProgressBar {



        private static ProgressDialog progressDialog;//ProgressDialog这个对象你从上面的导入也可以看到，这是Android库自带的

        public  void showProgressDialog(Context context, String message) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        public  void hideProgressDialog() {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }

}
