package com.byted.camp.todolist.db;

import android.provider.BaseColumns;

public final class TodoContract {
    public final static String create_table_op = "CREATE TABLE todo (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "date INTEGER," +
            "state INTEGER," +
            "content TEXT," +
            "priority INTEGER)";

    public final static String upgrade_table_op = "ALTER TABLE todo ADD COLUMN priority INTEGER";

    private TodoContract() {
    }

    public static class TodoNote implements BaseColumns {
        /* (class Note)
            public final long id; (contained in BaseColumns)
            private Date date;
            private State state;
            private String content;
            private Priority priority;
         */
        public static final String
                table_name = "todo",
                attr_date = "date",
                attr_state = "state",
                attr_content = "content",
                attr_priority = "priority";
    }
}
