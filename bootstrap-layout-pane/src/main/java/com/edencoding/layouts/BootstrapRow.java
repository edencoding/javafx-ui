package com.edencoding.layouts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BootstrapRow {

    private final List<BootstrapColumn> columns = new ArrayList<>();

    /**
     * Add a resizeable bootstrap column object
     * @param column the object to be added
     */
    public void addColumn(BootstrapColumn column){
        if(column == null) return;

        columns.add(column);
    }

    /**
     * Remove a bootstrap column object
     * @param column the object to be removed
     */
    public void removeColumn(BootstrapColumn column){
        columns.remove(column);
    }

    /**
     * Remove all columns from the row
     */
    public void clear(){
        columns.clear();
    }

    /**
     * Get all columns in the row
     * @return an unmodifiable view of the columns in this row.
     */
    public List<BootstrapColumn> getColumns(){
        return Collections.unmodifiableList(columns);
    }
}
