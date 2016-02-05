package com.mikestudio.luciddreamcatcher;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class TargetListActivity extends ListActivity {
    final static String STRING ="com.mikestudio.luciddreamcatcher.STRING";
    final static String IMAGE = "com.mikestudio.luciddreamcatcher.IMAGE";
    final String appPackageName = getPackageName();
    final static String SAVE = "settings";
    public SharedPreferences preferences;
   /* public Integer[] mTargetArray = new Integer[]{R.string.add,R.string.Bird,R.string.Trash,R.string.Sun,
    R.string.Plain,R.string.IceCream,R.string.Home,R.string.Dig,R.string.Cat};
    public Integer[]mImageArray = new Integer[] {R.mipmap.add,R.mipmap.bird,
            R.mipmap.trash,
            R.mipmap.sun,
            R.mipmap.plain,
            R.mipmap.icecream,
            R.mipmap.home,
            R.mipmap.dig,//œŒ“ŒÃ ÃŒ∆ÕŒ ƒŒ¡¿¬»“‹ —≈À≈ “Œ– » —“»À‹ — –ŒÀÀ¿
            R.mipmap.cat};//œŒ—ÃŒ“–≈“‹ ¿Õ»Ã¿÷»ﬁ «¿œŒÀÕ≈Õ≈Õ»ﬂ œŒ“ŒÃ!!! »« LISTNEWS*/
    public String mTargetArray;
    public String [] s;
    public String mImageArray = "add%bird%trash%sun%plain%icecream%home%dig%cat";
    public String[]img;
    private TargetAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences(SAVE, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        mTargetArray =  getString(R.string.Add) + "%" + getString(R.string.Bird) + "%" + getString(R.string.Trash) + "%" +
                getString(R.string.Sun) + "%" + getString(R.string.Plain) + "%" + getString(R.string.IceCream) + "%" +
                getString(R.string.Home) + "%" + getString(R.string.Dig) + "%" + getString(R.string.Cat);
        if (!preferences.contains("TargetArray") && !preferences.contains("ImageArray")){
            editor.putString("TargetArray", mTargetArray);
            editor.putString("ImageArray", mImageArray);
            editor.apply();//‰Îˇ ÔÎ‡ÚÌÓÈ ‚ÂÒËË
        }

        s = mTargetArray.split("%");
        img = mImageArray.split("%");

        mAdapter = new TargetAdapter(this);
        setListAdapter(mAdapter);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        if (position == 0) {
            //œÂ‰ÎÓÊÂÌËÂ ÔÓÎÌÓÈ ‚ÂÒËË
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException exception) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        } else {
            String selection = mAdapter.getString(position);
            int resId = mAdapter.getImage(position);
            Intent answerIntent = new Intent();
            answerIntent.putExtra(STRING, selection);
            answerIntent.putExtra(IMAGE, resId);
            setResult(RESULT_OK, answerIntent);
            finish();
        }
    }
    public class TargetAdapter extends BaseAdapter{
        private LayoutInflater mLayoutInflater;
        public TargetAdapter(Context context){
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return s.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public String getString(int position){
            return s[position];
        }

        public int getImage (int position){
            return (getResources().getIdentifier(img[position], "mipmap", getPackageName()));
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) convertView = mLayoutInflater.inflate(R.layout.activity_target_list,null);

            ImageView targetIcon = (ImageView)convertView.findViewById(R.id.imageViewIcon);
            targetIcon.setImageResource(getResources().getIdentifier(img[position], "mipmap", getPackageName()));
            TextView targetName = (TextView)convertView.findViewById(R.id.textViewTargetName);
            targetName.setText(s[position]);
            return convertView;
        }
    }
}


