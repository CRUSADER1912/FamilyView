package crusader.familyviewdemo;

import java.util.HashMap;

import crusader.familyviewdemo.db.dbModel.ProfileTable;

/**
 * Created by linus on 11/5/16.
 */
public class AppConstants {

    public static final String PARENT = "parent";

    public static final ProfileTable Ashu = new ProfileTable(1, "Ashwini", "https://i.stack.imgur.com/IIAjr.jpg", 0, "Apli Lucy Devi. The all knowing.");
    public static final ProfileTable rohan = new ProfileTable(2, "Rohan", "https://i.stack.imgur.com/IIAjr.jpg", 0, "Apli Lucy Devi che Aho.");
    public static final ProfileTable linus = new ProfileTable(3, "Linus", "https://i.stack.imgur.com/IIAjr.jpg", 1, "Crusader. Mala TT khelaycha aahe, carrom pan chalel :P");
    public static final ProfileTable teju = new ProfileTable(4, "Tejshree", "https://i.stack.imgur.com/IIAjr.jpg",  1, "Yaar, khup kaam aahe. Description takayla time nahi");
    public static final ProfileTable pranit = new ProfileTable(5, "Pranit", "https://i.stack.imgur.com/IIAjr.jpg",  1, "Aeee Chasnya...");
    public static final ProfileTable chinmay = new ProfileTable(6, "Chinmay", "https://i.stack.imgur.com/IIAjr.jpg",  4, "Ghantolya.. Atleast majha decription nit lihila asta");
    public static final ProfileTable chetan = new ProfileTable(7, "Chetan", "https://i.stack.imgur.com/IIAjr.jpg", 3, "Bhau me ya Crusader ch");
    public static final ProfileTable ana = new ProfileTable(8, "Anagha", "https://i.stack.imgur.com/IIAjr.jpg",  6, "hahahahaha... ");
    public static final ProfileTable gayu = new ProfileTable(9, "Gayatri", "https://i.stack.imgur.com/IIAjr.jpg",  8, "IPL che tickets aahe majhya kade... Chalo chalo.. MUmbai Indians.");
    public static final ProfileTable sandeep = new ProfileTable(10, "Sandeep", "https://i.stack.imgur.com/IIAjr.jpg", 8, "Sardar hai apun.. Pahile attack phir question");


    public static final HashMap<String, ProfileTable> model = new HashMap<String, ProfileTable>() {
        {
            put(PARENT, Ashu);
            put(PARENT, rohan);
            put(String.valueOf(Ashu.getProfileId()), linus);
            put(String.valueOf(Ashu.getProfileId()), teju);
            put(String.valueOf(Ashu.getProfileId()), pranit);
            put(String.valueOf(teju.getProfileId()), chinmay);
            put(String.valueOf(linus.getProfileId()), chetan);
            put(String.valueOf(chinmay.getProfileId()), ana);
            put(String.valueOf(ana.getProfileId()), gayu);
            put(String.valueOf(ana.getProfileId()), sandeep);
        }
    };


}
