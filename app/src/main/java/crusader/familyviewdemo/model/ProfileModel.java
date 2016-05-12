package crusader.familyviewdemo.model;

/**
 * Created by linus on 11/5/16.
 */
public class ProfileModel {

    int profileId;
    String name;
    String imgProfileUrl;
    String parentName;
    int parentProfileId;
    String description;
    boolean hasBroSis;
    int siblingLvl;


    public ProfileModel(int profileId, String name, String imgProfileUrl, String parentName, int parentProfileId, String description) {
        this.profileId = profileId;
        this.name = name;
        this.imgProfileUrl = imgProfileUrl;
        this.parentName = parentName;
        this.parentProfileId = parentProfileId;
        this.description = description;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgProfileUrl() {
        return imgProfileUrl;
    }

    public void setImgProfileUrl(String imgProfileUrl) {
        this.imgProfileUrl = imgProfileUrl;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public int getParentProfileId() {
        return parentProfileId;
    }

    public void setParentProfileId(int parentProfileId) {
        this.parentProfileId = parentProfileId;
    }

    public boolean isHasBroSis() {
        return hasBroSis;
    }

    public void setHasBroSis(boolean hasBroSis) {
        this.hasBroSis = hasBroSis;
    }

    public int getSiblingLvl() {
        return siblingLvl;
    }

    public void setSiblingLvl(int siblingLvl) {
        this.siblingLvl = siblingLvl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
