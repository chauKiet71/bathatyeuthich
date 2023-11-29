package saveEvent;

import entity.BaiHatEntity;
import java.util.List;

public class SearchStateManager {

    public static List<BaiHatEntity> searched;

    public SearchStateManager() {
    }

    public static List<BaiHatEntity> getSearched() {
        return searched;
    }

    public static void setSearched(List<BaiHatEntity> searched) {
        SearchStateManager.searched = searched;
    }

}
