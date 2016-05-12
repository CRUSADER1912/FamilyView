package crusader.familyviewdemo;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import crusader.familyviewdemo.custom.CanvasHelper;
import crusader.familyviewdemo.custom.CustomParentRelative;
import crusader.familyviewdemo.custom.LineView;
import crusader.familyviewdemo.custom.TwoDScrollView;
import crusader.familyviewdemo.db.ColumnValuePair;
import crusader.familyviewdemo.db.DBHelper;
import crusader.familyviewdemo.db.dbModel.ProfileTable;
import crusader.familyviewdemo.model.LayoutRelationModel;
import crusader.familyviewdemo.model.ProfileModel;
import crusader.familyviewdemo.utils.Utility;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DBHelper(this);

        TwoDScrollView twoDScrollView = new TwoDScrollView(this);

        CustomParentRelative relativeLayout = new CustomParentRelative(this);//new RelativeLayout(this);
        relativeLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        relativeLayout.setPadding(100, 100, 100, 100);

        relativeLayout.setBackgroundColor(Color.RED);

        twoDScrollView.addView(relativeLayout);

        insertRawInDb();


        ProfileModel profileModel1 = new ProfileModel(1 , "Linus" , "https://i.stack.imgur.com/IIAjr.jpg" , "ASHU" , 0 , "Stays in Vasai");
        ProfileModel profileModel2 = new ProfileModel(2 , "Teju" , "https://i.stack.imgur.com/IIAjr.jpg" , "ASHU" , 0 , "Stays in Mars :P");
        ProfileModel profileModel3 = new ProfileModel(3 , "Pranit" , "https://i.stack.imgur.com/IIAjr.jpg" , "ASHU" , 0 , "Stays in Lower Parel");
        ProfileModel profileModel4 = new ProfileModel(4 , "Chinmay" , "https://i.stack.imgur.com/IIAjr.jpg" , "ASHU" , 0 , "Stays in Mumbai Central");

        LinearLayout linearLayout1 = addProfileContainer(profileModel1,relativeLayout, null, 0);
        LinearLayout linearLayout2 = addProfileContainer(profileModel2,relativeLayout, linearLayout1, RelativeLayout.BELOW);
        LinearLayout linearLayout3 = addProfileContainer(profileModel3,relativeLayout, linearLayout1, RelativeLayout.RIGHT_OF);
        LinearLayout linearLayout4 = addProfileContainer(profileModel4,relativeLayout, linearLayout3, RelativeLayout.RIGHT_OF);

        ArrayList<LayoutRelationModel> list = new ArrayList<>();
        list.add(new LayoutRelationModel(linearLayout1, linearLayout2, LayoutRelationModel.RELATIONSHIP_TYPE_VERTICAL));
        list.add(new LayoutRelationModel(linearLayout3, linearLayout2, LayoutRelationModel.RELATIONSHIP_TYPE_VERTICAL));
        list.add(new LayoutRelationModel(linearLayout1, linearLayout3, LayoutRelationModel.RELATIONSHIP_TYPE_HORIZONTAL));
        relativeLayout.setLayoutRelationModels(list);
        relativeLayout.initPaintComponent();
        relativeLayout.invalidate();

        setContentView(twoDScrollView);
    }

    private void insertRawInDb() {
        ProfileTable profileTable = new ProfileTable();
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.Ashu);
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.rohan);
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.linus);
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.teju);
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.pranit);
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.chinmay);
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.chetan);
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.ana);
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.gayu);
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.sandeep);
    }

    private void calculateTierLvl(ArrayList<ProfileModel> completeDataList, ProfileTable profileTable) {
        ProfileTable refProfile = new ProfileTable();
        ArrayList<ProfileTable> filteredData ;
        if(profileTable == null){
            filteredData = refProfile.getFilteredData(dbHelper.getDb() , new ColumnValuePair(ProfileTable.PROFILE_COLUMN_ID , String.valueOf(0)));
        }else{
            filteredData = refProfile.getFilteredData(dbHelper.getDb() , new ColumnValuePair(ProfileTable.PROFILE_COLUMN_ID , String.valueOf(profileTable.getProfileId())));
        }

    }

    private LinearLayout addProfileContainer(ProfileModel profileModel, RelativeLayout parentRelativeLayout, ViewGroup relativeToLayout, int RELATIVE_TYPE) {
        LinearLayout ln_profileContainor = (LinearLayout) getLayoutInflater().inflate(R.layout.profile_container, null, false);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            ln_profileContainor.setId(Utility.generateViewId());
        } else {
            ln_profileContainor.setId(View.generateViewId());
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(500, RelativeLayout.LayoutParams.WRAP_CONTENT);
        if (relativeToLayout != null && relativeToLayout.getId() != -1) {
            layoutParams.addRule(RELATIVE_TYPE, relativeToLayout.getId());
        }
//        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        layoutParams.setMargins(30, 30, 30, 30);
        ln_profileContainor.setLayoutParams(layoutParams);
        parentRelativeLayout.addView(ln_profileContainor);

        TextView tvProfileName = (TextView) ln_profileContainor.findViewById(R.id.txt_profile_name);
        tvProfileName.setText(profileModel.getName());

        TextView tvDescription = (TextView) ln_profileContainor.findViewById(R.id.txt_description);
        tvDescription.setText(profileModel.getDescription());

        ImageView imageView = (ImageView) ln_profileContainor.findViewById(R.id.img_profile);
        Glide.with(this).load(profileModel.getImgProfileUrl())
                .into(imageView);


        return ln_profileContainor;
    }
}
