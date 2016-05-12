package crusader.familyviewdemo.model;

import android.view.ViewGroup;

/**
 * Created by linus on 11/5/16.
 */
public class LayoutRelationModel {

    public static final int RELATIONSHIP_TYPE_VERTICAL = 100;
    public static final int RELATIONSHIP_TYPE_HORIZONTAL = 101;

    ViewGroup firstLayout;
    ViewGroup secondLayout;

    int relationType;

    public LayoutRelationModel(ViewGroup firstLayout, ViewGroup secondLayout, int relationType) {
        this.firstLayout = firstLayout;
        this.secondLayout = secondLayout;
        this.relationType = relationType;
    }

    public ViewGroup getFirstLayout() {
        return firstLayout;
    }

    public void setFirstLayout(ViewGroup firstLayout) {
        this.firstLayout = firstLayout;
    }

    public ViewGroup getSecondLayout() {
        return secondLayout;
    }

    public void setSecondLayout(ViewGroup secondLayout) {
        this.secondLayout = secondLayout;
    }

    public int getRelationType() {
        return relationType;
    }

    public void setRelationType(int relationType) {
        this.relationType = relationType;
    }
}
