package org.tdeccn.table.bean;

import org.extremecomponents.table.core.TableModel;
import org.tdeccn.table.core.TDPreferencesConstants;

/**
 * Pull the complicated values for the TableTag. Because the default values
 * could be coming from the properties or resource bundle this class will
 * abstract that out.
 * 
 * @author Jeff Johnston
 */
public final class TDBeanDefaults {

	public static String setPaginationLocation(TableModel model, String paginationLocation) {
        if (paginationLocation == null) {
            return model.getPreferences().getPreference(TDPreferencesConstants.TABLE_SHOW_PAGINATION_LOCATION);
        }

        return paginationLocation;
    }
	
	public static Boolean setShowRowsDisplayed(TableModel model, Boolean showRowsDisplayed) {
        if (showRowsDisplayed == null) {
            return Boolean.valueOf(model.getPreferences().getPreference(TDPreferencesConstants.TABLE_SHOW_ROWS_DISPLAYED));
        }

        return showRowsDisplayed;
    }

	public static Boolean setShowGotoPage(TableModel model, Boolean showGotoPage) {
        if (showGotoPage == null) {
            return Boolean.valueOf(model.getPreferences().getPreference(TDPreferencesConstants.TABLE_SHOW_GOTO_PAGE));
        }

        return showGotoPage;
    }
}
