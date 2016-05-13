package crusader.familyviewdemo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import crusader.familyviewdemo.custom.CustomParentRelative;
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
        calculateTierLvlHash(null);
        calculateWeightage();

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
//        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.rohan);
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.linus);
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.teju);
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.pranit);
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.chinmay);
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.chetan);
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.ana);
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.gayu);
        profileTable.insertIfNotExistInDb(dbHelper.getDb(),AppConstants.sandeep);
    }

    private void calculateWeightage() {
        int markingWeightageValue = 1 ;
        insertWeightageDataDb(markingWeightageValue);
    }

    private void insertWeightageDataDb(int markingVal) {
        ProfileTable refProfileTable = new ProfileTable();
        ArrayList<ProfileTable> noChildrenProfiles = getProfilesNoChildren();
        for (int i = 0; i < noChildrenProfiles.size(); i++) {
            ProfileTable capturedProfile = noChildrenProfiles.get(i);
            capturedProfile.setWeightage(markingVal);
            refProfileTable.insertIfNotExistInDb(dbHelper.getDb(),capturedProfile);
        }
        //Till here we have set weightage for all with no children
        //Now we start assigning weightage to their parents

        for (int i = 0; i < noChildrenProfiles.size(); i++) {
            assignWeightageToParents(noChildrenProfiles.get(i));
        }

    }

    private void assignWeightageToParents(ProfileTable profileTable) {
        ProfileTable refProfile = new ProfileTable();
        //get list of parent..
        ArrayList<ProfileTable> list = refProfile.getFilteredData(dbHelper.getDb(),new ColumnValuePair(ProfileTable.PROFILE_COLUMN_ID, String.valueOf(profileTable.getParentProfileId())));
        for (int i = 0; i < list.size(); i++) {
            ProfileTable capturedProfile = list.get(i);
            ArrayList<ProfileTable> childrenProfiles = getChildrenProfiles(capturedProfile);
            int childrenWeightSum = 0;
            for (int j = 0; j < childrenProfiles.size(); j++) {
                childrenWeightSum = childrenWeightSum + childrenProfiles.get(j).getWeightage();
            }
            capturedProfile.setWeightage(childrenWeightSum);
            refProfile.insertIfNotExistInDb(dbHelper.getDb(),capturedProfile);
            if(capturedProfile.getParentProfileId() != 0){
                //It means we still not reached the peak of family tree at top
                //Hence, continue assigning weights
                assignWeightageToParents(capturedProfile);
            }
        }
    }

    private ArrayList<ProfileTable> getProfilesNoChildren(){
        ProfileTable refProfile = new ProfileTable();
        ArrayList<ProfileTable> filteredData ;
        filteredData = refProfile.getFilteredData(dbHelper.getDb() , new ColumnValuePair(ProfileTable.PROFILE_COLUMN_PROFILE_IS_PARENT , String.valueOf(0)));
        return filteredData;
    }

    private void calculateTierLvlHash(ProfileTable profileTable){
        int markingTierValue = 0;
        insertTierDataDb(profileTable,markingTierValue);
    }

    private void insertTierDataDb(ProfileTable profileTable, int markingVal){
        ProfileTable refProfileTable = new ProfileTable();
        ArrayList<ProfileTable> datas = getChildrenProfiles(profileTable);
        for (int i = 0; i < datas.size(); i++) {
            ProfileTable capturedProfile = datas.get(i);
            capturedProfile.setLvlTier(markingVal);
            if(refProfileTable.getRowCount(dbHelper.getDb(), new ColumnValuePair(ProfileTable.PROFILE_COLUMN_PROFILE_PARENT_ID, String.valueOf(capturedProfile.getProfileId()))) > 0){
                //Captured Profile is a parent
                capturedProfile.setParent(true);
                insertTierDataDb(capturedProfile, (markingVal + 1));
            }else{
                //Captured profile is not a parent
                capturedProfile.setParent(false);
            }
            refProfileTable.insertIfNotExistInDb(dbHelper.getDb(),capturedProfile);
        }
    }

    private ArrayList<ProfileTable> getChildrenProfiles(ProfileTable profileTable) {
        ProfileTable refProfile = new ProfileTable();
        ArrayList<ProfileTable> filteredData ;
        if(profileTable == null){
            filteredData = refProfile.getFilteredData(dbHelper.getDb() , new ColumnValuePair(ProfileTable.PROFILE_COLUMN_PROFILE_PARENT_ID , String.valueOf(0)));
        }else{
            filteredData = refProfile.getFilteredData(dbHelper.getDb() , new ColumnValuePair(ProfileTable.PROFILE_COLUMN_PROFILE_PARENT_ID , String.valueOf(profileTable.getProfileId())));
        }
        return filteredData;
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
