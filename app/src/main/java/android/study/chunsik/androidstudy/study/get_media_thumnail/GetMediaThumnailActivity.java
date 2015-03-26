package android.study.chunsik.androidstudy.study.get_media_thumnail;

import android.app.Activity;
import android.os.Bundle;
import android.study.chunsik.androidstudy.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

/**
 * Created by admin on 2015-03-24.
 */
public class GetMediaThumnailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_get_media_thumail);

        GridView gv = (GridView) findViewById(R.id.gv);
        CustomAdapter adapter = new CustomAdapter();
        gv.setAdapter(adapter);
    }

    public class CustomAdapter extends BaseAdapter{

        public CustomAdapter(){

        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
