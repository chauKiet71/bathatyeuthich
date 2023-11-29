package saveEvent;

import Table.Model_Table;
import java.util.List;

public class SearchStateManagerTable {

    public static List<Model_Table> searchedTable;

    public SearchStateManagerTable() {
    }

    public static List<Model_Table> getSearchedTable() {
        return searchedTable;
    }

    public static void setSearchedTable(List<Model_Table> searched) {
        SearchStateManagerTable.searchedTable = searched;
    }

}
