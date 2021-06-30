package utils;

import java.util.ArrayList;
import java.util.List;

public class ExcelParams {

    private boolean hasFilter = true;
    private boolean freezeHeader = false;
    private boolean autoSizeColumn = true;
    private List<String> sheetName = new ArrayList<>();
    private boolean headerBold = true;

    public boolean isHasFilter() {
        return hasFilter;
    }

    public void setHasFilter(boolean hasFilter) {
        this.hasFilter = hasFilter;
    }

    public boolean isFreezeHeader() {
        return freezeHeader;
    }

    public void setFreezeHeader(boolean freezeHeader) {
        this.freezeHeader = freezeHeader;
    }

    public boolean isAutoSizeColumn() {
        return autoSizeColumn;
    }

    public void setAutoSizeColumn(boolean autoSizeColumn) {
        this.autoSizeColumn = autoSizeColumn;
    }

    public boolean isHeaderBold() {
        return headerBold;
    }

    public void setHeaderBold(boolean headerBold) {
        this.headerBold = headerBold;
    }

    public List<String> getSheetName() {
        return sheetName;
    }

    public void setSheetName(List<String> sheetName) {
        this.sheetName = sheetName;
    }
}
