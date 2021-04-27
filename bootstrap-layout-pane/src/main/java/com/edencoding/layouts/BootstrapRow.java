package com.edencoding.layouts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BootstrapRow {

    List<BootstrapColumn> columns = new ArrayList<>();

    public void addColumn(BootstrapColumn column){
        columns.add(column);
    }

    public void removeColumn(BootstrapColumn column){
        columns.remove(column);
    }

    public void clear(){
        columns.clear();
    }

    public List<BootstrapColumn> getColumns(){
        return Collections.unmodifiableList(columns);
    }
}
