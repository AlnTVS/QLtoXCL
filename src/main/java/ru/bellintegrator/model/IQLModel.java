package ru.bellintegrator.model;

import java.util.ArrayList;
import java.util.List;


public class IQLModel {
    List<String> columns;
    String schema;
    String conditions;
    String groupBy;

    public IQLModel() {
    }

    public IQLModel(ArrayList<String> columns, String schema, String conditions, String groupBy) {
        this.columns = columns;
        this.schema = schema;
        this.conditions = conditions;
        this.groupBy = groupBy;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }
}
